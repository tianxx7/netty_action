// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package txx.proto;

public interface StudentResponseListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:txx.proto.StudentResponseList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .txx.proto.StudentResponse studentResponse = 1;</code>
   */
  java.util.List<StudentResponse>
      getStudentResponseList();
  /**
   * <code>repeated .txx.proto.StudentResponse studentResponse = 1;</code>
   */
  StudentResponse getStudentResponse(int index);
  /**
   * <code>repeated .txx.proto.StudentResponse studentResponse = 1;</code>
   */
  int getStudentResponseCount();
  /**
   * <code>repeated .txx.proto.StudentResponse studentResponse = 1;</code>
   */
  java.util.List<? extends StudentResponseOrBuilder>
      getStudentResponseOrBuilderList();
  /**
   * <code>repeated .txx.proto.StudentResponse studentResponse = 1;</code>
   */
  StudentResponseOrBuilder getStudentResponseOrBuilder(
          int index);
}