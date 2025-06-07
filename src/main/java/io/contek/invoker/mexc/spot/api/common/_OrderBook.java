package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _OrderBook {

  public long lastUpdateId;
  public List<_OrderBookLevel> asks;
  public List<_OrderBookLevel> bids;
  public long traceNano = System.nanoTime();
}
