package com.bom.sbt.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group implements Serializable {
  private Long id;
  private String name;
  private List<Student> students = new ArrayList<>();
  private Date createdAt;
  private Date updatedAt;

  @Id
  @SequenceGenerator(name = "id_seq", sequenceName = "groups_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public void addStudent(Student student) {
    student.setGroup(this);
    this.students.add(student);
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

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (students != null ? students.hashCode() : 0);
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

    Group group = (Group) o;

    if (id != null ? !id.equals(group.id) : group.id != null) {
      return false;
    }
    if (name != null ? !name.equals(group.name) : group.name != null) {
      return false;
    }
    if (students != null ? !students.equals(group.students) : group.students != null) {
      return false;
    }
    if (createdAt != null ? !createdAt.equals(group.createdAt) : group.createdAt != null) {
      return false;
    }
    return updatedAt != null ? updatedAt.equals(group.updatedAt) : group.updatedAt == null;
  }

  @Override
  public String toString() {
    return "Group{" +
        "id=" + id +
        ", name=" + name +
        ", students=" + students +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
