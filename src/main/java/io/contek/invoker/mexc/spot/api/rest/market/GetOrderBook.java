package io.contek.invoker.mexc.spot.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._OrderBook;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderBook extends MarketRestRequest<GetOrderBook.Response> {

  private String symbol;
  private Integer limit; // default 100

  GetOrderBook(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderBook setInstrumentName(String market_name) {
    this.symbol = market_name;
    return this;
  }

  public GetOrderBook setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/depth";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (limit != null) {
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends _OrderBook {}
}
