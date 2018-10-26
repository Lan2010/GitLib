// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint_operation.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

/**
 * <pre>
 *提现信息
 * </pre>
 *
 * Protobuf type {@code account.starpoint.operation.WithdrawInfo}
 */
public  final class WithdrawInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.starpoint.operation.WithdrawInfo)
    WithdrawInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use WithdrawInfo.newBuilder() to construct.
  private WithdrawInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WithdrawInfo() {
    accountToken_ = "";
    token_ = "";
    withdrawTime_ = 0L;
    withdrawCount_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WithdrawInfo(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
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

            withdrawTime_ = input.readInt64();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            withdrawCount_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_WithdrawInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_WithdrawInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.Builder.class);
  }

  public static final int ACCOUNTTOKEN_FIELD_NUMBER = 1;
  private volatile java.lang.Object accountToken_;
  /**
   * <pre>
   *需存储的第三方唯一token 必填
   * </pre>
   *
   * <code>string accountToken = 1;</code>
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
   * <code>string accountToken = 1;</code>
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
   * <code>string token = 2;</code>
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
   * <code>string token = 2;</code>
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

  public static final int WITHDRAWTIME_FIELD_NUMBER = 3;
  private long withdrawTime_;
  /**
   * <pre>
   *提现时间  必填
   * </pre>
   *
   * <code>int64 withdrawTime = 3;</code>
   */
  public long getWithdrawTime() {
    return withdrawTime_;
  }

  public static final int WITHDRAWCOUNT_FIELD_NUMBER = 4;
  private volatile java.lang.Object withdrawCount_;
  /**
   * <pre>
   *提现数量  必填
   * </pre>
   *
   * <code>string withdrawCount = 4;</code>
   */
  public java.lang.String getWithdrawCount() {
    java.lang.Object ref = withdrawCount_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      withdrawCount_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *提现数量  必填
   * </pre>
   *
   * <code>string withdrawCount = 4;</code>
   */
  public com.google.protobuf.ByteString
      getWithdrawCountBytes() {
    java.lang.Object ref = withdrawCount_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      withdrawCount_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getAccountTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, accountToken_);
    }
    if (!getTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, token_);
    }
    if (withdrawTime_ != 0L) {
      output.writeInt64(3, withdrawTime_);
    }
    if (!getWithdrawCountBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, withdrawCount_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
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
    if (withdrawTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, withdrawTime_);
    }
    if (!getWithdrawCountBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, withdrawCount_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo)) {
      return super.equals(obj);
    }
    com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo other = (com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo) obj;

    boolean result = true;
    result = result && getAccountToken()
        .equals(other.getAccountToken());
    result = result && getToken()
        .equals(other.getToken());
    result = result && (getWithdrawTime()
        == other.getWithdrawTime());
    result = result && getWithdrawCount()
        .equals(other.getWithdrawCount());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ACCOUNTTOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getAccountToken().hashCode();
    hash = (37 * hash) + TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getToken().hashCode();
    hash = (37 * hash) + WITHDRAWTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getWithdrawTime());
    hash = (37 * hash) + WITHDRAWCOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getWithdrawCount().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   *提现信息
   * </pre>
   *
   * Protobuf type {@code account.starpoint.operation.WithdrawInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.starpoint.operation.WithdrawInfo)
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_WithdrawInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_WithdrawInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.Builder.class);
    }

    // Construct using com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      accountToken_ = "";

      token_ = "";

      withdrawTime_ = 0L;

      withdrawCount_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_WithdrawInfo_descriptor;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo getDefaultInstanceForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.getDefaultInstance();
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo build() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo buildPartial() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo result = new com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo(this);
      result.accountToken_ = accountToken_;
      result.token_ = token_;
      result.withdrawTime_ = withdrawTime_;
      result.withdrawCount_ = withdrawCount_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo) {
        return mergeFrom((com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo other) {
      if (other == com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.getDefaultInstance()) return this;
      if (!other.getAccountToken().isEmpty()) {
        accountToken_ = other.accountToken_;
        onChanged();
      }
      if (!other.getToken().isEmpty()) {
        token_ = other.token_;
        onChanged();
      }
      if (other.getWithdrawTime() != 0L) {
        setWithdrawTime(other.getWithdrawTime());
      }
      if (!other.getWithdrawCount().isEmpty()) {
        withdrawCount_ = other.withdrawCount_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo) e.getUnfinishedMessage();
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
     * <code>string accountToken = 1;</code>
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
     * <code>string accountToken = 1;</code>
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
     * <code>string accountToken = 1;</code>
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
     * <code>string accountToken = 1;</code>
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
     * <code>string accountToken = 1;</code>
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
     * <code>string token = 2;</code>
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
     * <code>string token = 2;</code>
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
     * <code>string token = 2;</code>
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
     * <code>string token = 2;</code>
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
     * <code>string token = 2;</code>
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

    private long withdrawTime_ ;
    /**
     * <pre>
     *提现时间  必填
     * </pre>
     *
     * <code>int64 withdrawTime = 3;</code>
     */
    public long getWithdrawTime() {
      return withdrawTime_;
    }
    /**
     * <pre>
     *提现时间  必填
     * </pre>
     *
     * <code>int64 withdrawTime = 3;</code>
     */
    public Builder setWithdrawTime(long value) {
      
      withdrawTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *提现时间  必填
     * </pre>
     *
     * <code>int64 withdrawTime = 3;</code>
     */
    public Builder clearWithdrawTime() {
      
      withdrawTime_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object withdrawCount_ = "";
    /**
     * <pre>
     *提现数量  必填
     * </pre>
     *
     * <code>string withdrawCount = 4;</code>
     */
    public java.lang.String getWithdrawCount() {
      java.lang.Object ref = withdrawCount_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        withdrawCount_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *提现数量  必填
     * </pre>
     *
     * <code>string withdrawCount = 4;</code>
     */
    public com.google.protobuf.ByteString
        getWithdrawCountBytes() {
      java.lang.Object ref = withdrawCount_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        withdrawCount_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *提现数量  必填
     * </pre>
     *
     * <code>string withdrawCount = 4;</code>
     */
    public Builder setWithdrawCount(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      withdrawCount_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *提现数量  必填
     * </pre>
     *
     * <code>string withdrawCount = 4;</code>
     */
    public Builder clearWithdrawCount() {
      
      withdrawCount_ = getDefaultInstance().getWithdrawCount();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *提现数量  必填
     * </pre>
     *
     * <code>string withdrawCount = 4;</code>
     */
    public Builder setWithdrawCountBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      withdrawCount_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:account.starpoint.operation.WithdrawInfo)
  }

  // @@protoc_insertion_point(class_scope:account.starpoint.operation.WithdrawInfo)
  private static final com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo();
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<WithdrawInfo>
      PARSER = new com.google.protobuf.AbstractParser<WithdrawInfo>() {
    @java.lang.Override
    public WithdrawInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new WithdrawInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WithdrawInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WithdrawInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
