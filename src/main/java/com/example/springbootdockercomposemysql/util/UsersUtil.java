package com.example.springbootdockercomposemysql.util;

import static com.example.springbootdockercomposemysql.config.SecurityConfiguration.PASSWORD_ENCODER;

import com.example.springbootdockercomposemysql.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsersUtil {

  public static User prepareToSave(User user) {
    user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
    user.setEmail(user.getEmail().toLowerCase());
    return user;
  }
}