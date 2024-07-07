package org.wu.dashboardapi.common.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.wu.dashboardapi.common.jwt.AuthProvider;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
  private AuthProvider jwtTokenProvider;

  public JwtFilter(AuthProvider jwtTokenProvider) { this.jwtTokenProvider = jwtTokenProvider; }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpRes = (HttpServletResponse) request;

    try {
      if ("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
        httpRes.setStatus(HttpServletResponse.SC_OK);
      } else {
        String token = jwtTokenProvider.resolveToken(httpReq);
        if (null != token) {
          if (jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
        }
        filterChain.doFilter(request, response);
      }
    } catch (Exception e) {
      logger.error("filter exception");
    }
  }
}
