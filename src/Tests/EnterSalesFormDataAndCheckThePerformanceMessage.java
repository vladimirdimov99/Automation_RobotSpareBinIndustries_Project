package Tests;

import Pages.SalesForm;
import utils.LoadTheDriver;
import Pages.LogInForm;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class EnterSalesFormDataAndCheckThePerformanceMessage extends LoadTheDriver {

    String currentURL = "";
    String expectedURL = "";
    String performanceMessage = "";
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
        Assert.assertTrue(isVisible, "Sales form is not displayed!!!");
    }

    @Test(priority = 3)
    public void enterSalesFormDataAndCheckThePerformanceMessage() {
        SalesForm salesForm = new SalesForm();
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "50000");
        salesForm.checkPerformanceMessage();
        performanceMessage = getDriver().findElement(salesForm.performanceMessageLocator).getText();
        Assert.assertEquals(performanceMessage, "A positive result. Well done!");
    }

    @AfterTest
    public void closeTheWebsite() {
        quitTheDriver();
    }
}
