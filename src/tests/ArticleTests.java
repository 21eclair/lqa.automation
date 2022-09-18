package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testArticleHasTitleElement() {
        String search_line_1    = "Java";
        String description_1    = "Object-oriented programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_1);
        SearchPageObject.clickByArticleWithSubstring(description_1);

        //Expecting error here, because we do not use waiting for element to appear:
        ArticlePageObject.assertArticleHasTitle();
    }
}
