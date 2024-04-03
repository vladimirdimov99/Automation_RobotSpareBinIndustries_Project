package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoadTheDriver;

import java.time.Duration;

public class SalesForm extends LoadTheDriver {

    Duration timeout = Duration.ofSeconds(3);

    By firstNameInputFieldLocator = By.id("firstname");
    By lastNameInputFieldLocator = By.id("lastname");
    By salesTargetDropMenu = By.id("salestarget");
    public By selectSalesTargetPrice = By.xpath("//*[text()='$20,000']");
    By salesResultField = By.id("salesresult");
    By submitButton = By.cssSelector("button[type='submit']");
    public By performanceButton = By.xpath("//*[text()='Show performance']");
    public By hidePerformanceButton = By.xpath("//*[text()='Hide performance']");
    By deleteAllSalesEntriesButton = By.xpath("//*[text()='Delete all sales entries']");
    public By salesFormPanelLocator = By.id("sales-form");
    public By performanceMessageLocator = By.className("performance");
    public By errorMessageLocator = By.xpath("//*[text()='Please fill out this field.']");
    public By salesSummaryPanelLocator = By.xpath("//div[contains(@class,'sales-summary')]");
    public By salesResultPanelLocator = By.id("sales-results");

    public void enterSalesFormDataAndClickSubmit(String firstName, String lastName, String salesResult) {
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(lastNameInputFieldLocator));
        getDriver().findElement(firstNameInputFieldLocator).click();
        getDriver().findElement(firstNameInputFieldLocator).sendKeys(firstName);
        getDriver().findElement(lastNameInputFieldLocator).click();
        getDriver().findElement(lastNameInputFieldLocator).sendKeys(lastName);
        getDriver().findElement(salesTargetDropMenu).click();
        getDriver().findElement(selectSalesTargetPrice).click();
        getDriver().findElement(salesResultField).click();
        getDriver().findElement(salesResultField).sendKeys(salesResult);
        getDriver().findElement(submitButton).click();
    }

    public void checkPerformanceMessage() {
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(performanceButton));
        getDriver().findElement(performanceButton).click();
    }

    public void deleteAllSalesEntries() {
        getDriver().findElement(deleteAllSalesEntriesButton).click();
    }
}
