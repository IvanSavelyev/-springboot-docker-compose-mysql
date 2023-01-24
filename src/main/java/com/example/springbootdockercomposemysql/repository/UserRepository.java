package com.example.springbootdockercomposemysql.repository;

import com.example.springbootdockercomposemysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
