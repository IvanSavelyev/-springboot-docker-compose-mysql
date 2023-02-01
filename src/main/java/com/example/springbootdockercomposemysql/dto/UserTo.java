package com.example.springbootdockercomposemysql.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserTo(@Schema(hidden = true) Integer id,
                     @NotBlank @Size(min = 2, max = 128) String name,
                     @Email @NotBlank @Size(max = 128) String email,
                     @NotBlank @Size(min = 5, max = 32) String password) {

  public UserTo(Integer id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserTo:" + id + '[' + email + ']';
  }
}
