package ru.learnup.ibs.hello.spring.hellospring.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Description
 *
 * @author bse71
 * Created on 08.04.2022
 * @since
 */
public interface JwtService {

    //  Authorization
    String generateAccessToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
    String validateRefreshToken(String token);

    //  Service
    boolean validateTokenAndPutSecurityContext(String token);
}
