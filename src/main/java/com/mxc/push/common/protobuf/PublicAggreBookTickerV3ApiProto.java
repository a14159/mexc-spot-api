// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: PublicAggreBookTickerV3Api.proto
// Protobuf Java Version: 4.31.1

package com.mxc.push.common.protobuf;

@com.google.protobuf.Generated
public final class PublicAggreBookTickerV3ApiProto {
  private PublicAggreBookTickerV3ApiProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 31,
      /* patch= */ 1,
      /* suffix= */ "",
      PublicAggreBookTickerV3ApiProto.class.getName());
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
    internal_static_PublicAggreBookTickerV3Api_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PublicAggreBookTickerV3Api_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n PublicAggreBookTickerV3Api.proto\"j\n\032Pu" +
      "blicAggreBookTickerV3Api\022\020\n\010bidPrice\030\001 \001" +
      "(\t\022\023\n\013bidQuantity\030\002 \001(\t\022\020\n\010askPrice\030\003 \001(" +
      "\t\022\023\n\013askQuantity\030\004 \001(\tBC\n\034com.mxc.push.c" +
      "ommon.protobufB\037PublicAggreBookTickerV3A" +
      "piProtoH\001P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_PublicAggreBookTickerV3Api_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PublicAggreBookTickerV3Api_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PublicAggreBookTickerV3Api_descriptor,
        new java.lang.String[] { "BidPrice", "BidQuantity", "AskPrice", "AskQuantity", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
