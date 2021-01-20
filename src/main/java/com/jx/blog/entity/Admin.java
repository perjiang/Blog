package com.jx.blog.entity;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer id;
    private String username;
    private String salt;
    private String password;
    private List<Role> roles;


}
