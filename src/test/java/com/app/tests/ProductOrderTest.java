package com.app.tests;

import com.app.model.Customer;
import com.app.pages.HomePage;
import com.app.utils.ExelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class ProductOrderTest extends BaseTest {

    @Test(dataProvider = "customersProvider")
    public void checkoutTest(Customer customer) {
        var summaryPage = new HomePage(webDriver)
                .clickShopLink()
                .clickOnProductWithSpecifiedName(getEnvironment().getRequiredProperty("product_order_test.productName"))
                .addProductToCart()
                .goToCart()
                .goToCheckout()
                .provideCheckoutData(customer);

        var orderedProductName = summaryPage.getProductName().getText();
        var orderNotice = summaryPage.getOrderNotice().getText();

        Assert.assertEquals(orderNotice, getEnvironment().getRequiredProperty("product_order_test.orderNotice"));
        Assert.assertEquals(orderedProductName, getEnvironment().getRequiredProperty("product_order_test.productName"));
    }

    @DataProvider
    private Object[] customersProvider() throws IOException {
        var data = ExelReader.readExel("customer_data.xlsx");

        return Arrays.stream(data).map(this::convertDataToCustomer).toArray();
    }

    private Customer convertDataToCustomer(Object[] data) {
        if (data.length == 11) {
            return new Customer(data[1].toString(), data[2].toString(), data[3].toString(), data[4].toString(), data[5].toString(), data[6].toString(), data[7].toString(), data[8].toString(), data[9].toString(), data[10].toString());
        }
        return new Customer(data[1].toString(), data[2].toString(), data[3].toString(), data[4].toString(), data[5].toString(), data[6].toString(), data[7].toString(), data[8].toString(), data[9].toString(), data[10].toString(), data[11].toString());
    }
}


