package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {
    private static final String
                SEARCH_INIT_ELEMENT             = "org.wikipedia:id/search_container",
                SEARCH_INPUT                    = "org.wikipedia:id/search_src_text",
                SEARCH_CANCEL_BUTTON            = "org.wikipedia:id/search_close_btn",
                SEARCH_RESULT                   = "org.wikipedia:id/search_results_list",
                SEARCH_RESULT_BY_SUBSTRING_TPL  = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
                SEARCH_RESULT_ELEMENT           = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
                SEARCH_EMPTY_RESULT_LABEL       = "//*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find element to initialize search!"
        );
        this.waitForElementAndClick(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot click element to initialize search!",
                5
        );
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(
                By.id(SEARCH_INPUT),
                search_line,
                "Cannot find search input to send keys!",
                5
        );
    }

    public void clearSearchLine() {
        waitForElementAndClear(
                By.id(SEARCH_INPUT),
                "Cannot find search box to clear!",
                5
        );
    }

    public String getSearchInputText() {
        WebElement search_input_element = waitForElementPresent(
                By.id(SEARCH_INPUT),
                "Cannot find search input",
                5
        );
        return search_input_element.getAttribute("text");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find button to cancel search!",
                5
        );
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present!",
                5
        );
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot click on cancel search button!",
                5
        );
    }

    public void waitForSearchResults() {
        this.waitForElementPresent(
                By.id(SEARCH_RESULT),
                "Cannot find search results!",
                15
        );
    }

    public void waitForNoSearchResultsShows() {
        this.waitForElementNotPresent(
                By.id(SEARCH_RESULT),
                "Some search results still shows!",
                15
        );
    }

    public void waitForSearchResultsWithSubstring(String substring) {
        String search_result = getResultSearchElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result),
                "Cannot find search results with substring: '" + substring + "'!",
                15
        );
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result),
                "Cannot find and click search result with substring: '" + substring + "'!",
                15
        );
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request!",
                15
        );
        return this.getAmountOfElements(
                By.xpath(SEARCH_RESULT_ELEMENT)
        );
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_LABEL),
                "Cannot find empty results element!",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results!"
        );
    }

}
