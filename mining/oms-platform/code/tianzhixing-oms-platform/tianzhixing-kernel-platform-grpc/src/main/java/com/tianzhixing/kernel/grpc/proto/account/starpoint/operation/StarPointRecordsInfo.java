// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint_operation.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

/**
 * <pre>
 *星点需采集记录
 * </pre>
 *
 * Protobuf type {@code account.starpoint.operation.StarPointRecordsInfo}
 */
public  final class StarPointRecordsInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.starpoint.operation.StarPointRecordsInfo)
    StarPointRecordsInfoOrBuilder {
  // Use StarPointRecordsInfo.newBuilder() to construct.
  private StarPointRecordsInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StarPointRecordsInfo() {
    recordToken_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private StarPointRecordsInfo(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            recordToken_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_StarPointRecordsInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_StarPointRecordsInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo.Builder.class);
  }

  public static final int RECORDTOKEN_FIELD_NUMBER = 1;
  private volatile java.lang.Object recordToken_;
  /**
   * <pre>
   *记录token - 必填
   * </pre>
   *
   * <code>optional string recordToken = 1;</code>
   */
  public java.lang.String getRecordToken() {
    java.lang.Object ref = recordToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      recordToken_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *记录token - 必填
   * </pre>
   *
   * <code>optional string recordToken = 1;</code>
   */
  public com.google.protobuf.ByteString
      getRecordTokenBytes() {
    java.lang.Object ref = recordToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      recordToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getRecordTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, recordToken_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getRecordTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, recordToken_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo)) {
      return super.equals(obj);
    }
    com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo other = (com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo) obj;

    boolean result = true;
    result = result && getRecordToken()
        .equals(other.getRecordToken());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + RECORDTOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getRecordToken().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *星点需采集记录
   * </pre>
   *
   * Protobuf type {@code account.starpoint.operation.StarPointRecordsInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.starpoint.operation.StarPointRecordsInfo)
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_StarPointRecordsInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_StarPointRecordsInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo.Builder.class);
    }

    // Construct using com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      recordToken_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_StarPointRecordsInfo_descriptor;
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo getDefaultInstanceForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo.getDefaultInstance();
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo build() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo buildPartial() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo result = new com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo(this);
      result.recordToken_ = recordToken_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo) {
        return mergeFrom((com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo other) {
      if (other == com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo.getDefaultInstance()) return this;
      if (!other.getRecordToken().isEmpty()) {
        recordToken_ = other.recordToken_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object recordToken_ = "";
    /**
     * <pre>
     *记录token - 必填
     * </pre>
     *
     * <code>optional string recordToken = 1;</code>
     */
    public java.lang.String getRecordToken() {
      java.lang.Object ref = recordToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        recordToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *记录token - 必填
     * </pre>
     *
     * <code>optional string recordToken = 1;</code>
     */
    public com.google.protobuf.ByteString
        getRecordTokenBytes() {
      java.lang.Object ref = recordToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        recordToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *记录token - 必填
     * </pre>
     *
     * <code>optional string recordToken = 1;</code>
     */
    public Builder setRecordToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      recordToken_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *记录token - 必填
     * </pre>
     *
     * <code>optional string recordToken = 1;</code>
     */
    public Builder clearRecordToken() {
      
      recordToken_ = getDefaultInstance().getRecordToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *记录token - 必填
     * </pre>
     *
     * <code>optional string recordToken = 1;</code>
     */
    public Builder setRecordTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      recordToken_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:account.starpoint.operation.StarPointRecordsInfo)
  }

  // @@protoc_insertion_point(class_scope:account.starpoint.operation.StarPointRecordsInfo)
  private static final com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo();
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StarPointRecordsInfo>
      PARSER = new com.google.protobuf.AbstractParser<StarPointRecordsInfo>() {
    public StarPointRecordsInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new StarPointRecordsInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StarPointRecordsInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StarPointRecordsInfo> getParserForType() {
    return PARSER;
  }

  public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
