package io.contek.invoker.mexc.spot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._Order;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public class PostOrder extends UserRestRequest<PostOrder.Response> {

  private String symbol;
  private String side;
  private String type;
  private BigDecimal quantity;
  private String newClientOrderId;
  private BigDecimal price;

  PostOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public final PostOrder setSymbol(String instrument_name) {
    this.symbol = instrument_name;
    return this;
  }

  public final PostOrder setSide(String side) {
    this.side = side;
    return this;
  }

  public final PostOrder setType(String type) {
    this.type = type;
    return this;
  }

  public final PostOrder setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
    return this;
  }

  public final PostOrder setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public final PostOrder setNewClientOrderId(String newClientOrderId) {
    this.newClientOrderId = newClientOrderId;
    return this;
  }

  @Override
  protected final RestMethod getMethod() {
    return POST;
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

    requireNonNull(side);
    builder.add("side", side);

    requireNonNull(quantity);
    builder.add("quantity", quantity.toPlainString());

    requireNonNull(type);
    builder.add("type", type);

    if (price != null) {
      builder.add("price", price.toPlainString());
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
  public static final class Response extends _Order {}
}
