package com.fedag.recruitmentSystem.security.pojo;

import com.fedag.recruitmentSystem.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResopnse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;

    public JwtResopnse(String token, Long id, String firstname, String lastname, String email, Role role) {
        this.token = token;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
    }
}
