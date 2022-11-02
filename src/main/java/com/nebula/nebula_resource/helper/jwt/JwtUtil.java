package com.nebula.nebula_resource.helper.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RefreshScope
@Service
public class JwtUtil {
    private final Integer accessExpireSecond;
    private final Key key;
    private final String headerKey;
    private final String prefix;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.access-token-validity-in-seconds}") int configExpireTime,
                   @Value("${jwt.header}")String headerKey,
                   @Value("${jwt.prefix}") String prefix) {
        this.accessExpireSecond = configExpireTime;
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.headerKey = headerKey;
        this.prefix = prefix;
    }

    /** token 에서 username 추출 */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /** token 에서 만료 시간 추출 */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /** token Claim 에서 권한정보 가져오기 */
    public List<? extends GrantedAuthority> extractGrantedAuthority(String token){
        Claims claims = extractAllClaims(token);
        if(claims.get("roles")==null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        return Arrays.stream(claims.get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /** token 에서 clameResolver(function) 를 통해 Claim 추출 -> 토큰의 subject 와 expiration 추출 시 사용 */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /** token 의 Claim body 추출 */
    private Claims extractAllClaims(String token) {
        try {
            //Jwts parser를 최근 버전의 빌드패턴인 parserBuilder 로 변경하여 사용
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    /** token Claim 의 expiration이 현재 시간보다 이전인지 확인  */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /** 토큰의 유효성 검사 */
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            // 토큰 파싱에서 오류가 발생하지 않는지 체크
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            // 유저네임을 추출하여 해당 유저의 정보와 맞는지 && 토큰이 만료되었는지 체크
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new RuntimeException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT 토큰이 잘못되었습니다.");
        }
    }

    public String getHeaderKey(){
        return this.headerKey;
    }

    public String getPrefix(){
        return this.prefix;
    }
}