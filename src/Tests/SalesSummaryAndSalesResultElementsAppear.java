package Tests;

import Pages.LoadTheWebsite;
import Pages.LogInForm;
import Pages.SalesForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SalesSummaryAndSalesResultElementsAppear {
    WebDriver driver;
    String currentURL = "";
    Duration timeout = Duration.ofSeconds(3);
    List<String> inputs = new ArrayList<>();

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

    //The elements Sales Summary and Sales Result should not be displayed if it's the first sale or the previous sales were deleted.
    @Test(priority = 3)
    public void checkIfSalesSummaryAndSalesResultElementsAppear(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.checkIfSalesSummaryAndSalesResultElementsAppear();
        Boolean isSalesSummaryElementDisplayed;
        Boolean isSalesResultElementDisplayed;
        try{
            isSalesSummaryElementDisplayed = driver.findElement(By.xpath("//div[contains(@class,'sales-summary')]")).isDisplayed();
            isSalesResultElementDisplayed = driver.findElement(By.id("sales-results")).isDisplayed();
        }
        catch (Exception e){
            isSalesSummaryElementDisplayed = false;
            isSalesResultElementDisplayed = false;
        }
        Assert.assertEquals(isSalesSummaryElementDisplayed, true);
        Assert.assertEquals(isSalesResultElementDisplayed, true);

        //Check The Sales Result
        inputs.add(driver.findElement(By.xpath("//*[text()='Vladimir']")).getText());
        inputs.add(driver.findElement(By.xpath("//*[text()='$20,000']")).getText());
        Assert.assertEquals(salesForm.inputs, inputs);
    }

    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}
