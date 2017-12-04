package com.bom.sbt.bean;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {
  @Test
  public void constructorTest() {
    assertThat(Student.class, hasValidBeanConstructor());
  }

  @Test
  public void gettersAndSettersTest() {
    assertThat(Student.class, hasValidGettersAndSetters());
  }

  @Test
  public void hashCodeTest() {
    assertThat(Student.class, hasValidBeanHashCode());
  }

  @Test
  public void equalsTest() {
    assertThat(Student.class, hasValidBeanEquals());
  }

  @Test
  public void toStringTest() {
    long groupId = 1L;
    String groupName = "g1";
    Group group = new Group();
    group.setId(groupId);
    group.setName(groupName);

    long studentId = 2L;
    String studentName = "s1";
    Student student = new Student();
    student.setGroup(group);
    student.setId(studentId);
    student.setName(studentName);

    String expected = "Student{id=" + student.getId() + ", name=" + student.getName() + "," +
        " Group{id=" + group.getId() + ", name=" + group.getName() + "}}";

    assertEquals(expected, student.toString());
  }
}
