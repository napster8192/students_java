package com.bom.sbt.bean;

import com.owlike.genson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student implements Serializable {
  private Long id;
  private String name;
  private Group group;
  private Date createdAt;
  private Date updatedAt;

  @Id
  @SequenceGenerator(name = "id_seq", sequenceName = "students_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "group_id")
  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  @PrePersist
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  protected void onCreate() {
    createdAt = new Date();
  }

  @PreUpdate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  protected void onUpdate() {
    updatedAt = new Date();
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (group != null ? group.hashCode() : 0);
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Student student = (Student) o;

    if (id != null ? !id.equals(student.id) : student.id != null) {
      return false;
    }
    if (name != null ? !name.equals(student.name) : student.name != null) {
      return false;
    }
    if (group != null ? !group.equals(student.group) : student.group != null) {
      return false;
    }
    if (createdAt != null ? !createdAt.equals(student.createdAt) : student.createdAt != null) {
      return false;
    }
    return updatedAt != null ? updatedAt.equals(student.updatedAt) : student.updatedAt == null;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name=" + name +
        ", Group{id=" + group.getId() +
        ", name=" + group.getName() + "}" +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
