package ru.learnup.ibs.hello.spring.hellospring.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description
 *
 * @author bse71
 * Created on 08.04.2022
 * @since
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService, AuthenticationManager authenticationManager1) {
        super(authenticationManager);
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager1;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, null));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails user = (UserDetails) authResult.getPrincipal();
        final String accessToken = jwtService.generateAccessToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);

        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
    }
}
