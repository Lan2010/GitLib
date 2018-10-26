// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_reg.proto

package com.tianzhixing.kernel.grpc.proto.account.reg;

/**
 * Protobuf type {@code account.reg.CheckMobileResult}
 */
public  final class CheckMobileResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.reg.CheckMobileResult)
    CheckMobileResultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CheckMobileResult.newBuilder() to construct.
  private CheckMobileResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CheckMobileResult() {
    accountToken_ = "";
    thirdToken_ = "";
    exists_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CheckMobileResult(
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

            thirdToken_ = s;
            break;
          }
          case 24: {

            exists_ = input.readBool();
            break;
          }
          case 34: {
            com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder subBuilder = null;
            if (responseEntity_ != null) {
              subBuilder = responseEntity_.toBuilder();
            }
            responseEntity_ = input.readMessage(com.tianzhixing.kernel.grpc.proto.ResponseEntity.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(responseEntity_);
              responseEntity_ = subBuilder.buildPartial();
            }

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
    return com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceProto.internal_static_account_reg_CheckMobileResult_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceProto.internal_static_account_reg_CheckMobileResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.class, com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.Builder.class);
  }

  public static final int ACCOUNTTOKEN_FIELD_NUMBER = 1;
  private volatile java.lang.Object accountToken_;
  /**
   * <pre>
   *账户token，调用机构需存储，唯一识别账户标识码
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
   *账户token，调用机构需存储，唯一识别账户标识码
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

  public static final int THIRDTOKEN_FIELD_NUMBER = 2;
  private volatile java.lang.Object thirdToken_;
  /**
   * <pre>
   *第三方传入的token
   * </pre>
   *
   * <code>string thirdToken = 2;</code>
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
   *第三方传入的token
   * </pre>
   *
   * <code>string thirdToken = 2;</code>
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

  public static final int EXISTS_FIELD_NUMBER = 3;
  private boolean exists_;
  /**
   * <pre>
   *是否存在
   * </pre>
   *
   * <code>bool exists = 3;</code>
   */
  public boolean getExists() {
    return exists_;
  }

  public static final int RESPONSEENTITY_FIELD_NUMBER = 4;
  private com.tianzhixing.kernel.grpc.proto.ResponseEntity responseEntity_;
  /**
   * <pre>
   *code : 200=ok, 300=param error, 501=already exists
   * </pre>
   *
   * <code>.ResponseEntity responseEntity = 4;</code>
   */
  public boolean hasResponseEntity() {
    return responseEntity_ != null;
  }
  /**
   * <pre>
   *code : 200=ok, 300=param error, 501=already exists
   * </pre>
   *
   * <code>.ResponseEntity responseEntity = 4;</code>
   */
  public com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity() {
    return responseEntity_ == null ? com.tianzhixing.kernel.grpc.proto.ResponseEntity.getDefaultInstance() : responseEntity_;
  }
  /**
   * <pre>
   *code : 200=ok, 300=param error, 501=already exists
   * </pre>
   *
   * <code>.ResponseEntity responseEntity = 4;</code>
   */
  public com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder() {
    return getResponseEntity();
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
    if (!getThirdTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, thirdToken_);
    }
    if (exists_ != false) {
      output.writeBool(3, exists_);
    }
    if (responseEntity_ != null) {
      output.writeMessage(4, getResponseEntity());
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
    if (!getThirdTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, thirdToken_);
    }
    if (exists_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(3, exists_);
    }
    if (responseEntity_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getResponseEntity());
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
    if (!(obj instanceof com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult)) {
      return super.equals(obj);
    }
    com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult other = (com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult) obj;

    boolean result = true;
    result = result && getAccountToken()
        .equals(other.getAccountToken());
    result = result && getThirdToken()
        .equals(other.getThirdToken());
    result = result && (getExists()
        == other.getExists());
    result = result && (hasResponseEntity() == other.hasResponseEntity());
    if (hasResponseEntity()) {
      result = result && getResponseEntity()
          .equals(other.getResponseEntity());
    }
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
    hash = (37 * hash) + THIRDTOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getThirdToken().hashCode();
    hash = (37 * hash) + EXISTS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getExists());
    if (hasResponseEntity()) {
      hash = (37 * hash) + RESPONSEENTITY_FIELD_NUMBER;
      hash = (53 * hash) + getResponseEntity().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parseFrom(
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
  public static Builder newBuilder(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult prototype) {
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
   * Protobuf type {@code account.reg.CheckMobileResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.reg.CheckMobileResult)
      com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceProto.internal_static_account_reg_CheckMobileResult_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceProto.internal_static_account_reg_CheckMobileResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.class, com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.Builder.class);
    }

    // Construct using com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.newBuilder()
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

      thirdToken_ = "";

      exists_ = false;

      if (responseEntityBuilder_ == null) {
        responseEntity_ = null;
      } else {
        responseEntity_ = null;
        responseEntityBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceProto.internal_static_account_reg_CheckMobileResult_descriptor;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult getDefaultInstanceForType() {
      return com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.getDefaultInstance();
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult build() {
      com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult buildPartial() {
      com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult result = new com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult(this);
      result.accountToken_ = accountToken_;
      result.thirdToken_ = thirdToken_;
      result.exists_ = exists_;
      if (responseEntityBuilder_ == null) {
        result.responseEntity_ = responseEntity_;
      } else {
        result.responseEntity_ = responseEntityBuilder_.build();
      }
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
      if (other instanceof com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult) {
        return mergeFrom((com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult other) {
      if (other == com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.getDefaultInstance()) return this;
      if (!other.getAccountToken().isEmpty()) {
        accountToken_ = other.accountToken_;
        onChanged();
      }
      if (!other.getThirdToken().isEmpty()) {
        thirdToken_ = other.thirdToken_;
        onChanged();
      }
      if (other.getExists() != false) {
        setExists(other.getExists());
      }
      if (other.hasResponseEntity()) {
        mergeResponseEntity(other.getResponseEntity());
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
      com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult) e.getUnfinishedMessage();
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
     *账户token，调用机构需存储，唯一识别账户标识码
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
     *账户token，调用机构需存储，唯一识别账户标识码
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
     *账户token，调用机构需存储，唯一识别账户标识码
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
     *账户token，调用机构需存储，唯一识别账户标识码
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
     *账户token，调用机构需存储，唯一识别账户标识码
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

    private java.lang.Object thirdToken_ = "";
    /**
     * <pre>
     *第三方传入的token
     * </pre>
     *
     * <code>string thirdToken = 2;</code>
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
     *第三方传入的token
     * </pre>
     *
     * <code>string thirdToken = 2;</code>
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
     *第三方传入的token
     * </pre>
     *
     * <code>string thirdToken = 2;</code>
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
     *第三方传入的token
     * </pre>
     *
     * <code>string thirdToken = 2;</code>
     */
    public Builder clearThirdToken() {
      
      thirdToken_ = getDefaultInstance().getThirdToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *第三方传入的token
     * </pre>
     *
     * <code>string thirdToken = 2;</code>
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

    private boolean exists_ ;
    /**
     * <pre>
     *是否存在
     * </pre>
     *
     * <code>bool exists = 3;</code>
     */
    public boolean getExists() {
      return exists_;
    }
    /**
     * <pre>
     *是否存在
     * </pre>
     *
     * <code>bool exists = 3;</code>
     */
    public Builder setExists(boolean value) {
      
      exists_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *是否存在
     * </pre>
     *
     * <code>bool exists = 3;</code>
     */
    public Builder clearExists() {
      
      exists_ = false;
      onChanged();
      return this;
    }

    private com.tianzhixing.kernel.grpc.proto.ResponseEntity responseEntity_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.tianzhixing.kernel.grpc.proto.ResponseEntity, com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder, com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder> responseEntityBuilder_;
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public boolean hasResponseEntity() {
      return responseEntityBuilder_ != null || responseEntity_ != null;
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity() {
      if (responseEntityBuilder_ == null) {
        return responseEntity_ == null ? com.tianzhixing.kernel.grpc.proto.ResponseEntity.getDefaultInstance() : responseEntity_;
      } else {
        return responseEntityBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public Builder setResponseEntity(com.tianzhixing.kernel.grpc.proto.ResponseEntity value) {
      if (responseEntityBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        responseEntity_ = value;
        onChanged();
      } else {
        responseEntityBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public Builder setResponseEntity(
        com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder builderForValue) {
      if (responseEntityBuilder_ == null) {
        responseEntity_ = builderForValue.build();
        onChanged();
      } else {
        responseEntityBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public Builder mergeResponseEntity(com.tianzhixing.kernel.grpc.proto.ResponseEntity value) {
      if (responseEntityBuilder_ == null) {
        if (responseEntity_ != null) {
          responseEntity_ =
            com.tianzhixing.kernel.grpc.proto.ResponseEntity.newBuilder(responseEntity_).mergeFrom(value).buildPartial();
        } else {
          responseEntity_ = value;
        }
        onChanged();
      } else {
        responseEntityBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public Builder clearResponseEntity() {
      if (responseEntityBuilder_ == null) {
        responseEntity_ = null;
        onChanged();
      } else {
        responseEntity_ = null;
        responseEntityBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder getResponseEntityBuilder() {
      
      onChanged();
      return getResponseEntityFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder() {
      if (responseEntityBuilder_ != null) {
        return responseEntityBuilder_.getMessageOrBuilder();
      } else {
        return responseEntity_ == null ?
            com.tianzhixing.kernel.grpc.proto.ResponseEntity.getDefaultInstance() : responseEntity_;
      }
    }
    /**
     * <pre>
     *code : 200=ok, 300=param error, 501=already exists
     * </pre>
     *
     * <code>.ResponseEntity responseEntity = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.tianzhixing.kernel.grpc.proto.ResponseEntity, com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder, com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder> 
        getResponseEntityFieldBuilder() {
      if (responseEntityBuilder_ == null) {
        responseEntityBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.tianzhixing.kernel.grpc.proto.ResponseEntity, com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder, com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder>(
                getResponseEntity(),
                getParentForChildren(),
                isClean());
        responseEntity_ = null;
      }
      return responseEntityBuilder_;
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


    // @@protoc_insertion_point(builder_scope:account.reg.CheckMobileResult)
  }

  // @@protoc_insertion_point(class_scope:account.reg.CheckMobileResult)
  private static final com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult();
  }

  public static com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckMobileResult>
      PARSER = new com.google.protobuf.AbstractParser<CheckMobileResult>() {
    @java.lang.Override
    public CheckMobileResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CheckMobileResult(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CheckMobileResult> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CheckMobileResult> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

