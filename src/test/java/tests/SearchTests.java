package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

  @Test
  public void testSearch() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.waitForSearchResult("Object-oriented programming language");
  }

  @Test
  public void testCancelSearch() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
    SearchPageObject.initSearchInput();
    SearchPageObject.waitForCancelButtonToAppear();
    SearchPageObject.clickCancelSearch();
    SearchPageObject.waitForCancelButtonToDisappear();
  }

  @Test
  public void testComparePlaceholder() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
    SearchPageObject.initSearchInput();
  }

  @Test
  public void testCheckSeveralArticles() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Autotest";
    SearchPageObject.typeSearchLine(search_line);


    assertTrue("There is just one search result", SearchPageObject.getAmountOfFoundArticles() > 1);

    SearchPageObject.waitForCancelButtonToAppear();
    SearchPageObject.clickCancelSearch();

    SearchPageObject.assertThereIsNoResultsOfSearch();
  }

  @Test
  public void testAmountOfNoneEmptySearch() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Linkin Park discography";
    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.getAmountOfFoundArticles();
    int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
    assertTrue(
            "We found to few results",
            amount_of_search_results > 0
    );
  }

  @Test
  public void testAmountOfEmptySearch() {

    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    String search_line = "dfgdfgrg";
    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.waitForEmptyResultsLabel();
    SearchPageObject.assertThereIsNoResultsOfSearch();
  }
}
