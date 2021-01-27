package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;

    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setup(){

        initialization(); //then initialization method
        loginPage = new LoginPage(); //creating loginPage Object
        homePage = loginPage.login(prop.getProperty
                ("username"), prop.getProperty("password"));

    }

    @Test(priority = 1)
    public void verifyUser() {
        Assert
                .assertEquals(homePage.displaysCorrectUser(), prop.getProperty("username"));
    }
    @Test(priority = 2)
    public void testTitle(){
        Assert
                .assertTrue(homePage.verifyTitle());
    }
    @Test(priority = 3)
    public void verifyPollPage(){
        homePage
                .gotoPolls();
    }

    @AfterMethod
    public void tearDown(){
        TestUtil.sleep(1000);
        driver
                .quit();
    }

}
