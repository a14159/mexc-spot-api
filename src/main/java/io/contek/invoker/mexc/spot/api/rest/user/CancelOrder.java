package io.contek.invoker.mexc.spot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._CancelResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.DELETE;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public class CancelOrder extends UserRestRequest<CancelOrder.Response> {

  private String symbol;
  private String orderId;
  private String origClientOrderId;
  private String newClientOrderId;

  CancelOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public final CancelOrder setSymbol(String instrument_name) {
    this.symbol = instrument_name;
    return this;
  }

  public final CancelOrder setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public final CancelOrder setOrigClientOrderId(String origClientOrderId) {
    this.origClientOrderId = origClientOrderId;
    return this;
  }

  public final CancelOrder setNewClientOrderId(String newClientOrderId) {
    this.newClientOrderId = newClientOrderId;
    return this;
  }

  @Override
  protected final RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/order";
  }

  @Override
  protected final RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (orderId != null) {
      builder.add("orderId", orderId);
    }

    if (origClientOrderId != null) {
      builder.add("origClientOrderId", origClientOrderId);
    }

    if (newClientOrderId != null) {
      builder.add("newClientOrderId", newClientOrderId);
    }

    return builder.build();
  }

  @Override
  protected final Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends _CancelResponse {}
}
