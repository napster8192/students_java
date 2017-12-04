package com.bom.sbt.dal;

import com.bom.sbt.bean.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentDALTest {
  @Test
  public void getStudentByIdTest() {
    StudentDAL studentDAL = new StudentDAL();

    Student student = new Student();
    student.setName("s1");

    Student savedStudent = studentDAL.createStudent(student);

    Student getStudentById = studentDAL.getStudentById(savedStudent.getId());

    assertEquals(student.getId(), getStudentById.getId());
    assertEquals(student.getName(), getStudentById.getName());
  }

  @Test
  public void updateStudentTest() {
    StudentDAL studentDAL = new StudentDAL();

    Student student = new Student();
    student.setName("s2");

    studentDAL.createStudent(student);

    student.setName("New Student Name");

    studentDAL.updateStudent(student);

    Student updatedStudent = studentDAL.getStudentById(student.getId());

    assertEquals(student.getName(), updatedStudent.getName());
  }

  @Test
  public void deleteStudentTest() {
    StudentDAL studentDAL = new StudentDAL();

    Student student = new Student();
    student.setName("s3");

    studentDAL.createStudent(student);

    studentDAL.deleteStudent(student.getId());

    Student deletedStudent = studentDAL.getStudentById(student.getId());

    assertNull(deletedStudent);
  }

  @Test
  public void getStudentsTest() {
    StudentDAL studentDAL = new StudentDAL();

    Student student1 = new Student();
    student1.setName("s4");

    Student student2 = new Student();
    student2.setName("s5");

    studentDAL.createStudent(student1);
    studentDAL.createStudent(student2);

    List<Student> students = studentDAL.getStudents();

    assertTrue(students.contains(student1));
    assertTrue(students.contains(student2));
  }
}
