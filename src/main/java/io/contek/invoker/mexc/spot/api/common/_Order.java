package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String symbol;
  public String orderId;
  public String price;
  public String origQty;
  public String type;
  public String side;
  public String transactTime;
}
