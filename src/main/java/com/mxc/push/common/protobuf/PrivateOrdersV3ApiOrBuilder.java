// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: PrivateOrdersV3Api.proto
// Protobuf Java Version: 4.31.1

package com.mxc.push.common.protobuf;

@com.google.protobuf.Generated
public interface PrivateOrdersV3ApiOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PrivateOrdersV3Api)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string clientId = 2;</code>
   * @return The clientId.
   */
  java.lang.String getClientId();
  /**
   * <code>string clientId = 2;</code>
   * @return The bytes for clientId.
   */
  com.google.protobuf.ByteString
      getClientIdBytes();

  /**
   * <code>string price = 3;</code>
   * @return The price.
   */
  java.lang.String getPrice();
  /**
   * <code>string price = 3;</code>
   * @return The bytes for price.
   */
  com.google.protobuf.ByteString
      getPriceBytes();

  /**
   * <code>string quantity = 4;</code>
   * @return The quantity.
   */
  java.lang.String getQuantity();
  /**
   * <code>string quantity = 4;</code>
   * @return The bytes for quantity.
   */
  com.google.protobuf.ByteString
      getQuantityBytes();

  /**
   * <code>string amount = 5;</code>
   * @return The amount.
   */
  java.lang.String getAmount();
  /**
   * <code>string amount = 5;</code>
   * @return The bytes for amount.
   */
  com.google.protobuf.ByteString
      getAmountBytes();

  /**
   * <code>string avgPrice = 6;</code>
   * @return The avgPrice.
   */
  java.lang.String getAvgPrice();
  /**
   * <code>string avgPrice = 6;</code>
   * @return The bytes for avgPrice.
   */
  com.google.protobuf.ByteString
      getAvgPriceBytes();

  /**
   * <code>int32 orderType = 7;</code>
   * @return The orderType.
   */
  int getOrderType();

  /**
   * <code>int32 tradeType = 8;</code>
   * @return The tradeType.
   */
  int getTradeType();

  /**
   * <code>bool isMaker = 9;</code>
   * @return The isMaker.
   */
  boolean getIsMaker();

  /**
   * <code>string remainAmount = 10;</code>
   * @return The remainAmount.
   */
  java.lang.String getRemainAmount();
  /**
   * <code>string remainAmount = 10;</code>
   * @return The bytes for remainAmount.
   */
  com.google.protobuf.ByteString
      getRemainAmountBytes();

  /**
   * <code>string remainQuantity = 11;</code>
   * @return The remainQuantity.
   */
  java.lang.String getRemainQuantity();
  /**
   * <code>string remainQuantity = 11;</code>
   * @return The bytes for remainQuantity.
   */
  com.google.protobuf.ByteString
      getRemainQuantityBytes();

  /**
   * <code>string lastDealQuantity = 12;</code>
   * @return The lastDealQuantity.
   */
  java.lang.String getLastDealQuantity();
  /**
   * <code>string lastDealQuantity = 12;</code>
   * @return The bytes for lastDealQuantity.
   */
  com.google.protobuf.ByteString
      getLastDealQuantityBytes();

  /**
   * <code>string cumulativeQuantity = 13;</code>
   * @return The cumulativeQuantity.
   */
  java.lang.String getCumulativeQuantity();
  /**
   * <code>string cumulativeQuantity = 13;</code>
   * @return The bytes for cumulativeQuantity.
   */
  com.google.protobuf.ByteString
      getCumulativeQuantityBytes();

  /**
   * <code>string cumulativeAmount = 14;</code>
   * @return The cumulativeAmount.
   */
  java.lang.String getCumulativeAmount();
  /**
   * <code>string cumulativeAmount = 14;</code>
   * @return The bytes for cumulativeAmount.
   */
  com.google.protobuf.ByteString
      getCumulativeAmountBytes();

  /**
   * <code>int32 status = 15;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>int64 createTime = 16;</code>
   * @return The createTime.
   */
  long getCreateTime();

  /**
   * <code>string market = 17;</code>
   * @return The market.
   */
  java.lang.String getMarket();
  /**
   * <code>string market = 17;</code>
   * @return The bytes for market.
   */
  com.google.protobuf.ByteString
      getMarketBytes();

  /**
   * <code>int32 triggerType = 18;</code>
   * @return The triggerType.
   */
  int getTriggerType();

  /**
   * <code>string triggerPrice = 19;</code>
   * @return The triggerPrice.
   */
  java.lang.String getTriggerPrice();
  /**
   * <code>string triggerPrice = 19;</code>
   * @return The bytes for triggerPrice.
   */
  com.google.protobuf.ByteString
      getTriggerPriceBytes();

  /**
   * <code>int32 state = 20;</code>
   * @return The state.
   */
  int getState();

  /**
   * <code>string ocoId = 21;</code>
   * @return The ocoId.
   */
  java.lang.String getOcoId();
  /**
   * <code>string ocoId = 21;</code>
   * @return The bytes for ocoId.
   */
  com.google.protobuf.ByteString
      getOcoIdBytes();

  /**
   * <code>string routeFactor = 22;</code>
   * @return The routeFactor.
   */
  java.lang.String getRouteFactor();
  /**
   * <code>string routeFactor = 22;</code>
   * @return The bytes for routeFactor.
   */
  com.google.protobuf.ByteString
      getRouteFactorBytes();

  /**
   * <code>string symbolId = 23;</code>
   * @return The symbolId.
   */
  java.lang.String getSymbolId();
  /**
   * <code>string symbolId = 23;</code>
   * @return The bytes for symbolId.
   */
  com.google.protobuf.ByteString
      getSymbolIdBytes();

  /**
   * <code>string marketId = 24;</code>
   * @return The marketId.
   */
  java.lang.String getMarketId();
  /**
   * <code>string marketId = 24;</code>
   * @return The bytes for marketId.
   */
  com.google.protobuf.ByteString
      getMarketIdBytes();

  /**
   * <code>string marketCurrencyId = 25;</code>
   * @return The marketCurrencyId.
   */
  java.lang.String getMarketCurrencyId();
  /**
   * <code>string marketCurrencyId = 25;</code>
   * @return The bytes for marketCurrencyId.
   */
  com.google.protobuf.ByteString
      getMarketCurrencyIdBytes();

  /**
   * <code>string currencyId = 26;</code>
   * @return The currencyId.
   */
  java.lang.String getCurrencyId();
  /**
   * <code>string currencyId = 26;</code>
   * @return The bytes for currencyId.
   */
  com.google.protobuf.ByteString
      getCurrencyIdBytes();
}
