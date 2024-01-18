package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    @FindBy(xpath = "//tr[@class='woocommerce-cart-form__cart-item cart_item']")
    private List<WebElement> productsInCart;

    @FindBy(xpath = "//div[@class = 'wc-proceed-to-checkout']//a")
    private WebElement goToCheckoutButton;

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public CheckoutPage goToCheckout(){
        goToCheckoutButton.click();
        return new CheckoutPage(driver);
    }
}
