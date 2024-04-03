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

public class DeleteAllSalesEntries extends LoadTheDriver {

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
        SalesForm salesForm = new SalesForm();
        logInForm.enterCredentialsToLogInAndClickLogInButton("maria", "thoushallnotpass");
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesForm.salesFormPanelLocator));

        Boolean isVisible;
        try {
            isVisible = getDriver().findElement(salesForm.salesFormPanelLocator).isDisplayed();
        } catch (Exception e) {
            isVisible = false;
        }
        Assert.assertTrue(isVisible, "Sales Form Panel is not displayed !!!");
    }

    @Test(priority = 3)
    public void enterSalesFormDataAndCheckThePerformanceMessage() {
        SalesForm salesForm = new SalesForm();
        // Check for Positive Result
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "50000");
        salesForm.checkPerformanceMessage();
        performanceMessage = getDriver().findElement(salesForm.performanceMessageLocator).getText();
        Assert.assertEquals(performanceMessage, "A positive result. Well done!");
        // Check for Negative Result
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "15000");
        salesForm.checkPerformanceMessage();
        performanceMessage = getDriver().findElement(salesForm.performanceMessageLocator).getText();
        Assert.assertEquals(performanceMessage, "Well. It was a nice attempt. I guess?");
    }

    @Test(priority = 4)
    public void deleteAllSalesEntries() {
        SalesForm salesForm = new SalesForm();
        salesForm.deleteAllSalesEntries();
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.invisibilityOfElementLocated(salesForm.salesSummaryPanelLocator));

        Boolean isVisible;
        try {
            isVisible = getDriver().findElement(salesForm.salesSummaryPanelLocator).isDisplayed();
        } catch (Exception e) {
            isVisible = false;
        }
        Assert.assertFalse(isVisible, "The Sales Summary Panel is displayed !!!");
    }

    @AfterTest
    public void closeTheWebsite() {
        quitTheDriver();
    }
}
