package com.hrant.security;

import com.hrant.util.exception.JwtAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String tokenInHeader = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
//        try {
//            if (tokenInHeader != null && !tokenInHeader.equals("null") && jwtTokenProvider.validateJwtToken(tokenInHeader)) {
//                Authentication authentication = jwtTokenProvider.getAuthentication(tokenInHeader);
//                if (authentication != null) {
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        } catch (JwtAuthenticationException e) {
//            SecurityContextHolder.clearContext();
//            ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
//            LOGGER.error("JWT token is expired or invalid {}", e.getMessage());
//            throw new JwtAuthenticationException("JWT token is expired or invalid");
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenInHeader = jwtTokenProvider.resolveToken(request);
        try {
            if (tokenInHeader != null && !tokenInHeader.equals("null") && jwtTokenProvider.validateJwtToken(tokenInHeader)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(tokenInHeader);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
//            request.sendError(e.getHttpStatus().value());
            LOGGER.error("JWT token is expired or invalid {}", e.getMessage());
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/user");
    }
    //return "/auth/login".equals(request.getRequestURI())
}
