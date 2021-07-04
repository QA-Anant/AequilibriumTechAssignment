package Pages;

import TestSetup.TestSetup;
import Utilities.Log;
import Utilities.TestUtils;
import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Reporting.ExtentManager.getTest;

public class LoginPage extends TestSetup {

    @FindBy(id = "user-name")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "login-button")
    public WebElement loginBtn;

    @FindBy(xpath = "*//h3[@data-test='error']")
    public WebElement errorMessageLbl;

    @FindBy(className = "error-button")
    public WebElement resetBtn;

    @FindBy(xpath = "*//span[contains(text(),'Products')]")
    public WebElement landingPageTitle;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logOutBtn;

    public void setUsername(String uname){
        TestUtils.isElementPresent(username,10);
        TestUtils.clearAndEnterText(username,uname,"Username Field");
    }

    public void setPassword(String pass){
        TestUtils.isElementPresent(password,10);
        TestUtils.clearAndEnterText(password,pass,"Password Field");
    }

    public void clickLoginBtn(){
        TestUtils.isElementPresent(loginBtn,10);
        TestUtils.clickOnElement(loginBtn,"Login Button");
    }

    public void verifyUserNameValidationMessage(String message) {
        TestUtils.waitTillElementIsDisplayed(errorMessageLbl,10);
        TestUtils.textVerification(errorMessageLbl,message);
        TestUtils.clickOnElement(resetBtn,"Reset Button");
        TestUtils.clearText(username,"Username");
        TestUtils.clearText(password,"password");
    }

    public void verifyPasswordValidationMessage(String message) {
        TestUtils.waitTillElementIsDisplayed(errorMessageLbl,10);
        TestUtils.textVerification(errorMessageLbl,message);
        TestUtils.clickOnElement(resetBtn,"Reset Button");
        TestUtils.clearText(username,"Username");
        TestUtils.clearText(password,"password");
    }

    public void verifyInvalidUsernameAndPasswordValidationMessage(String message){
        TestUtils.waitTillElementIsDisplayed(errorMessageLbl,10);
        TestUtils.textVerification(errorMessageLbl,message);
        TestUtils.clickOnElement(resetBtn,"Reset Button");
        TestUtils.clearText(username,"Username");
        TestUtils.clearText(password,"password");
    }

    public void verifyStandardUserLogin() {
            TestUtils.isElementPresent(landingPageTitle,5);
            TestUtils.textVerification(landingPageTitle,"PRODUCTS");
            TestUtils.clickOnElement(menuBtn,"Menu Button");
            TestUtils.waitTillElementIsDisplayed(logOutBtn, 10);
            TestUtils.clickOnElement(logOutBtn,"Logout Button");
            verifyLoginPage();
    }

    public void verifyLockedOutUserLogin(String message) {
        TestUtils.waitTillElementIsDisplayed(errorMessageLbl,10);
        TestUtils.textVerification(errorMessageLbl,message);
        TestUtils.clickOnElement(resetBtn,"Reset Button");
        TestUtils.clearText(username,"Username");
        TestUtils.clearText(password,"password");
    }

    public void verifyLoginPage() {
        if (webDriver.getTitle().equalsIgnoreCase("Swag Labs")){
            TestUtils.implicitWait(10);
            Assert.assertTrue(true);
            getTest().log(Status.PASS, "LoginPage",
                        TestUtils.addScreenshotInReport(TestUtils.captureScreenshot()));
            Log.info("User is on the Login Page.");
        }else {
            getTest().log(Status.FAIL, "Home Page title is not correct",
                    TestUtils.addScreenshotInReport(TestUtils.captureScreenshot()));
            Assert.fail();
        }
    }

    public void verifyPerformanceDegrade() {
        TestUtils.waitTillElementIsDisplayed(landingPageTitle,10);
    }
}