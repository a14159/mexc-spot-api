package io.contek.invoker.mexc.spot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserRestApi {

  private final IActor actor;
  private final RestContext context;

  public UserRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public PostOrder getPostOrder() {
    return new PostOrder(actor, context);
  }

  public CancelOrder getCancelOrder() {
    return new CancelOrder(actor, context);
  }

  public CancelAllOrders getCancelAllOrders() {
    return new CancelAllOrders(actor, context);
  }

  public GetCurrentOrders getCurrentOrders() {
    return new GetCurrentOrders(actor, context);
  }

  public GetAccountTradeList getAccountTradeList() {
    return new GetAccountTradeList(actor, context);
  }

  public PostListenKey getPostListenKey() {
    return new PostListenKey(actor, context);
  }

  public PutListenKey getPutListenKey() {
    return new PutListenKey(actor, context);
  }
}
