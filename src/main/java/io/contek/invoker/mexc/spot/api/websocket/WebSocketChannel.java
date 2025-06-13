package io.contek.invoker.mexc.spot.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.mexc.spot.api.websocket.common.SubscriptionParams;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketRequest;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketSubscriptionResponse;

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

  private final String scope;

  private final AtomicReference<WebSocketRequest<SubscriptionParams>> pendingRequestHolder =
      new AtomicReference<>();

  protected WebSocketChannel(WebSocketChannelId<Message> id, String scope) {
    super(id);
    this.scope = scope;
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
      SubscriptionParams params = new SubscriptionParams();
      params.params = List.of(id.getValue());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.method = getSubscribeMethod();
      request.params = params;
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
      SubscriptionParams params = new SubscriptionParams();
      params.params = List.of(id.getValue());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.method = getUnsubscribeMethod();
      request.params = params;
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

    synchronized (pendingRequestHolder) {
      WebSocketRequest<SubscriptionParams> command = pendingRequestHolder.get();
      if (command == null) {
        return null;
      }

      if (confirmation.code != 0 || command.params.params.getFirst().equals(confirmation.msg)) {
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
    return scope + '/' + _subscribe;
  }

  private String getUnsubscribeMethod() {
    return scope + '/' + _unsubscribe;
  }
}
