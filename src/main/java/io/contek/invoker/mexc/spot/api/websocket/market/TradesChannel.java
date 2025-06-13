package io.contek.invoker.mexc.spot.api.websocket.market;

import com.mxc.push.common.protobuf.PushDataV3ApiWrapperOrBuilder;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannelId;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.mexc.spot.api.websocket.common.constants.WebSocketChannelKeys._trades;
import static java.lang.String.format;

@ThreadSafe
public final class TradesChannel
    extends MarketWebSocketChannel<TradesChannel.Message, PushDataV3ApiWrapperOrBuilder> {

  TradesChannel(TradesChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName) {
      return new Id(format("%s@10ms@%s", _trades, instrumentName));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<PushDataV3ApiWrapperOrBuilder> {}

}
