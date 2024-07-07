package org.wu.dashboardapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wu.dashboardapi.common.response.ApiResponseDto;
import org.wu.dashboardapi.common.response.RtCode;
import org.wu.dashboardapi.dto.AuthenticationDto;
import org.wu.dashboardapi.dto.LoginDto;
import org.wu.dashboardapi.dto.MemberDto;
import org.wu.dashboardapi.service.impl.SignServiceImpl;

@Slf4j
@RestController
@RequestMapping(value = "tango/api")
@RequiredArgsConstructor
public class SignController {
  private final SignServiceImpl signService;

  @PostMapping(value = "admin/signup")
  public ApiResponseDto<MemberDto> signUp(@RequestBody MemberDto memberDto) {
    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

    MemberDto result = signService.signUp(null, memberDto);
    return new ApiResponseDto<>(RtCode.SUCCESS, result);
  }

  @PostMapping(value = "admin/signin")
  public ApiResponseDto<AuthenticationDto> signIn(@RequestBody LoginDto loginDto) {
    AuthenticationDto result = signService.signIn(loginDto);
    return new ApiResponseDto<>(RtCode.SUCCESS, result);
  }
}
