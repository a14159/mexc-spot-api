package io.contek.invoker.mexc.spot.api.websocket;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mxc.push.common.protobuf.PushDataV3ApiWrapper;
import com.mxc.push.common.protobuf.PushDataV3ApiWrapperOrBuilder;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketBinaryMessageParser;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketPongResponse;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketSubscriptionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class WebSocketMessageParser extends WebSocketBinaryMessageParser {

  private static final Logger log = LogManager.getLogger(WebSocketMessageParser.class);

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    JSONObject obj = JSON.parseObject(text);
    if (obj.containsKey("msg")) {
      if ("PONG".equals(obj.get("msg").toString())) {
        return obj.toJavaObject(WebSocketPongResponse.class);
      }

      if (obj.containsKey("code") && obj.get("code").equals("0")) {
        return obj.toJavaObject(WebSocketSubscriptionResponse.class);
      }
    }

    throw new IllegalArgumentException(text);
  }

  @Override
  protected AnyWebSocketMessage fromBytes(byte[] bytes) {
    try {
      PushDataV3ApiWrapper msg = PushDataV3ApiWrapper.parseFrom(bytes);

      WebSocketChannelMessage<PushDataV3ApiWrapperOrBuilder> dealsMsg = new WebSocketChannelMessage<>();
      dealsMsg.data = msg;
      dealsMsg.channel = msg.getChannel();
      dealsMsg.createTime = msg.getCreateTime();
      dealsMsg.sendTime = msg.getSendTime();
      dealsMsg.symbol = msg.getSymbol();
      return dealsMsg;

    } catch (InvalidProtocolBufferException e) {
        throw new RuntimeException(e);
    }
  }
}
