package Tests;

import Pages.LoadTheWebsite;
import Pages.LogInForm;
import Pages.SalesForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class DeleteAllSalesEntries {
    WebDriver driver;
    String currentURL = "";
    String expectedURL = "";
    String performanceMessage = "";
    Duration timeout = Duration.ofSeconds(3);

    @BeforeTest
    public void OpenTheWebsite(){
        driver = new ChromeDriver();
        new LoadTheWebsite().LoadTheWebsite(driver);
    }

    @Test(priority = 1)
    public void checkIfTheWebsiteIsCorrect(){
        currentURL = driver.getCurrentUrl();
        expectedURL = "https://robotsparebinindustries.com/#/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    @Test(priority = 2)
    public void logInToTheWebsite(){
        LogInForm logInForm = new LogInForm(driver);
        SalesForm salesForm = new SalesForm(driver);
        logInForm.enterCredentialsToLogInAndClickLogInButton("maria", "thoushallnotpass");
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesForm.salesFormPanelLocator));

        Boolean isVisible;
        try{
            isVisible = driver.findElement(salesForm.salesFormPanelLocator).isDisplayed();
        }
        catch(Exception e){
            isVisible = false;
        }
        Assert.assertTrue(isVisible, "Sales Form Panel is not displayed !!!");
    }

    @Test(priority = 3)
    public void enterSalesFormDataAndCheckPerformanceMessage(){
        SalesForm salesForm = new SalesForm(driver);
        // Check for Positive Result
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "50000");
        salesForm.checkPerformanceMessage();
        performanceMessage = driver.findElement(salesForm.performanceMessageLocator).getText();
        Assert.assertEquals(performanceMessage, "A positive result. Well done!");
        // Check for Negative Result
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "15000");
        salesForm.checkPerformanceMessage();
        performanceMessage = driver.findElement(salesForm.performanceMessageLocator).getText();
        Assert.assertEquals(performanceMessage, "Well. It was a nice attempt. I guess?");
    }

    @Test(priority = 4)
    public void deleteAllSalesEntries(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.deleteAllSalesEntries();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(salesForm.salesSummaryPanelLocator));

        Boolean isTrue;
        try{
            isTrue = driver.findElement(salesForm.salesSummaryPanelLocator).isDisplayed();
        }
        catch(Exception e){
            isTrue = false;
        }
        Assert.assertFalse(isTrue, "The Sales Summary Panel is displayed !!!");
    }

    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}
