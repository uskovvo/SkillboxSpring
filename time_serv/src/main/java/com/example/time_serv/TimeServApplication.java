package com.example.time_serv;

import com.example.time_serv.times.TimeProviderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@PropertySource("classpath:custom.properties") //подключение кастомного properties
@EnableConfigurationProperties(TimeProviderProperties.class)
public class TimeServApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeServApplication.class, args);
    }

}
