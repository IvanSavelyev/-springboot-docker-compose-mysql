package com.example.springbootdockercomposemysql.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.Assert;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(hidden = true)
  private Long id;

  @NotBlank
  @Size(min = 2, max = 100)
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @Size(min = 2, max = 100)
  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "email", nullable = false, unique = true)
  @Email
  @NotBlank
  @Size(max = 128)
  private String email;

  @Column(name = "password", nullable = false)
  @NotBlank
  @Size(max = 256)
  // https://stackoverflow.com/a/12505165/548473
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      uniqueConstraints = @UniqueConstraint(columnNames = {"user_id",
          "role"}, name = "uk_user_roles"))
  @Column(name = "role")
  @ElementCollection(fetch = FetchType.EAGER)
  @JoinColumn
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<Role> roles;

  @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
  @NotNull
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Date registered = new Date();

  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;

  public boolean hasRole(Role role) {
    return roles.contains(role);
  }

  public long id() {
    Assert.notNull(id, "Entity must have id");
    return id;
  }
}
