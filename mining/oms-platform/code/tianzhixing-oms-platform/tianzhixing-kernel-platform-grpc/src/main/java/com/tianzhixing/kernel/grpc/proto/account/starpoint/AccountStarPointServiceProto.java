// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint;

public final class AccountStarPointServiceProto {
  private AccountStarPointServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_AccountInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_AccountInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RankingInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RankingInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RecordsCondition_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RecordsCondition_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RecordsWithDayCondition_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RecordsWithDayCondition_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_UnCollectionRecordsCondition_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_UnCollectionRecordsCondition_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_PageMapper_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_PageMapper_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_Result_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_Result_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RankingResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RankingResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RankingRecordsInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RankingRecordsInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RecordsResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RecordsResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RecordsWithDayResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RecordsWithDayResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_UnCollectionRecordsResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_UnCollectionRecordsResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RecordsInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RecordsInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_RecordsWithDayInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_RecordsWithDayInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_UnCollectionRecordsInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_UnCollectionRecordsInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_StarPointWithTaskInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_StarPointWithTaskInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_StarPointWithTaskResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_StarPointWithTaskResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_starpoint_StarPointWithTaskResultDetail_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_starpoint_StarPointWithTaskResultDetail_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027account_starpoint.proto\022\021account.starp" +
      "oint\032\025response_entity.proto\"2\n\013AccountIn" +
      "fo\022\024\n\014accountToken\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\"" +
      "O\n\013RankingInfo\0221\n\npageMapper\030\001 \001(\0132\035.acc" +
      "ount.starpoint.PageMapper\022\r\n\005token\030\002 \001(\t" +
      "\"\314\001\n\020RecordsCondition\022\024\n\014accountToken\030\001 " +
      "\001(\t\022\r\n\005token\030\002 \001(\t\022\021\n\tbeginTime\030\003 \001(\003\022\017\n" +
      "\007endTime\030\004 \001(\003\0221\n\npageMapper\030\005 \001(\0132\035.acc" +
      "ount.starpoint.PageMapper\022\016\n\006taskId\030\006 \001(" +
      "\t\022\027\n\017advertisementId\030\007 \001(\t\022\023\n\013recordsTyp",
      "e\030\010 \001(\t\"]\n\027RecordsWithDayCondition\022\024\n\014ac" +
      "countToken\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\022\014\n\004days\030" +
      "\003 \001(\005\022\017\n\007endTime\030\004 \001(\003\"g\n\034UnCollectionRe" +
      "cordsCondition\022\024\n\014accountToken\030\001 \001(\t\022\r\n\005" +
      "token\030\002 \001(\t\022\021\n\tbeginTime\030\003 \001(\003\022\017\n\007endTim" +
      "e\030\004 \001(\003\"(\n\nPageMapper\022\014\n\004from\030\001 \001(\005\022\014\n\004s" +
      "ize\030\002 \001(\005\"f\n\006Result\022\032\n\022availableStarPoin" +
      "t\030\001 \001(\t\022\027\n\017frozenStarPoint\030\002 \001(\t\022\'\n\016resp" +
      "onseEntity\030\003 \001(\0132\017.ResponseEntity\"\177\n\rRan" +
      "kingResult\0226\n\007records\030\001 \003(\0132%.account.st",
      "arpoint.RankingRecordsInfo\022\r\n\005total\030\002 \001(" +
      "\003\022\'\n\016responseEntity\030\003 \001(\0132\017.ResponseEnti" +
      "ty\"Q\n\022RankingRecordsInfo\022\022\n\nthirdToken\030\001" +
      " \001(\t\022\024\n\014accountToken\030\002 \001(\t\022\021\n\tstarPoint\030" +
      "\003 \001(\t\"x\n\rRecordsResult\022/\n\007records\030\001 \003(\0132" +
      "\036.account.starpoint.RecordsInfo\022\r\n\005total" +
      "\030\002 \001(\003\022\'\n\016responseEntity\030\003 \001(\0132\017.Respons" +
      "eEntity\"w\n\024RecordsWithDayResult\0226\n\007recor" +
      "ds\030\001 \003(\0132%.account.starpoint.RecordsWith" +
      "DayInfo\022\'\n\016responseEntity\030\003 \001(\0132\017.Respon",
      "seEntity\"\201\001\n\031UnCollectionRecordsResult\022;" +
      "\n\007records\030\001 \003(\0132*.account.starpoint.UnCo" +
      "llectionRecordsInfo\022\'\n\016responseEntity\030\002 " +
      "\001(\0132\017.ResponseEntity\"\235\001\n\013RecordsInfo\022\025\n\r" +
      "operStarPoint\030\001 \001(\t\022\025\n\roperationType\030\002 \001" +
      "(\t\022\023\n\013recordsType\030\003 \001(\t\022\016\n\006taskId\030\004 \001(\t\022" +
      "\027\n\017advertisementId\030\005 \001(\t\022\016\n\006remark\030\006 \001(\t" +
      "\022\022\n\ncreateTime\030\007 \001(\003\"5\n\022RecordsWithDayIn" +
      "fo\022\021\n\tstarPoint\030\001 \001(\t\022\014\n\004date\030\002 \001(\t\"\332\001\n\027" +
      "UnCollectionRecordsInfo\022\025\n\roperStarPoint",
      "\030\001 \001(\t\022\034\n\024longitudeAndLatitude\030\002 \001(\t\022\023\n\013" +
      "recordsType\030\003 \001(\t\022\016\n\006taskId\030\004 \001(\t\022\027\n\017adv" +
      "ertisementId\030\005 \001(\t\022\016\n\006remark\030\006 \001(\t\022\022\n\ncr" +
      "eateTime\030\007 \001(\003\022\023\n\013recordToken\030\010 \001(\t\022\023\n\013o" +
      "verdueTime\030\t \001(\003\"M\n\025StarPointWithTaskInf" +
      "o\022\017\n\007taskIds\030\001 \003(\t\022\024\n\014accountToken\030\002 \001(\t" +
      "\022\r\n\005token\030\003 \001(\t\"\205\001\n\027StarPointWithTaskRes" +
      "ult\022A\n\007records\030\001 \003(\01320.account.starpoint" +
      ".StarPointWithTaskResultDetail\022\'\n\016respon" +
      "seEntity\030\002 \001(\0132\017.ResponseEntity\"B\n\035StarP",
      "ointWithTaskResultDetail\022\021\n\tstarPoint\030\001 " +
      "\001(\t\022\016\n\006taskId\030\002 \001(\t2\324\004\n\027AccountStarPoint" +
      "Service\022H\n\tstarPoint\022\036.account.starpoint" +
      ".AccountInfo\032\031.account.starpoint.Result\"" +
      "\000\022M\n\007ranking\022\036.account.starpoint.Ranking" +
      "Info\032 .account.starpoint.RankingResult\"\000" +
      "\022R\n\007records\022#.account.starpoint.RecordsC" +
      "ondition\032 .account.starpoint.RecordsResu" +
      "lt\"\000\022v\n\023unCollectionRecords\022/.account.st" +
      "arpoint.UnCollectionRecordsCondition\032,.a",
      "ccount.starpoint.UnCollectionRecordsResu" +
      "lt\"\000\022g\n\016recordsWithDay\022*.account.starpoi" +
      "nt.RecordsWithDayCondition\032\'.account.sta" +
      "rpoint.RecordsWithDayResult\"\000\022k\n\021starPoi" +
      "ntWithTask\022(.account.starpoint.StarPoint" +
      "WithTaskInfo\032*.account.starpoint.StarPoi" +
      "ntWithTaskResult\"\000BU\n3com.tianzhixing.ke" +
      "rnel.grpc.proto.account.starpointB\034Accou" +
      "ntStarPointServiceProtoP\001b\006proto3"
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
    internal_static_account_starpoint_AccountInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_account_starpoint_AccountInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_AccountInfo_descriptor,
        new java.lang.String[] { "AccountToken", "Token", });
    internal_static_account_starpoint_RankingInfo_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_account_starpoint_RankingInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RankingInfo_descriptor,
        new java.lang.String[] { "PageMapper", "Token", });
    internal_static_account_starpoint_RecordsCondition_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_account_starpoint_RecordsCondition_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RecordsCondition_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "BeginTime", "EndTime", "PageMapper", "TaskId", "AdvertisementId", "RecordsType", });
    internal_static_account_starpoint_RecordsWithDayCondition_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_account_starpoint_RecordsWithDayCondition_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RecordsWithDayCondition_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "Days", "EndTime", });
    internal_static_account_starpoint_UnCollectionRecordsCondition_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_account_starpoint_UnCollectionRecordsCondition_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_UnCollectionRecordsCondition_descriptor,
        new java.lang.String[] { "AccountToken", "Token", "BeginTime", "EndTime", });
    internal_static_account_starpoint_PageMapper_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_account_starpoint_PageMapper_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_PageMapper_descriptor,
        new java.lang.String[] { "From", "Size", });
    internal_static_account_starpoint_Result_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_account_starpoint_Result_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_Result_descriptor,
        new java.lang.String[] { "AvailableStarPoint", "FrozenStarPoint", "ResponseEntity", });
    internal_static_account_starpoint_RankingResult_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_account_starpoint_RankingResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RankingResult_descriptor,
        new java.lang.String[] { "Records", "Total", "ResponseEntity", });
    internal_static_account_starpoint_RankingRecordsInfo_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_account_starpoint_RankingRecordsInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RankingRecordsInfo_descriptor,
        new java.lang.String[] { "ThirdToken", "AccountToken", "StarPoint", });
    internal_static_account_starpoint_RecordsResult_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_account_starpoint_RecordsResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RecordsResult_descriptor,
        new java.lang.String[] { "Records", "Total", "ResponseEntity", });
    internal_static_account_starpoint_RecordsWithDayResult_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_account_starpoint_RecordsWithDayResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RecordsWithDayResult_descriptor,
        new java.lang.String[] { "Records", "ResponseEntity", });
    internal_static_account_starpoint_UnCollectionRecordsResult_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_account_starpoint_UnCollectionRecordsResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_UnCollectionRecordsResult_descriptor,
        new java.lang.String[] { "Records", "ResponseEntity", });
    internal_static_account_starpoint_RecordsInfo_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_account_starpoint_RecordsInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RecordsInfo_descriptor,
        new java.lang.String[] { "OperStarPoint", "OperationType", "RecordsType", "TaskId", "AdvertisementId", "Remark", "CreateTime", });
    internal_static_account_starpoint_RecordsWithDayInfo_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_account_starpoint_RecordsWithDayInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_RecordsWithDayInfo_descriptor,
        new java.lang.String[] { "StarPoint", "Date", });
    internal_static_account_starpoint_UnCollectionRecordsInfo_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_account_starpoint_UnCollectionRecordsInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_UnCollectionRecordsInfo_descriptor,
        new java.lang.String[] { "OperStarPoint", "LongitudeAndLatitude", "RecordsType", "TaskId", "AdvertisementId", "Remark", "CreateTime", "RecordToken", "OverdueTime", });
    internal_static_account_starpoint_StarPointWithTaskInfo_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_account_starpoint_StarPointWithTaskInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_StarPointWithTaskInfo_descriptor,
        new java.lang.String[] { "TaskIds", "AccountToken", "Token", });
    internal_static_account_starpoint_StarPointWithTaskResult_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_account_starpoint_StarPointWithTaskResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_StarPointWithTaskResult_descriptor,
        new java.lang.String[] { "Records", "ResponseEntity", });
    internal_static_account_starpoint_StarPointWithTaskResultDetail_descriptor =
      getDescriptor().getMessageTypes().get(17);
    internal_static_account_starpoint_StarPointWithTaskResultDetail_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_starpoint_StarPointWithTaskResultDetail_descriptor,
        new java.lang.String[] { "StarPoint", "TaskId", });
    com.tianzhixing.kernel.grpc.proto.ResponseEntityProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
