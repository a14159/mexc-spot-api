package io.contek.invoker.mexc.spot.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.mexc.spot.api.websocket.WebSocketMessageParser;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketWebSocketApi extends BaseWebSocketApi {

  private final Map<BookSnapshotChannel.Id, BookSnapshotChannel> bookSnapshotChannels = new HashMap<>();
  private final Map<TradesChannel.Id, TradesChannel> tradesChannels = new HashMap<>();
  private final Map<TickerChannel.Id, TickerChannel> tickerChannels = new HashMap<>();

  private final WebSocketContext context;

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, new WebSocketMessageParser(), IWebSocketAuthenticator.noOp(),
            new MarketWebSocketLiveKeeper());
    this.context = context;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws");
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) {}

  public BookSnapshotChannel getBookSnapshotChannel(
      String instrumentName, int depth) {
    synchronized (bookSnapshotChannels) {
      return bookSnapshotChannels.computeIfAbsent(
          BookSnapshotChannel.Id.of(instrumentName, depth),
          k -> {
              BookSnapshotChannel result = new BookSnapshotChannel(k);
              attach(result);
              return result;
          });
    }
  }

  public TradesChannel getTradesChannel(String instrumentName) {
    synchronized (tradesChannels) {
      return tradesChannels.computeIfAbsent(
          TradesChannel.Id.of(instrumentName),
          k -> {
              TradesChannel result = new TradesChannel(k);
              attach(result);
              return result;
          });
    }
  }

  public TickerChannel getTickerChannel(String instrumentName) {
    synchronized (tickerChannels) {
        return tickerChannels.computeIfAbsent(
            TickerChannel.Id.of(instrumentName),
            k -> {
                TickerChannel result = new TickerChannel(k);
                attach(result);
                return result;
            });
    }
  }
}
