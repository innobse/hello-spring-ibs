package ru.learnup.ibs.hello.spring.hellospring.services;

import org.springframework.stereotype.Component;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Logger;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
public class ConsoleLogger implements Logger {

    private final String prefix;

    public ConsoleLogger(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void log(Object obj) {
        System.out.println(prefix + obj);
    }
}
