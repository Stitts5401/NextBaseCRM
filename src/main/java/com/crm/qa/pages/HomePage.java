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
    @FindBy(xpath = "//*[@id=\"user-name\"]")
    WebElement userTxt;
    @FindBy(xpath = "//*[@id=\"feed-add-post-form-tab-vote\"]/span")
    WebElement pollBtn;


    //Initialize//
    public HomePage() {
        PageFactory
                .initElements(driver, this);
    }

    //ACTIONS//
    public boolean verifyTitle() {
        String title = driver.getTitle();
        boolean flag=false;
        if(  title.contains("Portal")){
            flag=true;
        }
        return flag;

    }

    public PollPage gotoPolls() {
        pollBtn.click();
        return new PollPage();
    }

    public String displaysCorrectUser() {
        return userTxt
                .getText();
    }

    //ACTIONS//

}
