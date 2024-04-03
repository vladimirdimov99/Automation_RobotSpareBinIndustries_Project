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

public class CheckIfHidePerformanceButtonWorks extends LoadTheDriver {

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
    public void enterSalesFormDataAndCheckPerformanceMessage() {
        SalesForm salesForm = new SalesForm();
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "15000");
        salesForm.checkPerformanceMessage();
        performanceMessage = getDriver().findElement(salesForm.performanceMessageLocator).getText();
        Assert.assertEquals(performanceMessage, "Well. It was a nice attempt. I guess?");
    }

    @Test(priority = 4)
    public void clickHidePerformanceButtonAndCheckIfMessagesHide() {
        SalesForm salesForm = new SalesForm();
        getDriver().findElement(salesForm.hidePerformanceButton).click();
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.invisibilityOfElementLocated(salesForm.performanceMessageLocator));

        Boolean isVisible;
        try {
            isVisible = getDriver().findElement(salesForm.performanceMessageLocator).isDisplayed();
        } catch (Exception e) {
            isVisible = false;
        }
        Assert.assertFalse(isVisible, "The Performance Message is displayed !!!");
    }

    @AfterTest
    public void closeTheWebsite() {
        quitTheDriver();
    }
}

