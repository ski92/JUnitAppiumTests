package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwArticlePageObject extends ArticlePageObject {
  static {
    TITLE = "css:#content h1";
    FOOTER_ELEMENT = "css:footer";
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions a#ca-watch";
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:mw-ui-icon-mf-watched watched";
  }

  public MwArticlePageObject(RemoteWebDriver driver) {
    super(driver);
  }
}
