package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;
import pageObjects.nopCommerce.CustomerInforPageObject;

public class Level_06_Login_Page_Generator_Manager extends BaseTest {
	WebDriver driver;
	PageGeneratorManager pageGenerator;
	String projectLocation = System.getProperty("user.dir");
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	CustomerInforPageObject customerInfoPage;
	String emailAddress;

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

	public int getRanDomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
	}

}
