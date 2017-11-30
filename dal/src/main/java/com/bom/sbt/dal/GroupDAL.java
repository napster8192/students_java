package com.bom.sbt.dal;

import com.bom.sbt.bean.Group;
import com.bom.sbt.bean.Student;
import com.bom.sbt.dal.utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class GroupDAL {
  private static final Logger logger = LogManager.getLogger(GroupDAL.class);

  public List<Group> getGroups() {
    logger.debug("getGroups DAL");

    Session session = null;
    List groups = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      groups = (List<Group>) session.createQuery("FROM Group").list();
    } catch (Exception e) {
      logger.error("Error getGroups DAL");
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return groups;
  }

  public Group createGroup(Group group) {
    logger.debug("createGroup DAL {0}", group);

    Session session = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(group);
      session.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Error createGroup DAL {0}", group);
      if (session != null) {
        session.getTransaction().rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return group;
  }

  public Group updateGroup(Group group) {
    logger.debug("updateGroup DAL {0}", group);

    Session session = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.saveOrUpdate(group);
      session.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Error updateGroup DAL {0}", group);
      if (session != null) {
        session.getTransaction().rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return group;
  }

  public Group getGroupById(Long id) {
    logger.debug("getGroupById DAL id = {0}", id);

    Session session = null;
    Group group = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      group = (Group) session.get(Group.class, id);
    } catch (Exception e) {
      logger.error("Error findGroupById DAL id = {0}", id);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return group;
  }

  public void deleteGroup(Long id) {
    logger.debug("deleteGroupById DAL id = {0}", id);

    Session session = null;
    Group group = new Group();

    group.setId(id);

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(group);
      session.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Error deleteGroup DAL id = {0}", id);
      if (session != null) {
        session.getTransaction().rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public List<Student> getStudentsByGroupId(Long id) {
    logger.debug("getStudentsByGroupId DAL id = {0}", id);

    Session session = null;
    Group group = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      group = (Group) session.get(Group.class, id);
    } catch (Exception e) {
      logger.error("Error getStudentsByGroupId DAL id = {0}", id);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return group != null ? group.getStudents() : null;
  }

  public Group addStudent(Long groupId, Long studentId) {
    logger.debug("addStudent DAL {0} to Group {1}", studentId, groupId);

    Session session = null;
    Group group = null;
    Student student = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();
      group = (Group) session.get(Group.class, groupId);
      student = (Student) session.get(Student.class, studentId);
      group.addStudent(student);
      session.beginTransaction();
      session.save(group);
      session.getTransaction().commit();
    } catch (Exception e) {
      logger.error("addStudent DAL {0} to Group {1}", studentId, groupId);
      if (session != null) {
        session.getTransaction().rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }

    return group;
  }
}
