package com.example.backend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EntityListeners(AuditingEntityListener.class)
public class Subpost extends BaseEntity {
  private String name;
  private String description;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Post> posts;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
