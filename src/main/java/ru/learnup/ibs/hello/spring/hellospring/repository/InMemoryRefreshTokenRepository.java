package ru.learnup.ibs.hello.spring.hellospring.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description
 *
 * @author bse71
 * Created on 08.04.2022
 * @since
 */
@Repository
@Profile("in-memory")
public class InMemoryRefreshTokenRepository implements RefreshTokenRepository {

    private final Map<String, String> storage = new ConcurrentHashMap<>();


    @Override
    public void newToken(String username, String newRefreshToken) {
        storage.put(username, newRefreshToken);
    }

    @Override
    public boolean checkAndDelete(String username, String refreshToken) {
        final boolean contains = refreshToken.equals(storage.get(username));
        if (contains)
            storage.remove(username, refreshToken);
        return contains;
    }
}
