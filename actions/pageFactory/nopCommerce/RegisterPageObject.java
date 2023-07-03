package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageIUs.nopcommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	WebDriver driver;
public RegisterPageObject(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
@FindBy(id="FirstName")
private WebElement firstNameTextbox;
@FindBy(id="LastName")
private WebElement lastNameTextbox;
@FindBy(id="Email")
private WebElement emailTextbox;
@FindBy(id="Password")
private WebElement passwordTextbox;
@FindBy(id="ConfirmPassword")
private WebElement confirmPasswordTextbox;
@FindBy(id="register-button")
private WebElement registerButton;
@FindBy(xpath="//div[@class='result' and text()='Your registration completed']")
private WebElement registerSuccessMessage;
@FindBy(css=".ico-login")
private WebElement loginLink;
public void enterToFirstNameTextbox(String firstName) {
	waitForElementVisible (driver, firstNameTextbox);
	senkeyToElement(driver, firstNameTextbox, firstName);
}

public void enterToLastNameTextbox(String lastname) {
	waitForElementVisible(driver, lastNameTextbox);
	senkeyToElement(driver, lastNameTextbox, lastname);
}

public void enterToEmailTextbox(String emailAddress) {
	waitForElementVisible(driver, emailTextbox);
	senkeyToElement(driver, emailTextbox, emailAddress);
	
}

public void enterToPasswordTextbox(String password) {
	waitForElementVisible(driver, passwordTextbox);
	senkeyToElement(driver, passwordTextbox, password);
}

public void enterToConfirmPasswordTextbox(String confirmPassword) {
	waitForElementVisible(driver, confirmPasswordTextbox);
	senkeyToElement(driver, confirmPasswordTextbox, confirmPassword);
}

public void clickToRegisterButton() {
	waitForElementClickble(driver, registerButton);
	clickToElement(driver, registerButton);
}

public boolean isSuccessMessageDisplay() {
	waitForElementVisible(driver,registerSuccessMessage );
	return isElementDisplay(driver, registerSuccessMessage);
}

public void clickToLoginlink() {
	waitForElementClickble(driver, loginLink);
	clickToElement(driver, loginLink);
	
}

}