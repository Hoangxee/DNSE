import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.*;

public class DNSE extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, GlobalConstants.URL);

        homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Description("Senses")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void senses(){
        sensesPage = homePage.openSensesPage();
        detailPage = sensesPage.searchAndNavigateDetailPage(searchData);
        detailPage.openSharePopupAndPrintLink();
        detailPage.verifyLink(DSELink);
        detailPage.closeSharePopup();
        sensesPage = detailPage.openSensesPage();
        detailPage = sensesPage.openDetailPageOf3rdInTopSearch();
        detailPage.printMaCoPhieu();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    HomePageObject homePage;
    SensesPageObject sensesPage;
    DetailPageObject detailPage;
    String searchData = "DS";
    String DSELink = "https://www.dnse.com.vn/senses/co-phieu-DSE";
}
