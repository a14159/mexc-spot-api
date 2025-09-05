package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Instrument {

  public String symbol;
  public String status;
  public String baseAsset;
  public int baseAssetPrecision;
  public String quoteAsset;
  public int quotePrecision;
  public int quoteAssetPrecision;
  public boolean isSpotTradingAllowed;
  public boolean isMarginTradingAllowed;

  public Double makerCommission;
  public Double takerCommission;
  public Double quoteAmountPrecision;
  public Double baseSizePrecision;
}
