package io.contek.invoker.mexc.spot.api.websocket.user;

import com.mxc.push.common.protobuf.PrivateOrdersV3ApiOrBuilder;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketChannelId;
import io.contek.invoker.mexc.spot.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.mexc.spot.api.websocket.common.constants.WebSocketChannelKeys._user_trades;

@ThreadSafe
public final class UserTradesChannel
    extends UserWebSocketChannel<UserTradesChannel.Message, PrivateOrdersV3ApiOrBuilder> {

  UserTradesChannel(Id id) {
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

    public Id() {
      this(_user_trades);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<PrivateOrdersV3ApiOrBuilder> {}

}
