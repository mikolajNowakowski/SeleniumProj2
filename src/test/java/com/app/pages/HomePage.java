package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    @FindBy(xpath = "//span[text() = 'My account']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//li//span[text() = 'Shop']")
    private List<WebElement> shopLink;

    private WebDriver driver;
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public ShopProductListPage clickShopLink(){
        shopLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        return new ShopProductListPage(driver);
    }

    public LoginRegistrationPage myAccountLinkClick(){
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        return new LoginRegistrationPage(driver);
    }
}
