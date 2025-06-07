package io.contek.invoker.mexc.spot.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._Instrument;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public final class GetExchangeInfo extends MarketRestRequest<GetExchangeInfo.Response> {

  private String symbol;

  GetExchangeInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/exchangeInfo";
  }

  public GetExchangeInfo setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    Objects.requireNonNull(symbol);
    builder.add("symbol", symbol);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends _Instrument {}
}
