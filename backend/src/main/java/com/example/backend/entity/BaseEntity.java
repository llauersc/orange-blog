package com.example.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @CreatedDate
  private Date createdAt;

  @LastModifiedDate
  private Date updatedAt;

  @PreUpdate
  public void setChangeDate() {
    this.updatedAt = new Date();
  }
}