import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class AdvancedTests {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/illuminate/Desktop/lqa/lqaAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSaveArticlesInMyListThenDeleteOneArticleAndCheckOtherArticle() {
        String search_line_1    = "Java";
        String article_name_1   = "Java (programming language)";
        String description_1    = "Object-oriented programming language";
        String search_line_2    = "Appium";
        String article_name_2   = "Appium";
        String description_2    = "Automation for Apps";
        String list_name        = "lqaAutomation";

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input field!",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line_1,
                "Cannot find search box to send keys!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + description_1 + "']"),
                "Cannot find article by description '" + description_1 + "' to click!",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article's title element!",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article's options!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list!",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay!",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of reading list!",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                list_name,
                "Cannot put text into list's name input!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button to create reading list!!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, X-link not found!",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input field!",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line_2,
                "Cannot find search box to send keys!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + description_2 + "']"),
                "Cannot find article by description '" + description_2 + "'to click!",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article's title element!",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article's options!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + list_name + "']"),
                "Cannot find reading list with name: " + list_name + "!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, X-link not found!",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to 'My lists'!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + list_name + "']"),
                "Cannot find reading list with name '" + list_name + "'!",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@text='" + article_name_1 + "']"),
                "Cannot find saved article with name '" + article_name_1 + "' on the screen!",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + article_name_1 + "']"),
                "Cannot find saved article with name '" + article_name_1 + "' to swipe!"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + article_name_1 + "']"),
                "Cannot delete saved article with name '" + article_name_1 + "!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + article_name_2 + "']"),
                "Cannot find saved article with name '" + article_name_2 + "' on the screen!",
                5
        );

        String title = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article's title element!",
                10
        );

        Assert.assertEquals(
                "Found unexpected text in article's title!",
                article_name_2,
                title
        );
    }

    @Test
    public void testArticleHasTitleElement() {
        String search_line_1    = "Java";
        String description_1    = "Object-oriented programming language";

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input field!",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line_1,
                "Cannot find search box to send keys!",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + description_1 + "']"),
                "Cannot find article by description '" + description_1 + "' to click!",
                15
        );

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article's title element!"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeout_in_seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeout_in_seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private List<WebElement> waitForAllElementsPresent(By by, String error_message, long timeout_in_seconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeout_in_seconds) {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeout_in_seconds) {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeout_in_seconds) {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.clear();
        return element;
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeout_in_seconds){
        WebElement element = waitForElementPresent(
                by,
                error_message,
                timeout_in_seconds
        );
        return element.getAttribute(attribute);
    }

    private void assertElementHasText(By by, String expected_text, String error_message) {
        String text = waitForElementPresent(
                by,
                "Cannot locate an element to retrieve text!"
        ).getAttribute("text");

        Assert.assertEquals(
                error_message,
                expected_text,
                text
        );
    }

    private void assertElementPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        String default_message = "An element '" + by.toString() + "' suppose to be present!";
        if (amount_of_elements == 0) {
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        String default_message = "An element '" + by.toString() + "' suppose to be not present!";
        if (amount_of_elements > 0) {
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUp(int time_of_swipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(time_of_swipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;

        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(
                        by,
                        "Cannot find element by swiping up \n" + error_message,
                        0
                );
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }
}
