package pageObjects.hahalolo;

import org.openqa.selenium.WebDriver;

import PageUIs.hahalolo.LoginPageUI;
import commons.BasePage;

public class LoginPageObject extends BasePage{
	// là biến trung gian chỉ class này mới sử dụng => hứng lấy driver map tư class testcase qua
	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
			this.driver = driver;
	}
	

	// Hàm khởi tạo (constructor)
	public void inputToUsernameTextbox(String usernameOrEmail) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_EMAIL_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.USERNAME_EMAIL_TEXTBOX, usernameOrEmail);
	}

	public void clickToSigninButton() {
		waitForElementClickble(driver, LoginPageUI.SIGNUP_BOTTON);
		clickToElement(driver, LoginPageUI.SIGNUP_BOTTON);
		
	}

	public boolean isEmptyEmailErrorMessageDisplayed() {
		waitForElementVisible(driver, LoginPageUI.EMPTY_EMAIL_ERROR_MSG);
		return isElementDisplay(driver, LoginPageUI.EMPTY_EMAIL_ERROR_MSG);
	}

	public String getInvalidEmailErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.INVALID_EMAILANDPASSWORD_ERROR_MSG);
		return getElementText(driver, LoginPageUI.INVALID_EMAILANDPASSWORD_ERROR_MSG);
	}
	
	
	public boolean isEmptyPasswordErrorMessageDisplayed() {
		waitForElementVisible(driver, LoginPageUI.EMPTY_PASSWORD_ERROR_MSG);
		return isElementDisplay(driver, LoginPageUI.EMPTY_PASSWORD_ERROR_MSG);
	}

	public boolean isInvalidPasswordErrorMessageDisplayed() {
		waitForElementVisible(driver, LoginPageUI.INVALID_EMAILANDPASSWORD_ERROR_MSG);
		return isElementDisplay(driver, LoginPageUI.INVALID_EMAILANDPASSWORD_ERROR_MSG);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}


	


}
