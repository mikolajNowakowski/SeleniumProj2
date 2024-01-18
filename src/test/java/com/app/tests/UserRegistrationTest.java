package com.app.tests;

import com.app.pages.HomePage;
import com.app.utils.ExelReader;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class UserRegistrationTest extends BaseTest {
    @Test(dataProvider = "passwordProvider")
    public void userRegistrationWithCorrectData(String password) {
        var email = new Faker().internet().emailAddress();

        var stringInfo = new HomePage(webDriver)
                .myAccountLinkClick()
                .registerUserCorrectData(email, password)
                .getWelcomeInfo();

        Assert.assertTrue(stringInfo.contains(email.split("@")[0]));
    }

    @Test
    public void userRegistrationWithIncorrectData() {
        var errorInfo = new HomePage(webDriver)
                .myAccountLinkClick()
                .registerUserIncorrectData(applicationContext.getEnvironment().getRequiredProperty("user_registration_with_incorrect_data.email"),
                        applicationContext.getEnvironment().getRequiredProperty("user_registration_with_incorrect_data.email"))
                .getErrorInfo();
        Assert.assertTrue(errorInfo.contains(applicationContext.getEnvironment().getRequiredProperty("user_registration_with_incorrect_data.errorInfo")));
    }

    @Test(dataProvider = "notGoodEnoughPasswordProvider")
    public void userRegistrationWithNotGoodEnoughPassword(String password){
        var email = new Faker().internet().emailAddress();

        var buttonEnabled = new HomePage(webDriver)
                .myAccountLinkClick()
                .registerUserIncorrectData(email, password)
                .isRegisterButtonEnabled();

        Assert.assertFalse(buttonEnabled);
    }

    @DataProvider
    private Object[] passwordProvider() throws IOException {
        var data = ExelReader.readExel("selenium_data.xlsx");
        var numberOfCol = getNumberOfColumnWithTitle(data, "correctPasswords");
        return Arrays.stream(data).map(objects -> objects[numberOfCol]).skip(1).toArray();
    }

    @DataProvider
    private Object[] notGoodEnoughPasswordProvider() throws IOException {
        var data = ExelReader.readExel("selenium_data.xlsx");
        var numberOfCol = getNumberOfColumnWithTitle(data, "incorrectPasswords");
        return Arrays.stream(data).map(objects -> objects[numberOfCol]).skip(1).toArray();
    }

    private int getNumberOfColumnWithTitle(Object[][] data, String title) {
        for (int j = 0; j < data[0].length; j++) {
            if (data[0][j].toString().equalsIgnoreCase(title)) {
                return j;
            }
        }
        throw new IllegalArgumentException("There's no column with inputted title");
    }
}
