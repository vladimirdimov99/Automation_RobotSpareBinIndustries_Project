package Tests;

import utils.LoadTheDriver;
import Pages.LogInForm;
import Pages.SalesForm;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ErrorMessagePopsUpIfInputFieldIsEmpty extends LoadTheDriver {

    String currentURL = "";
    String expectedURL = "";
    Duration timeout = Duration.ofSeconds(3);

    @BeforeTest
    public void OpenTheWebsite() {
        new LoadTheDriver().LoadTheWebsite(getDriver());
    }

    @Test(priority = 1)
    public void checkIfTheWebsiteIsCorrect() {
        currentURL = getDriver().getCurrentUrl();
        expectedURL = "https://robotsparebinindustries.com/#/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    @Test(priority = 2)
    public void logInToTheWebsite() {
        LogInForm logInForm = new LogInForm();
        logInForm.enterCredentialsToLogInAndClickLogInButton("maria", "thoushallnotpass");
        SalesForm salesForm = new SalesForm();
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesForm.salesFormPanelLocator));

        Boolean isVisible;
        try {
            isVisible = getDriver().findElement(salesForm.salesFormPanelLocator).isDisplayed();
        } catch (Exception e) {
            isVisible = false;
        }
        Assert.assertTrue(isVisible, "The Sales Form is not displayed!!!");
    }

    @Test(priority = 3)
    public void checkIfErrorMessagesPopsUpIfInputFieldIsEmpty() {
        SalesForm salesForm = new SalesForm();
        salesForm.enterSalesFormDataAndClickSubmit("", "Dimov", "50000");

        Boolean isErrorMessageDisplayed;
        try {
            isErrorMessageDisplayed = getDriver().findElement(salesForm.errorMessageLocator).isDisplayed();
        } catch (Exception e) {
            isErrorMessageDisplayed = false;
        }
        Assert.assertTrue(isErrorMessageDisplayed, "The Error Message is not displayed !!!");
    }

    @AfterTest
    public void closeTheWebsite() {
        quitTheDriver();
    }
}
