package pageObject;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.SensesPageUI;

import java.util.List;


public class SensesPageObject extends BasePage {
    private WebDriver driver;

    public SensesPageObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }

    @Step("Search and navigate to Detail page")
    public DetailPageObject searchAndNavigateDetailPage(String searchData) {
        Assert.assertTrue(getPageURL(driver).contains("senses"));

        waitForElementVisible(driver, SensesPageUI.SEARCH_BAR);
        sendKeyToElement(driver,SensesPageUI.SEARCH_BAR,searchData);

        selectItemInSearchBar(driver,SensesPageUI.SEARCH_ITEMS,"DSE");

        return PageGeneratorManager.getDetailPage(driver);
    }

    @Step("Navigate to Detail page the 3rd in Top Search")
    public DetailPageObject openDetailPageOf3rdInTopSearch() {
        waitForElementVisibleByIndex(driver,SensesPageUI.TOP_SEARCH,2);
        clickToListElementByIndex(driver,SensesPageUI.TOP_SEARCH,2);

        return PageGeneratorManager.getDetailPage(driver);
    }
}
