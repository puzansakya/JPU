/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzan.jpa.DAO.impl;

import com.puzan.jpa.DAO.StudentDAO;
import com.puzan.jpa.Entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author puzan
 */
public class StudentDAOImpl implements StudentDAO {

    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction trans;

    public StudentDAOImpl() {
        emf = Persistence.createEntityManagerFactory("JPU");
        em = emf.createEntityManager();
    }

    @Override
    public List<Student> getAll() {
        return em.createQuery("Select s from Student s").getResultList();

    }

    @Override
    public Student getById(int id) {
        return em.find(Student.class, id);
    }

    @Override
    public void insert(Student s) {
        trans = em.getTransaction();
        trans.begin();
        em.persist(s);
        trans.commit();
    }

    @Override
    public void update(Student s) {
        trans = em.getTransaction();
        trans.begin();
        em.merge(s);
        trans.commit();

    }

    @Override
    public boolean delete(int id) {
        Student s = getById(id);
        if (s != null) {
            trans = em.getTransaction();
            trans.begin();
            em.remove(s);
            trans.commit();
            return true;
        }
        return false;
    }

}
