package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.List;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() throws InterruptedException {
        getDriver().get(getTestData("ianaURL")); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver())
                .getTitle()
                .equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }


    @Test(groups = {"web"},
    description = "Make sure that there is not empty list of relevant results for search 'EPAM'")
    public void webSearchResultsTest() throws IllegalAccessException,
                                                NoSuchFieldException, InstantiationException {
        getDriver().get(getTestData("googleSearchURL"));

        getPo().getWelement("searchField")
                .sendKeys(getTestData("searchText"));
        getPo().getWelement("searchField").sendKeys(Keys.ENTER);

        List<WebElement> resultsList = getPo().getWelements("searchResults");

        //This is for logging purposes only
        System.out.println(resultsList.size());
        for (WebElement we : resultsList) {
            System.out.println(we.getText());
            System.out.println("==================");
        }
        System.out.println("Check 2 : " + getPo().getWelements("searchResults").size());

       Assert.assertFalse(resultsList.isEmpty());

    }



}
