package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ListsTests extends CoreTestCase {
    @Test
    public void testSaveArticlesInMyListThenDeleteOneArticleAndCheckOtherArticle() {
        String search_line_1    = "Java";
        String article_name_1   = "Java (programming language)";
        String description_1    = "Object-oriented programming language";
        String search_line_2    = "Appium";
        String article_name_2   = "Appium";
        String description_2    = "Automation for Apps";
        String list_name        = "lqaAutomation";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);


        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_1);
        SearchPageObject.clickByArticleWithSubstring(description_1);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToNewList(list_name);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_2);
        SearchPageObject.clickByArticleWithSubstring(description_2);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExistedList(list_name);
        ArticlePageObject.closeArticle();

        NavigationUI.clickMyLists();

        MyListsPageObject.openFolderByName(list_name);
        MyListsPageObject.swipeByArticleToDelete(article_name_1);
        MyListsPageObject.openArticleByName(article_name_2);

        String title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Found unexpected text in article's title!",
                article_name_2,
                title
        );
    }
}
