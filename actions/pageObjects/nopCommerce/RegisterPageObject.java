package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageIUs.nopcommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver=driver;
	
}

	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void enterToLastNameTextbox(String lastname) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastname);
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
		
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	public void clickToRegisterButton() {
		waitForElementClickble(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public boolean isSuccessMessageDisplay() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSGE);
		return isElementDisplay(driver, RegisterPageUI.REGISTER_SUCCESS_MESSGE);
	}
	
	public void ClickToContinue() {
		waitForElementClickble(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
		
	}
	public void clickToLogoutlink() {
		waitForElementClickble(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		
	}
	public LoginPageObject clickToLoginlink() {
		waitForElementClickble(driver, RegisterPageUI.LOGINT_LINK);
		clickToElement(driver, RegisterPageUI.LOGINT_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	
}
