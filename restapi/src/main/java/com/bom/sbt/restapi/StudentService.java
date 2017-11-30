package com.bom.sbt.restapi;

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

@Path("students")
@Produces(MediaType.APPLICATION_JSON)
public interface StudentService {
  @GET
  public List<Student> getStudents();

  @GET
  @Path("/{id}")
  public Student getStudent(@PathParam("id") Long id);

  @POST
  public Response createStudent(Student student);

  @PUT
  @Path("/{id}")
  public Response updateStudent(@PathParam("id") Long id, Student student);

  @DELETE
  @Path("/{id}")
  public Response deleteStudent(@PathParam("id") Long id);
}
