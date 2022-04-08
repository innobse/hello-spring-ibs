package ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learnup.ibs.hello.spring.hellospring.repository.RefreshTokenRepository;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.JwtService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.learnup.ibs.hello.spring.hellospring.filters.JwtAuthorizationFilter.getToken;

/**
 * Description
 *
 * @author bse71
 * Created on 08.04.2022
 * @since
 */
@RestController
public class AuthController {

    private JwtService jwtService;
    private UserDetailsService userService;

    @Autowired
    public AuthController(UserDetailsService userService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/api/token-refresh")
    public void authByRefreshToken(HttpServletRequest req, HttpServletResponse response) {
        final String token = getToken(req);
        final String username = jwtService.validateRefreshToken(token);

        if (username != null) {
            final UserDetails user = userService.loadUserByUsername(username);
            final String accessToken = jwtService.generateAccessToken(user);
            final String refreshToken = jwtService.generateRefreshToken(user);

            response.setHeader("access_token", accessToken);
            response.setHeader("refresh_token", refreshToken);
        }
    }
}
