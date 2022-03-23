package ru.learnup.ibs.hello.spring.hellospring.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import ru.learnup.ibs.hello.spring.hellospring.events.CarRegistrationEvent;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Logger;

import java.util.Locale;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
public class EventLogger implements ApplicationListener<CarRegistrationEvent>, Logger, ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void log(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void onApplicationEvent(CarRegistrationEvent event) {
        final String targetSerial = ((CarRegistrationEvent.Info) event.getSource()).getSerial();
//        Locale locale = Locale.getDefault();
        Locale locale = Locale.ITALY;


        String msg = ctx.getMessage("newcarregister", new Object[]{targetSerial}, locale);
        log(msg);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
