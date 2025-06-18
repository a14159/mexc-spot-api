package io.contek.invoker.mexc.spot.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketRequest;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketSubscriptionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.mexc.spot.api.websocket.common.constants.WebSocketKeys._subscribe;
import static io.contek.invoker.mexc.spot.api.websocket.common.constants.WebSocketKeys._unsubscribe;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketChannelMessage<Data>, Data>
    extends BaseWebSocketChannel<WebSocketChannelId<Message>, Message, Data> {

  private static final Logger log = LogManager.getLogger(WebSocketChannel.class);

  private final AtomicReference<WebSocketRequest> pendingRequestHolder = new AtomicReference<>();

  protected WebSocketChannel(WebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  protected final Data getData(Message message) {
    return message.data;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      WebSocketChannelId<Message> id = getId();

      WebSocketRequest request = new WebSocketRequest();
      request.method = getSubscribeMethod();
      request.params = List.of(id.getValue());
      session.send(request);
      pendingRequestHolder.set(request);
    }
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      WebSocketChannelId<Message> id = getId();

      WebSocketRequest request = new WebSocketRequest();
      request.method = getUnsubscribeMethod();
      request.params = List.of(id.getValue());
      session.send(request);
      pendingRequestHolder.set(request);
    }

    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketSubscriptionResponse confirmation)) {
      return null;
    }

    log.debug("Receiving subscription response for channel: {}", confirmation.msg);

    synchronized (pendingRequestHolder) {
      WebSocketRequest command = pendingRequestHolder.get();
      if (command == null) {
        return null;
      }

      if (confirmation.code != 0 || !command.params.getFirst().equals(confirmation.msg)) {
        return null;
      }

      reset();
      if (command.method.equals(getSubscribeMethod())) {
        return SUBSCRIBED;
      }
      if (command.method.equals(getUnsubscribeMethod())) {
        return UNSUBSCRIBED;
      }
      throw new IllegalStateException();
    }
  }

  @Override
  protected final void reset() {
    synchronized (pendingRequestHolder) {
      pendingRequestHolder.set(null);
    }
  }

  private String getSubscribeMethod() {
    return _subscribe;
  }

  private String getUnsubscribeMethod() {
    return _unsubscribe;
  }
}
