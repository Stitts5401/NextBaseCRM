package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends TestBase {
    LoginPage loginPage;

    HomePage homePage;

    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {

        initialization(); //then initialization method
        loginPage = new LoginPage(); //creating loginPage Object
        homePage = loginPage.login(prop.getProperty
                ("username"), prop.getProperty("password"));
        homePage.clickPoll();
        HomePage.waitTillPollSelected();

    }

    @Test(priority = 1)
    public void testTitleText() {
        homePage.sendKeysToTitle("TitleTest123");
        List<WebElement> l= driver.findElements(By.xpath("TitleTest123"));
        // verify list size
        boolean flag;
        flag = l.size() > 0;
        Assert.assertTrue(flag);
    }

    @Test(priority = 2)
    public void testBodyText() {
        Assert.assertEquals(homePage.sendKeyBody
                ("HelloWorld"), "HelloWorld");
    }

    @Test(priority = 3)
    public void testQuestionText()  {
       homePage
               .sendKeysToQuestion("HelloWorld");
    }

    @Test(priority = 4)
    public void testAnswer()throws InterruptedException{
        homePage.sendKeyAnswer("HelloWorld"); Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void testCheckBox() {
        Assert.assertTrue(homePage
                .validateCheckBox());
    }

    @Test(priority = 7)
    public void testCancelBtn(){
        Assert
                .assertTrue(homePage.testCancelBtn());
    }

    @Test(priority = 9)
    public void testSubmitBtn() {
        Assert
                .assertTrue(homePage.isSubmitEnabled());
    }

    @Test(priority = 10)
    public void testTitleKey() {
        Assert
                .assertTrue(homePage.isTopicKeyEnabled());
    }

    @Test(priority = 11)
    public void verifyUser() {
        Assert
                .assertEquals(homePage.displaysCorrectUser(), prop.getProperty("username"));
    }

    @Test(priority = 12)
    public void testTitle(){
        Assert
                .assertEquals(homePage.verifyTitle(),"(50) Portal");
    }
    @Test(priority = 13)
    public void fullTest()throws InterruptedException{
        homePage.fullTest("Title","Body","Question","Answer");
    }
    @Test(priority = 14)
    public void deleteFullTest() throws InterruptedException {
        homePage.closeTest();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
