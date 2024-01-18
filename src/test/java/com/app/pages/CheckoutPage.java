package com.app.pages;

import com.app.model.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    @FindBy(name = "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(name = "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(name = "billing_company")
    private WebElement companyInput;

    @FindBy(id = "billing_country")
    private WebElement billingCountrySelect;

    @FindBy(id = "billing_address_1")
    private WebElement billingAddressInput;
    @FindBy(id = "billing_address_2")
    private WebElement additionalBillingAddressInput;

    @FindBy(name = "billing_postcode")
    private WebElement billingPostcodeInput;

    @FindBy(name = "billing_city")
    private WebElement billingCityInput;

    @FindBy(name = "billing_phone")
    private WebElement billingPhoneInput;

    @FindBy(name = "billing_email")
    private WebElement billingEmailInput;

    @FindBy(id = "order_comments")
    private WebElement additionalInfoInput;

    @FindBy(xpath = "//button[@id = 'place_order']")
    private WebElement placeOrderButton;


    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public OrderSummaryPage provideCheckoutData(Customer customer){
        firstNameInput.sendKeys(customer.getFirstName());
        lastNameInput.sendKeys(customer.getLastName());
        companyInput.sendKeys(customer.getCompanyName());
        Select countrySelect = new Select(billingCountrySelect);
        countrySelect.selectByVisibleText(customer.getCountry());
        billingAddressInput.sendKeys(customer.getStreetName());
        billingPostcodeInput.sendKeys(customer.getZipCode());
        billingCityInput.sendKeys(customer.getCity());
        billingPhoneInput.sendKeys(customer.getPhone());
        billingEmailInput.sendKeys(customer.getEmail());
        wait(2000);
        driver.findElement(By.xpath("//button[@id = 'place_order']")).click(); // Tu jest syf bo ten przycisk za dobrze nie działa i zmienia jakos dziwnie stan po wpisaniu wszystkich danych
        return new OrderSummaryPage(driver);
    }

    private void wait(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {

        }
    }



}
