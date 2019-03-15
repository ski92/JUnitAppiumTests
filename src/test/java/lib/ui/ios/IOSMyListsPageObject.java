package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

  static {
    ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    ARTICLE_BY_DESCRIPTION_TPL = "//XCUIElementTypeLink[contains(@name,'{SUBTITLE}')]";
  }

  public IOSMyListsPageObject(AppiumDriver driver) {
    super(driver);
  }
}
