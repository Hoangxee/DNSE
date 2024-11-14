package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private static BasePage basePage;
    protected final Log log;
    protected BasePage(){
        log = LogFactory.getLog(getClass());
    }

    public WebElement getWebElement(WebDriver driver, String locatorType){
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType){
        return driver.findElements(getByLocator(locatorType));
    }

    private By getByLocator(String locatorType){
        By by = null;
        if(locatorType.startsWith("xpath")||locatorType.startsWith("XPATH")||locatorType.startsWith("Xpath")||locatorType.startsWith("XPath")){
            by = By.xpath(locatorType.substring(6));
        } else if(locatorType.startsWith("id")||locatorType.startsWith("ID")||locatorType.startsWith("Id")){
            by = By.id(locatorType.substring(3));
        } else if(locatorType.startsWith("css")||locatorType.startsWith("CSS")||locatorType.startsWith("Css")){
            by = By.cssSelector(locatorType.substring(4));
        } else if(locatorType.startsWith("name")||locatorType.startsWith("NAME")||locatorType.startsWith("Name")){
            by = By.name(locatorType.substring(5));
        } else if(locatorType.startsWith("class")||locatorType.startsWith("CLASS")||locatorType.startsWith("Class")){
            by = By.className(locatorType.substring(6));
        } else{
            throw new RuntimeException("Locator type is not supported!!");
        }
        return by;
    }

    public void clickToElement(WebDriver driver, String locatorType){
        getWebElement(driver, locatorType).click();
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String valueToSendKey){
        getWebElement(driver, locatorType).clear();
        getWebElement(driver, locatorType).sendKeys(valueToSendKey);
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public String getAttribute(WebDriver driver, String locatorType, String attributeName){
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getTextInElement(WebDriver driver, String locatorType){
        return getWebElement(driver,locatorType).getText();
    }

    public void waitForElementVisible(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementPresence(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorType)));
    }

    public String getPageURL(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public void clickToListElementByIndex(WebDriver driver, String locatorType, int index){
        getListWebElement(driver, locatorType).get(index).click();
    }

    public void waitForElementVisibleByIndex(WebDriver driver, String locatorType, int index){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locatorType).get(index)));
    }

    public void selectItemInSearchBar(WebDriver driver, String childLocator,String expectedItem){
        waitForElementPresence(driver,childLocator);
        List<WebElement> allNumberDropdown = getListWebElement(driver, childLocator);

        for (WebElement item:allNumberDropdown) {
            if(item.getText().equals(expectedItem)){
                item.click();
                break;
            }
        }
    }
}
