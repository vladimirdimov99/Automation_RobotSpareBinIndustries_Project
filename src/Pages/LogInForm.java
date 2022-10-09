package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInForm {
    WebDriver driver;

    By username = By.id("username");
    By password = By.id("password");
    By logInButton = By.xpath("//*[text()='Log in']");

    public LogInForm(WebDriver driver) {this.driver = driver;}

    public void enterCredentialsToLogInAndClickLogInButton(){
        driver.findElement(username).click();
        driver.findElement(username).sendKeys("maria");
        driver.findElement(password).click();
        driver.findElement(password).sendKeys("thoushallnotpass");
        driver.findElement(logInButton).click();
    }
}
