// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: PublicMiniTickerV3Api.proto
// Protobuf Java Version: 4.31.1

package com.mxc.push.common.protobuf;

@com.google.protobuf.Generated
public final class PublicMiniTickerV3ApiProto {
  private PublicMiniTickerV3ApiProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 31,
      /* patch= */ 1,
      /* suffix= */ "",
      PublicMiniTickerV3ApiProto.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PublicMiniTickerV3Api_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PublicMiniTickerV3Api_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033PublicMiniTickerV3Api.proto\"\364\001\n\025Public" +
      "MiniTickerV3Api\022\016\n\006symbol\030\001 \001(\t\022\r\n\005price" +
      "\030\002 \001(\t\022\014\n\004rate\030\003 \001(\t\022\021\n\tzonedRate\030\004 \001(\t\022" +
      "\014\n\004high\030\005 \001(\t\022\013\n\003low\030\006 \001(\t\022\016\n\006volume\030\007 \001" +
      "(\t\022\020\n\010quantity\030\010 \001(\t\022\025\n\rlastCloseRate\030\t " +
      "\001(\t\022\032\n\022lastCloseZonedRate\030\n \001(\t\022\025\n\rlastC" +
      "loseHigh\030\013 \001(\t\022\024\n\014lastCloseLow\030\014 \001(\tB>\n\034" +
      "com.mxc.push.common.protobufB\032PublicMini" +
      "TickerV3ApiProtoH\001P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_PublicMiniTickerV3Api_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PublicMiniTickerV3Api_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PublicMiniTickerV3Api_descriptor,
        new java.lang.String[] { "Symbol", "Price", "Rate", "ZonedRate", "High", "Low", "Volume", "Quantity", "LastCloseRate", "LastCloseZonedRate", "LastCloseHigh", "LastCloseLow", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
