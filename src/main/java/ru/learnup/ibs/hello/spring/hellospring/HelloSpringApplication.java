package ru.learnup.ibs.hello.spring.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.CarService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
//@EnableJpaRepositories
//@EntityScan({"ru.learnup.ibs.hello.spring.hellospring.model"})
public class HelloSpringApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(HelloSpringApplication.class, args);

        final CarService carService = ctx.getBean(CarService.class);

//        final ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(() -> {
//            carService.updateVin("TX 1");
//        });
//        executorService.submit(() -> {
//            carService.updateVin("TX 2");
//        });

        carService.updateVin("PROGRAMM");
    }

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
