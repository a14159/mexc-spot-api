package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Ticker {

  public String symbol;

  public Double askQty;
  public Double askPrice;
  public Double bidQty;
  public Double bidPrice;

  public long traceNano = System.nanoTime();
}
