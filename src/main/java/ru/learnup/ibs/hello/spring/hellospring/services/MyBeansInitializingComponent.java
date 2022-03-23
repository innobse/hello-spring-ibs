package ru.learnup.ibs.hello.spring.hellospring.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author bse71
 * Created on 21.03.2022
 * @since
 */
@Component
public class MyBeansInitializingComponent implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Начинаю инициализацию " + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Создание бина " + beanName + " завершено");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
