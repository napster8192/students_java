package com.bom.sbt.restapi;

import com.bom.sbt.bean.Student;
import com.bom.sbt.dal.StudentDAL;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.List;

public class StudentServiceImpl implements StudentService {
  private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
  private final StudentDAL studentDAL;

  @Inject
  public StudentServiceImpl(StudentDAL studentDAL) {
    this.studentDAL = studentDAL;
  }

  public List<Student> getStudents() {
    logger.debug("getStudents");

    return studentDAL.getStudents();
  }

  public Student getStudent(Long id) {
    logger.debug("getStudentById id = {0}", id);

    return studentDAL.getStudentById(id);
  }

  public Response createStudent(Student student) {
    logger.debug("createStudent");

    return Response.ok().entity(studentDAL.createStudent(student)).build();
  }

  public Response updateStudent(Long id, Student student) {
    logger.debug("updateStudent");

    return Response.ok().entity(studentDAL.updateStudent(student)).build();
  }

  public Response deleteStudent(Long id) {
    logger.debug("deleteStudentById id = {0}", id);

    studentDAL.deleteStudent(id);

    return Response.ok().build();
  }
}
