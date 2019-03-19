package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

  public MyListsPageObject(RemoteWebDriver driver) {
    super(driver);
  }

  protected static String
          FOLDER_BY_NAME_TPL,
          ARTICLE_BY_TITLE_TPL,
          ARTICLE_BY_DESCRIPTION_TPL,
          REMOVE_FROM_SAVED_BUTTON;

  private static String getFolderXpathByName(String name_of_folder) {
    return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
  }

  private static String getSavedArticleXpathByTitle(String article_title) {
    return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
  }

  private static String getRemoveButtonByTitle(String article_title) {
    return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
  }

  private static String getSavedArticleXpathByDescription(String article_subtitle) {
    return ARTICLE_BY_DESCRIPTION_TPL.replace("{SUBTITLE}", article_subtitle);
  }

  public void openFolderByName(String name_of_folder) {
    String folder_name_xpath = getFolderXpathByName(name_of_folder);
    this.waitForElementAndClick(
            folder_name_xpath,
            "Cannot find created folder",
            5
    );
  }

  public void waitForTitleToAppearByTitle(String article_title) {
    String article_xpath = getSavedArticleXpathByTitle(article_title);
    this.waitForElementPresent(
            article_xpath,
            "Cannot find saved article by Xpath",
            5
    );
  }

  public WebElement getArticleByDescription(String article_subtitle) {
    String article_xpath = getSavedArticleXpathByDescription(article_subtitle);

    return this.waitForElementPresent(
            article_xpath,
            "Cannot find saved article by Xpath",
            5
    );
  }

  public void swipeByArticleToDelete(String article_title) {
    this.waitForTitleToAppearByTitle(article_title);
    String article_xpath = getSavedArticleXpathByTitle(article_title);

    if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
      this.swipeElementToLeft(
              article_xpath,
              "Cannot find saved article"
      );
    } else {
      String remove_locator = getRemoveButtonByTitle(article_title);
      this.waitForElementAndClick(
              remove_locator,
              "Cannot click button to remove article from saved.",
              10
      );
      driver.navigate().refresh();
    }
    if (Platform.getInstance().isIOS()) {
      this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
    }
    if (Platform.getInstance().isMW()) {
      driver.navigate().refresh();
    }
  }

  public void waitForTitleToDisappearByTitle(String article_title) {
    String article_xpath = getSavedArticleXpathByTitle(article_title);
    this.waitForElementNotPresent(
            article_xpath,
            "Cannot delete saved article",
            5
    );
  }
}
