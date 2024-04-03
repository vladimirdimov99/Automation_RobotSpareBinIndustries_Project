package Pages;

import org.openqa.selenium.By;
import utils.LoadTheDriver;

public class LogInForm extends LoadTheDriver {

    By usernameFieldLocator = By.id("username");
    By passwordFieldLocator = By.id("password");
    By logInButton = By.cssSelector("button[type='submit']");

    public void enterCredentialsToLogInAndClickLogInButton(String username, String password) {
        getDriver().findElement(usernameFieldLocator).click();
        getDriver().findElement(usernameFieldLocator).sendKeys(username);
        getDriver().findElement(passwordFieldLocator).click();
        getDriver().findElement(passwordFieldLocator).sendKeys(password);
        getDriver().findElement(logInButton).click();
    }
}
