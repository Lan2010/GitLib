// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: advertisement_operation.proto

package com.tianzhixing.kernel.grpc.proto.advertisement.operation;

public final class AdvertisementOperationServiceProto {
  private AdvertisementOperationServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_advertisement_operation_AdvertisementInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_advertisement_operation_AdvertisementInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_advertisement_operation_ClickResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_advertisement_operation_ClickResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035advertisement_operation.proto\022\027adverti" +
      "sement.operation\032\025response_entity.proto\"" +
      "\207\001\n\021AdvertisementInfo\022\024\n\014accountToken\030\001 " +
      "\001(\t\022\r\n\005token\030\002 \001(\t\022\020\n\010advertId\030\003 \001(\t\022\021\n\t" +
      "starPoint\030\004 \001(\t\022\021\n\tclickTime\030\005 \001(\003\022\025\n\rop" +
      "erationType\030\006 \001(\005\"~\n\013ClickResult\022\032\n\022avai" +
      "lableStarPoint\030\001 \001(\t\022\027\n\017frozenStarPoint\030" +
      "\002 \001(\t\022\021\n\tstarPoint\030\004 \001(\t\022\'\n\016responseEnti" +
      "ty\030\003 \001(\0132\017.ResponseEntity2\204\001\n\035Advertisem" +
      "entOperationService\022c\n\rclickOrAccess\022*.a" +
      "dvertisement.operation.AdvertisementInfo" +
      "\032$.advertisement.operation.ClickResult\"\000" +
      "Ba\n9com.tianzhixing.kernel.grpc.proto.ad" +
      "vertisement.operationB\"AdvertisementOper" +
      "ationServiceProtoP\001b\006proto3"
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
    internal_static_advertisement_operation_AdvertisementInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_advertisement_operation_AdvertisementInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_advertisement_operation_AdvertisementInfo_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "AdvertId", "StarPoint", "ClickTime", "OperationType", });
    internal_static_advertisement_operation_ClickResult_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_advertisement_operation_ClickResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_advertisement_operation_ClickResult_descriptor,
        new java.lang.String[] { "AvailableStarPoint", "FrozenStarPoint", "StarPoint", "ResponseEntity", });
    com.tianzhixing.kernel.grpc.proto.ResponseEntityProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
