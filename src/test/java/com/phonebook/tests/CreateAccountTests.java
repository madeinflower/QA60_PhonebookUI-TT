package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void ensurePrecondition(){
if(!app.getUser().isLoginLinkPresent()){
    app.getUser().clickOnSignOutButton();
}
    }

    @Test(enabled = false)
    public void newUserRegistrationPositiveTest(){
        int i=(int)((System.currentTimeMillis()/1000)%3600);

        app.getUser().clickOnLoginButton();
        app.getUser().fillRegisterLognForm(new User().setEmail("kris22" +i +"@gmail.com")
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void existedUserRegistrationNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLognForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegistrationButton();
        softAssert.assertTrue(app.getUser().isAlertDisplyed());
        softAssert.assertTrue(app.getUser().isErrorMessagePresent());
        softAssert.assertAll();

    }


}
