package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginRegistrationPage {
    @FindBy(id = "reg_email")
    private WebElement regEmailInput;

    @FindBy(id = "reg_password")
    private WebElement regPasswordInput;

    @FindBy(name = "register")
    private WebElement registerButton;

    @FindBy(xpath = "//ul[@class = 'woocommerce-error']//li" )
    private WebElement errorInfo;

    private WebDriver driver;
    public LoginRegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public LoggedUserPage registerUserCorrectData(String email, String password){
        fillRegisterInfo( email,  password);
        return new LoggedUserPage(driver);
    }

    public LoginRegistrationPage registerUserIncorrectData(String email, String password){
        fillRegisterInfo( email,  password);
        return this;
    }

    public String getErrorInfo(){
        return errorInfo.getAttribute("textContent");
    }

    public boolean isRegisterButtonEnabled(){
        return registerButton.isEnabled();
    }

    private void fillRegisterInfo(String email, String password){
        regEmailInput.sendKeys(email);
        regPasswordInput.sendKeys(password);
        registerButton.click();
    }
}
