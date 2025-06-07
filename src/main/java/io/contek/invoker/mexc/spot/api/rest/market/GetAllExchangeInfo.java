package io.contek.invoker.mexc.spot.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._Instrument;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

@NotThreadSafe
public final class GetAllExchangeInfo extends MarketRestRequest<GetAllExchangeInfo.Response> {

  GetAllExchangeInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/exchangeInfo";
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Instrument> {}
}
