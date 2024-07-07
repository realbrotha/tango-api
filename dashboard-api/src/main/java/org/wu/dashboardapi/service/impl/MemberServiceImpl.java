package org.wu.dashboardapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.wu.dashboardapi.common.exception.ExInternalServerErrorException;
import org.wu.dashboardapi.common.exception.ExUnauthorizedException;
import org.wu.dashboardapi.common.jwt.CustomUserDetails;
import org.wu.dashboardapi.dao.MemberRepository;
import org.wu.dashboardapi.dto.MemberDto;
import org.wu.dashboardapi.entity.Member;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl {
  private final MemberRepository memberRepository;
  private final ModelMapper modelMapper;

  public List<MemberDto> getList(CustomUserDetails user) {
    if (!user.getRoles().equals("ADMIN")) {
      log.error("not admin");
      throw new ExUnauthorizedException("not allowed");
    }
    List<Member> memberList = memberRepository.findAll();
    List<MemberDto> memberDtoList = memberList.stream().map(p -> modelMapper.map(p, MemberDto.class)).collect(Collectors.toList());
    return memberDtoList;
  }

  public MemberDto update(CustomUserDetails user, MemberDto memberDto) {
    if (!user.getRoles().equals("ADMIN")) {
      log.error("not admin");
      throw new ExUnauthorizedException("not allowed");
    }
    Member member = memberRepository.findById(memberDto.getId()).orElseThrow(
        () -> {
          log.error("not exist member id : {}", memberDto.getId());
          throw new ExInternalServerErrorException("not exist member. id :" + memberDto.getId());
        }
    );
    member = modelMapper.map(memberDto, Member.class);

    member = memberRepository.save(member);
    MemberDto result = modelMapper.map(member, MemberDto.class);
    return result;
  }
  public void delete(CustomUserDetails user, String memberId) {
    if (!user.getRoles().equals("ADMIN")) {
      log.error("not admin");
      throw new ExUnauthorizedException("not allowed");
    }
    memberRepository.deleteById(memberId);
  }
}
