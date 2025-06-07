package io.contek.invoker.mexc.spot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.mexc.spot.api.common._ListenKey;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.POST;

@NotThreadSafe
public class PostListenKey extends UserRestRequest<PostListenKey.Response> {

  PostListenKey(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected final RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/userDataStream";
  }

  @Override
  protected final RestParams getParams() {

    return RestParams.empty();
  }

  @Override
  protected final Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends _ListenKey {}
}
