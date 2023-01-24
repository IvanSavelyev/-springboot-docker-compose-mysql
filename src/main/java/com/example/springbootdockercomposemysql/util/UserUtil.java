package com.example.springbootdockercomposemysql.util;

import com.example.springbootdockercomposemysql.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

  public List<User> createUserGroup() {
    var users = new ArrayList<User>();
    for (int i = 0; i < 100; i++) {
      var user = new User();
      user.setCountry("Pioneer_" + i);
      user.setName("Ivan_" + i);
      users.add(user);
    }
    return users;
  }

}
