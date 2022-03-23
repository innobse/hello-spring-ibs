package ru.learnup.ibs.hello.spring.hellospring.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 *
 * @author bse71
 * Created on 21.03.2022
 * @since
 */
@Configuration
@EnableConfigurationProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings {

    @Value("${spring.application.name}")
    private String applicationName;


    @Configuration
    @ConfigurationProperties(prefix = "my-config.loggers.console")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConsoleLoggerProps {

        @Value("${prefix:LOG }")
        private String loggerPrefix;

        @Value("${postfix: }")
        private String loggerPostfix;
    }
}
