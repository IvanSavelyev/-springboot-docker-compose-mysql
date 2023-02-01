package com.example.springbootdockercomposemysql.web;

import com.example.springbootdockercomposemysql.entity.Role;
import lombok.Getter;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {

  private final com.example.springbootdockercomposemysql.entity.User user;

  public AuthUser(com.example.springbootdockercomposemysql.entity.User user) {
    super(user.getName(), user.getPassword(), user.getRoles());
    this.user = user;
  }

  public long id() {
    return user.id();
  }

  public boolean hasRole(Role role) {
    return user.hasRole(role);
  }

  @Override
  public String toString() {
    return "AuthUser:" + user.getId() + '[' + user.getEmail() + ']';
  }
}
