package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebPageObject  {

    @FindBy(xpath = "//input[@name ='q']")
    WebElement searchField;

    @FindBy(xpath = "//h1[text() = 'Результаты поиска']/parent::div/div/div[@data-hveid]")
    public List<WebElement> searchResults;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }

}
