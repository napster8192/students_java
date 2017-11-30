package com.bom.sbt.dal;

import com.bom.sbt.bean.Student;
import com.bom.sbt.dal.utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class StudentDAL {
  private static final Logger logger = LogManager.getLogger(StudentDAL.class);

  public List<Student> getStudents() {
    logger.debug("getStudents DAL");

    Session session = null;
    List students = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      students = (List<Student>) session.createQuery("FROM Student").list();
    } catch (Exception e) {
      logger.error("Error getStudents DAL");
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return students;
  }

  public Student createStudent(Student student) {
    logger.debug("createStudent DAL {0}", student);

    Session session = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(student);
      session.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Error createStudent DAL {0}", student);
      if (session != null) {
        session.getTransaction().rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return student;
  }

  public Student updateStudent(Student student) {
    logger.debug("updateStudent DAL {0}", student);

    Session session = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.saveOrUpdate(student);
      session.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Error updateStudent DAL {0}", student);
      if (session != null) {
        session.getTransaction().rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return student;
  }

  public Student getStudentById(Long id) {
    logger.debug("getStudentById DAL id = {0}", id);

    Session session = null;
    Student student = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      student = (Student) session.get(Student.class, id);
    } catch (Exception e) {
      logger.error("Error findStudentById DAL id = {0}", id);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return student;
  }

  public void deleteStudent(Long id) {
    logger.debug("deleteStudentById DAL id = {0}", id);

    Session session = null;
    Student student = new Student();

    student.setId(id);

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(student);
      session.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Error deleteStudent DAL id = {0}", id);
      if (session != null) {
        session.getTransaction().rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }
}
