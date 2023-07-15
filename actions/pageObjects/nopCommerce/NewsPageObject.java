package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageIUs.nopcommerce.HomePageUI;
import pageIUs.nopcommerce.NewsPageUI;
import pageIUs.nopcommerce.ShoppingCartPageUI;

public class NewsPageObject extends BasePage{
	WebDriver driver;
	
	public NewsPageObject(WebDriver driver) {
		this.driver=driver;
		}


}

