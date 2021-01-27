package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.PollPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PollPageTest extends TestBase {

    HomePage homePage;
    PollPage pollPage;
    LoginPage loginPage;
   // String sheet;
   // String sheetName;

    public PollPageTest(){super(); }

    @BeforeMethod
    public void setup(){
        initialization();//then initialization method
        loginPage = new LoginPage(); //creating loginPage Object
        homePage = loginPage.login(prop.getProperty
                ("username"), prop.getProperty("password"));
        pollPage= homePage.gotoPolls();
    }

    @Test(priority = 1)
    public void testTitleText() {
        pollPage
                .sendKeysToTitle("TitleTest");
    }
    @Test(priority = 2)
    public void testBodyText() {
        Assert.assertEquals(pollPage.sendKeyBody
                ("HelloWorld"), "HelloWorld");
    }
    @Test(priority = 3)
    public void testQuestionText()  {
        pollPage
                .sendKeysToQuestion("HelloWorld");
    }
    @Test(priority = 4)
    public void testAnswer(){
        pollPage
                .sendKeyAnswer("HelloWorld");
    }
    @Test(priority = 5)
    public void testCheckBox() {
        Assert.assertTrue(
               pollPage.validateCheckBox());
    }
    @Test(priority = 6)
    public void testCancelBtn(){
        Assert
                .assertTrue(pollPage.testCancelBtn());
    }
    @Test(priority = 7)
    public void testSubmitBtn() {
        Assert
                .assertTrue(pollPage.isSubmitEnabled());
    }
    @Test(priority = 8)
    public void testTitleKey() {
        Assert
                .assertTrue(pollPage.isTopicKeyEnabled());
    }
   // @DataProvider
   //    public Object[][] getCRMTestData(){
   //        return TestUtil.getTestData(sheetName);
   //    }
    @Test(priority = 9)//, dataProvider="getCRMTestData" String title, String body, String quest, String answer
    public void fullPollTest(){
        pollPage
                //.fullTest(title,body,quest,answer);
                .fullTest("Title","Body","question","answer");
    }
    @Test(priority = 10)
    public void deletePoll(){
        pollPage
                .deletePollTest();
    }

    @AfterMethod
    public void tearDown()  {
        TestUtil.sleep(1000);
        driver
                .quit();
    }

}
