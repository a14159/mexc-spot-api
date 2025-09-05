package io.contek.invoker.mexc.spot.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.Double;

@NotThreadSafe
public class _Balance {
  //  //        "asset": "NBNTEST",
  //  //        "free": "1111078",
  //  //        "locked": "33"

  public String asset;
  public Double free;
  public Double locked;
}
