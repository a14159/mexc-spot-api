package io.contek.invoker.mexc.spot.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketResponse extends WebSocketInboundMessage {

  public Integer id;
  public Integer code;
  public String msg;
}
