package ru.learnup.ibs.hello.spring.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.CarService;

import java.util.Collection;

@SpringBootApplication
//@EnableJpaRepositories
//@EntityScan({"ru.learnup.ibs.hello.spring.hellospring.model"})
public class HelloSpringApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(HelloSpringApplication.class, args);

        final Collection<CarEntity> azlkCars = ctx.getBean(CarService.class)
                .getAllSort("Daewoo", "Matiz");

        for (CarEntity availableCar : azlkCars) {
            System.out.println(availableCar);
        }
    }

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
