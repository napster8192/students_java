package com.bom.sbt.bean;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupTest {
  @Test
  public void constructorTest() {
    assertThat(Group.class, hasValidBeanConstructor());
  }

  @Test
  public void gettersAndSettersTest() {
    assertThat(Group.class, hasValidGettersAndSetters());
  }

  @Test
  public void hashCodeTest() {
    assertThat(Group.class, hasValidBeanHashCodeExcluding("students"));
  }

  @Test
  public void equalsTest() {
    assertThat(Group.class, hasValidBeanEqualsExcluding("students"));
  }

  @Test
  public void toStringTest() {
    assertThat(Group.class, hasValidBeanToString());
  }

  @Test
  public void addStudentTest() {
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

    group.addStudent(student);

    assertEquals(group, student.getGroup());
    assertTrue(group.getStudents().contains(student));
  }
}
