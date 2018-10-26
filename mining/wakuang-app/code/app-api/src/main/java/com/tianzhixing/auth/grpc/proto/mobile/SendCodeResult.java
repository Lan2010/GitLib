// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MobileValidationService.proto

package com.tianzhixing.auth.grpc.proto.mobile;

/**
 * <pre>
 *发送验证码反馈结果
 * </pre>
 *
 * Protobuf type {@code mobile.SendCodeResult}
 */
public  final class SendCodeResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:mobile.SendCodeResult)
    SendCodeResultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SendCodeResult.newBuilder() to construct.
  private SendCodeResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SendCodeResult() {
    code_ = 0;
    message_ = "";
    mobile_ = "";
    codeToken_ = "";
    sendPlatform_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SendCodeResult(
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
          case 8: {

            code_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            mobile_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            codeToken_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            sendPlatform_ = s;
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
    return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeResult_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.class, com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.Builder.class);
  }

  public static final int CODE_FIELD_NUMBER = 1;
  private int code_;
  /**
   * <pre>
   *200成功，300业务错误
   * </pre>
   *
   * <code>int32 code = 1;</code>
   */
  public int getCode() {
    return code_;
  }

  public static final int MESSAGE_FIELD_NUMBER = 2;
  private volatile java.lang.Object message_;
  /**
   * <pre>
   *信息
   * </pre>
   *
   * <code>string message = 2;</code>
   */
  public java.lang.String getMessage() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *信息
   * </pre>
   *
   * <code>string message = 2;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MOBILE_FIELD_NUMBER = 3;
  private volatile java.lang.Object mobile_;
  /**
   * <pre>
   *手机号
   * </pre>
   *
   * <code>string mobile = 3;</code>
   */
  public java.lang.String getMobile() {
    java.lang.Object ref = mobile_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      mobile_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *手机号
   * </pre>
   *
   * <code>string mobile = 3;</code>
   */
  public com.google.protobuf.ByteString
      getMobileBytes() {
    java.lang.Object ref = mobile_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      mobile_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CODETOKEN_FIELD_NUMBER = 4;
  private volatile java.lang.Object codeToken_;
  /**
   * <pre>
   *授权码-验证时使用
   * </pre>
   *
   * <code>string codeToken = 4;</code>
   */
  public java.lang.String getCodeToken() {
    java.lang.Object ref = codeToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      codeToken_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *授权码-验证时使用
   * </pre>
   *
   * <code>string codeToken = 4;</code>
   */
  public com.google.protobuf.ByteString
      getCodeTokenBytes() {
    java.lang.Object ref = codeToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      codeToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SENDPLATFORM_FIELD_NUMBER = 5;
  private volatile java.lang.Object sendPlatform_;
  /**
   * <pre>
   *发送平台
   * </pre>
   *
   * <code>string sendPlatform = 5;</code>
   */
  public java.lang.String getSendPlatform() {
    java.lang.Object ref = sendPlatform_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      sendPlatform_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *发送平台
   * </pre>
   *
   * <code>string sendPlatform = 5;</code>
   */
  public com.google.protobuf.ByteString
      getSendPlatformBytes() {
    java.lang.Object ref = sendPlatform_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      sendPlatform_ = b;
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
    if (code_ != 0) {
      output.writeInt32(1, code_);
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, message_);
    }
    if (!getMobileBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, mobile_);
    }
    if (!getCodeTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, codeToken_);
    }
    if (!getSendPlatformBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, sendPlatform_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (code_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, code_);
    }
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, message_);
    }
    if (!getMobileBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, mobile_);
    }
    if (!getCodeTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, codeToken_);
    }
    if (!getSendPlatformBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, sendPlatform_);
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
    if (!(obj instanceof com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult)) {
      return super.equals(obj);
    }
    com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult other = (com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult) obj;

    boolean result = true;
    result = result && (getCode()
        == other.getCode());
    result = result && getMessage()
        .equals(other.getMessage());
    result = result && getMobile()
        .equals(other.getMobile());
    result = result && getCodeToken()
        .equals(other.getCodeToken());
    result = result && getSendPlatform()
        .equals(other.getSendPlatform());
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
    hash = (37 * hash) + CODE_FIELD_NUMBER;
    hash = (53 * hash) + getCode();
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    hash = (37 * hash) + MOBILE_FIELD_NUMBER;
    hash = (53 * hash) + getMobile().hashCode();
    hash = (37 * hash) + CODETOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getCodeToken().hashCode();
    hash = (37 * hash) + SENDPLATFORM_FIELD_NUMBER;
    hash = (53 * hash) + getSendPlatform().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parseFrom(
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
  public static Builder newBuilder(com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult prototype) {
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
   *发送验证码反馈结果
   * </pre>
   *
   * Protobuf type {@code mobile.SendCodeResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:mobile.SendCodeResult)
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeResult_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.class, com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.Builder.class);
    }

    // Construct using com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.newBuilder()
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
      code_ = 0;

      message_ = "";

      mobile_ = "";

      codeToken_ = "";

      sendPlatform_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeResult_descriptor;
    }

    @java.lang.Override
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult getDefaultInstanceForType() {
      return com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.getDefaultInstance();
    }

    @java.lang.Override
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult build() {
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult buildPartial() {
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult result = new com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult(this);
      result.code_ = code_;
      result.message_ = message_;
      result.mobile_ = mobile_;
      result.codeToken_ = codeToken_;
      result.sendPlatform_ = sendPlatform_;
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
      if (other instanceof com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult) {
        return mergeFrom((com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult other) {
      if (other == com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.getDefaultInstance()) return this;
      if (other.getCode() != 0) {
        setCode(other.getCode());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (!other.getMobile().isEmpty()) {
        mobile_ = other.mobile_;
        onChanged();
      }
      if (!other.getCodeToken().isEmpty()) {
        codeToken_ = other.codeToken_;
        onChanged();
      }
      if (!other.getSendPlatform().isEmpty()) {
        sendPlatform_ = other.sendPlatform_;
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
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int code_ ;
    /**
     * <pre>
     *200成功，300业务错误
     * </pre>
     *
     * <code>int32 code = 1;</code>
     */
    public int getCode() {
      return code_;
    }
    /**
     * <pre>
     *200成功，300业务错误
     * </pre>
     *
     * <code>int32 code = 1;</code>
     */
    public Builder setCode(int value) {
      
      code_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *200成功，300业务错误
     * </pre>
     *
     * <code>int32 code = 1;</code>
     */
    public Builder clearCode() {
      
      code_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object message_ = "";
    /**
     * <pre>
     *信息
     * </pre>
     *
     * <code>string message = 2;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *信息
     * </pre>
     *
     * <code>string message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *信息
     * </pre>
     *
     * <code>string message = 2;</code>
     */
    public Builder setMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *信息
     * </pre>
     *
     * <code>string message = 2;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *信息
     * </pre>
     *
     * <code>string message = 2;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object mobile_ = "";
    /**
     * <pre>
     *手机号
     * </pre>
     *
     * <code>string mobile = 3;</code>
     */
    public java.lang.String getMobile() {
      java.lang.Object ref = mobile_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        mobile_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *手机号
     * </pre>
     *
     * <code>string mobile = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMobileBytes() {
      java.lang.Object ref = mobile_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        mobile_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *手机号
     * </pre>
     *
     * <code>string mobile = 3;</code>
     */
    public Builder setMobile(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      mobile_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *手机号
     * </pre>
     *
     * <code>string mobile = 3;</code>
     */
    public Builder clearMobile() {
      
      mobile_ = getDefaultInstance().getMobile();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *手机号
     * </pre>
     *
     * <code>string mobile = 3;</code>
     */
    public Builder setMobileBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      mobile_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object codeToken_ = "";
    /**
     * <pre>
     *授权码-验证时使用
     * </pre>
     *
     * <code>string codeToken = 4;</code>
     */
    public java.lang.String getCodeToken() {
      java.lang.Object ref = codeToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        codeToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *授权码-验证时使用
     * </pre>
     *
     * <code>string codeToken = 4;</code>
     */
    public com.google.protobuf.ByteString
        getCodeTokenBytes() {
      java.lang.Object ref = codeToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        codeToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *授权码-验证时使用
     * </pre>
     *
     * <code>string codeToken = 4;</code>
     */
    public Builder setCodeToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      codeToken_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *授权码-验证时使用
     * </pre>
     *
     * <code>string codeToken = 4;</code>
     */
    public Builder clearCodeToken() {
      
      codeToken_ = getDefaultInstance().getCodeToken();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *授权码-验证时使用
     * </pre>
     *
     * <code>string codeToken = 4;</code>
     */
    public Builder setCodeTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      codeToken_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object sendPlatform_ = "";
    /**
     * <pre>
     *发送平台
     * </pre>
     *
     * <code>string sendPlatform = 5;</code>
     */
    public java.lang.String getSendPlatform() {
      java.lang.Object ref = sendPlatform_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        sendPlatform_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *发送平台
     * </pre>
     *
     * <code>string sendPlatform = 5;</code>
     */
    public com.google.protobuf.ByteString
        getSendPlatformBytes() {
      java.lang.Object ref = sendPlatform_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sendPlatform_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *发送平台
     * </pre>
     *
     * <code>string sendPlatform = 5;</code>
     */
    public Builder setSendPlatform(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      sendPlatform_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送平台
     * </pre>
     *
     * <code>string sendPlatform = 5;</code>
     */
    public Builder clearSendPlatform() {
      
      sendPlatform_ = getDefaultInstance().getSendPlatform();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送平台
     * </pre>
     *
     * <code>string sendPlatform = 5;</code>
     */
    public Builder setSendPlatformBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      sendPlatform_ = value;
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


    // @@protoc_insertion_point(builder_scope:mobile.SendCodeResult)
  }

  // @@protoc_insertion_point(class_scope:mobile.SendCodeResult)
  private static final com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult();
  }

  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SendCodeResult>
      PARSER = new com.google.protobuf.AbstractParser<SendCodeResult>() {
    @java.lang.Override
    public SendCodeResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SendCodeResult(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SendCodeResult> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SendCodeResult> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

