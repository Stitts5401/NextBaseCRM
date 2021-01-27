package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import static com.crm.qa.pages.HomePage.waitForFrameSwitch;
import static com.crm.qa.pages.HomePage.waitTillElementVisible;

public class PollPage extends TestBase {

    //Page Factory- OR:
    @FindBy(name = "POST_TITLE")
    WebElement titleBlk;
    @FindBy(xpath = "//*[@title='Topic']")
    WebElement titleKey;
    @FindBy(xpath = "//input[@placeholder='Answer  1']")
    WebElement answer1;
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
    @FindBy(xpath = "//*[@id=\"logo_24_a\"]")
    WebElement homeBtn;
    @FindBy(xpath = "//span[contains(@id,'feed-post-more')][1]")
    WebElement gearTab;
    @FindBy(xpath = "//span[contains(@class,'menu-popup-no-icon')][5]")
    WebElement selectDelete;
    //INITIALIZATION//

    public PollPage(){
        PageFactory
                .initElements(driver,this);
    }

    //ACTION//
    public void sendKeysToTitle(String str) {
        titleBlk.click();
        titleBlk.clear();
        titleBlk.sendKeys(str);
        titleBlk.click();
    }
    public boolean isTopicKeyEnabled() {
        return titleKey
                .isEnabled();
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
        cancelBtn.click();
        return !cancelBtn.isDisplayed();
    }
    public boolean isSubmitEnabled() {
        return
                submitBtn.isEnabled();
    }
    public String sendKeyBody(String str)  {

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
    public void  deletePollTest()  {
        homeBtn.click();
        waitTillElementVisible("//span[contains(@id,'feed-post-more')][1]");
        gearTab.click();
        waitTillElementVisible("//span[contains(@class,'menu-popup-no-icon')][5]");
        selectDelete.click();
        driver.switchTo().alert().accept();
    }
    public void fullTest(String title, String body, String quest, String answer){

       // Select select = new Select(driver.findElement(By.name("title")));
      //  select.selectByVisibleText(title);

        waitTillElementVisible("//span[contains(@class,'feed-add-post-form-link feed-add-post-form-link-active')"+
                "and contains(@id,'feed-add-post-form-tab-vote') ] ");
        titleBlk.sendKeys(title);
        sendKeyBody(body);
        question.sendKeys(quest);
        answer1.sendKeys(answer);
        checkbox.click();
        submitBtn.click();

    }


}
