package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;

@NotThreadSafe
public class _Order {

  public String symbol;
  public String id;
  public String clientOrderId;
  public BigDecimal price;
  public BigDecimal quantity;
  public BigDecimal amount;
  public BigDecimal avgPrice;
  public int orderType; // Order type: LIMIT_ORDER (1), POST_ONLY (2), IMMEDIATE_OR_CANCEL (3), FILL_OR_KILL (4), MARKET_ORDER (5); Stop loss/take profit (100)
  public int tradeType; // Trade type (1: Buy, 2: Sell)
  public BigDecimal remainAmount;
  public BigDecimal remainQuantity;
  public BigDecimal cumulativeQuantity;
  public BigDecimal cumulativeAmount;
  public int status; // Order status: 1: Not traded, 2: Fully traded, 3: Partially traded, 4: Canceled, 5: Partially canceled
  public long createTime;
}
