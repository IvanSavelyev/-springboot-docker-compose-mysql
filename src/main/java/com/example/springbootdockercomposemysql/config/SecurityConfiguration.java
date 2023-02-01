package com.example.springbootdockercomposemysql.config;

import com.example.springbootdockercomposemysql.entity.User;
import com.example.springbootdockercomposemysql.repository.UserRepository;
import com.example.springbootdockercomposemysql.web.AuthUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Slf4j
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

  public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  private final UserRepository userRepository;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PASSWORD_ENCODER;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      Optional<User> optionalUser = userRepository.findByName(username);
      return new AuthUser(optionalUser.orElseThrow(
          () -> new UsernameNotFoundException("User: " + username + "was not found")));
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/", "/v3/**", "/swagger-ui/**").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/profile").anonymous()
        .requestMatchers(HttpMethod.GET, "/api/profile").anonymous()
        .requestMatchers("/api/**").authenticated()
        .and().httpBasic()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().csrf().disable();
    return http.build();
  }
}
