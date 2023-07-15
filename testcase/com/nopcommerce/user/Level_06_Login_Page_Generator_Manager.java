package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.AboutUsPageObject;
import pageObjects.nopCommerce.CustomerInforPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.NewsPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;
import pageObjects.nopCommerce.ShoppingCartPageObject;
import pageObjects.nopCommerce.SiteMapPageObject;

public class Level_06_Login_Page_Generator_Manager extends BaseTest {
	WebDriver driver;
	PageGeneratorManager pageGenerator;
	String projectLocation = System.getProperty("user.dir");
	

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDrivers(browserName, urlValue);
		driver.get("https://demo.nopcommerce.com/");
		pageGenerator=PageGeneratorManager.getPageGenerator();
		emailAddress = "Torres" + getRanDomNumber() + "@liverpool.com";
		homePage = pageGenerator.getHomePage(driver);
	}

	@Test
	public void Login_01_Register_To_System() {

		registerPage = homePage.clickToRegisterLink();
		registerPage.enterToFirstNameTextbox("Fernando");
		registerPage.enterToLastNameTextbox("Torres");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isSuccessMessageDisplay());
		registerPage.sleepInSecond(2);
	}

	@Test
	public void Login_02_Login_To_System() {
		loginPage = registerPage.clickToLoginlink();
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPassWordTextbox("123456");
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void User_03_Customer_Infor() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertEquals(customerInfoPage.getFirstnameTextboxValue(), "Fernando");
		Assert.assertEquals(customerInfoPage.getLastnameTextboxValue(), "Torres");
		Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);
	}

	@Test
	public void User_04_Dynamic_Page_Locator() {
		//Customer Infor->Site Map
		siteMapPage=(SiteMapPageObject)customerInfoPage.openFooterPageByName(driver, "Sitemap");
		newsPage= (NewsPageObject)siteMapPage.openFooterPageByName(driver, "News");
		shoppingCartPage=(ShoppingCartPageObject)newsPage.openFooterPageByName(driver, "Shopping cart");
		aboutUsPage=(AboutUsPageObject)shoppingCartPage.openFooterPageByName(driver, "About us");
		shoppingCartPage=(ShoppingCartPageObject)aboutUsPage.openFooterPageByName(driver, "Shopping cart");
		siteMapPage=(SiteMapPageObject)shoppingCartPage.openFooterPageByName(driver, "Sitemap");
		aboutUsPage=(AboutUsPageObject)siteMapPage.openFooterPageByName(driver, "About us");
		newsPage=(NewsPageObject)aboutUsPage.openFooterPageByName(driver, "News");
		siteMapPage=(SiteMapPageObject)newsPage.openFooterPageByName(driver, "Sitemap");
	}
	@Test
	public void User_05_Dynamic_Page_Locator() {
		//Customer Infor->Site Map
		customerInfoPage.openFooterPageName(driver, "Sitemap");
		siteMapPage=PageGeneratorManager.getSiteMapPageObject(driver);

		siteMapPage.openFooterPageName(driver, "News");
		newsPage= PageGeneratorManager.getNewsPageObject(driver);
		
		newsPage.openFooterPageName(driver, "Shopping cart");
		shoppingCartPage=PageGeneratorManager.getShoppingCartPageObject(driver);
		
		shoppingCartPage.openFooterPageName(driver, "About us");
		aboutUsPage=PageGeneratorManager.getAboutUsPage(driver);
		
		aboutUsPage.openFooterPageName(driver, "Shopping cart");
		shoppingCartPage=PageGeneratorManager.getShoppingCartPageObject(driver);
		
		shoppingCartPage.openFooterPageName(driver, "Sitemap");
		siteMapPage=PageGeneratorManager.getSiteMapPageObject(driver);
		
		siteMapPage.openFooterPageName(driver, "About us");
		aboutUsPage=PageGeneratorManager.getAboutUsPage(driver);
		
		aboutUsPage.openFooterPageName(driver, "News");
		newsPage=PageGeneratorManager.getNewsPageObject(driver);
		
		newsPage.openFooterPageName(driver, "Sitemap");
		siteMapPage=PageGeneratorManager.getSiteMapPageObject(driver);
	}
	public int getRanDomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
	}
	HomePageObject homePage;
	NewsPageObject newsPage;
	LoginPageObject loginPage;
	SiteMapPageObject siteMapPage;
	RegisterPageObject registerPage;
	CustomerInforPageObject customerInfoPage;
	AboutUsPageObject aboutUsPage;
	ShoppingCartPageObject shoppingCartPage;
	String emailAddress;

}
