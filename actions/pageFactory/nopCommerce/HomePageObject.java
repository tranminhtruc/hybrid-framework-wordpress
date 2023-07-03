package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageFactory.nopCommerce.BasePage;


public class HomePageObject extends BasePage{ 
	WebDriver driver;
public HomePageObject(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);}


@FindBy(css=".ico-register")
private WebElement registerLink;

@FindBy(css=".ico-login")
private WebElement loginlink;

@FindBy(css=".ico-account")
@CacheLookup
private WebElement myAccountLink;

public void clickToRegisterLink() {
	waitForElementClickble(driver, registerLink);
	clickToElement(driver, registerLink);
}

public void clickToLoginLink() {
	waitForElementVisible(driver, loginlink);
	clickToElement(driver, loginlink);
}

public boolean isMyAccountLinkDisplayed() {
	waitForElementVisible(driver, myAccountLink);
	return isElementDisplay(driver, myAccountLink);
}
}
