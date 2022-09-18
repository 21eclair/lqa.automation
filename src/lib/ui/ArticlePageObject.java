package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
                TITLE                           = "org.wikipedia:id/view_page_title_text",
                FOOTER_ELEMENT                  = "//*[@text='View page in browser']",
                OPTIONS_BUTTON                  = "//android.widget.ImageView[@content-desc='More options']",
                OPTIONS_ADD_TO_MY_LIST_BUTTON   = "//*[@text='Add to reading list']",
                ADD_TO_MY_LIST_OVERLAY          = "org.wikipedia:id/onboarding_button",
                MY_LIST_NAME_INPUT              = "org.wikipedia:id/text_input",
                MY_LIST_EXISTING_NAME_TPL       = "//*[@text='{FOLDER_NAME}']",
                MY_LIST_OK_BUTTON               = "//*[@text='OK']",
                CLOSE_ARTICLE_BUTTON            = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getListNameXpath(String folder_name) {
        return MY_LIST_EXISTING_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                By.id(TITLE),
                "Cannot find article's title on page!",
                15
        );
    }

    public void assertArticleHasTitle() {
        assertElementPresent(
                By.id(TITLE),
                "Cannot find article's title element!"
        );
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end (footer element) of article!",
                 20
        );
    }

    public void addArticleToNewList(String folder_name) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article's options!",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list!",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay!",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of reading list!",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                folder_name,
                "Cannot put text into list's name input!",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press 'OK' button to create reading list!!",
                5
        );
    }

    public void addArticleToExistedList(String folder_name) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article's options!",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list!",
                5
        );
        String folder_name_xpath = getListNameXpath(folder_name);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find reading list with name: " + folder_name + "!",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, X-link not found!",
                5
        );
    }
}
