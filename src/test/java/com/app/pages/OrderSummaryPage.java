package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryPage {
    @FindBy(xpath = "//div[@class='woocommerce-order']/p")
    private WebElement orderNotice;

    @FindBy(xpath = "//td[@class = 'woocommerce-table__product-name product-name']/a")
    private WebElement productName;

    private WebDriver driver;

    public OrderSummaryPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public WebElement getOrderNotice() {
        return orderNotice;
    }

    public WebElement getProductName() {
        return productName;
    }
}
