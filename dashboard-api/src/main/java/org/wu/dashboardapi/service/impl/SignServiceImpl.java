package org.wu.dashboardapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.wu.dashboardapi.common.exception.ExInternalServerErrorException;
import org.wu.dashboardapi.common.jwt.AuthProvider;
import org.wu.dashboardapi.common.jwt.CustomUserDetails;
import org.wu.dashboardapi.dao.MemberRepository;
import org.wu.dashboardapi.dto.AuthenticationDto;
import org.wu.dashboardapi.dto.LoginDto;
import org.wu.dashboardapi.dto.MemberDto;
import org.wu.dashboardapi.entity.Member;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignServiceImpl {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;
  private final AuthProvider authProvider;

  public MemberDto signUp(CustomUserDetails user, MemberDto memberDto) {
    memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
    log.info("member : {}", memberDto.toString());

    memberDto.setIsDelete(false);
    if (memberRepository.existsById(memberDto.getId())) {
      log.error("already is exist. id : {}", memberDto.getId());
      return null;
    }
    Member member = memberRepository.save(modelMapper.map(memberDto, Member.class));

    MemberDto result = modelMapper.map(member, MemberDto.class);
    return result;
  }
  public AuthenticationDto signIn(LoginDto loginDto) {
    Member member = memberRepository.findTop1ById(loginDto.getId()).orElseThrow(
        () -> {
          log.error("login failed. id wrong");
          throw new ExInternalServerErrorException("login failed.");
        }
    );
    if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
      log.error("login failed. password wrong");
      throw new ExInternalServerErrorException("login failed.");
    }

    String accessToken = authProvider.createToken(member.getId(), member.getRoles(), true, null);
    String refreshToken = authProvider.createToken(member.getId(), member.getRoles(), false, null);

    AuthenticationDto authenticationDto = new AuthenticationDto(accessToken, refreshToken);
    return authenticationDto;
  }
}
