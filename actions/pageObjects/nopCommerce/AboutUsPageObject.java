package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageIUs.nopcommerce.AboutUsPageUI;
import pageIUs.nopcommerce.HomePageUI;

public class AboutUsPageObject extends BasePage{
	WebDriver driver;
	
	public AboutUsPageObject(WebDriver driver) {
		this.driver=driver;
	
	}

	
}

