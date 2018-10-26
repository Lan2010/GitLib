// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint;

/**
 * <pre>
 *排行信息详情
 * </pre>
 *
 * Protobuf type {@code account.starpoint.RankingRecordsInfo}
 */
public  final class RankingRecordsInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.starpoint.RankingRecordsInfo)
    RankingRecordsInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RankingRecordsInfo.newBuilder() to construct.
  private RankingRecordsInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RankingRecordsInfo() {
    thirdToken_ = "";
    accountToken_ = "";
    starPoint_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RankingRecordsInfo(
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

            thirdToken_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            accountToken_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            starPoint_ = s;
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
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RankingRecordsInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RankingRecordsInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo.Builder.class);
  }

  public static final int THIRDTOKEN_FIELD_NUMBER = 1;
  private volatile java.lang.Object thirdToken_;
  /**
   * <pre>
   *第三方token
   * </pre>
   *
   * <code>string thirdToken = 1;</code>
   */
  public java.lang.String getThirdToken() {
    java.lang.Object ref = thirdToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      thirdToken_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *第三方token
   * </pre>
   *
   * <code>string thirdToken = 1;</code>
   */
  public com.google.protobuf.ByteString
      getThirdTokenBytes() {
    java.lang.Object ref = thirdToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      thirdToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ACCOUNTTOKEN_FIELD_NUMBER = 2;
  private volatile java.lang.Object accountToken_;
  /**
   * <pre>
   *account token
   * </pre>
   *
   * <code>string accountToken = 2;</code>
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
   *account token
   * </pre>
   *
   * <code>string accountToken = 2;</code>
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

  public static final int STARPOINT_FIELD_NUMBER = 3;
  private volatile java.lang.Object starPoint_;
  /**
   * <pre>
   *星点数
   * </pre>
   *
   * <code>string starPoint = 3;</code>
   */
  public java.lang.String getStarPoint() {
    java.lang.Object ref = starPoint_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      starPoint_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *星点数
   * </pre>
   *
   * <code>string starPoint = 3;</code>
   */
  public com.google.protobuf.ByteString
      getStarPointBytes() {
    java.lang.Object ref = starPoint_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      starPoint_ = b;
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
    if (!getThirdTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, thirdToken_);
    }
    if (!getAccountTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, accountToken_);
    }
    if (!getStarPointBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, starPoint_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getThirdTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, thirdToken_);
    }
    if (!getAccountTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, accountToken_);
    }
    if (!getStarPointBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, starPoint_);
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
    if (!(obj instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo)) {
      return super.equals(obj);
    }
    com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo other = (com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo) obj;

    boolean result = true;
    result = result && getThirdToken()
        .equals(other.getThirdToken());
    result = result && getAccountToken()
        .equals(other.getAccountToken());
    result = result && getStarPoint()
        .equals(other.getStarPoint());
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
    hash = (37 * hash) + THIRDTOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getThirdToken().hashCode();
    hash = (37 * hash) + ACCOUNTTOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getAccountToken().hashCode();
    hash = (37 * hash) + STARPOINT_FIELD_NUMBER;
    hash = (53 * hash) + getStarPoint().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parseFrom(
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
  public static Builder newBuilder(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo prototype) {
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
   *排行信息详情
   * </pre>
   *
   * Protobuf type {@code account.starpoint.RankingRecordsInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.starpoint.RankingRecordsInfo)
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RankingRecordsInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RankingRecordsInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo.Builder.class);
    }

    // Construct using com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo.newBuilder()
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
      thirdToken_ = "";

      accountToken_ = "";

      starPoint_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RankingRecordsInfo_descriptor;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo getDefaultInstanceForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo.getDefaultInstance();
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo build() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo buildPartial() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo result = new com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo(this);
      result.thirdToken_ = thirdToken_;
      result.accountToken_ = accountToken_;
      result.starPoint_ = starPoint_;
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
      if (other instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo) {
        return mergeFrom((com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo other) {
      if (other == com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo.getDefaultInstance()) return this;
      if (!other.getThirdToken().isEmpty()) {
        thirdToken_ = other.thirdToken_;
        onChanged();
      }
      if (!other.getAccountToken().isEmpty()) {
        accountToken_ = other.accountToken_;
        onChanged();
      }
      if (!other.getStarPoint().isEmpty()) {
        starPoint_ = other.starPoint_;
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
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object thirdToken_ = "";
    /**
     * <pre>
     *第三方token
     * </pre>
     *
     * <code>string thirdToken = 1;</code>
     */
    public java.lang.String getThirdToken() {
      java.lang.Object ref = thirdToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        thirdToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *第三方token
     * </pre>
     *
     * <code>string thirdToken = 1;</code>
     */
    public com.google.protobuf.ByteString
        getThirdTokenBytes() {
      java.lang.Object ref = thirdToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        thirdToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *第三方token
     * </pre>
     *
     * <code>string thirdToken = 1;</code>
     */
    public Builder setThirdToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      thirdToken_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *第三方token
     * </pre>
     *
     * <code>string thirdToken = 1;</code>
     */
    public Builder clearThirdToken() {
      
      thirdToken_ = getDefaultInstance().getThirdToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *第三方token
     * </pre>
     *
     * <code>string thirdToken = 1;</code>
     */
    public Builder setThirdTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      thirdToken_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object accountToken_ = "";
    /**
     * <pre>
     *account token
     * </pre>
     *
     * <code>string accountToken = 2;</code>
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
     *account token
     * </pre>
     *
     * <code>string accountToken = 2;</code>
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
     *account token
     * </pre>
     *
     * <code>string accountToken = 2;</code>
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
     *account token
     * </pre>
     *
     * <code>string accountToken = 2;</code>
     */
    public Builder clearAccountToken() {
      
      accountToken_ = getDefaultInstance().getAccountToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *account token
     * </pre>
     *
     * <code>string accountToken = 2;</code>
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

    private java.lang.Object starPoint_ = "";
    /**
     * <pre>
     *星点数
     * </pre>
     *
     * <code>string starPoint = 3;</code>
     */
    public java.lang.String getStarPoint() {
      java.lang.Object ref = starPoint_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        starPoint_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *星点数
     * </pre>
     *
     * <code>string starPoint = 3;</code>
     */
    public com.google.protobuf.ByteString
        getStarPointBytes() {
      java.lang.Object ref = starPoint_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        starPoint_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *星点数
     * </pre>
     *
     * <code>string starPoint = 3;</code>
     */
    public Builder setStarPoint(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      starPoint_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *星点数
     * </pre>
     *
     * <code>string starPoint = 3;</code>
     */
    public Builder clearStarPoint() {
      
      starPoint_ = getDefaultInstance().getStarPoint();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *星点数
     * </pre>
     *
     * <code>string starPoint = 3;</code>
     */
    public Builder setStarPointBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      starPoint_ = value;
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


    // @@protoc_insertion_point(builder_scope:account.starpoint.RankingRecordsInfo)
  }

  // @@protoc_insertion_point(class_scope:account.starpoint.RankingRecordsInfo)
  private static final com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo();
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RankingRecordsInfo>
      PARSER = new com.google.protobuf.AbstractParser<RankingRecordsInfo>() {
    @java.lang.Override
    public RankingRecordsInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RankingRecordsInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RankingRecordsInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RankingRecordsInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
