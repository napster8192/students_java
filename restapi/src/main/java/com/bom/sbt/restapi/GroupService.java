package com.bom.sbt.restapi;

import com.bom.sbt.bean.Group;
import com.bom.sbt.bean.Student;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("groups")
@Produces(MediaType.APPLICATION_JSON)
public interface GroupService {
  @GET
  public List<Group> getGroups();

  @GET
  @Path("/{id}")
  public Group getGroup(@PathParam("id") Long id);

  @POST
  public Response createGroup(Group group);

  @PUT
  @Path("/{id}")
  public Response updateGroup(@PathParam("id") Long id, Group group);

  @DELETE
  @Path("/{id}")
  public Response deleteGroup(@PathParam("id") Long id);

  @GET
  @Path("/{id}/students")
  public List<Student> getStudentsByGroupId(@PathParam("id") Long id);

  @PUT
  @Path("/{groupId}/add/{studentId}")
  public Response addStudent(@PathParam("groupId") Long groupId, @PathParam("studentId") Long studentId);
}
