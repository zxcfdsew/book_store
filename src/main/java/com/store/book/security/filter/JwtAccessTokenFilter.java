package com.store.book.security.filter;

import com.store.book.entity.User;
import com.store.book.repository.UserMapper;
import com.store.book.security.jwt.JwtProvider;
import com.store.book.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAccessTokenFilter extends GenericFilter {

    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String bearerAccessToken = request.getHeader("Authorization");

        if (bearerAccessToken == null || bearerAccessToken.isBlank()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String accessToken = jwtProvider.removeBearer(bearerAccessToken);

        Claims claims = null;
        try {
            claims = jwtProvider.getClaims(accessToken);
            Long userId = ((Integer) claims.get("userId")).longValue();
            User user = userMapper.findById(userId);

            if (user == null) {
                throw new JwtException("존재하지 않는 사용자입니다.");
            }
            PrincipalUser principalUser = user.toPrincipal();
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalUser, null, AuthorityUtils.NO_AUTHORITIES);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.info("토근 검증 실패");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
