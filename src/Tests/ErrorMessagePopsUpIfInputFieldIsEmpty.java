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

public class ErrorMessagePopsUpIfInputFieldIsEmpty {
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
        logInForm.enterCredentialsToLogInAndClickLogInButton("maria", "thoushallnotpass");
        SalesForm salesForm = new SalesForm(driver);
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salesForm.salesFormPanelLocator));

        Boolean isVisible;
        try{
            isVisible = driver.findElement(salesForm.salesFormPanelLocator).isDisplayed();
        }
        catch(Exception e){
            isVisible = false;
        }
        Assert.assertTrue(isVisible, "The Sales Form is not displayed!!!");
    }

    @Test(priority = 3)
    public void checkIfErrorMessagesPopsUpIfInputFieldIsEmpty(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.doASaleWithAnEmptyInputField();

        Boolean isErrorMessageDisplayed;
        try{
            isErrorMessageDisplayed = driver.findElement(salesForm.errorMessageLocator).isDisplayed();
        }
        catch (Exception e){
            isErrorMessageDisplayed = false;
        }
        Assert.assertTrue(isErrorMessageDisplayed, "The Error Message is not displayed !!!");
    }

    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}
