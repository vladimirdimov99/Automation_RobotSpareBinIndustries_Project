package Tests;

import Pages.SalesForm;
import Pages.LoadTheWebsite;
import Pages.LogInForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PositiveSalesResult {
    WebDriver driver;
    String currentURL = "";

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
    }

    @Test(priority = 3)
    public void typeNameAndSelectSalesTargetAndSalesResultForPositiveResult(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.positiveSalesResultAndCheckPerformanceMessage();
        String resultMessage = driver.findElement(By.className("performance")).getText();
        Assert.assertEquals(resultMessage, "A positive result. Well done!");
    }


    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}
