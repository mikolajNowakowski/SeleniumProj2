package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    @FindBy(name = "add-to-cart")
    private WebElement addToCarButton;

    @FindBy(xpath = "//a[text() = 'View cart']")
    private List<WebElement> viewCartButton;

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ProductPage addProductToCart() {
        addToCarButton.click();
        return this;
    }

    public CartPage goToCart() {
        viewCartButton.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        return new CartPage(driver);
    }

}
