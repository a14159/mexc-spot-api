package io.contek.invoker.mexc.spot.api.websocket.market;

import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannel;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannelId;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MarketWebSocketChannel<Message extends WebSocketChannelMessage<Data>, Data>
    extends WebSocketChannel<Message, Data> {

  MarketWebSocketChannel(
      WebSocketChannelId<Message> id) {
    super(id, "public");
  }

  @Override
  public String toString() {
    return getId().toString();
  }
}
