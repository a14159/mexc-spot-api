package io.contek.invoker.mexc.spot.api.websocket.user;

import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;
import io.contek.invoker.mexc.spot.api.rest.user.PostListenKey;
import io.contek.invoker.mexc.spot.api.rest.user.UserRestApi;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketPingRequest;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketPongResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;


@ThreadSafe
public final class UserWebSocketLiveKeeper implements IWebSocketLiveKeeper {

    private static final Logger log = LogManager.getLogger(UserWebSocketLiveKeeper.class);

    public static final boolean DEBUG = false; // javac should remove the corresponding code

    private static final long HEARTBEAT_TIMEOUT = 20_000L; // seconds
    private final WebSocketPingRequest pingRequest = new WebSocketPingRequest();

    private volatile int pongResponses = 0;
    private volatile int pingRequests = 0;
    private volatile long lastPongTimestamp = 0;
    private volatile long lastPingTimestamp = 0;
    private volatile long lastMessageTimestamp = 0;


    private static final Duration REFRESH_PERIOD = Duration.ofMinutes(30);

    private final UserRestApi userRestApi;
    private final Clock clock;

    private final AtomicReference<State> stateHolder = new AtomicReference<>(null);

    UserWebSocketLiveKeeper(UserRestApi userRestApi, Clock clock) {
        this.userRestApi = userRestApi;
        this.clock = clock;
    }

    @Override
    public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {
        // send ping if needed, expire connection if no activity
        long now = System.currentTimeMillis();
        if (lastPongTimestamp != 0 || pingRequests > 3) {
            if (now - lastMessageTimestamp > 2 * HEARTBEAT_TIMEOUT) {
                log.warn("No pong responses for the last {} seconds, resetting connection", 5 * HEARTBEAT_TIMEOUT / 2);
                throw new WebSocketSessionInactiveException("No pong or other messages from server");
            }
        }
        if (now - lastPingTimestamp > HEARTBEAT_TIMEOUT) {
            session.send(pingRequest);
            lastPingTimestamp = now;
            if (DEBUG) {
                log.debug("Sending ping request");
            }
        }

        // update key if needed
        synchronized (stateHolder) {
            State state = stateHolder.get();
            if (state == null) {
                return;
            }

            Instant timestamp = clock.instant();
            Instant expire = state.lastRefreshTimestamp.plus(REFRESH_PERIOD);
            if (timestamp.isBefore(expire)) {
                return;
            }

            try {
                userRestApi.getPutListenKey().setListenKey(state.listenKey).submit();
                stateHolder.set(new State(state.listenKey, timestamp));
            } catch (AnyHttpException e) {
                log.warn("Failed to refresh listen key: {} {}", e.getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    private final WebSocketPingRequest testRequest = new WebSocketPingRequest();

    @Override
    public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
        lastMessageTimestamp = System.currentTimeMillis();
        if (message instanceof WebSocketPongResponse) {
            lastPongTimestamp = lastMessageTimestamp;
            if (DEBUG) {
                pongResponses++;
                log.debug("Received pong message #{}", pongResponses);
            }
        }
    }

    @Override
    public void afterDisconnect() {
        pingRequests = 0;
        pongResponses = 0;
        lastPongTimestamp = 0;
        synchronized (stateHolder) {
            stateHolder.set(null);
        }
    }

    public String init() {
        synchronized (stateHolder) {
            Instant timestamp = clock.instant();
            PostListenKey.Response newListenKey = userRestApi.getPostListenKey().submit();
            String listenKey = newListenKey.listenKey;
            stateHolder.set(new State(listenKey, timestamp));
            return listenKey;
        }
    }

    @Immutable
    private static final class State {

        private final String listenKey;
        private final Instant lastRefreshTimestamp;

        private State(String listenKey, Instant lastRefreshTimestamp) {
            this.listenKey = listenKey;
            this.lastRefreshTimestamp = lastRefreshTimestamp;
        }
    }
}
