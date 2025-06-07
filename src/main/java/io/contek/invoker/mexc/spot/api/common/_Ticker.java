package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;

@NotThreadSafe
public class _Ticker {

  public String symbol;

  public BigDecimal askQty;
  public BigDecimal askPrice;
  public BigDecimal bidQty;
  public BigDecimal bidPrice;

  public long traceNano = System.nanoTime();
}
