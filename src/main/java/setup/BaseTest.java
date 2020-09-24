package setup;

import io.appium.java_client.AppiumDriver;
import io.restassured.http.ContentType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    private static IPageObject po;
    private static Properties testData;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName","appType","udid","deviceName","browserName",
            "app", "appPackage", "appActivity", "bundleId", "deviceLocation"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType,
                      @Optional("") String udid,
                      @Optional("") String deviceName,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId,
                      @Optional("") String deviceLocation) throws Exception {
        System.out.println("Before: app type - " + appType);

        if(appType.equalsIgnoreCase("native") &&
                deviceLocation.equalsIgnoreCase("remote")) {
            installApp(platformName, udid);
        }

        setAppiumDriver(platformName, udid, deviceName,
                            browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
        setUpDataManagement();

    }

    @Parameters({"deviceLocation", "udid"})
    @AfterSuite(alwaysRun = true)
    public void tearDown( @Optional("")String deviceLocation, String udid) throws Exception {
        System.out.println("After");
        if(deviceLocation.equalsIgnoreCase("remote")) {
            releaseDevice(udid);
        }
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String udid, String deviceName,
                                 String browserName, String app, String appPackage,
                                 String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("udid", udid);

        if(app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        capabilities.setCapability("bundleId", bundleId);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }


    private void setUpDataManagement() {
        testData = new Properties();

        try(FileInputStream dataStream =
                    new FileInputStream(new File("src/main/resources/app.properties"))) {
            testData.load(dataStream);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " " + "File with test data wasn't found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTestData(String dataName) {
        return testData.getProperty(dataName);
    }


    private void installApp(String platformName, String udid) throws Exception {
        String appName = "";
        switch(platformName) {
            case "iOS":
                appName = "./src/main/resources/EPAMTestApp.ipa";
                break;
            case "Android":
                appName = "./src/main/resources/EPAMTestApp.apk";
                break;
            default: throw new Exception("No installer available for platform " + platformName);
        }

        System.out.println("started to take device");

        given()
                .log().all()
                .header("Authorization", "Bearer " + System.getenv("MOBILE_CLOUD_EPAM_API_KEY"))
                .contentType(ContentType.JSON)
                .baseUri("https://mobilecloud.epam.com/automation/api/device")
                .pathParam("serial", udid)
                .post("/{serial}")
        .then()
                .statusCode(200);

        System.out.println("started to install app");


        given()
                .log().all()
                .header("Authorization", "Bearer " + System.getenv("MOBILE_CLOUD_EPAM_API_KEY"))
                .contentType("multipart/form-data")
                .baseUri("https://mobilecloud.epam.com/automation/api/storage/install")
                .pathParam("serial", udid)
                .multiPart("file", new File(appName))
                .post("/{serial}")
                .prettyPeek()
        .then()
                .statusCode(201);

        System.out.println("install finished");
    }

    private void releaseDevice(String udid) {
        System.out.println("started to release device");

        given()
                .log().all()
                .header("Authorization", "Bearer " + System.getenv("MOBILE_CLOUD_EPAM_API_KEY"))
                .contentType(ContentType.JSON)
                .baseUri("https://mobilecloud.epam.com/automation/api/device")
                .pathParam("serial", udid)
                .delete("/{serial}")
                .prettyPeek()
                .then()
                .statusCode(200);
    }


}
