package com.crm.qa.pages;

import com.crm.qa.base.TestBase;

import com.crm.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class HomePage extends TestBase {

    public static void waitTillElementVisible(String xpath){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofMillis(300))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver1) {
                return driver1.findElement(By.xpath(xpath));
            }
        });

    }



    public static void waitTillPollSelected(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofMillis(300))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver1) {
                return driver1.findElement(By.xpath("//span[contains(@class,'feed-add-post-form-link feed-add-post-form-link-active') and contains(@id,'feed-add-post-form-tab-vote') ] "));
            }
        });

    }
    public static void waitForFrameSwitch(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement z;
        z = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@contenteditable='true']")));
        z.click();
    }

    //Page Factory- OR:
    String sheet;
    String sheetName;
    @FindBy(xpath = "//*[@id=\"user-name\"]")
    WebElement userTxt;
    @FindBy(xpath = "//*[@id=\"feed-add-post-form-tab-vote\"]/span")
    WebElement pollBtn;
    @FindBy(name = "POST_TITLE")
    WebElement titleBlk;
    @FindBy(xpath = "//*[@title='Topic']")
    WebElement titleKey;
    @FindBy(xpath = "//input[@placeholder='Answer  1']")
    WebElement answer1;
    @FindBy(xpath = "//input[@placeholder='Answer  2']")
    WebElement answer2;
    @FindBy(xpath = "//*[@id=\"multi_0\"]")
    WebElement checkbox;
    @FindBy(xpath = "//*[@id=\"blog-submit-button-cancel\"]")
    WebElement cancelBtn;
    @FindBy(xpath = "//*[@id=\"blog-submit-button-save\"]")
    WebElement submitBtn;
    @FindBy(xpath = "//*[@contenteditable='true']")
    WebElement bodyTxt;
    @FindBy(className = "bx-editor-iframe")
    WebElement iframe;
    @FindBy(xpath = "//input[@placeholder='Question ']")
    WebElement question;
    @FindBy(xpath = "//span[contains(@id,'feed-post-more')][1]")
    WebElement gearTab;
    @FindBy(xpath = "//span[contains(@class,'menu-popup-no-icon')][5]")
    WebElement selectDelete;

    //Initialize//
    public HomePage() {
        PageFactory
                .initElements(driver, this);
    }

    //Action//
    public String verifyTitle() {
        return
                driver.getTitle();
    }

    public void clickPoll() {
        pollBtn
                .click();
    }

    public String displaysCorrectUser() {
        return userTxt
                .getText();
    }

    public void sendKeysToTitle(String str) {

        titleBlk
                .sendKeys(str);

    }

    public boolean isTopicKeyEnabled() {
        pollBtn.click();
        waitTillPollSelected();
        return titleKey.isEnabled();
    }

    public void sendKeyAnswer(String str) {
        answer1
                .sendKeys(str);
    }

    public boolean validateCheckBox() {
        checkbox.click();
        return checkbox.isSelected();
    }

    public boolean testCancelBtn()  {
        pollBtn.click();
        waitTillPollSelected();
        cancelBtn.click();
        return !cancelBtn.isDisplayed();
    }

    public boolean isSubmitEnabled() {
        return
                submitBtn.isEnabled();
    }

    public String sendKeyBody(String str)  {
        pollBtn.click();
        waitTillPollSelected();
        driver.switchTo().frame(iframe);
        waitForFrameSwitch();
        bodyTxt.sendKeys(str);
        String flag = bodyTxt.getText();
        driver.switchTo().defaultContent();
        return flag;
    }

    public void sendKeysToQuestion(String str) {
        question
                .sendKeys(str);
    }

    @DataProvider
    public Object[][] getCRMTestData(){

        return TestUtil.getTestData(sheetName);
    }

   @Test(dataProvider="getCRMTestData")
    public void fullTest(String a, String b, String c, String d){
        pollBtn.click();
        waitTillElementVisible("//span[contains(@class,'feed-add-post-form-link feed-add-post-form-link-active')"+
                "and contains(@id,'feed-add-post-form-tab-vote') ] ");
        titleBlk.sendKeys(a);
        sendKeyBody(b);
        question.sendKeys(c);
        answer1.sendKeys(d);
        checkbox.click();
        submitBtn.click();


    }
    public void closeTest()  {
        cancelBtn.click();
        waitTillElementVisible("//span[contains(@id,'feed-post-more')][1]");
        gearTab.click();
        waitTillElementVisible("//span[contains(@class,'menu-popup-no-icon')][5]");
        selectDelete.click();
        driver.switchTo().alert().accept();
    }
}
