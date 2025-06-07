package io.contek.invoker.mexc.spot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._ListenKey;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.PUT;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public class PutListenKey extends UserRestRequest<PutListenKey.Response> {

  private String listenKey;

  PutListenKey(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PutListenKey setListenKey(String listenKey) {
    this.listenKey = listenKey;
    return this;
  }

  @Override
  protected final RestMethod getMethod() {
    return PUT;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/userDataStream";
  }

  @Override
  protected final RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(listenKey);
    builder.add("listenKey", listenKey);

    return builder.build();
  }

  @Override
  protected final Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends _ListenKey {}
}
