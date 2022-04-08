package ru.learnup.ibs.hello.spring.hellospring.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.learnup.ibs.hello.spring.hellospring.repository.RefreshTokenRepository;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.JwtService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Description
 *
 * @author bse71
 * Created on 08.04.2022
 * @since
 */
@Component
public class JwtServiceImpl implements JwtService {

    private static final long TIMEOUT_ACCESS = 1000L * 60 * 15;
    private static final long TIMEOUT_REFRESH = 1000L * 24 * 3600 * 15;

    private final RefreshTokenRepository refreshTokenRepository;
    private final Algorithm algo;
    private final JWTVerifier verifier;

    @Autowired
    public JwtServiceImpl(RefreshTokenRepository refreshTokenRepository, @Value("${my-config.auth.secret:secret}") String secret) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.algo = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algo).build();
    }

    @Override
    public String generateAccessToken(UserDetails user) {

        final List<String> roleNames = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList());

        return JWT.create()
                .withIssuer("IBS")
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TIMEOUT_ACCESS))

                .withClaim("roles", roleNames)

                .sign(algo);
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        final String refreshToken = JWT.create()
                .withIssuer("IBS")
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TIMEOUT_REFRESH))

                .sign(algo);
        refreshTokenRepository.newToken(user.getUsername(), refreshToken);
        return refreshToken;
    }

    @Override
    public boolean validateTokenAndPutSecurityContext(String token) {
        try {
            final DecodedJWT decodedJwt = verifier.verify(token);
            final String username = decodedJwt.getSubject();
            final List<String> roleNames = decodedJwt.getClaim("roles").asList(String.class);

            final List<SimpleGrantedAuthority> roles = roleNames.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(toList());

            final SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(
                    new UsernamePasswordAuthenticationToken(username, null, roles));
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public String validateRefreshToken(String token) {
        try {
            final DecodedJWT decodedJwt = verifier.verify(token);
            final String username = decodedJwt.getSubject();

            if (refreshTokenRepository.checkAndDelete(username, token)) {
                return username;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }
}
