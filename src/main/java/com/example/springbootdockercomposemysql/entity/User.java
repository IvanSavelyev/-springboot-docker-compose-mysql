package com.example.springbootdockercomposemysql.entity;

import com.example.springbootdockercomposemysql.mapper.Default;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

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
//  @Email
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
  @Temporal(TemporalType.DATE)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Date registered = new Date();

  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;

  @Default
  public User(Long id, String name, String email, String password) {
    this(id, name, email, "Random", password, true, new Date(), EnumSet.of(Role.USER));
  }

  public User(Long id, String name, String email, String country, String password, boolean enabled,
      Date registered, Collection<Role> roles) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.email = email;
    this.password = password;
    this.enabled = enabled;
    this.registered = registered;
    setRoles(roles);
  }

  public void setRoles(Collection<Role> roles) {
    this.roles =
        CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
  }

  public boolean hasRole(Role role) {
    return roles.contains(role);
  }

  public long id() {
    Assert.notNull(id, "Entity must have id");
    return id;
  }
}
