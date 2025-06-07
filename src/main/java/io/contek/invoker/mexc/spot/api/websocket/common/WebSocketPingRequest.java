package io.contek.invoker.mexc.spot.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public class WebSocketPingRequest extends AnyWebSocketMessage {
    public String method = "PING";
}
