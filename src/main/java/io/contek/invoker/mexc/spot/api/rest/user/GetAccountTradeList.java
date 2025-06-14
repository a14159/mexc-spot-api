package io.contek.invoker.mexc.spot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._Fill;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public class GetAccountTradeList extends UserRestRequest<GetAccountTradeList.Response> {

  private String symbol;
  private String orderId;
  private long startTime;
  private long endTime;

  GetAccountTradeList(IActor actor, RestContext context) {
    super(actor, context);
  }

  public final GetAccountTradeList setSymbol(String instrument_name) {
    this.symbol = instrument_name;
    return this;
  }

  public final GetAccountTradeList setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public final GetAccountTradeList setStartTime(long startTime) {
    this.startTime = startTime;
    return this;
  }

  public final GetAccountTradeList setEndTime(long endTime) {
    this.endTime = endTime;
    return this;
  }

  @Override
  protected final RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/myTrades";
  }

  @Override
  protected final RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (orderId != null)
      builder.add("orderId", orderId);

    if (startTime != 0)
      builder.add("startTime", startTime);

    if (endTime != 0)
      builder.add("endTime", endTime);

    builder.add("timestamp", Long.toString(System.currentTimeMillis()));

    return builder.build();
  }

  @Override
  protected final Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Fill> {}
}
