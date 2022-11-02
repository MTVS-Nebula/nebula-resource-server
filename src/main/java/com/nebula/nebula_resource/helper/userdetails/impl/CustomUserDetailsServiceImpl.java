package com.nebula.nebula_resource.helper.userdetails.impl;

import com.nebula.nebula_resource.app.dao.entity.User;
import com.nebula.nebula_resource.app.dao.repository.UserRepository;
import com.nebula.nebula_resource.helper.jwt.JwtUtil;
import com.nebula.nebula_resource.helper.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    /** username 으로 UserDetails 객체 반환*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), null
        );
    }

    /** token으로 UserDetails 객체 반환*/
    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        String username = jwtUtil.extractUsername(token);
        List<GrantedAuthority> authorities = jwtUtil.extractGrantedAuthority(token);

        //db에 회원 정보가 없을 경우 에러 반환
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities
        );
    }
}

