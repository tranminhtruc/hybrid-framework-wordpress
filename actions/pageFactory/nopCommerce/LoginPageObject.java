package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePage{
	WebDriver driver;
public LoginPageObject(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
//public static final String EMAIL_TEXTBOX="//input[@id='Email']";
//public static final String PASSWORD_TEXTBOX="//input[@id='Password']";
//public static final String LOGIN_BUTTON="//button[@class='button-1 login-button']";
@FindBy(id="Email")
private WebElement emailTextBox;

@FindBy(id="Password")
private WebElement passwordTextBox;

@FindBy(css=".login-button")
private WebElement loginButton;

public void enterToEmailTextbox(String emailAddress) {
	waitForElementVisible(driver, emailTextBox);
	senkeyToElement(driver, emailTextBox, emailAddress);
}
public void enterToPasswordTextbox(String password) {
	waitForElementVisible(driver, passwordTextBox);
	senkeyToElement(driver, passwordTextBox, password);
}
public void clickToLoginTextbox() {
	waitForElementClickble(driver, loginButton);
	clickToElement(driver, loginButton);
}

}