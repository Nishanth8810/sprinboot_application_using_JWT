package com.exmaple.project.JWT.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JwtResponse {
    private User user;
    private String jwtToken;

    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }


}
