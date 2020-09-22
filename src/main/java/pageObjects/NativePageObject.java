package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NativePageObject  {

    @AndroidFindBy(xpath ="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
            + "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]"
            + "/android.view.ViewGroup/android.widget.TextView")
    WebElement heading;

    @AndroidFindBy(xpath ="/hierarchy/android.widget.FrameLayout"
            + "/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup"
            + "/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    WebElement regHeading;

    @AndroidFindBy(xpath ="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
            + "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]"
            + "/android.view.ViewGroup/android.widget.TextView")
    WebElement BudgetPageHeading;


    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement emailOrLoginFieldOnSignInPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement passwordFieldOnSignInPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    WebElement goToRegisterPageButton;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement emailFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement userNameFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement passwordFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement confirmPasswordFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerButtonOnRegPage;


    public NativePageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }


}
