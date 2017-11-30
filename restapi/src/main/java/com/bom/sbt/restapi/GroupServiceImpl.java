package com.bom.sbt.restapi;

import com.bom.sbt.bean.Group;
import com.bom.sbt.bean.Student;
import com.bom.sbt.dal.GroupDAL;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.List;

public class GroupServiceImpl implements GroupService {
  private static final Logger logger = LogManager.getLogger(GroupServiceImpl.class);
  private final GroupDAL groupDAL;

  @Inject
  public GroupServiceImpl(GroupDAL groupDAL) {
    this.groupDAL = groupDAL;
  }

  public List<Group> getGroups() {
    logger.debug("getGroups");

    return groupDAL.getGroups();
  }

  public Group getGroup(Long id) {
    logger.debug("getGroupById id = {0}", id);

    return groupDAL.getGroupById(id);
  }

  public Response createGroup(Group group) {
    logger.debug("createGroup");

    return Response.ok().entity(groupDAL.createGroup(group)).build();
  }

  public Response updateGroup(Long id, Group group) {
    logger.debug("updateGroup");

    return Response.ok().entity(groupDAL.updateGroup(group)).build();
  }

  public Response deleteGroup(Long id) {
    logger.debug("deleteGroupById id = {0}", id);

    groupDAL.deleteGroup(id);

    return Response.ok().build();
  }

  public List<Student> getStudentsByGroupId(Long id) {
    logger.debug("getStudentsByGroupId id = {0}", id);

    return groupDAL.getStudentsByGroupId(id);
  }

  public Response addStudent(Long groupId, Long studentId) {
    logger.debug("addStudent {0} to Group {1}", studentId, groupId);

    return Response.ok().entity(groupDAL.addStudent(groupId, studentId)).build();
  }
}
