package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedUserPage {

    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']//p")
    private WebElement welcomeInformation;

    private WebDriver driver;
    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public String getWelcomeInfo(){
        return welcomeInformation.getAttribute("textContent");
    }
}
