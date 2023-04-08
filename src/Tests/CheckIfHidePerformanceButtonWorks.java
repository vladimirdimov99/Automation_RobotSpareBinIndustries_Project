package Tests;

import Pages.LoadTheWebsite;
import Pages.LogInForm;
import Pages.SalesForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class CheckIfHidePerformanceButtonWorks {
    WebDriver driver;
    String currentURL = "";
    String expectedURL = "";
    String performanceMessage = "";
    Duration timeout = Duration.ofSeconds(3);

    @BeforeTest
    public void OpenTheWebsite(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
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
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "15000");
        salesForm.checkPerformanceMessage();
        performanceMessage = driver.findElement(salesForm.performanceMessageLocator).getText();
        Assert.assertEquals(performanceMessage, "Well. It was a nice attempt. I guess?");
    }

    @Test(priority = 4)
    public void clickHidePerformanceButtonAndCheckIfMessagesHide(){
        SalesForm salesForm = new SalesForm(driver);
        driver.findElement(salesForm.hidePerformanceButton).click();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(salesForm.performanceMessageLocator));

        Boolean isVisible;
        try{
            isVisible = driver.findElement(salesForm.performanceMessageLocator).isDisplayed();
        }
        catch(Exception e){
            isVisible = false;
        }
        Assert.assertFalse(isVisible, "The Performance Message is displayed !!!");
    }

    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}

