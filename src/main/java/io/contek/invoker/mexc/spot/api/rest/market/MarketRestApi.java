package io.contek.invoker.mexc.spot.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarketRestApi {

  private final IActor actor;
  private final RestContext context;

  public MarketRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public GetExchangeInfo getExchangeInfo() {
    return new GetExchangeInfo(actor, context);
  }

  public GetAllExchangeInfo getAllExchangeInfo() {
    return new GetAllExchangeInfo(actor, context);
  }

  public GetOrderBook getOrderBook() {
    return new GetOrderBook(actor, context);
  }

  public GetTicker getTicker() {
    return new GetTicker(actor, context);
  }

  public Get24hTicker get24hTicker() {
    return new Get24hTicker(actor, context);
  }
}
