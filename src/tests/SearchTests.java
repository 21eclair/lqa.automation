package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearchElementHasText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_input_text = SearchPageObject.getSearchInputText();
        assertEquals(
                "Found unexpected text in the search box!",
                "Search…",
                search_input_text
        );
    }

    @Test
    public void testCheckSeveralSearchResultsAndCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "Expected several search results, but only one found!",
                amount_of_search_results > 1
        );

        SearchPageObject.clearSearchLine();
        SearchPageObject.waitForNoSearchResultsShows();
    }
}
