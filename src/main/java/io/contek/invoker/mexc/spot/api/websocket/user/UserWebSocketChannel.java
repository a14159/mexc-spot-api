package io.contek.invoker.mexc.spot.api.websocket.user;

import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannel;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannelId;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class UserWebSocketChannel<Message extends WebSocketChannelMessage<Data>, Data>
    extends WebSocketChannel<Message, Data> {

  UserWebSocketChannel(WebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  public String toString() {
    return getId().toString();
  }
}
