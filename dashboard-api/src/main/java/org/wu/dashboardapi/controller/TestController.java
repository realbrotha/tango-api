package org.wu.dashboardapi.controller;

import org.wu.dashboardapi.common.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wu.dashboardapi.common.response.RtCode;

@Slf4j
@RestController
@RequestMapping(value = "tango/api")
@RequiredArgsConstructor
public class TestController {
    @GetMapping(value = "health")
    public ApiResponseDto<Boolean> test() {
        log.info("echo");
        return new ApiResponseDto<>(RtCode.SUCCESS, true);
    }
}
