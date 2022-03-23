package ru.learnup.ibs.hello.spring.hellospring.services;

import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Logger;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
public class ErrorLogger implements Logger {

    @Override
    public void log(Object obj) {
        System.err.println("ERROR: " + obj);
    }
}
