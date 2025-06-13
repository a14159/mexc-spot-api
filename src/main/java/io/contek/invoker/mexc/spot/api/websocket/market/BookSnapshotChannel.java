package io.contek.invoker.mexc.spot.api.websocket.market;

import com.mxc.push.common.protobuf.PushDataV3ApiWrapperOrBuilder;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannelId;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.mexc.spot.api.websocket.common.constants.WebSocketChannelKeys._book;
import static java.lang.String.format;

@ThreadSafe
public final class BookSnapshotChannel
    extends MarketWebSocketChannel<BookSnapshotChannel.Message, PushDataV3ApiWrapperOrBuilder> {

  BookSnapshotChannel(Id id) {
    super(id);
  }

  @Override
  public Class<BookSnapshotChannel.Message> getMessageType() {
    return BookSnapshotChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, int depth) { // depth can be 5, 10, 20
      return new Id(format("%s@%s@%s", _book, instrumentName, depth));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<PushDataV3ApiWrapperOrBuilder> {}

}
