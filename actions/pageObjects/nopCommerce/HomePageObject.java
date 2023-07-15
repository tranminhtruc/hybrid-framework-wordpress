package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageIUs.nopcommerce.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	PageGeneratorManager pageGenerator;
	public HomePageObject(WebDriver driver) {
		this.driver=driver;
		pageGenerator= new PageGeneratorManager();
	}

	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickble(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return pageGenerator.getRegisterPage(driver);
	}	

	public LoginPageObject clickToLoginLink() {
		waitForElementVisible(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return pageGenerator.getLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplay(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
	public CustomerInforPageObject clickToMyAccountLink() {
		waitForElementClickble(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerPage(driver);
	}


}
