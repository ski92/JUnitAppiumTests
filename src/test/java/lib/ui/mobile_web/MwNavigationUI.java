package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwNavigationUI extends NavigationUI {
  static
  {
    MY_LIST_LINK = "css:a[data-event-name=watchlist]";
    OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
  }

  public MwNavigationUI(RemoteWebDriver driver)
  {
    super(driver);
  }
}
