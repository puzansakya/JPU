/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puza.jpa.api;

import com.puzan.jpa.DAO.StudentDAO;
import com.puzan.jpa.DAO.impl.StudentDAOImpl;
import com.puzan.jpa.Entities.Student;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author puzan
 */
@Path("student")
public class StudentApi {

    private StudentDAO StudentDAO = new StudentDAOImpl();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Student> index() {
        return StudentDAO.getAll();
    }

    @Path(value = "/{id}")
    @GET
    public Student getDetail(@PathParam("id") int id) {
        return StudentDAO.getById(id);
    }

    @Path(value = "/save")
    @POST
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public String save(@FormParam("id") int id, @FormParam("first_name") String firstName, @FormParam("last_name") String lastName, @FormParam("email") String email, @FormParam("status") int status) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setStatus((status == 1) ? true : false);
        if (id == 0) {
            StudentDAO.insert(student);
        } else {
            student.setId(id);
            student.setModifiedDate(new Date());
            StudentDAO.update(student);
        }
        return "success";
    }

    @Path(value = "/delete/{id}")
    @GET
    @Produces(value = "text/plain")
    public String remove(@PathParam("id") int id) {
        return (StudentDAO.delete(id)) ? "success" : "error";
    }
}
