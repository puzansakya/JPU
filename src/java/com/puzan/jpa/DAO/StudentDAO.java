/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.jpa.DAO;

import com.puzan.jpa.Entities.Student;
import java.util.List;

/**
 *
 * @author puzan
 */
public interface StudentDAO {

    List<Student> getAll();

    Student getById(int id);

    void insert(Student s);

    void update(Student s);

    boolean delete(int id);
}
