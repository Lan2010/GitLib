// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint;

/**
 * <pre>
 *流水结果
 * </pre>
 *
 * Protobuf type {@code account.starpoint.RecordsResult}
 */
public  final class RecordsResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.starpoint.RecordsResult)
    RecordsResultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RecordsResult.newBuilder() to construct.
  private RecordsResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  public RecordsResult() {
    records_ = java.util.Collections.emptyList();
    total_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RecordsResult(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              records_ = new java.util.ArrayList<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            records_.add(
                input.readMessage(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.parser(), extensionRegistry));
            break;
          }
          case 16: {

            total_ = input.readInt64();
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        records_ = java.util.Collections.unmodifiableList(records_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsResult_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.Builder.class);
  }

  private int bitField0_;
  public static final int RECORDS_FIELD_NUMBER = 1;
  private java.util.List<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo> records_;
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
   */
  public java.util.List<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo> getRecordsList() {
    return records_;
  }
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
   */
  public java.util.List<? extends com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfoOrBuilder> 
      getRecordsOrBuilderList() {
    return records_;
  }
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
   */
  public int getRecordsCount() {
    return records_.size();
  }
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
   */
  public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo getRecords(int index) {
    return records_.get(index);
  }
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
   */
  public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfoOrBuilder getRecordsOrBuilder(
      int index) {
    return records_.get(index);
  }

  public static final int TOTAL_FIELD_NUMBER = 2;
  private long total_;
  /**
   * <pre>
   *条数
   * </pre>
   *
   * <code>int64 total = 2;</code>
   */
  public long getTotal() {
    return total_;
  }

  public static final int RESPONSEENTITY_FIELD_NUMBER = 3;
  private com.tianzhixing.kernel.grpc.proto.ResponseEntity responseEntity_;
  /**
   * <code>.ResponseEntity responseEntity = 3;</code>
   */
  public boolean hasResponseEntity() {
    return responseEntity_ != null;
  }
  /**
   * <code>.ResponseEntity responseEntity = 3;</code>
   */
  public com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity() {
    return responseEntity_ == null ? com.tianzhixing.kernel.grpc.proto.ResponseEntity.getDefaultInstance() : responseEntity_;
  }
  /**
   * <code>.ResponseEntity responseEntity = 3;</code>
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
    for (int i = 0; i < records_.size(); i++) {
      output.writeMessage(1, records_.get(i));
    }
    if (total_ != 0L) {
      output.writeInt64(2, total_);
    }
    if (responseEntity_ != null) {
      output.writeMessage(3, getResponseEntity());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < records_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, records_.get(i));
    }
    if (total_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, total_);
    }
    if (responseEntity_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getResponseEntity());
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
    if (!(obj instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult)) {
      return super.equals(obj);
    }
    com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult other = (com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult) obj;

    boolean result = true;
    result = result && getRecordsList()
        .equals(other.getRecordsList());
    result = result && (getTotal()
        == other.getTotal());
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
    if (getRecordsCount() > 0) {
      hash = (37 * hash) + RECORDS_FIELD_NUMBER;
      hash = (53 * hash) + getRecordsList().hashCode();
    }
    hash = (37 * hash) + TOTAL_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTotal());
    if (hasResponseEntity()) {
      hash = (37 * hash) + RESPONSEENTITY_FIELD_NUMBER;
      hash = (53 * hash) + getResponseEntity().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parseFrom(
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
  public static Builder newBuilder(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult prototype) {
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
   *流水结果
   * </pre>
   *
   * Protobuf type {@code account.starpoint.RecordsResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.starpoint.RecordsResult)
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsResult_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.class, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.Builder.class);
    }

    // Construct using com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.newBuilder()
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
        getRecordsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (recordsBuilder_ == null) {
        records_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        recordsBuilder_.clear();
      }
      total_ = 0L;

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
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.internal_static_account_starpoint_RecordsResult_descriptor;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult getDefaultInstanceForType() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.getDefaultInstance();
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult build() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult buildPartial() {
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult result = new com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (recordsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          records_ = java.util.Collections.unmodifiableList(records_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.records_ = records_;
      } else {
        result.records_ = recordsBuilder_.build();
      }
      result.total_ = total_;
      if (responseEntityBuilder_ == null) {
        result.responseEntity_ = responseEntity_;
      } else {
        result.responseEntity_ = responseEntityBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult) {
        return mergeFrom((com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult other) {
      if (other == com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.getDefaultInstance()) return this;
      if (recordsBuilder_ == null) {
        if (!other.records_.isEmpty()) {
          if (records_.isEmpty()) {
            records_ = other.records_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureRecordsIsMutable();
            records_.addAll(other.records_);
          }
          onChanged();
        }
      } else {
        if (!other.records_.isEmpty()) {
          if (recordsBuilder_.isEmpty()) {
            recordsBuilder_.dispose();
            recordsBuilder_ = null;
            records_ = other.records_;
            bitField0_ = (bitField0_ & ~0x00000001);
            recordsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getRecordsFieldBuilder() : null;
          } else {
            recordsBuilder_.addAllMessages(other.records_);
          }
        }
      }
      if (other.getTotal() != 0L) {
        setTotal(other.getTotal());
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
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo> records_ =
      java.util.Collections.emptyList();
    private void ensureRecordsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        records_ = new java.util.ArrayList<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo>(records_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfoOrBuilder> recordsBuilder_;

    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public java.util.List<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo> getRecordsList() {
      if (recordsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(records_);
      } else {
        return recordsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public int getRecordsCount() {
      if (recordsBuilder_ == null) {
        return records_.size();
      } else {
        return recordsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo getRecords(int index) {
      if (recordsBuilder_ == null) {
        return records_.get(index);
      } else {
        return recordsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder setRecords(
        int index, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo value) {
      if (recordsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRecordsIsMutable();
        records_.set(index, value);
        onChanged();
      } else {
        recordsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder setRecords(
        int index, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder builderForValue) {
      if (recordsBuilder_ == null) {
        ensureRecordsIsMutable();
        records_.set(index, builderForValue.build());
        onChanged();
      } else {
        recordsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder addRecords(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo value) {
      if (recordsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRecordsIsMutable();
        records_.add(value);
        onChanged();
      } else {
        recordsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder addRecords(
        int index, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo value) {
      if (recordsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRecordsIsMutable();
        records_.add(index, value);
        onChanged();
      } else {
        recordsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder addRecords(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder builderForValue) {
      if (recordsBuilder_ == null) {
        ensureRecordsIsMutable();
        records_.add(builderForValue.build());
        onChanged();
      } else {
        recordsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder addRecords(
        int index, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder builderForValue) {
      if (recordsBuilder_ == null) {
        ensureRecordsIsMutable();
        records_.add(index, builderForValue.build());
        onChanged();
      } else {
        recordsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder addAllRecords(
        java.lang.Iterable<? extends com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo> values) {
      if (recordsBuilder_ == null) {
        ensureRecordsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, records_);
        onChanged();
      } else {
        recordsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder clearRecords() {
      if (recordsBuilder_ == null) {
        records_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        recordsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public Builder removeRecords(int index) {
      if (recordsBuilder_ == null) {
        ensureRecordsIsMutable();
        records_.remove(index);
        onChanged();
      } else {
        recordsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder getRecordsBuilder(
        int index) {
      return getRecordsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfoOrBuilder getRecordsOrBuilder(
        int index) {
      if (recordsBuilder_ == null) {
        return records_.get(index);  } else {
        return recordsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public java.util.List<? extends com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfoOrBuilder> 
         getRecordsOrBuilderList() {
      if (recordsBuilder_ != null) {
        return recordsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(records_);
      }
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder addRecordsBuilder() {
      return getRecordsFieldBuilder().addBuilder(
          com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder addRecordsBuilder(
        int index) {
      return getRecordsFieldBuilder().addBuilder(
          index, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.getDefaultInstance());
    }
    /**
     * <pre>
     *记录
     * </pre>
     *
     * <code>repeated .account.starpoint.RecordsInfo records = 1;</code>
     */
    public java.util.List<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder> 
         getRecordsBuilderList() {
      return getRecordsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfoOrBuilder> 
        getRecordsFieldBuilder() {
      if (recordsBuilder_ == null) {
        recordsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo.Builder, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfoOrBuilder>(
                records_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        records_ = null;
      }
      return recordsBuilder_;
    }

    private long total_ ;
    /**
     * <pre>
     *条数
     * </pre>
     *
     * <code>int64 total = 2;</code>
     */
    public long getTotal() {
      return total_;
    }
    /**
     * <pre>
     *条数
     * </pre>
     *
     * <code>int64 total = 2;</code>
     */
    public Builder setTotal(long value) {
      
      total_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *条数
     * </pre>
     *
     * <code>int64 total = 2;</code>
     */
    public Builder clearTotal() {
      
      total_ = 0L;
      onChanged();
      return this;
    }

    private com.tianzhixing.kernel.grpc.proto.ResponseEntity responseEntity_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.tianzhixing.kernel.grpc.proto.ResponseEntity, com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder, com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder> responseEntityBuilder_;
    /**
     * <code>.ResponseEntity responseEntity = 3;</code>
     */
    public boolean hasResponseEntity() {
      return responseEntityBuilder_ != null || responseEntity_ != null;
    }
    /**
     * <code>.ResponseEntity responseEntity = 3;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity() {
      if (responseEntityBuilder_ == null) {
        return responseEntity_ == null ? com.tianzhixing.kernel.grpc.proto.ResponseEntity.getDefaultInstance() : responseEntity_;
      } else {
        return responseEntityBuilder_.getMessage();
      }
    }
    /**
     * <code>.ResponseEntity responseEntity = 3;</code>
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
     * <code>.ResponseEntity responseEntity = 3;</code>
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
     * <code>.ResponseEntity responseEntity = 3;</code>
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
     * <code>.ResponseEntity responseEntity = 3;</code>
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
     * <code>.ResponseEntity responseEntity = 3;</code>
     */
    public com.tianzhixing.kernel.grpc.proto.ResponseEntity.Builder getResponseEntityBuilder() {
      
      onChanged();
      return getResponseEntityFieldBuilder().getBuilder();
    }
    /**
     * <code>.ResponseEntity responseEntity = 3;</code>
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
     * <code>.ResponseEntity responseEntity = 3;</code>
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


    // @@protoc_insertion_point(builder_scope:account.starpoint.RecordsResult)
  }

  // @@protoc_insertion_point(class_scope:account.starpoint.RecordsResult)
  private static final com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult();
  }

  public static com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RecordsResult>
      PARSER = new com.google.protobuf.AbstractParser<RecordsResult>() {
    @java.lang.Override
    public RecordsResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RecordsResult(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RecordsResult> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RecordsResult> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

