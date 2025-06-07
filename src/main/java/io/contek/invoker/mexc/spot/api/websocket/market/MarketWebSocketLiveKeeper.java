package io.contek.invoker.mexc.spot.api.websocket.market;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketPingRequest;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketPongResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.concurrent.ThreadSafe;


@ThreadSafe
public final class MarketWebSocketLiveKeeper implements IWebSocketLiveKeeper {

    private static final Logger log = LogManager.getLogger(MarketWebSocketLiveKeeper.class);

    public static final boolean DEBUG = false; // javac should remove the corresponding code

    private static final long HEARTBEAT_TIMEOUT = 20_000L; // seconds
    private final WebSocketPingRequest pingRequest = new WebSocketPingRequest();

    private volatile int pongResponses = 0;
    private volatile int pingRequests = 0;
    private volatile long lastPongTimestamp = 0;
    private volatile long lastPingTimestamp = 0;
    private volatile long lastMessageTimestamp = 0;


    MarketWebSocketLiveKeeper() {
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
    }

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
    }
}
