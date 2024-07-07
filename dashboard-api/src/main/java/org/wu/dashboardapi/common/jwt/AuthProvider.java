package org.wu.dashboardapi.common.jwt;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import io.jsonwebtoken.*;
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthProvider {
  private static final String BEARER_TYPE = "bearer ";
  private static final String AUTHORITIES_KEY = "auth";
  private static final String ACCESS_USER_ID = "id";
  private static final String COMPANY_ID = "companyid";

  /*
  jwt.signature : worldstarHQH!@#ThisisPWDISNT
jwt.token_expired :60000
jwt.refresh_token_expired : 43200000
   */
  @Value("${jwt.token_expired}")
  private long TOKEN_EXPIRE_TIME;

  @Value("${jwt.signature}")
  private String SIGNATURE_KEY;

  @Value("${jwt.refresh_token_expired}")
  private long REFRESH_TOKEN_EXPIRE_TIME;

  @PostConstruct
  protected void init() {
    SIGNATURE_KEY = Base64.getEncoder().encodeToString(SIGNATURE_KEY.getBytes());
  }

  public String createToken(String id, String role, boolean isAccessToken, Date expired) {
//  public String createToken(Long id, String username, String role, boolean isAccessToken, Date expired) {
    long now = (new Date()).getTime();
    Date accessTokenExpiresIn;

    if (isAccessToken) {
      accessTokenExpiresIn = new Date(now + TOKEN_EXPIRE_TIME);
    } else {
      if (ObjectUtils.isEmpty(expired)) {
        accessTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
      } else {
        accessTokenExpiresIn = expired;
      }
    }
    final JwtBuilder builder = Jwts.builder()
        .setSubject(id)
        .setExpiration(accessTokenExpiresIn)
        .claim(AUTHORITIES_KEY, role)
        //.claim(ACCESS_USER_ID, id)
        .signWith(SignatureAlgorithm.HS256, SIGNATURE_KEY);
    return BEARER_TYPE + builder.compact();
  }

  public String getUserPk(String token) {
    return Jwts.parser().setSigningKey(SIGNATURE_KEY).parseClaimsJws(token).getBody().getSubject();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser().setSigningKey(SIGNATURE_KEY).parseClaimsJws(token).getBody();

    String username = claims.getSubject();
    long id = claims.get(ACCESS_USER_ID, Integer.class);
    String role = claims.get(AUTHORITIES_KEY, String.class);

    CustomUserDetails userDetails = new CustomUserDetails(id, username, role);
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }
  public Long getUserIdFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(SIGNATURE_KEY).parseClaimsJws(token).getBody();

    String username = claims.getSubject();
    long id = claims.get(ACCESS_USER_ID, Integer.class);
    String role = claims.get(AUTHORITIES_KEY, String.class);
    return id;
  }
  public String resolveToken(HttpServletRequest request) {
    if (request.getHeader("token") == null) {
      return null;
    }
    return request.getHeader("token").replace(BEARER_TYPE, "");
  }
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(SIGNATURE_KEY).parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      log.error("validate token failed. token {}", token);
      return false;
    }
  }
  public Date getExpirationDateFromJWT(String token) {
    Jws<Claims> claims = Jwts.parser().setSigningKey(SIGNATURE_KEY).parseClaimsJws(token);
    Date result = claims.getBody().getExpiration();
    return result;
  }
}
