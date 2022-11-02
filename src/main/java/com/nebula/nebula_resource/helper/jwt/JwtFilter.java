package com.nebula.nebula_resource.helper.jwt;

import com.nebula.nebula_resource.helper.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final String headerKey;
    private final String prefix;


    @Autowired
    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.headerKey = jwtUtil.getHeaderKey();
        this.prefix = jwtUtil.getPrefix();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("do filter");
            String token = resolveToken(httpServletRequest);
            setSecurityAuthentication(token);
        } catch (RuntimeException e){
//            throw new RuntimeException("Security Filter 오류");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /** Request Header에서 토큰 정보 꺼내오기*/
    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(headerKey);
        if(bearerToken.startsWith(prefix + " ")){
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    /** SecurityContextHolder 의 Authentication setting */
    private void setSecurityAuthentication(String token) {
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        getUsernamePasswordAuthenticationToken(token)
                );
    }

    /** Jwt token 으로 UsernamePasswordAuthenticationToken 만들기 */
    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token){
        try {
            UserDetails userDetails = userDetailsService.loadUserByToken(token);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } catch (RuntimeException e){
            throw e;
        }
    }
}
