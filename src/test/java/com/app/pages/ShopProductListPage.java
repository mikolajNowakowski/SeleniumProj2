package com.app.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class ShopProductListPage {

    private WebDriver driver;

    public ShopProductListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ProductPage clickOnProductWithSpecifiedName(String productName) {
        driver.findElement(By.xpath("//h2[text() = '%s']".formatted(productName))).click();
        return new ProductPage(driver);
    }

}
