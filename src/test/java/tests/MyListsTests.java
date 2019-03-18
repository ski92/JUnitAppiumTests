package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

  private static final String NAME_OF_FOLDER = "Learning programming",
          SUBTITLE = "Description of a type of XML document",
          FIRST_ARTICLE = "XML",
          FOLDER_FOR_TWO_ARTICLES = "Markup language",
          SECOND_ARTICLE = "XML schema",
          LOGIN = "Kompanetzzz",
          PASSWORD = "ibhJdz5tih9CFTa";

  @Test
  public void testSaveFirstArticleToMyList() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
    ArticlePageObject.waitForTitleElement();
    String article_title = ArticlePageObject.getArticleTitle();

    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
    } else {
      ArticlePageObject.addArticlesToMySaved();
    }
    if (Platform.getInstance().isMW()) {
      AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
      Auth.clickAuthButton();
      Auth.enterLoginData(LOGIN, PASSWORD);
      Auth.submitForm();

      ArticlePageObject.waitForTitleElement();

      assertEquals("We are not on the same page after login.",
              article_title,
              ArticlePageObject.getArticleTitle()
      );
      ArticlePageObject.addArticlesToMySaved();
    }
    ArticlePageObject.closeArticle();
    NavigationUI NavigationUI = NavigationUIFactory.get(driver);
    NavigationUI.openNavigation();
    NavigationUI.clickMyList();

    MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

    if (Platform.getInstance().isAndroid()) {
      MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
    }
    MyListsPageObject.swipeByArticleToDelete(article_title);
    MyListsPageObject.waitForTitleToDisappearByTitle(article_title);
  }

  @Test
  public void testSaveTwoArticlesToMyList() {

    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine(FIRST_ARTICLE);
    SearchPageObject.clickByArticleWithSubstring(FIRST_ARTICLE);

    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
    ArticlePageObject.waitForTitleElement();
    String article_title = ArticlePageObject.getArticleTitle();

    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToMyList(FOLDER_FOR_TWO_ARTICLES);
    } else {
      ArticlePageObject.addArticlesToMySaved();
    }

    ArticlePageObject.closeArticle();

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine(SECOND_ARTICLE);
    SearchPageObject.clickByArticleWithSubstring(SECOND_ARTICLE);

    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToExistingList(FOLDER_FOR_TWO_ARTICLES);
    } else {
      ArticlePageObject.addArticlesToMySaved();
    }

    NavigationUI NavigationUI = NavigationUIFactory.get(driver);
    NavigationUI.clickMyList();

    MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

    if (Platform.getInstance().isAndroid()) {
      MyListsPageObject.openFolderByName(FOLDER_FOR_TWO_ARTICLES);
    }

    MyListsPageObject.swipeByArticleToDelete(FIRST_ARTICLE);
    MyListsPageObject.waitForTitleToDisappearByTitle(article_title);

    assertNotNull(
            "Cannot find saved article that contains " + SUBTITLE + " in description",
            MyListsPageObject.getArticleByDescription(SUBTITLE));
  }
}
