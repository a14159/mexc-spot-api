package io.contek.invoker.mexc.spot.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._Ticker;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetTicker extends MarketRestRequest<GetTicker.Response> {

  private String symbol;

  GetTicker(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/ticker/bookTicker";
  }

  public GetTicker setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends _Ticker {}
}
