package io.contek.invoker.mexc.spot.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class WebSocketChannelMessage<T> extends WebSocketInboundMessage {

  public String channel;

  public String symbol;

  public T data;

  public long createTime;
  public long sendTime;
}
