package ru.learnup.ibs.hello.spring.hellospring.repository;

/**
 * Description
 *
 * @author bse71
 * Created on 08.04.2022
 * @since
 */
public interface RefreshTokenRepository {

    void newToken(String username, String newRefreshToken);
    boolean checkAndDelete(String username, String refreshToken);
}
