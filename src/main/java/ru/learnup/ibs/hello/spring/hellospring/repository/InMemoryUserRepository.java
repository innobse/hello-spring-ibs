package ru.learnup.ibs.hello.spring.hellospring.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.learnup.ibs.hello.spring.hellospring.security.User;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Description
 *
 * @author bse71
 * Created on 04.04.2022
 * @since
 */
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void add(User user) {
        users.put(user.getUsername(), user);
    }
}
