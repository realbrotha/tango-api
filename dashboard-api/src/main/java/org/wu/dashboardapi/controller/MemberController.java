package org.wu.dashboardapi.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wu.dashboardapi.common.jwt.CustomUserDetails;
import org.wu.dashboardapi.common.response.ApiResponseDto;
import org.wu.dashboardapi.common.response.RtCode;
import org.wu.dashboardapi.dto.MemberDto;
import org.wu.dashboardapi.entity.Member;
import org.wu.dashboardapi.service.impl.MemberServiceImpl;

@Slf4j
@RestController
@RequestMapping(value = "tango/api")
@RequiredArgsConstructor
public class MemberController {
  private final MemberServiceImpl memberService;

  @GetMapping(value = "admin/member")
  public ApiResponseDto<List<MemberDto>> getList() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

    List<MemberDto> result = memberService.getList(user);
    return new ApiResponseDto<>(RtCode.SUCCESS, result);
  }


  @PutMapping(value = "admin/member")
  public ApiResponseDto<MemberDto> update(@RequestBody MemberDto memberDto) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

    MemberDto result = memberService.update(user, memberDto);
    return new ApiResponseDto<>(RtCode.SUCCESS, result);
  }

  @DeleteMapping(value = "admin/member/{memberId}")
  public ApiResponseDto<List<Member>> delete(@PathVariable(value = "memberId") String memberId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

    memberService.delete(user, memberId);
    return new ApiResponseDto<>(RtCode.SUCCESS);
  }
}
