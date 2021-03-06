package com.example.todo.config.jwt;

import com.example.todo.config.principal.UserPrincipal;
import com.example.todo.config.principal.UserPrincipalService;
import com.example.todo.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

// JWT 생성하고 검증하는 클래스
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final MemberRepository memberRepository;
    private final UserPrincipalService userPrincipalService;

    private String secretKey = "asfdasfdsafsfsa";
    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    // 객체 초기화 , secretKey를 Base64로 인코딩
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payLoad에 저장되는 정보단위
//        claims.put("roles", roles); // 정보는 key : value 형태로 추가됨
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 저장할 claim(조각)
                .setIssuedAt(now) // 발행시간
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰 만료시간
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 secretkey
                .compact(); // 생성
    }

    // JWT 토큰에서 인증정보 조회
    public Authentication getAuthentication(String token) {
        UserPrincipal findMember = (UserPrincipal) userPrincipalService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(findMember, "", null);
    }

    // 토큰에서 회원정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token값을 가져온다 "X-AUTH-TOKEN":"TOKEN값"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("x-auth-token"); // Header의 X-AUTH-TOKEN 값 가져온다
    }

    // 토큰의 유효성 및 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
