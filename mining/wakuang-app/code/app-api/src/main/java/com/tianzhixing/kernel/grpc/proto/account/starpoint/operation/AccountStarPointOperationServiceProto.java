// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint_operation.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

public final class AccountStarPointOperationServiceProto {
  private AccountStarPointOperationServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_AwardInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_AwardInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_BindAddressAwardInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_BindAddressAwardInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_CollectionStarPointInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_CollectionStarPointInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_StarPointRecordsInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_StarPointRecordsInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_WithdrawInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_WithdrawInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_CollectionResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_CollectionResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_WithdrawResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_WithdrawResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_operation_AwardResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_operation_AwardResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!account_starpoint_operation.proto\022\033acc" +
      "ount.starpoint.operation\032\025response_entit" +
      "y.proto\"X\n\tAwardInfo\022\024\n\014accountToken\030\001 \001" +
      "(\t\022\r\n\005token\030\002 \001(\t\022\022\n\nadwardTime\030\003 \001(\003\022\022\n" +
      "\nadwardType\030\004 \001(\t\"e\n\024BindAddressAwardInf" +
      "o\022\024\n\014accountToken\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\022\022" +
      "\n\nadwardTime\030\003 \001(\003\022\024\n\014contactCount\030\004 \001(\005" +
      "\"\313\001\n\027CollectionStarPointInfo\022\024\n\014accountT" +
      "oken\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\022\026\n\016collectionT" +
      "ime\030\003 \001(\003\022B\n\007records\030\004 \003(\01321.account.sta" +
      "rpoint.operation.StarPointRecordsInfo\022\020\n" +
      "\010advertId\030\005 \001(\t\022\035\n\025advertIdOperationType" +
      "\030\006 \001(\005\"+\n\024StarPointRecordsInfo\022\023\n\013record" +
      "Token\030\001 \001(\t\"`\n\014WithdrawInfo\022\024\n\014accountTo" +
      "ken\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\022\024\n\014withdrawTime" +
      "\030\003 \001(\003\022\025\n\rwithdrawCount\030\004 \001(\t\"\203\001\n\020Collec" +
      "tionResult\022\032\n\022availableStarPoint\030\001 \001(\t\022\027" +
      "\n\017frozenStarPoint\030\002 \001(\t\022\021\n\tstarpoint\030\004 \001" +
      "(\t\022\'\n\016responseEntity\030\003 \001(\0132\017.ResponseEnt" +
      "ity\"n\n\016WithdrawResult\022\032\n\022availableStarPo" +
      "int\030\001 \001(\t\022\027\n\017frozenStarPoint\030\002 \001(\t\022\'\n\016re" +
      "sponseEntity\030\003 \001(\0132\017.ResponseEntity\"~\n\013A" +
      "wardResult\022\032\n\022availableStarPoint\030\001 \001(\t\022\027" +
      "\n\017frozenStarPoint\030\002 \001(\t\022\021\n\tstarPoint\030\004 \001" +
      "(\t\022\'\n\016responseEntity\030\003 \001(\0132\017.ResponseEnt" +
      "ity2\314\003\n AccountStarPointOperationService" +
      "\022s\n\ncollection\0224.account.starpoint.opera" +
      "tion.CollectionStarPointInfo\032-.account.s" +
      "tarpoint.operation.CollectionResult\"\000\022d\n" +
      "\010withdraw\022).account.starpoint.operation." +
      "WithdrawInfo\032+.account.starpoint.operati" +
      "on.WithdrawResult\"\000\022[\n\005award\022&.account.s" +
      "tarpoint.operation.AwardInfo\032(.account.s" +
      "tarpoint.operation.AwardResult\"\000\022p\n\017bind" +
      "AddressList\0221.account.starpoint.operatio" +
      "n.BindAddressAwardInfo\032(.account.starpoi" +
      "nt.operation.AwardResult\"\000Bh\n=com.tianzh" +
      "ixing.kernel.grpc.proto.account.starpoin" +
      "t.operationB%AccountStarPointOperationSe" +
      "rviceProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.tianzhixing.kernel.grpc.proto.ResponseEntityProto.getDescriptor(),
        }, assigner);
    internal_static_account_starpoint_operation_AwardInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_account_starpoint_operation_AwardInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_AwardInfo_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "AdwardTime", "AdwardType", });
    internal_static_account_starpoint_operation_BindAddressAwardInfo_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_account_starpoint_operation_BindAddressAwardInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_BindAddressAwardInfo_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "AdwardTime", "ContactCount", });
    internal_static_account_starpoint_operation_CollectionStarPointInfo_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_account_starpoint_operation_CollectionStarPointInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_CollectionStarPointInfo_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "CollectionTime", "Records", "AdvertId", "AdvertIdOperationType", });
    internal_static_account_starpoint_operation_StarPointRecordsInfo_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_account_starpoint_operation_StarPointRecordsInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_StarPointRecordsInfo_descriptor,
        new java.lang.String[] { "RecordToken", });
    internal_static_account_starpoint_operation_WithdrawInfo_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_account_starpoint_operation_WithdrawInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_WithdrawInfo_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "WithdrawTime", "WithdrawCount", });
    internal_static_account_starpoint_operation_CollectionResult_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_account_starpoint_operation_CollectionResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_CollectionResult_descriptor,
        new java.lang.String[] { "AvailableStarPoint", "FrozenStarPoint", "Starpoint", "ResponseEntity", });
    internal_static_account_starpoint_operation_WithdrawResult_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_account_starpoint_operation_WithdrawResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_WithdrawResult_descriptor,
        new java.lang.String[] { "AvailableStarPoint", "FrozenStarPoint", "ResponseEntity", });
    internal_static_account_starpoint_operation_AwardResult_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_account_starpoint_operation_AwardResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_operation_AwardResult_descriptor,
        new java.lang.String[] { "AvailableStarPoint", "FrozenStarPoint", "StarPoint", "ResponseEntity", });
    com.tianzhixing.kernel.grpc.proto.ResponseEntityProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}