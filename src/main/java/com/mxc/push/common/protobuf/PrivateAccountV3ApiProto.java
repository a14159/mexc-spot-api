// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: PrivateAccountV3Api.proto
// Protobuf Java Version: 4.31.1

package com.mxc.push.common.protobuf;

@com.google.protobuf.Generated
public final class PrivateAccountV3ApiProto {
  private PrivateAccountV3ApiProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 31,
      /* patch= */ 1,
      /* suffix= */ "",
      PrivateAccountV3ApiProto.class.getName());
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
    internal_static_PrivateAccountV3Api_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PrivateAccountV3Api_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031PrivateAccountV3Api.proto\"\272\001\n\023PrivateA" +
      "ccountV3Api\022\021\n\tvcoinName\030\001 \001(\t\022\016\n\006coinId" +
      "\030\002 \001(\t\022\025\n\rbalanceAmount\030\003 \001(\t\022\033\n\023balance" +
      "AmountChange\030\004 \001(\t\022\024\n\014frozenAmount\030\005 \001(\t" +
      "\022\032\n\022frozenAmountChange\030\006 \001(\t\022\014\n\004type\030\007 \001" +
      "(\t\022\014\n\004time\030\010 \001(\003B<\n\034com.mxc.push.common." +
      "protobufB\030PrivateAccountV3ApiProtoH\001P\001b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_PrivateAccountV3Api_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PrivateAccountV3Api_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PrivateAccountV3Api_descriptor,
        new java.lang.String[] { "VcoinName", "CoinId", "BalanceAmount", "BalanceAmountChange", "FrozenAmount", "FrozenAmountChange", "Type", "Time", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
