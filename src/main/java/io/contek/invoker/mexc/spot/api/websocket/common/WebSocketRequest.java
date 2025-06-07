package io.contek.invoker.mexc.spot.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketRequest<Params> extends AnyWebSocketMessage {

  public String method;
  public Params params;
}
