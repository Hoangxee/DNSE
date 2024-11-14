package commons;

import org.openqa.selenium.WebDriver;
import pageObject.*;

public class PageGeneratorManager extends BasePage {
    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }

    public static SensesPageObject getSensesPage(WebDriver driver){
        return new SensesPageObject(driver);
    }

    public static DetailPageObject getDetailPage(WebDriver driver) {
        return new DetailPageObject(driver);
    }
}
