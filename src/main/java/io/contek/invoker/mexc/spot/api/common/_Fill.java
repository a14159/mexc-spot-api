package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Fill {

  public String symbol;
  public String id;
  public String orderId;
  public String clientOrderId;
  public String price;
  public String qty;
  public String commission;
  public String commissionAsset;
  public long time;
  public boolean isBuyer;
  public boolean isMaker;
  public String isBestMatch; // sic!
  public String isSelfTrade;

  public long traceNano = System.nanoTime();
}
