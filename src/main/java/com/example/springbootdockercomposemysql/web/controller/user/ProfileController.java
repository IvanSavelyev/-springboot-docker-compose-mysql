package com.example.springbootdockercomposemysql.web.controller.user;

import com.example.springbootdockercomposemysql.dto.UserTo;
import com.example.springbootdockercomposemysql.entity.User;
import com.example.springbootdockercomposemysql.error.IllegalRequestDataException;
import com.example.springbootdockercomposemysql.mapper.UserMapper;
import com.example.springbootdockercomposemysql.util.UsersUtil;
import com.example.springbootdockercomposemysql.web.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

  private final UserMapper userMapper;

  static final String REST_URL = "/api/profile";

  //Getting auth user
  @GetMapping
  public User get(@AuthenticationPrincipal AuthUser authUser) {
    log.info("get {}", authUser);
    return authUser.getUser();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
    log.info("register {}", userTo);
    checkNew(userTo);
    var created = UsersUtil.prepareToSave(userMapper.toEntity(userTo));
    var uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path(REST_URL).build().toUri();
    return ResponseEntity.created(uriOfNewResource).body(created);
  }

  public void checkNew(UserTo userTo) {
    if (userTo.id() != null) {
      throw new IllegalRequestDataException(
          userTo.getClass().getSimpleName() + " must be new (id=null)");
    }
  }
}
