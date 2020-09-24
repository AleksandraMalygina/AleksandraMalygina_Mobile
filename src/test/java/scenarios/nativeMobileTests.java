package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;
import static org.assertj.core.api.Assertions.assertThat;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "This test register a new account and then sign in")
    public void registerAndLoginTest() throws IllegalAccessException,
                                                    NoSuchFieldException, InstantiationException {
        getPo().getWelement("goToRegisterPageButton").click();

        getPo()
                .getWelement("emailFieldOnRegPage")
                .sendKeys(getTestData("userEmail"));
        getPo()
                .getWelement("userNameFieldOnRegPage")
                .sendKeys(getTestData("userName"));
        getPo()
                .getWelement("passwordFieldOnRegPage")
                .sendKeys(getTestData("userPassword"));
        getPo()
                .getWelement("confirmPasswordFieldOnRegPage")
                .sendKeys(getTestData("userPassword"));

        getPo().getWelement("registerButtonOnRegPage").click();

        getPo()
                .getWelement("emailOrLoginFieldOnSignInPage")
                .sendKeys(getTestData("userEmail"));
        getPo()
                .getWelement("passwordFieldOnSignInPage")
                .sendKeys(getTestData("userPassword"));


        getPo().getWelement("signInBtn").click();


        assertThat(getPo().getWelement("BudgetPageHeading").getText())
                .containsIgnoringCase(getTestData("budgetPagePartialName"));
        System.out.println("Test Finished");
    }

}
