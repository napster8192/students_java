package com.bom.sbt.restapi;

import com.bom.sbt.bean.Group;
import com.bom.sbt.bean.Student;
import com.bom.sbt.dal.StudentDAL;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentServiceImplTest {
  @Test
  void getStudentsTest() {
    Student student1 = new Student();
    student1.setId(1L);
    student1.setName("s1");

    Student student2 = new Student();
    student2.setId(2L);
    student2.setName("s2");

    Group group = new Group();
    group.setId(1L);
    group.setName("g1");
    group.addStudent(student1);
    group.addStudent(student2);

    StudentDAL studentDAL = new StudentDAL() {
      @Override
      public List<Student> getStudents() {
        return new ArrayList<Student>() {{
          add(student1);
          add(student2);
        }};
      }
    };

    StudentService studentService = new StudentServiceImpl(studentDAL);
    List<Student> students = studentService.getStudents();

    assertTrue(students.contains(student1));
    assertEquals(group, students.get(0).getGroup());
    assertTrue(students.contains(student2));
    assertEquals(group, students.get(1).getGroup());
  }

  @Test
  void getStudentTest() {
    Student student = new Student();
    student.setId(3L);
    student.setName("s3");

    StudentDAL studentDAL = new StudentDAL() {
      @Override
      public Student getStudentById(Long id) {
        return student;
      }
    };

    StudentService studentService = new StudentServiceImpl(studentDAL);

    assertEquals(student, studentService.getStudent(3L));
  }

  @Test
  void createStudentTest() {
    Student student = new Student();
    student.setId(1L);
    student.setName("s1");

    StudentDAL studentDAL = new StudentDAL() {
      @Override
      public Student createStudent(Student student) {
        return student;
      }
    };

    StudentService studentService = new StudentServiceImpl(studentDAL);
    Response response = studentService.createStudent(student);

    assertEquals(200, response.getStatus());
    assertEquals(student, response.getEntity());
  }

  @Test
  void updateStudentTest() {
    Student student = new Student();
    student.setId(2L);
    student.setName("s2");

    StudentDAL studentDAL = new StudentDAL() {
      @Override
      public Student updateStudent(Student student) {
        return student;
      }
    };

    StudentService studentService = new StudentServiceImpl(studentDAL);
    Response response = studentService.updateStudent(student.getId(), student);

    assertEquals(200, response.getStatus());
    assertEquals(student, response.getEntity());
  }

  @Test
  void deleteStudentTest() {
    StudentDAL studentDAL = new StudentDAL() {
      @Override
      public void deleteStudent(Long id) {}
    };

    StudentService studentService = new StudentServiceImpl(studentDAL);
    Response response = studentService.deleteStudent(1L);

    assertEquals(200, response.getStatus());
    assertNull(response.getEntity());
  }
}
