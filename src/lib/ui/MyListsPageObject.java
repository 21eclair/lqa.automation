package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    private static final String
                FOLDER_BY_NAME_TPL      = "//*[@text='{FOLDER_NAME}']",
                ARTICLE_BY_TITLE_TPL    = "//*[@text='{ARTICLE_TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String folder_name) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }

    public void openFolderByName(String folder_name) {
        String folder_name_xpath = getFolderXpathByName(folder_name);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find reading list with name '" + folder_name + "'!",
                15
        );
    }

    public void openArticleByName(String article_name) {
        String article_name_xpath = getSavedArticleXpathByTitle(article_name);
        this.waitForElementAndClick(
                By.xpath(article_name_xpath),
                "Cannot find article with name '" + article_name + "'!",
                5
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_title_xpath),
                "Cannot find saved article with name '" + article_title + "' to swipe!"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_title_xpath),
                "Saved article still present with title '" + article_title + "!",
                10
        );
    }

    public void waitForArticleAppearByTitle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_title_xpath),
                "Cannot find saved article by title '" + article_title + "!",
                10
        );
    }
}
