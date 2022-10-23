package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInForm {
    WebDriver driver;

    By usernameFieldLocator = By.id("username");
    By passwordFieldLocator = By.id("password");
    By logInButton = By.cssSelector("button[type='submit']");

    public LogInForm(WebDriver driver) {this.driver = driver;}

    public void enterCredentialsToLogInAndClickLogInButton(String username, String password){
        driver.findElement(usernameFieldLocator).click();
        driver.findElement(usernameFieldLocator).sendKeys(username);
        driver.findElement(passwordFieldLocator).click();
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(logInButton).click();
    }
}
