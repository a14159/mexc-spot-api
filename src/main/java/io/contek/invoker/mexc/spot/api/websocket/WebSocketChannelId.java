package io.contek.invoker.mexc.spot.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  protected WebSocketChannelId(String channel) {
    super(channel);
  }

  public final String getChannel() {
    return getValue();
  }

  @Override
  public final boolean accepts(Message message) {
    return getChannel().equals(message.channel);
  }
}
