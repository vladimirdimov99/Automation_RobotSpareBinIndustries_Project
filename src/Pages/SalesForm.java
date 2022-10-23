package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SalesForm {
    WebDriver driver;
    Duration timeout = Duration.ofSeconds(3);

    By firstNameInputFieldLocator = By.id("firstname");
    By lastNameInputFieldLocator = By.id("lastname");
    By salesTargetDropMenu = By.id("salestarget");
    public By selectSalesTargetPrice = By.xpath("//*[text()='$20,000']");
    By salesResultField = By.id("salesresult");
    By submitButton = By.cssSelector("button[type='submit']");
    By showPerformanceButton = By.xpath("//*[text()='Show performance']");
    By deleteAllSalesEntriesButton = By.xpath("//*[text()='Delete all sales entries']");
    public By salesFormPanelLocator = By.id("sales-form");
    public By performanceMessageLocator = By.className("performance");
    public By errorMessageLocator = By.xpath("//*[text()='Please fill out this field.']");
    public By salesSummaryPanelLocator = By.xpath("//div[contains(@class,'sales-summary')]");
    public By salesResultPanelLocator = By.id("sales-results");

    public SalesForm(WebDriver driver) {this.driver = driver;}

    public void enterSalesFormDataAndClickSubmit(String firstName, String lastName, String salesResult){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(lastNameInputFieldLocator));
        driver.findElement(firstNameInputFieldLocator).click();
        driver.findElement(firstNameInputFieldLocator).sendKeys(firstName);
        driver.findElement(lastNameInputFieldLocator).click();
        driver.findElement(lastNameInputFieldLocator).sendKeys(lastName);
        driver.findElement(salesTargetDropMenu).click();
        driver.findElement(selectSalesTargetPrice).click();
        driver.findElement(salesResultField).click();
        driver.findElement(salesResultField).sendKeys(salesResult);
        driver.findElement(submitButton).click();
    }

    public void checkPerformanceMessage(){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(showPerformanceButton));
        driver.findElement(showPerformanceButton).click();
    }

    public void deleteAllSalesEntries(){
        driver.findElement(deleteAllSalesEntriesButton).click();
    }
}
