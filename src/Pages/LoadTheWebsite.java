package Pages;

import org.openqa.selenium.WebDriver;

public class LoadTheWebsite {
    String websiteURL = "https://robotsparebinindustries.com/";

    public void LoadTheWebsite (WebDriver driver) {
        driver.get(websiteURL);
        driver.manage().window().maximize();
    }

}
