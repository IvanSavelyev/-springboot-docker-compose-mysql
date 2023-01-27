package com.example.springbootdockercomposemysql.controller;

import com.example.springbootdockercomposemysql.entity.User;
import com.example.springbootdockercomposemysql.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @PostMapping("/users")
  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }

  @GetMapping("/create")
  public List<User> users() {
    var users = new User();
    users.setId(1L);
    users.setName("Sam");
    users.setCountry("Development");
    userRepository.save(users);
    return userRepository.findAll();
  }

  @GetMapping("/users")
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @PutMapping("/users/{user_id}")
  public User update(@PathVariable("user_id") Long userId, @RequestBody User userObject) {
    var user = userRepository.findById(userId).get();
    user.setName(userObject.getName());
    user.setCountry(userObject.getCountry());
    return userRepository.save(user);
  }

  @DeleteMapping("/users/{user_id}")
  public List<User> delete(@PathVariable("user_id") Long userId) {
    userRepository.deleteById(userId);
    return userRepository.findAll();
  }

  @GetMapping("/users/{user_id}")
  @ResponseBody
  public User findByUserId(@PathVariable("user_id") Long userId) {
    return userRepository.findById(userId).get();
  }
}
