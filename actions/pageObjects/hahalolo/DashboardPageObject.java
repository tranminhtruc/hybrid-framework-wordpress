package pageObjects.hahalolo;

import org.openqa.selenium.WebDriver;

import PageUIs.hahalolo.DashboardPageUI;
import commons.BasePage;

public class DashboardPageObject extends BasePage {
	WebDriver driver;
	public DashboardPageObject(WebDriver driver) {
			this.driver = driver;
	}
	public boolean isDashboardHeaderTextDisplay() {
		waitForElementVisible(driver,DashboardPageUI.DASHBOARD_HEADER_TEXT);
		return isElementDisplay(driver, DashboardPageUI.DASHBOARD_HEADER_TEXT);
	}

}
