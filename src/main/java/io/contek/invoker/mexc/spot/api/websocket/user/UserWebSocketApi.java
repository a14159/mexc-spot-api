package io.contek.invoker.mexc.spot.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.mexc.spot.api.rest.user.UserRestApi;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketMessageParser;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserWebSocketApi extends BaseWebSocketApi {

  private final Map<UserOrdersChannel.Id, UserOrdersChannel> userOrdersChannels = new HashMap<>();
  private final Map<UserTradesChannel.Id, UserTradesChannel> userTradesChannels = new HashMap<>();

  private final WebSocketContext context;

  public UserWebSocketApi(IActor actor, WebSocketContext context, UserRestApi userRestApi) {
    super(actor,
            new WebSocketMessageParser(),
            IWebSocketAuthenticator.noOp(),
            new UserWebSocketLiveKeeper(userRestApi, actor.getClock()));
    this.context = context;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    UserWebSocketLiveKeeper liveKeeper = (UserWebSocketLiveKeeper) getLiveKeeper();
    String listenKey = liveKeeper.init();
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws?" + listenKey);
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) {}

  public UserOrdersChannel getUserOrdersChannel() {
    return getUserOrdersChannel(UserOrdersChannel.Id.of());
  }

  public UserTradesChannel getUserTradesChannel() {
    return getUserTradesChannel(new UserTradesChannel.Id());
  }

  private UserOrdersChannel getUserOrdersChannel(UserOrdersChannel.Id id) {
    synchronized (userOrdersChannels) {
      return userOrdersChannels.computeIfAbsent(
          id,
          k -> {
            UserOrdersChannel result = new UserOrdersChannel(k);
            attach(result);
            return result;
          });
    }
  }

  private UserTradesChannel getUserTradesChannel(UserTradesChannel.Id id) {
    synchronized (userTradesChannels) {
      return userTradesChannels.computeIfAbsent(
          id,
          k -> {
            UserTradesChannel result = new UserTradesChannel(k);
            attach(result);
            return result;
          });
    }
  }
}
