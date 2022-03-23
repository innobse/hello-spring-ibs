package ru.learnup.ibs.hello.spring.hellospring.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Description
 *
 * @author bse71
 * Created on 21.03.2022
 * @since
 */
public class CarRegistrationEvent extends ApplicationEvent {


    public CarRegistrationEvent(Info info) {
        super(info);
    }

    @Data
    @AllArgsConstructor
    public static class Info {

        private String serial;
    }
}
