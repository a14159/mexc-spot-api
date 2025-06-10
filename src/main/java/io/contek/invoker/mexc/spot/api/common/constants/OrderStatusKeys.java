package io.contek.invoker.mexc.spot.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatusKeys {


  public static final String _open = "NEW";

  public static final String _filled = "FILLED";

  public static final String _partially_filled = "PARTIALLY_FILLED";

  public static final String _cancelled = "CANCELED";

  public static final String _partially_cancelled = "PARTIALLY_CANCELED";
}
