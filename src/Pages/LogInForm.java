package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInForm {
    WebDriver driver;

    By username = By.id("username");
    By password = By.id("password");
    //By logInButton = By.xpath("//button[@type='submit']");
    By logInButton = By.cssSelector("button[type='submit']");

    public LogInForm(WebDriver driver) {this.driver = driver;}

    public void enterCredentialsToLogInAndClickLogInButton(){
        driver.findElement(username).click();
        driver.findElement(username).sendKeys("maria");  // Make entered values as variables of the class
        driver.findElement(password).click();
        driver.findElement(password).sendKeys("thoushallnotpass");  // Make entered values as variables of the class
        driver.findElement(logInButton).click();
    }
}
