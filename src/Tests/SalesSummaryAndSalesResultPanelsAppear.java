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

public class SalesSummaryAndSalesResultPanelsAppear {
    WebDriver driver;
    String currentURL = "";
    String expectedURL = "";
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
        Assert.assertTrue(isVisible, "The Sales Form Panel is not displayed !!!");
    }

    @Test(priority = 3)
    public void enterSalesFormDataAndClickSubmitButton(){
        SalesForm salesForm = new SalesForm(driver);
        salesForm.enterSalesFormDataAndClickSubmit("Vladimir", "Dimov", "50000");
        currentURL = driver.getCurrentUrl();
        expectedURL = "https://robotsparebinindustries.com/?firstname=Vladimir&lastname=Dimov&salesresult=50000#/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    //The Sales Summary and Sales Result Panels should be displayed when a sale is made.
    @Test(priority = 4)
    public void checkIfSalesSummaryAndSalesResultPanelsAppear(){
        SalesForm salesForm = new SalesForm(driver);
        Boolean isSalesSummaryPanelDisplayed;
        Boolean isSalesResultPanelDisplayed;
        try{
            isSalesSummaryPanelDisplayed = driver.findElement(salesForm.salesSummaryPanelLocator).isDisplayed();
            isSalesResultPanelDisplayed = driver.findElement(salesForm.salesResultPanelLocator).isDisplayed();
        }
        catch (Exception e){
            isSalesSummaryPanelDisplayed = false;
            isSalesResultPanelDisplayed = false;
        }
        Assert.assertTrue(isSalesSummaryPanelDisplayed, "The Sales Summary Panel is not displayed !!!");
        Assert.assertTrue(isSalesResultPanelDisplayed, "The Sales Result Panel is not displayed !!!");
    }

    @AfterTest
    public void closeTheWebsite() {driver.quit();}
}
