package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageIUs.nopcommerce.CustomerInforPageUI;

public class CustomerInforPageObject extends BasePage {
	WebDriver driver;
	PageGeneratorManager pageGenerator;
	public CustomerInforPageObject(WebDriver driver) {
		this.driver=driver;
		pageGenerator= new PageGeneratorManager();
	}
	public String getFirstnameTextboxValue() {
		waitForElementVisible(driver, CustomerInforPageUI.FIRSTNAME_TEXTBOX);
		return getElementAttributeByValue(driver,CustomerInforPageUI.FIRSTNAME_TEXTBOX);
	}
	public String getLastnameTextboxValue() {
		waitForElementVisible(driver, CustomerInforPageUI.LASTNAME_TEXTBOX);
		return getElementAttributeByValue(driver,CustomerInforPageUI.LASTNAME_TEXTBOX);
	}
	public String getEmailTextboxValue() {
		waitForElementVisible(driver, CustomerInforPageUI.EMAIL_TEXTBOX);
		return getElementAttributeByValue(driver,CustomerInforPageUI.EMAIL_TEXTBOX);
	}

	
	

}
