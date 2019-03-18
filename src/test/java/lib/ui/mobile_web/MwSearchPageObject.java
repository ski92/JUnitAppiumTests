package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwSearchPageObject extends SearchPageObject {
  static {
    SEARCH_INIT_ELEMENT = "css:button#searchIcon";
    SEARCH_INPUT = "css:form>input[type='search']";
    SEARCH_CANCEL_BUTTON = "css:button.cancel";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[@class='wikidata-description'][contains(text(),'{SUBSTRING}')]";
    SEARCH_RESULT_ELEMENT = "css:ul.mw-search-results>li.mw-search-result";
    EMPTY_RESULT_LABEL = "css:p.mw-search-nonefound";
  }

  public MwSearchPageObject(RemoteWebDriver driver) {
    super(driver);
  }
}

