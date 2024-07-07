package org.wu.dashboardapi.common.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class CustomUserDetails implements UserDetails {
  private long id;
  private String username;
  private String password;
  private List<String> roles = new ArrayList<>();
  private String role;

  public CustomUserDetails(long id, String email, String role) {
    this.id = id;
    this.username = email;
    this.role = role;

    List<String> roleList = new ArrayList<>();
    roleList.add("ROLE_" + role);
    this.roles = roleList;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> roles = this.roles.stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    return roles;
  }
}
