package Tests;

import TestSetup.TestSetup;
import Utilities.TestUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static Interfaces.ClassObjects.loginPage;

public class LoginTest extends TestSetup {

    String username;
    String password;

    @Test(description = "Verify the homePage of site", enabled = true, priority = 1)
    public void verifyHomePage(){
        loginPage.verifyLoginPage();
    }

    @Test(description = "Verify the validation message for no username input", enabled = true, priority = 2)
    public void verifyNoUserNameInput() throws IOException {
        loginPage.setUsername("");
        loginPage.setPassword("someRandomValue");
        loginPage.clickLoginBtn();
        loginPage.verifyUserNameValidationMessage(TestUtils.getMessagesFromJson("loginPage.usernameMissingTxt"));
       }

    @Test(description = "Verify the validation message for no password input", enabled = true, priority = 3)
    public void verifyNoPasswordInput() throws IOException {
        loginPage.setUsername("someRandomValue");
        loginPage.setPassword("");
        loginPage.clickLoginBtn();
        loginPage.verifyPasswordValidationMessage(TestUtils.getMessagesFromJson("loginPage.passwordMissingTxt"));
    }


    @Test(description = "Verify the validation message for invalid username and password input", enabled = true, priority = 4)
    public void verifyNoUsernameAndPasswordInput() throws IOException {
        loginPage.setUsername("dummyUser");
        loginPage.setPassword("dummyPassword");
        loginPage.clickLoginBtn();
        loginPage.verifyInvalidUsernameAndPasswordValidationMessage(TestUtils.getMessagesFromJson("loginPage.incorrectUsernameAndPasswordTxt"));
    }


    @Test(description = "Verify that standard user is able to login into the site successfully", enabled = true, priority = 5)
    public void verifyStandardUserLogin() throws IOException {
        username = TestUtils.getValueFromJson("standardUserCredential.Username");
        password = TestUtils.getValueFromJson("standardUserCredential.Password");
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginBtn();
        loginPage.verifyStandardUserLogin();
    }


    @Test(description = "Verify that Locked Out user is not able to login into the site successfully", enabled = true, priority = 6)
    public void VerifyLockedOutUserLogin() throws IOException {
        username = TestUtils.getValueFromJson("lockedOutUserCredential.Username");
        password = TestUtils.getValueFromJson("lockedOutUserCredential.Password");
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginBtn();
        loginPage.verifyLockedOutUserLogin(TestUtils.getMessagesFromJson("loginPage.lockedOutUser"));
    }

    @Test(description = "Verify that there is performance glitch observed for the performance glitch user", enabled = true, priority = 7)
    public void VerifyPerformanceGlitchUserLogin() throws IOException {
        username = TestUtils.getValueFromJson("performanceGlitchUserCredential.Username");
        password = TestUtils.getValueFromJson("performanceGlitchUserCredential.Password");
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginBtn();
        loginPage.verifyPerformanceDegrade();
    }


}
