package com.example.springbootdockercomposemysql.repository;

import com.example.springbootdockercomposemysql.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByName(String name);

  Optional<User> findTopByOrderByIdDesc();
}
