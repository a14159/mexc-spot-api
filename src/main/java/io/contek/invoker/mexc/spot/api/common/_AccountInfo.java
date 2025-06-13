package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;
import java.util.List;

@NotThreadSafe
public class _AccountInfo {

  //     "canTrade": true,
  //    "canWithdraw": true,
  //    "canDeposit": true,
  //    "updateTime": null,
  //    "accountType": "SPOT",
  //    "balances": [{
  //        "asset": "NBNTEST",
  //        "free": "1111078",
  //        "locked": "33"
  //    }, {
  //        "asset": "MAIN",
  //        "free": "1020000",
  //        "locked": "0"
  //    }],
  //    "permissions": ["SPOT"]

  public boolean canTrade;
  public String accountType;
  public List<_Balance> balances;
}
