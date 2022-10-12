package Tests;

import Pages.LoadTheWebsite;
import Pages.LogInForm;
import Pages.SalesForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class NegativeSalesResult {
    WebDriver driver;
    String currentURL = "";
    Duration timeout = Duration.ofSeconds(3);

    @BeforeTest
    public void OpenTheWebsite(){
        driver = new ChromeDriver();
        new LoadTheWebsite().LoadTheWebsite(driver);
    }

    @Test(priority = 1)
    public void checkIfTheWebsiteIsCorrect(){
        currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://robotsparebinindustries.com/#/");
    }

    @Test(priority = 2)
    public void logInToTheWebsite(){
        LogInForm logInForm = new LogInForm(driver);
        logInForm.enterCredentialsToLogInAndClickLogInButton();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sales-form")));
        Boolean isVisible;
        try{
            isVisible = driver.findElement(By.id("sales-form")).isDisplayed();
        }
        catch(Exception e){
            isVisible = false;
        }
        Assert.assertEquals(isVisible, true);
    }

    @Test(priority = 3)
    public void typeNameAndSelectSalesTargetAndSalesResultForNegativeResult(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.negativeSalesResultAndCheckPerformanceMessage();
        String resultMessage = driver.findElement(By.className("performance")).getText();
        Assert.assertEquals(resultMessage, "Hmm. Did not quite make it.");
    }

    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}
