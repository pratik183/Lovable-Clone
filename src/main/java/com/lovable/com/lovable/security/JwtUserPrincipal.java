package com.lovable.com.lovable.security;


import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record JwtUserPrincipal(
        Long userId, // Internal user id from token
        String username, // Username from token subject
        List<GrantedAuthority> authorities // Granted authorities (currently empty)
){

}
