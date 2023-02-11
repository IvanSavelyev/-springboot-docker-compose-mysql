package com.example.springbootdockercomposemysql;

import com.example.springbootdockercomposemysql.entity.User;
import com.example.springbootdockercomposemysql.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("postgres")
public class UserRepositoryTest extends DatabaseTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void checkInitUsersInBd() {
    List<User> all = userRepository.findAll();
    Assertions.assertNotNull(all);
  }
}
