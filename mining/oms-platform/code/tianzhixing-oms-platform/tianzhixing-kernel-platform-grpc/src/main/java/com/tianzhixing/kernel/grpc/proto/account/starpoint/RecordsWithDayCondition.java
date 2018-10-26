// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint;

/**
 * <pre>
 *流水查询条件-统计数据
 * </pre>
 *
 * Protobuf type {@code account.starpoint.RecordsWithDayCondition}
 */
public  final class RecordsWithDayCondition extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.starpoint.RecordsWithDayCondition)
    RecordsWithDayConditionOrBuilder {
  // Use RecordsWithDayCondition.newBuilder() to construct.
  private RecordsWithDayCondition(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RecordsWithDayCondition() {
    accountToken_ = "";
    token_ = "";
    days_ = 0;
    endTime_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private RecordsWithDayCondition(
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

            accountToken_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            token_ = s;
            break;
          }
          case 24: {

            days_ = input.readInt32();
            break;
          }
          case 32: {

            endTime_ = input.readInt64();
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
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsWithDayCondition_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsWithDayCondition_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.Builder.class);
  }

  public static final int ACCOUNTTOKEN_FIELD_NUMBER = 1;
  private volatile java.lang.Object accountToken_;
  /**
   * <pre>
   *需存储的第三方唯一token 必填
   * </pre>
   *
   * <code>optional string accountToken = 1;</code>
   */
  public java.lang.String getAccountToken() {
    java.lang.Object ref = accountToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      accountToken_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *需存储的第三方唯一token 必填
   * </pre>
   *
   * <code>optional string accountToken = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAccountTokenBytes() {
    java.lang.Object ref = accountToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      accountToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TOKEN_FIELD_NUMBER = 2;
  private volatile java.lang.Object token_;
  /**
   * <pre>
   *安全校验码-必填
   * </pre>
   *
   * <code>optional string token = 2;</code>
   */
  public java.lang.String getToken() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      token_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *安全校验码-必填
   * </pre>
   *
   * <code>optional string token = 2;</code>
   */
  public com.google.protobuf.ByteString
      getTokenBytes() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      token_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DAYS_FIELD_NUMBER = 3;
  private int days_;
  /**
   * <pre>
   *天数-必填
   * </pre>
   *
   * <code>optional int32 days = 3;</code>
   */
  public int getDays() {
    return days_;
  }

  public static final int ENDTIME_FIELD_NUMBER = 4;
  private long endTime_;
  /**
   * <pre>
   *结束时间-必填
   * </pre>
   *
   * <code>optional int64 endTime = 4;</code>
   */
  public long getEndTime() {
    return endTime_;
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
    if (!getAccountTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, accountToken_);
    }
    if (!getTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, token_);
    }
    if (days_ != 0) {
      output.writeInt32(3, days_);
    }
    if (endTime_ != 0L) {
      output.writeInt64(4, endTime_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAccountTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, accountToken_);
    }
    if (!getTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, token_);
    }
    if (days_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, days_);
    }
    if (endTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, endTime_);
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
    if (!(obj instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition)) {
      return super.equals(obj);
    }
    com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition other = (com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition) obj;

    boolean result = true;
    result = result && getAccountToken()
        .equals(other.getAccountToken());
    result = result && getToken()
        .equals(other.getToken());
    result = result && (getDays()
        == other.getDays());
    result = result && (getEndTime()
        == other.getEndTime());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + ACCOUNTTOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getAccountToken().hashCode();
    hash = (37 * hash) + TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getToken().hashCode();
    hash = (37 * hash) + DAYS_FIELD_NUMBER;
    hash = (53 * hash) + getDays();
    hash = (37 * hash) + ENDTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getEndTime());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parseFrom(
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
  public static Builder newBuilder(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition prototype) {
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
   *流水查询条件-统计数据
   * </pre>
   *
   * Protobuf type {@code account.starpoint.RecordsWithDayCondition}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.starpoint.RecordsWithDayCondition)
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayConditionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsWithDayCondition_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsWithDayCondition_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.Builder.class);
    }

    // Construct using com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.newBuilder()
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
      accountToken_ = "";

      token_ = "";

      days_ = 0;

      endTime_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsWithDayCondition_descriptor;
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition getDefaultInstanceForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.getDefaultInstance();
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition build() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition buildPartial() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition result = new com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition(this);
      result.accountToken_ = accountToken_;
      result.token_ = token_;
      result.days_ = days_;
      result.endTime_ = endTime_;
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
      if (other instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition) {
        return mergeFrom((com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition other) {
      if (other == com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.getDefaultInstance()) return this;
      if (!other.getAccountToken().isEmpty()) {
        accountToken_ = other.accountToken_;
        onChanged();
      }
      if (!other.getToken().isEmpty()) {
        token_ = other.token_;
        onChanged();
      }
      if (other.getDays() != 0) {
        setDays(other.getDays());
      }
      if (other.getEndTime() != 0L) {
        setEndTime(other.getEndTime());
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
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object accountToken_ = "";
    /**
     * <pre>
     *需存储的第三方唯一token 必填
     * </pre>
     *
     * <code>optional string accountToken = 1;</code>
     */
    public java.lang.String getAccountToken() {
      java.lang.Object ref = accountToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        accountToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *需存储的第三方唯一token 必填
     * </pre>
     *
     * <code>optional string accountToken = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAccountTokenBytes() {
      java.lang.Object ref = accountToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        accountToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *需存储的第三方唯一token 必填
     * </pre>
     *
     * <code>optional string accountToken = 1;</code>
     */
    public Builder setAccountToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      accountToken_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *需存储的第三方唯一token 必填
     * </pre>
     *
     * <code>optional string accountToken = 1;</code>
     */
    public Builder clearAccountToken() {
      
      accountToken_ = getDefaultInstance().getAccountToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *需存储的第三方唯一token 必填
     * </pre>
     *
     * <code>optional string accountToken = 1;</code>
     */
    public Builder setAccountTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      accountToken_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object token_ = "";
    /**
     * <pre>
     *安全校验码-必填
     * </pre>
     *
     * <code>optional string token = 2;</code>
     */
    public java.lang.String getToken() {
      java.lang.Object ref = token_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        token_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *安全校验码-必填
     * </pre>
     *
     * <code>optional string token = 2;</code>
     */
    public com.google.protobuf.ByteString
        getTokenBytes() {
      java.lang.Object ref = token_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        token_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *安全校验码-必填
     * </pre>
     *
     * <code>optional string token = 2;</code>
     */
    public Builder setToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      token_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *安全校验码-必填
     * </pre>
     *
     * <code>optional string token = 2;</code>
     */
    public Builder clearToken() {
      
      token_ = getDefaultInstance().getToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *安全校验码-必填
     * </pre>
     *
     * <code>optional string token = 2;</code>
     */
    public Builder setTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      token_ = value;
      onChanged();
      return this;
    }

    private int days_ ;
    /**
     * <pre>
     *天数-必填
     * </pre>
     *
     * <code>optional int32 days = 3;</code>
     */
    public int getDays() {
      return days_;
    }
    /**
     * <pre>
     *天数-必填
     * </pre>
     *
     * <code>optional int32 days = 3;</code>
     */
    public Builder setDays(int value) {
      
      days_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *天数-必填
     * </pre>
     *
     * <code>optional int32 days = 3;</code>
     */
    public Builder clearDays() {
      
      days_ = 0;
      onChanged();
      return this;
    }

    private long endTime_ ;
    /**
     * <pre>
     *结束时间-必填
     * </pre>
     *
     * <code>optional int64 endTime = 4;</code>
     */
    public long getEndTime() {
      return endTime_;
    }
    /**
     * <pre>
     *结束时间-必填
     * </pre>
     *
     * <code>optional int64 endTime = 4;</code>
     */
    public Builder setEndTime(long value) {
      
      endTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *结束时间-必填
     * </pre>
     *
     * <code>optional int64 endTime = 4;</code>
     */
    public Builder clearEndTime() {
      
      endTime_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:account.starpoint.RecordsWithDayCondition)
  }

  // @@protoc_insertion_point(class_scope:account.starpoint.RecordsWithDayCondition)
  private static final com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition();
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RecordsWithDayCondition>
      PARSER = new com.google.protobuf.AbstractParser<RecordsWithDayCondition>() {
    public RecordsWithDayCondition parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RecordsWithDayCondition(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RecordsWithDayCondition> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RecordsWithDayCondition> getParserForType() {
    return PARSER;
  }

  public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

