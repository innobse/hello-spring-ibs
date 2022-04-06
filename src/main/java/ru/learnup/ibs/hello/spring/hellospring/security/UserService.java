package ru.learnup.ibs.hello.spring.hellospring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.learnup.ibs.hello.spring.hellospring.repository.UserRepository;

/**
 * Description
 *
 * @author bse71
 * Created on 04.04.2022
 * @since
 */
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User byUsername = userRepository.findByUsername(username);
        if (byUsername == null) throw new UsernameNotFoundException("Нет такого пользователя!");
        return byUsername;
    }
}
