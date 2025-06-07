package io.contek.invoker.mexc.spot.api.websocket;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mxc.push.common.protobuf.*;
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
        PushDataV3ApiWrapper.BodyCase bodyCase = msg.getBodyCase();
        switch (bodyCase) {
          case PUBLICDEALS -> {
            WebSocketChannelMessage<PublicDealsV3ApiOrBuilder> dealsMsg = new WebSocketChannelMessage<>();
            dealsMsg.data = msg.getPublicDeals();
            dealsMsg.channel = msg.getChannel();
            dealsMsg.createTime = msg.getCreateTime();
            dealsMsg.sendTime = msg.getSendTime();
            dealsMsg.symbol = msg.getSymbol();
            return dealsMsg;
          }

          case PUBLICBOOKTICKER -> {
            WebSocketChannelMessage<PublicAggreBookTickerV3ApiOrBuilder> tickerMsg = new WebSocketChannelMessage<>();
            tickerMsg.data = msg.getPublicAggreBookTicker();
            tickerMsg.channel = msg.getChannel();
            tickerMsg.createTime = msg.getCreateTime();
            tickerMsg.sendTime = msg.getSendTime();
            tickerMsg.symbol = msg.getSymbol();
            return tickerMsg;
          }

          case PUBLICAGGREDEPTHS -> {
            WebSocketChannelMessage<PublicAggreDepthsV3ApiOrBuilder> bookMsg = new WebSocketChannelMessage<>();
            bookMsg.data = msg.getPublicAggreDepths();
            bookMsg.channel = msg.getChannel();
            bookMsg.createTime = msg.getCreateTime();
            bookMsg.sendTime = msg.getSendTime();
            bookMsg.symbol = msg.getSymbol();
            return bookMsg;
          }

          case PRIVATEDEALS -> {
            WebSocketChannelMessage<PrivateDealsV3ApiOrBuilder> dealsMsg = new WebSocketChannelMessage<>();
            dealsMsg.data = msg.getPrivateDeals();
            dealsMsg.channel = msg.getChannel();
            dealsMsg.createTime = msg.getCreateTime();
            dealsMsg.sendTime = msg.getSendTime();
            dealsMsg.symbol = msg.getSymbol();
            return dealsMsg;
          }

          case PRIVATEORDERS -> {
            WebSocketChannelMessage<PrivateOrdersV3ApiOrBuilder> ordersMsg = new WebSocketChannelMessage<>();
            ordersMsg.data = msg.getPrivateOrders();
            ordersMsg.channel = msg.getChannel();
            ordersMsg.createTime = msg.getCreateTime();
            ordersMsg.sendTime = msg.getSendTime();
            ordersMsg.symbol = msg.getSymbol();
            return ordersMsg;
          }

          default -> {
            log.warn("Received unknown message channel: {}, body case: {}", msg.getChannel(), msg.getBodyCase());
          }
        }
      } catch (InvalidProtocolBufferException e) {
          throw new RuntimeException(e);
      }

      return super.fromBytes(bytes);
  }
}
