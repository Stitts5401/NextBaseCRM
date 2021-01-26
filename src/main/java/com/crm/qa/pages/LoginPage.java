package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {



    @FindBy(name="USER_LOGIN")
    WebElement username;

    @FindBy(name="USER_PASSWORD")
    WebElement password;
    @FindBy(className = "login-btn")
    WebElement loginBtn;
    @FindBy(xpath = "//*[@id='login-popup']/form/div[2]/a")
    WebElement resetPass;
    @FindBy(xpath = "//*[contains(@class,'head')]")
    WebElement authHeader;
    //initialize Elements//
        public LoginPage(){
            PageFactory.initElements(driver,this);
        }

    //ACTIONS//
        public String validateLoginPageTitle(){
            return driver.getTitle();
        }

        public boolean validateHead(){
            return authHeader.isDisplayed();
        }

        public HomePage login(String un,String pass){
            username.sendKeys(un);
            password.sendKeys(pass);
            loginBtn.click();
            return new HomePage();
        }
    //ACTIONS//
}
