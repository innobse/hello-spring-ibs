package ru.learnup.ibs.hello.spring.hellospring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.learnup.ibs.hello.spring.hellospring.services.ConsoleLogger;
import ru.learnup.ibs.hello.spring.hellospring.services.ErrorLogger;
import ru.learnup.ibs.hello.spring.hellospring.services.EventLogger;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Logger;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
@Configuration
public class LoggerConfig {

    @Bean
    @Primary
    @Profile("!error")
    public Logger consoleLogger(Settings.ConsoleLoggerProps loggerSettings) {
        return new ConsoleLogger(
                loggerSettings.getLoggerPrefix());
    }

    @Bean
    @Profile("error")
    public Logger errorLogger() {
        return new ErrorLogger();
    }

    @Bean
    public Logger eventLogger() {
        return new EventLogger();
    }
}
