package com.bom.sbt.restapi;

import com.bom.sbt.bean.Group;
import com.bom.sbt.bean.Student;
import com.bom.sbt.dal.GroupDAL;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupServiceImplTest {
  @Test
  void getGroupsTest() {
    Group group1 = new Group();
    group1.setId(1L);
    group1.setName("g1");

    Group group2 = new Group();
    group2.setId(2L);
    group2.setName("g2");

    GroupDAL groupDAL = new GroupDAL() {
      @Override
      public List<Group> getGroups() {
        return new ArrayList<Group>() {{
          add(group1);
          add(group2);
        }};
      }
    };

    GroupService groupService = new GroupServiceImpl(groupDAL);
    List<Group> groups = groupService.getGroups();

    assertTrue(groups.contains(group1));
    assertTrue(groups.contains(group2));
  }

  @Test
  void getGroupTest() {
    Group group = new Group();
    group.setId(3L);
    group.setName("g3");

    GroupDAL groupDAL = new GroupDAL() {
      @Override
      public Group getGroupById(Long id) {
        return group;
      }
    };

    GroupService groupService = new GroupServiceImpl(groupDAL);

    assertEquals(group, groupService.getGroup(3L));
  }

  @Test
  void createGroupTest() {
    Group group = new Group();
    group.setId(1L);
    group.setName("g1");

    GroupDAL groupDAL = new GroupDAL() {
      @Override
      public Group createGroup(Group group) {
        return group;
      }
    };

    GroupService groupService = new GroupServiceImpl(groupDAL);
    Response response = groupService.createGroup(group);

    assertEquals(200, response.getStatus());
    assertEquals(group, response.getEntity());
  }

  @Test
  void updateGroupTest() {
    Group group = new Group();
    group.setId(2L);
    group.setName("g2");

    GroupDAL groupDAL = new GroupDAL() {
      @Override
      public Group updateGroup(Group group) {
        return group;
      }
    };

    GroupService groupService = new GroupServiceImpl(groupDAL);
    Response response = groupService.updateGroup(group.getId(), group);

    assertEquals(200, response.getStatus());
    assertEquals(group, response.getEntity());
  }

  @Test
  void deleteGroupTest() {
    GroupDAL groupDAL = new GroupDAL() {
      @Override
      public void deleteGroup(Long id) {}
    };

    GroupService groupService = new GroupServiceImpl(groupDAL);
    Response response = groupService.deleteGroup(1L);

    assertEquals(200, response.getStatus());
    assertNull(response.getEntity());
  }

  @Test
  public void getStudentsByGroupIdTest() {
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

    GroupDAL groupDAL = new GroupDAL() {
      @Override
      public List<Student> getStudentsByGroupId(Long id) {
        return new ArrayList<Student>() {{
          add(student1);
          add(student2);
        }};
      }
    };

    GroupService groupService = new GroupServiceImpl(groupDAL);
    List<Student> students = groupService.getStudentsByGroupId(1L);

    assertTrue(students.contains(student1));
    assertEquals(group, students.get(0).getGroup());
    assertTrue(students.contains(student2));
    assertEquals(group, students.get(1).getGroup());
  }

  @Test
  public void addStudentTest() {
    Student student = new Student();
    student.setId(1L);
    student.setName("s1");

    Group group = new Group();
    group.setId(1L);
    group.setName("g1");
    group.addStudent(student);

    GroupDAL groupDAL = new GroupDAL() {
      @Override
      public Group addStudent(Long groupId, Long studentId) {
        return group;
      }
    };

    GroupService groupService = new GroupServiceImpl(groupDAL);
    Response response = groupService.addStudent(1L, 1L);

    assertEquals(200, response.getStatus());
    assertEquals(group, response.getEntity());
  }
}
