package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;

@NotThreadSafe
public class _Order {

  public String symbol;
  public String orderId;
  public String clientOrderId;
  public BigDecimal price;
  public BigDecimal origQty;
  public BigDecimal executedQty;
  public String type; // Order type: LIMIT_ORDER (1), POST_ONLY (2), IMMEDIATE_OR_CANCEL (3), FILL_OR_KILL (4), MARKET_ORDER (5); Stop loss/take profit (100)
  public String side; // Trade type (1: Buy, 2: Sell)
  public String status; // Order status: 1: Not traded, 2: Fully traded, 3: Partially traded, 4: Canceled, 5: Partially canceled
  public long time;
}
