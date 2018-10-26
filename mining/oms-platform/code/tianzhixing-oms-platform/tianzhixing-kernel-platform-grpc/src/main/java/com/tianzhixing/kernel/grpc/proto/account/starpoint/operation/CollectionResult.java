// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint_operation.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

/**
 * <pre>
 *采集结果
 * </pre>
 *
 * Protobuf type {@code account.starpoint.operation.CollectionResult}
 */
public  final class CollectionResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.starpoint.operation.CollectionResult)
    CollectionResultOrBuilder {
  // Use CollectionResult.newBuilder() to construct.
  private CollectionResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CollectionResult() {
    availableStarPoint_ = "";
    frozenStarPoint_ = "";
    starpoint_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CollectionResult(
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

            availableStarPoint_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            frozenStarPoint_ = s;
            break;
          }
          case 26: {
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
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            starpoint_ = s;
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
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_CollectionResult_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_CollectionResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.Builder.class);
  }

  public static final int AVAILABLESTARPOINT_FIELD_NUMBER = 1;
  private volatile java.lang.Object availableStarPoint_;
  /**
   * <pre>
   *可用星点数
   * </pre>
   *
   * <code>optional string availableStarPoint = 1;</code>
   */
  public java.lang.String getAvailableStarPoint() {
    java.lang.Object ref = availableStarPoint_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      availableStarPoint_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *可用星点数
   * </pre>
   *
   * <code>optional string availableStarPoint = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAvailableStarPointBytes() {
    java.lang.Object ref = availableStarPoint_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      availableStarPoint_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FROZENSTARPOINT_FIELD_NUMBER = 2;
  private volatile java.lang.Object frozenStarPoint_;
  /**
   * <pre>
   *冻结星点数量
   * </pre>
   *
   * <code>optional string frozenStarPoint = 2;</code>
   */
  public java.lang.String getFrozenStarPoint() {
    java.lang.Object ref = frozenStarPoint_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      frozenStarPoint_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *冻结星点数量
   * </pre>
   *
   * <code>optional string frozenStarPoint = 2;</code>
   */
  public com.google.protobuf.ByteString
      getFrozenStarPointBytes() {
    java.lang.Object ref = frozenStarPoint_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      frozenStarPoint_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int STARPOINT_FIELD_NUMBER = 4;
  private volatile java.lang.Object starpoint_;
  /**
   * <pre>
   *采集星点数
   * </pre>
   *
   * <code>optional string starpoint = 4;</code>
   */
  public java.lang.String getStarpoint() {
    java.lang.Object ref = starpoint_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      starpoint_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *采集星点数
   * </pre>
   *
   * <code>optional string starpoint = 4;</code>
   */
  public com.google.protobuf.ByteString
      getStarpointBytes() {
    java.lang.Object ref = starpoint_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      starpoint_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RESPONSEENTITY_FIELD_NUMBER = 3;
  private com.tianzhixing.kernel.grpc.proto.ResponseEntity responseEntity_;
  /**
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  public boolean hasResponseEntity() {
    return responseEntity_ != null;
  }
  /**
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  public com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity() {
    return responseEntity_ == null ? com.tianzhixing.kernel.grpc.proto.ResponseEntity.getDefaultInstance() : responseEntity_;
  }
  /**
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  public com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder() {
    return getResponseEntity();
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
    if (!getAvailableStarPointBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, availableStarPoint_);
    }
    if (!getFrozenStarPointBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, frozenStarPoint_);
    }
    if (responseEntity_ != null) {
      output.writeMessage(3, getResponseEntity());
    }
    if (!getStarpointBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, starpoint_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAvailableStarPointBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, availableStarPoint_);
    }
    if (!getFrozenStarPointBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, frozenStarPoint_);
    }
    if (responseEntity_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getResponseEntity());
    }
    if (!getStarpointBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, starpoint_);
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
    if (!(obj instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult)) {
      return super.equals(obj);
    }
    com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult other = (com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult) obj;

    boolean result = true;
    result = result && getAvailableStarPoint()
        .equals(other.getAvailableStarPoint());
    result = result && getFrozenStarPoint()
        .equals(other.getFrozenStarPoint());
    result = result && getStarpoint()
        .equals(other.getStarpoint());
    result = result && (hasResponseEntity() == other.hasResponseEntity());
    if (hasResponseEntity()) {
      result = result && getResponseEntity()
          .equals(other.getResponseEntity());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + AVAILABLESTARPOINT_FIELD_NUMBER;
    hash = (53 * hash) + getAvailableStarPoint().hashCode();
    hash = (37 * hash) + FROZENSTARPOINT_FIELD_NUMBER;
    hash = (53 * hash) + getFrozenStarPoint().hashCode();
    hash = (37 * hash) + STARPOINT_FIELD_NUMBER;
    hash = (53 * hash) + getStarpoint().hashCode();
    if (hasResponseEntity()) {
      hash = (37 * hash) + RESPONSEENTITY_FIELD_NUMBER;
      hash = (53 * hash) + getResponseEntity().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parseFrom(
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
  public static Builder newBuilder(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult prototype) {
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
   *采集结果
   * </pre>
   *
   * Protobuf type {@code account.starpoint.operation.CollectionResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.starpoint.operation.CollectionResult)
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_CollectionResult_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_CollectionResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.Builder.class);
    }

    // Construct using com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.newBuilder()
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
      availableStarPoint_ = "";

      frozenStarPoint_ = "";

      starpoint_ = "";

      if (responseEntityBuilder_ == null) {
        responseEntity_ = null;
      } else {
        responseEntity_ = null;
        responseEntityBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.internal_static_account_starpoint_operation_CollectionResult_descriptor;
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult getDefaultInstanceForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.getDefaultInstance();
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult build() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult buildPartial() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult result = new com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult(this);
      result.availableStarPoint_ = availableStarPoint_;
      result.frozenStarPoint_ = frozenStarPoint_;
      result.starpoint_ = starpoint_;
      if (responseEntityBuilder_ == null) {
        result.responseEntity_ = responseEntity_;
      } else {
        result.responseEntity_ = responseEntityBuilder_.build();
      }
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
      if (other instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult) {
        return mergeFrom((com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult other) {
      if (other == com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.getDefaultInstance()) return this;
      if (!other.getAvailableStarPoint().isEmpty()) {
        availableStarPoint_ = other.availableStarPoint_;
        onChanged();
      }
      if (!other.getFrozenStarPoint().isEmpty()) {
        frozenStarPoint_ = other.frozenStarPoint_;
        onChanged();
      }
      if (!other.getStarpoint().isEmpty()) {
        starpoint_ = other.starpoint_;
        onChanged();
      }
      if (other.hasResponseEntity()) {
        mergeResponseEntity(other.getResponseEntity());
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
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object availableStarPoint_ = "";
    /**
     * <pre>
     *可用星点数
     * </pre>
     *
     * <code>optional string availableStarPoint = 1;</code>
     */
    public java.lang.String getAvailableStarPoint() {
      java.lang.Object ref = availableStarPoint_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        availableStarPoint_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *可用星点数
     * </pre>
     *
     * <code>optional string availableStarPoint = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAvailableStarPointBytes() {
      java.lang.Object ref = availableStarPoint_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        availableStarPoint_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *可用星点数
     * </pre>
     *
     * <code>optional string availableStarPoint = 1;</code>
     */
    public Builder setAvailableStarPoint(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      availableStarPoint_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *可用星点数
     * </pre>
     *
     * <code>optional string availableStarPoint = 1;</code>
     */
    public Builder clearAvailableStarPoint() {
      
      availableStarPoint_ = getDefaultInstance().getAvailableStarPoint();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *可用星点数
     * </pre>
     *
     * <code>optional string availableStarPoint = 1;</code>
     */
    public Builder setAvailableStarPointBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      availableStarPoint_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object frozenStarPoint_ = "";
    /**
     * <pre>
     *冻结星点数量
     * </pre>
     *
     * <code>optional string frozenStarPoint = 2;</code>
     */
    public java.lang.String getFrozenStarPoint() {
      java.lang.Object ref = frozenStarPoint_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        frozenStarPoint_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *冻结星点数量
     * </pre>
     *
     * <code>optional string frozenStarPoint = 2;</code>
     */
    public com.google.protobuf.ByteString
        getFrozenStarPointBytes() {
      java.lang.Object ref = frozenStarPoint_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        frozenStarPoint_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *冻结星点数量
     * </pre>
     *
     * <code>optional string frozenStarPoint = 2;</code>
     */
    public Builder setFrozenStarPoint(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      frozenStarPoint_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *冻结星点数量
     * </pre>
     *
     * <code>optional string frozenStarPoint = 2;</code>
     */
    public Builder clearFrozenStarPoint() {
      
      frozenStarPoint_ = getDefaultInstance().getFrozenStarPoint();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *冻结星点数量
     * </pre>
     *
     * <code>optional string frozenStarPoint = 2;</code>
     */
    public Builder setFrozenStarPointBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      frozenStarPoint_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object starpoint_ = "";
    /**
     * <pre>
     *采集星点数
     * </pre>
     *
     * <code>optional string starpoint = 4;</code>
     */
    public java.lang.String getStarpoint() {
      java.lang.Object ref = starpoint_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        starpoint_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *采集星点数
     * </pre>
     *
     * <code>optional string starpoint = 4;</code>
     */
    public com.google.protobuf.ByteString
        getStarpointBytes() {
      java.lang.Object ref = starpoint_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        starpoint_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *采集星点数
     * </pre>
     *
     * <code>optional string starpoint = 4;</code>
     */
    public Builder setStarpoint(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      starpoint_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *采集星点数
     * </pre>
     *
     * <code>optional string starpoint = 4;</code>
     */
    public Builder clearStarpoint() {
      
      starpoint_ = getDefaultInstance().getStarpoint();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *采集星点数
     * </pre>
     *
     * <code>optional string starpoint = 4;</code>
     */
    public Builder setStarpointBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      starpoint_ = value;
      onChanged();
      return this;
    }

    private com.tianzhixing.kernel.grpc.proto.ResponseEntity responseEntity_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.tianzhixing.kernel.grpc.proto.ResponseEntity, com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder, com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder> responseEntityBuilder_;
    /**
     * <code>optional .ResponseEntity responseEntity = 3;</code>
     */
    public boolean hasResponseEntity() {
      return responseEntityBuilder_ != null || responseEntity_ != null;
    }
    /**
     * <code>optional .ResponseEntity responseEntity = 3;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity() {
      if (responseEntityBuilder_ == null) {
        return responseEntity_ == null ? com.tianzhixing.kernel.grpc.proto.ResponseEntity.getDefaultInstance() : responseEntity_;
      } else {
        return responseEntityBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .ResponseEntity responseEntity = 3;</code>
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
     * <code>optional .ResponseEntity responseEntity = 3;</code>
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
     * <code>optional .ResponseEntity responseEntity = 3;</code>
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
     * <code>optional .ResponseEntity responseEntity = 3;</code>
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
     * <code>optional .ResponseEntity responseEntity = 3;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder getResponseEntityBuilder() {
      
      onChanged();
      return getResponseEntityFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .ResponseEntity responseEntity = 3;</code>
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
     * <code>optional .ResponseEntity responseEntity = 3;</code>
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
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:account.starpoint.operation.CollectionResult)
  }

  // @@protoc_insertion_point(class_scope:account.starpoint.operation.CollectionResult)
  private static final com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult();
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CollectionResult>
      PARSER = new com.google.protobuf.AbstractParser<CollectionResult>() {
    public CollectionResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CollectionResult(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CollectionResult> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CollectionResult> getParserForType() {
    return PARSER;
  }

  public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

