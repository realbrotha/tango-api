package org.wu.dashboardapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "members")
@Data
public class Member {
  @Id
  @Column(length = 100, nullable = false)
  private String id;

  @Column(length = 100, nullable = false)
  private String password;

  @Column
  private String roles;

  @Column
  private Boolean isDelete;
}
