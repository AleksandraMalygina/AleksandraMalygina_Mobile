package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeOther[@label='Budget']")
    WebElement BudgetPageHeading;


    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='user@example.com']")
    WebElement emailOrLoginFieldOnSignInPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeSecureTextField[@value='Required']") //[@value='Required']
    WebElement passwordFieldOnSignInPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@label='Sign In']")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@label='Register new account']")
    WebElement goToRegisterPageButton;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeTextField[@value='user@example.com']")
    WebElement emailFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeTextField[@value='TimApple']")
    WebElement userNameFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeSecureTextField[@value='Required']")
    WebElement passwordFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeSecureTextField[@value='Repeat please']")
    WebElement confirmPasswordFieldOnRegPage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@label='Register new account']")
    WebElement registerButtonOnRegPage;


    public NativePageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }


}
