package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {

   LoginPage loginPage;
   HomePage homePage;

    public LoginPageTest(){
        super(); //1st initialize the superMethod--
        // --TestBase inorder to initialize properties
    }

    @BeforeMethod
    public void setup(){

        initialization(); //then initialization method
       loginPage = new LoginPage(); //creating loginPage Object

    }

    @Test(priority = 1)
    public void loginPageTitleTest(){
       String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title,"Authorization");
    }
    @Test(priority = 2)
    public void authorizationHeaderTest(){
        boolean flag = loginPage.validateHead();
        Assert.assertTrue(flag,"HeaderFail");
    }
    @Test(priority = 3)
    public void loginTest(){
       homePage = loginPage.login(prop.getProperty("username")
               , prop.getProperty("password") );
    }

    @AfterMethod
    public void tearDown(){
        TestUtil.sleep(1000);
        driver.quit();

    }

}
