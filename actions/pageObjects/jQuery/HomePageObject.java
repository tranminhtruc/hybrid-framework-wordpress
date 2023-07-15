package pageObjects.jQuery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void opentPageByNumber(String pageNumber) {
		waitForElementClickble(driver, HomePageUI.HOME_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.HOME_PAGE_BY_NUMBER, pageNumber);
	}

	public boolean isPageActiveByNumber(String pageNumber) {
		waitForElementClickble(driver, HomePageUI.HOME_PAGE_ACTIVE_BY_NUMBER, pageNumber);
		return isElementDisplay(driver, HomePageUI.HOME_PAGE_ACTIVE_BY_NUMBER, pageNumber);
	}

	public void inputToHeaderTextboxByLabel(String labelName, String value) {
		waitForElementClickble(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, labelName);
		senkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, value, labelName);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME,Keys.ENTER,labelName );
	}

	public boolean areRowRecordDisplayed(String female, String countryName, String male, String total) {
		waitForElementVisible(driver, HomePageUI.ROW_VALUE_BY_ALL_FIELD, female,countryName,male,total);
		return isElementDisplay(driver, HomePageUI.ROW_VALUE_BY_ALL_FIELD, female,countryName,male,total);
	}
}
