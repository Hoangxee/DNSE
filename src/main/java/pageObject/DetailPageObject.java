package pageObject;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.DetailPageUI;
import pageUIs.HomePageUI;
import pageUIs.SensesPageUI;


public class DetailPageObject extends BasePage {
    private WebDriver driver;

    public DetailPageObject(WebDriver mappingDriver){
        driver = mappingDriver;
    }


    @Step("Click to Share button and print link")
    public void openSharePopupAndPrintLink() {
        waitForElementClickable(driver, DetailPageUI.SHARE_BTN);
        clickToElement(driver, DetailPageUI.SHARE_BTN);

        waitForElementVisible(driver,DetailPageUI.SHARE_LINK);
        System.out.println("Share link: "+getTextInElement(driver,DetailPageUI.SHARE_LINK));
    }

    @Step("Verify share link")
    public void verifyLink(String link) {
        waitForElementVisible(driver,DetailPageUI.SHARE_LINK);
        Assert.assertEquals(getTextInElement(driver,DetailPageUI.SHARE_LINK),link);
    }

    @Step("Close Share popup")
    public void closeSharePopup() {
        waitForElementClickable(driver,DetailPageUI.CLOSE_ICON);
        clickToElement(driver,DetailPageUI.CLOSE_ICON);
    }

    @Step("Click Senses in Thi Truong dropdown")
    public SensesPageObject openSensesPage() {
        waitForElementClickable(driver, HomePageUI.THI_TRUONG_DROPDOWN);
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

    @Step("Print Ma co phieu")
    public void printMaCoPhieu() {
        waitForElementVisible(driver,DetailPageUI.MA_CO_PHIEU);
        System.out.println("Ma co phieu: "+getTextInElement(driver,DetailPageUI.MA_CO_PHIEU));
    }
}
