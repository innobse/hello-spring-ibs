package ru.learnup.ibs.hello.spring.hellospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.learnup.ibs.hello.spring.hellospring.repository.InMemoryUserRepository;
import ru.learnup.ibs.hello.spring.hellospring.repository.UserRepository;
import ru.learnup.ibs.hello.spring.hellospring.security.User;
import ru.learnup.ibs.hello.spring.hellospring.security.UserService;

import java.util.Collections;

import static java.util.Arrays.asList;

/**
 * Description
 *
 * @author bse71
 * Created on 04.04.2022
 * @since
 */
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/api/v1/cars/*").hasRole("ADMIN")
//                .antMatchers("/hello", "/world/**").authenticated()
                .anyRequest().permitAll()

                .and()
                .formLogin()

                .and()
                .logout();
    }

    @Bean
    public UserDetailsService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile("in-memory")
    public InMemoryUserRepository userRepository(PasswordEncoder passwordEncoder) {
        final InMemoryUserRepository repo = new InMemoryUserRepository();
        repo.add(
                new User("admin", passwordEncoder.encode("admin123"), asList("ROLE_ADMIN", "ROLE_USER")));
        repo.add(
                new User("user", passwordEncoder.encode("user123"), Collections.singletonList("ROLE_USER")));

        return repo;
    }

}
