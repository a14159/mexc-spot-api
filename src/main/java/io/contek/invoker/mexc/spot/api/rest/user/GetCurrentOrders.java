package io.contek.invoker.mexc.spot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._Order;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public class GetCurrentOrders extends UserRestRequest<GetCurrentOrders.Response> {

  private String symbol;

  GetCurrentOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public final GetCurrentOrders setSymbol(String instrument_name) {
    this.symbol = instrument_name;
    return this;
  }

  @Override
  protected final RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/openOrders";
  }

  @Override
  protected final RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    builder.add("timestamp", Long.toString(System.currentTimeMillis()));

    return builder.build();
  }

  @Override
  protected final Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Order> {}
}
