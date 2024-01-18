package com.app.config;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.*;

import org.springframework.test.context.TestPropertySource;

@Configuration
@TestPropertySource({"classpath:application_test.properties"})
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    @Scope("prototype")
    public ChromeDriver chromeDriver() {
        return new ChromeDriver();
    }
}
