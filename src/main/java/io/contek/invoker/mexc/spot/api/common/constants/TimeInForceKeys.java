package io.contek.invoker.mexc.spot.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TimeInForceKeys {

  public static String _good_til_cancelled = "GTC";

  public static String _fill_or_kill = "FOK";

  public static String _immediate_or_cancel = "IOC";

  private TimeInForceKeys() {}
}
