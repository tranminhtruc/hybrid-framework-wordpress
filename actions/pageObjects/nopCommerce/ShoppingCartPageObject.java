package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageIUs.nopcommerce.AboutUsPageUI;
import pageIUs.nopcommerce.HomePageUI;
import pageIUs.nopcommerce.ShoppingCartPageUI;

public class ShoppingCartPageObject extends BasePage{
	WebDriver driver;
	
	
	public ShoppingCartPageObject(WebDriver driver) {
		this.driver=driver;
	
	}

	
}

