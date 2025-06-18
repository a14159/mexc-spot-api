package io.contek.invoker.mexc.spot.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketRequest extends AnyWebSocketMessage {

  public String method;
  public List<String> params;
}
