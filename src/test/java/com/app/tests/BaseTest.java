package com.app.tests;

import com.app.config.AppConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = AppConfig.class)
@TestPropertySource("classpath:application_test.properties")

public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    protected ApplicationContext applicationContext;
    public WebDriver webDriver;

    @BeforeMethod
    public void setup() {
        webDriver = applicationContext.getBean(ChromeDriver.class);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("http://seleniumdemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

    public Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }
}
