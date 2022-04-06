package ru.learnup.ibs.hello.spring.hellospring.repository;

import ru.learnup.ibs.hello.spring.hellospring.security.User;

/**
 * Description
 *
 * @author bse71
 * Created on 04.04.2022
 * @since
 */
public interface UserRepository {

    User findByUsername(String username);
    void add(User user);

}
