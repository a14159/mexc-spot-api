package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.Double;

@NotThreadSafe
public class _24hTicker {
  //     "symbol": "BTCUSDT",
  //    "priceChange": "184.34",
  //    "priceChangePercent": "0.00400048",
  //    "prevClosePrice": "46079.37",
  //    "lastPrice": "46263.71",
  //    "bidPrice": "46260.38",
  //    "bidQty": "",
  //    "askPrice": "46260.41",
  //    "askQty": "",
  //    "openPrice": "46079.37",
  //    "highPrice": "47550.01",
  //    "lowPrice": "45555.5",
  //    "volume": "1732.461487",
  //    "quoteVolume": null,
  //    "openTime": 1641349500000,
  //    "closeTime": 1641349582808,
  //    "count": null

  public String symbol;

  public Double lastPrice;
  public Double volume;
  public Double quoteVolume;
}
