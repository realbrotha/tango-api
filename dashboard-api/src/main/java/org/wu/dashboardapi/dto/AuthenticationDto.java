package org.wu.dashboardapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDto {
  private String accessToken;
  private String refreshToken;
}
