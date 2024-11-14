package pageObject;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.HomePageUI;

import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Click Senses in Thi Truong dropdown")
    public SensesPageObject openSensesPage() {
        waitForElementClickable(driver,HomePageUI.THI_TRUONG_DROPDOWN);
        clickToElement(driver,HomePageUI.THI_TRUONG_DROPDOWN);

        waitForElementClickable(driver,HomePageUI.THI_TRUONG_DROPDOWN);
        clickToElement(driver,HomePageUI.THI_TRUONG_DROPDOWN);

        String status = getAttribute(driver,HomePageUI.THI_TRUONG_DROPDOWN_STATUS,"class");

        if(status.contains("open")){
            waitForElementClickable(driver,HomePageUI.SENSES_ITEM);
            clickToElement(driver,HomePageUI.SENSES_ITEM);
        }

        return PageGeneratorManager.getSensesPage(driver);
    }


}
