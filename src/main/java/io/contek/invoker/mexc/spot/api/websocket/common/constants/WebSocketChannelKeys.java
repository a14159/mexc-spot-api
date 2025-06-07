package io.contek.invoker.mexc.spot.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String _channel = "channel";

  public static final String _trades = "spot@public.aggre.deals.v3.api.pb";

  public static final String _book = "spot@public.limit.depth.v3.api.pb";

  public static final String _tickers = "spot@public.aggre.bookTicker.v3.api.pb";

  public static final String _user_orders = "spot@private.orders.v3.api.pb";

  public static final String _user_trades = "spot@private.deals.v3.api.pb";

}
