package io.contek.invoker.mexc.spot.api.websocket.market;

import com.mxc.push.common.protobuf.PublicBookTickerV3ApiOrBuilder;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannelId;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.mexc.spot.api.websocket.common.constants.WebSocketChannelKeys._tickers;
import static java.lang.String.format;

@ThreadSafe
public final class TickerChannel
    extends MarketWebSocketChannel<TickerChannel.Message, PublicBookTickerV3ApiOrBuilder> {

  TickerChannel(Id id) {
    super(id);
  }

  @Override
  public Class<TickerChannel.Message> getMessageType() {
    return TickerChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName) {
      return new Id(format("%s@10ms@%s", _tickers, instrumentName));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<PublicBookTickerV3ApiOrBuilder> {}

}
