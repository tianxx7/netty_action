// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package txx.proto;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_txx_proto_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_txx_proto_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_txx_proto_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_txx_proto_MyResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rStudent.proto\022\ttxx.proto\"\035\n\tMyRequest\022" +
      "\020\n\010username\030\001 \001(\t\"\036\n\nMyResponse\022\020\n\010realn" +
      "ame\030\001 \001(\t2X\n\016StudentService\022F\n\025getRealNa" +
      "meByUserName\022\024.txx.proto.MyRequest\032\025.txx" +
      ".proto.MyResponse\"\000B\033\n\ttxx.protoB\014Studen" +
      "tProtoP\001b\006proto3"
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
        }, assigner);
    internal_static_txx_proto_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_txx_proto_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_txx_proto_MyRequest_descriptor,
        new String[] { "Username", });
    internal_static_txx_proto_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_txx_proto_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_txx_proto_MyResponse_descriptor,
        new String[] { "Realname", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
