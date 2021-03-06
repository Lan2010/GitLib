// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MobileValidationService.proto

package com.tianzhixing.auth.grpc.proto.mobile;

/**
 * <pre>
 *发送验证码信息
 * </pre>
 *
 * Protobuf type {@code mobile.SendCodeInfo}
 */
public  final class SendCodeInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:mobile.SendCodeInfo)
    SendCodeInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SendCodeInfo.newBuilder() to construct.
  private SendCodeInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SendCodeInfo() {
    mobile_ = "";
    token_ = "";
    sendCodeType_ = 0;
    codeLength_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SendCodeInfo(
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

            mobile_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            token_ = s;
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            sendCodeType_ = rawValue;
            break;
          }
          case 32: {

            codeLength_ = input.readInt32();
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
    return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.class, com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.Builder.class);
  }

  /**
   * Protobuf enum {@code mobile.SendCodeInfo.SendCodeType}
   */
  public enum SendCodeType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     *验证码类
     * </pre>
     *
     * <code>SECURITY_CODE = 0;</code>
     */
    SECURITY_CODE(0),
    /**
     * <pre>
     *营销短信 - 暂未开通
     * </pre>
     *
     * <code>MARKETING = 1;</code>
     */
    MARKETING(1),
    UNRECOGNIZED(-1),
    ;

    /**
     * <pre>
     *验证码类
     * </pre>
     *
     * <code>SECURITY_CODE = 0;</code>
     */
    public static final int SECURITY_CODE_VALUE = 0;
    /**
     * <pre>
     *营销短信 - 暂未开通
     * </pre>
     *
     * <code>MARKETING = 1;</code>
     */
    public static final int MARKETING_VALUE = 1;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static SendCodeType valueOf(int value) {
      return forNumber(value);
    }

    public static SendCodeType forNumber(int value) {
      switch (value) {
        case 0: return SECURITY_CODE;
        case 1: return MARKETING;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<SendCodeType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        SendCodeType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<SendCodeType>() {
            public SendCodeType findValueByNumber(int number) {
              return SendCodeType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.getDescriptor().getEnumTypes().get(0);
    }

    private static final SendCodeType[] VALUES = values();

    public static SendCodeType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private SendCodeType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:mobile.SendCodeInfo.SendCodeType)
  }

  public static final int MOBILE_FIELD_NUMBER = 1;
  private volatile java.lang.Object mobile_;
  /**
   * <pre>
   *手机号码-必填
   * </pre>
   *
   * <code>string mobile = 1;</code>
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
   *手机号码-必填
   * </pre>
   *
   * <code>string mobile = 1;</code>
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

  public static final int TOKEN_FIELD_NUMBER = 2;
  private volatile java.lang.Object token_;
  /**
   * <pre>
   *平台身份识别-必填
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
   *平台身份识别-必填
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

  public static final int SENDCODETYPE_FIELD_NUMBER = 3;
  private int sendCodeType_;
  /**
   * <pre>
   *发送代码分类 - 必填
   * </pre>
   *
   * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
   */
  public int getSendCodeTypeValue() {
    return sendCodeType_;
  }
  /**
   * <pre>
   *发送代码分类 - 必填
   * </pre>
   *
   * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
   */
  public com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType getSendCodeType() {
    @SuppressWarnings("deprecation")
    com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType result = com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType.valueOf(sendCodeType_);
    return result == null ? com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType.UNRECOGNIZED : result;
  }

  public static final int CODELENGTH_FIELD_NUMBER = 4;
  private int codeLength_;
  /**
   * <pre>
   *验证码长度 -必填(4-8长度)
   * </pre>
   *
   * <code>int32 codeLength = 4;</code>
   */
  public int getCodeLength() {
    return codeLength_;
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
    if (!getMobileBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, mobile_);
    }
    if (!getTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, token_);
    }
    if (sendCodeType_ != com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType.SECURITY_CODE.getNumber()) {
      output.writeEnum(3, sendCodeType_);
    }
    if (codeLength_ != 0) {
      output.writeInt32(4, codeLength_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getMobileBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, mobile_);
    }
    if (!getTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, token_);
    }
    if (sendCodeType_ != com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType.SECURITY_CODE.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, sendCodeType_);
    }
    if (codeLength_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, codeLength_);
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
    if (!(obj instanceof com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo)) {
      return super.equals(obj);
    }
    com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo other = (com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo) obj;

    boolean result = true;
    result = result && getMobile()
        .equals(other.getMobile());
    result = result && getToken()
        .equals(other.getToken());
    result = result && sendCodeType_ == other.sendCodeType_;
    result = result && (getCodeLength()
        == other.getCodeLength());
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
    hash = (37 * hash) + MOBILE_FIELD_NUMBER;
    hash = (53 * hash) + getMobile().hashCode();
    hash = (37 * hash) + TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getToken().hashCode();
    hash = (37 * hash) + SENDCODETYPE_FIELD_NUMBER;
    hash = (53 * hash) + sendCodeType_;
    hash = (37 * hash) + CODELENGTH_FIELD_NUMBER;
    hash = (53 * hash) + getCodeLength();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parseFrom(
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
  public static Builder newBuilder(com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo prototype) {
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
   *发送验证码信息
   * </pre>
   *
   * Protobuf type {@code mobile.SendCodeInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:mobile.SendCodeInfo)
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.class, com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.Builder.class);
    }

    // Construct using com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.newBuilder()
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
      mobile_ = "";

      token_ = "";

      sendCodeType_ = 0;

      codeLength_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.internal_static_mobile_SendCodeInfo_descriptor;
    }

    @java.lang.Override
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo getDefaultInstanceForType() {
      return com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.getDefaultInstance();
    }

    @java.lang.Override
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo build() {
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo buildPartial() {
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo result = new com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo(this);
      result.mobile_ = mobile_;
      result.token_ = token_;
      result.sendCodeType_ = sendCodeType_;
      result.codeLength_ = codeLength_;
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
      if (other instanceof com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo) {
        return mergeFrom((com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo other) {
      if (other == com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.getDefaultInstance()) return this;
      if (!other.getMobile().isEmpty()) {
        mobile_ = other.mobile_;
        onChanged();
      }
      if (!other.getToken().isEmpty()) {
        token_ = other.token_;
        onChanged();
      }
      if (other.sendCodeType_ != 0) {
        setSendCodeTypeValue(other.getSendCodeTypeValue());
      }
      if (other.getCodeLength() != 0) {
        setCodeLength(other.getCodeLength());
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
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object mobile_ = "";
    /**
     * <pre>
     *手机号码-必填
     * </pre>
     *
     * <code>string mobile = 1;</code>
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
     *手机号码-必填
     * </pre>
     *
     * <code>string mobile = 1;</code>
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
     *手机号码-必填
     * </pre>
     *
     * <code>string mobile = 1;</code>
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
     *手机号码-必填
     * </pre>
     *
     * <code>string mobile = 1;</code>
     */
    public Builder clearMobile() {
      
      mobile_ = getDefaultInstance().getMobile();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *手机号码-必填
     * </pre>
     *
     * <code>string mobile = 1;</code>
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

    private java.lang.Object token_ = "";
    /**
     * <pre>
     *平台身份识别-必填
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
     *平台身份识别-必填
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
     *平台身份识别-必填
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
     *平台身份识别-必填
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
     *平台身份识别-必填
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

    private int sendCodeType_ = 0;
    /**
     * <pre>
     *发送代码分类 - 必填
     * </pre>
     *
     * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
     */
    public int getSendCodeTypeValue() {
      return sendCodeType_;
    }
    /**
     * <pre>
     *发送代码分类 - 必填
     * </pre>
     *
     * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
     */
    public Builder setSendCodeTypeValue(int value) {
      sendCodeType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送代码分类 - 必填
     * </pre>
     *
     * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
     */
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType getSendCodeType() {
      @SuppressWarnings("deprecation")
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType result = com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType.valueOf(sendCodeType_);
      return result == null ? com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     *发送代码分类 - 必填
     * </pre>
     *
     * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
     */
    public Builder setSendCodeType(com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      sendCodeType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送代码分类 - 必填
     * </pre>
     *
     * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
     */
    public Builder clearSendCodeType() {
      
      sendCodeType_ = 0;
      onChanged();
      return this;
    }

    private int codeLength_ ;
    /**
     * <pre>
     *验证码长度 -必填(4-8长度)
     * </pre>
     *
     * <code>int32 codeLength = 4;</code>
     */
    public int getCodeLength() {
      return codeLength_;
    }
    /**
     * <pre>
     *验证码长度 -必填(4-8长度)
     * </pre>
     *
     * <code>int32 codeLength = 4;</code>
     */
    public Builder setCodeLength(int value) {
      
      codeLength_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *验证码长度 -必填(4-8长度)
     * </pre>
     *
     * <code>int32 codeLength = 4;</code>
     */
    public Builder clearCodeLength() {
      
      codeLength_ = 0;
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


    // @@protoc_insertion_point(builder_scope:mobile.SendCodeInfo)
  }

  // @@protoc_insertion_point(class_scope:mobile.SendCodeInfo)
  private static final com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo();
  }

  public static com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SendCodeInfo>
      PARSER = new com.google.protobuf.AbstractParser<SendCodeInfo>() {
    @java.lang.Override
    public SendCodeInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SendCodeInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SendCodeInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SendCodeInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

