package com.bom.sbt.dal;

import com.bom.sbt.bean.Group;
import com.bom.sbt.bean.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupDALTest {
  @Test
  public void getGroupByIdTest() {
    GroupDAL GroupDAL = new GroupDAL();

    Group group = new Group();
    group.setName("g1");

    Group savedGroup = GroupDAL.createGroup(group);

    Group getGroupById = GroupDAL.getGroupById(savedGroup.getId());

    assertEquals(group.getId(), getGroupById.getId());
    assertEquals(group.getName(), getGroupById.getName());
  }

  @Test
  public void updateGroupTest() {
    GroupDAL groupDAL = new GroupDAL();

    Group group = new Group();
    group.setName("g2");

    groupDAL.createGroup(group);

    group.setName("New Group Name");

    groupDAL.updateGroup(group);

    Group updatedGroup = groupDAL.getGroupById(group.getId());

    assertEquals(group.getName(), updatedGroup.getName());
  }

  @Test
  public void deleteGroupTest() {
    GroupDAL groupDAL = new GroupDAL();

    Group group = new Group();
    group.setName("g3");

    groupDAL.createGroup(group);

    groupDAL.deleteGroup(group.getId());

    Group deletedGroup = groupDAL.getGroupById(group.getId());

    assertNull(deletedGroup);
  }

  @Test
  public void getGroupsTest() {
    GroupDAL groupDAL = new GroupDAL();

    Group group1 = new Group();
    group1.setName("g4");

    Group group2 = new Group();
    group2.setName("g5");

    groupDAL.createGroup(group1);
    groupDAL.createGroup(group2);

    List<Group> groups = groupDAL.getGroups();

    assertTrue(groups.contains(group1));
    assertTrue(groups.contains(group2));
  }

  @Test
  public void addStudentTest() {
    GroupDAL groupDAL = new GroupDAL();
    StudentDAL studentDAL = new StudentDAL();

    Group group = new Group();
    group.setName("g6");

    Student student = new Student();
    student.setName("s1");

    groupDAL.createGroup(group);
    studentDAL.createStudent(student);

    Group groupWithStudent = groupDAL.addStudent(group.getId(), student.getId());
    Student studentInGroup = studentDAL.getStudentById(student.getId());

    assertTrue(groupWithStudent.getStudents().contains(studentInGroup));
    assertEquals(studentInGroup.getGroup(), groupWithStudent);
  }

  @Test
  public void getStudentsByGroupIdTest() {
    GroupDAL groupDAL = new GroupDAL();
    StudentDAL studentDAL = new StudentDAL();

    Group group = new Group();
    group.setName("g7");

    groupDAL.createGroup(group);

    Student student1 = new Student();
    student1.setName("s2");

    Student student2 = new Student();
    student2.setName("s3");

    studentDAL.createStudent(student1);
    studentDAL.createStudent(student2);

    group = groupDAL.addStudent(group.getId(), student1.getId());
    student1 = studentDAL.getStudentById(student1.getId());
    student2 = studentDAL.getStudentById(student2.getId());

    List<Student> students = groupDAL.getStudentsByGroupId(group.getId());

    assertTrue(group.getStudents().contains(student1));
    assertFalse(group.getStudents().contains(student2));
    assertTrue(students.contains(student1));
    assertFalse(students.contains(student2));
  }
}
