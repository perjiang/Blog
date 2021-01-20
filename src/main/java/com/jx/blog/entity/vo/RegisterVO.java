package com.jx.blog.entity.vo;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterVO {
    private String username;
    private String password;
    private String inviteCode;
}
