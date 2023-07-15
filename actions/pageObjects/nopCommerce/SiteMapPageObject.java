package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageIUs.nopcommerce.HomePageUI;
import pageIUs.nopcommerce.SiteMapPageUI;

public class SiteMapPageObject extends BasePage{
	WebDriver driver;
	
	public SiteMapPageObject(WebDriver driver) {
		this.driver=driver;
	
	}

	
}

