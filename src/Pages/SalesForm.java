package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SalesForm {
    WebDriver driver;
    Duration timeout = Duration.ofSeconds(3);

    public SalesForm(WebDriver driver) {this.driver = driver;}

    By firstName = By.id("firstname");
    By lastName = By.id("lastname");
    By salesTargetDropMenu = By.id("salestarget");
    By selectSalesTargetPrice = By.xpath("//*[text()='$20,000']");
    By salesResultField = By.id("salesresult");
    By submitButton = By.xpath("//*[text()='Submit']");
    By showPerformanceButton = By.xpath("//*[text()='Show performance']");
    By resultMessage = By.className("performance");
    By deleteAllSalesEntriesButton = By.xpath("//*[text()='Delete all sales entries']");

    public void positiveSalesResultAndCheckPerformanceMessage(){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.id("firstname")));
        driver.findElement(firstName).click();
        driver.findElement(firstName).sendKeys("Vladimir");
        driver.findElement(lastName).click();
        driver.findElement(lastName).sendKeys("Dimov");
        driver.findElement(salesTargetDropMenu).click();
        driver.findElement(selectSalesTargetPrice).click();
        driver.findElement(salesResultField).click();
        driver.findElement(salesResultField).sendKeys("50000");
        driver.findElement(submitButton).click();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(showPerformanceButton));
        driver.findElement(showPerformanceButton).click();
        driver.findElement(resultMessage).getText();
    }

    public void negativeSalesResultAndCheckPerformanceMessage(){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(By.id("firstname")));
        driver.findElement(firstName).click();
        driver.findElement(firstName).sendKeys("Vladimir");
        driver.findElement(lastName).click();
        driver.findElement(lastName).sendKeys("Dimov");
        driver.findElement(salesTargetDropMenu).click();
        driver.findElement(selectSalesTargetPrice).click();
        driver.findElement(salesResultField).click();
        driver.findElement(salesResultField).sendKeys("19000");
        driver.findElement(submitButton).click();
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(showPerformanceButton));
        driver.findElement(showPerformanceButton).click();
        driver.findElement(resultMessage).getText();
    }

    public void deleteAllSalesEntries(){
        driver.findElement(deleteAllSalesEntriesButton).click();
    }
}
