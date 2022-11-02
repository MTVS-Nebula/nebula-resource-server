package com.nebula.nebula_resource.helper.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username);
    public UserDetails loadUserByToken(String token);

}
