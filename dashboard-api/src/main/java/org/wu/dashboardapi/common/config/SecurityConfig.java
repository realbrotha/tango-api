package org.wu.dashboardapi.common.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().disable()
        .authorizeRequests((authz) -> authz
            .anyRequest().permitAll()
        )
        .httpBasic(withDefaults());
    return http.build();
  }
}