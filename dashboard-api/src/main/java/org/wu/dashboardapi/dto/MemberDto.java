package org.wu.dashboardapi.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String id;
    private String password;
    private String roles;
    private Boolean isDelete;
}
