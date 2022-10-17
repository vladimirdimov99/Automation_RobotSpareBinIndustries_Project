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

public class DeleteAllSalesEntries {
    WebDriver driver;
    String currentURL = "";
    Duration timeout = Duration.ofSeconds(3);

    @BeforeTest
    public void OpenTheWebsite(){
        driver = new ChromeDriver();
        new LoadTheWebsite().LoadTheWebsite(driver);
    }

    @Test(priority = 1) // Exists more than once - DRY
    public void checkIfTheWebsiteIsCorrect(){
        currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://robotsparebinindustries.com/#/");
    }

    @Test(priority = 2) // Exists more than once - DRY
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
        //Assert.assertEquals(isVisible, true); // Ambiguous method call
        Assert.assertTrue(isVisible,"[Error] false");
    }

    @Test(priority = 3) // Exists more than once - DRY
    public void typeNameAndSelectSalesTargetAndSalesResultForPositiveResult(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.positiveSalesResultAndCheckPerformanceMessage();
        String resultMessage = driver.findElement(By.className("performance")).getText();
        Assert.assertEquals(resultMessage, "A positive result. Well done!");
    }

    @Test(priority = 4) // Exists more than once - DRY
    public void typeNameAndSelectSalesTargetAndSalesResultForNegativeResult(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.negativeSalesResultAndCheckPerformanceMessage();
        String resultMessage = driver.findElement(By.className("performance")).getText();
        Assert.assertEquals(resultMessage, "Hmm. Did not quite make it.");
    }

    @Test(priority = 5) // Can't exist on its own - to be fixed
    public void deleteAllSalesEntries(){
        // neither there is a description of the method logic nor comments, don't know what to expect and how it should perform
        SalesForm salesForm = new SalesForm(driver);
        salesForm.deleteAllSalesEntries();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'sales-summary')]"))); // Locator
        Boolean isTrue;
        try{
            isTrue = driver.findElement(By.xpath("//div[contains(@class,'sales-summary')]")).isDisplayed();  // Locator
        }
        catch(Exception e){
            isTrue = false;
        }
        // Ambiguous method call
        Assert.assertFalse(isTrue);
    }

    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}
