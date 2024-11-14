package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    WebDriver driver;
    protected final Log log;
    String osName = System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");

    @BeforeSuite
    public void initBeforeSuite() {
        deleteAllFileInFolder();
    }

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName, String appURL) {
        BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
//                options.addArguments(
//                         "user-agent='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.6422.77 Safari/537.36'");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("disable-infobars");

                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
                driver = new FirefoxDriver();
                break;

            case EDGE:
                if (osName.contains("linux") || osName.contains("Linux")) {
                    System.out.println(osName);
                    System.out.println(projectPath);
                    System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
                    System.out.println("osName: Linux!");
                } else if (osName.contains("windows") || osName.contains("Windows")) {
                    System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
                    System.out.println("osName: Windows!");
                }

                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Please enter the correct Browser name!");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get(appURL);
        return driver;
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }


    public void deleteAllFileInFolder() {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/target/allure-results";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print("Not found"+e.getMessage());
        }
    }
}
