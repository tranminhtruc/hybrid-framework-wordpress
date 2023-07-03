package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.nopCommerce.HomePageObject;
import pageFactory.nopCommerce.LoginPageObject;
import pageFactory.nopCommerce.RegisterPageObject;


public class Level_05_Login_Page_Factory extends BaseTest {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String emailAddress;
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver= getBrowserDrivers(browserName, urlValue);
		driver.get("https://demo.nopcommerce.com/");
		emailAddress="Torres" + getRanDomNumber()+"@liverpool.com";
		homePage=new HomePageObject(driver);
	}

	@Test
	public void Login_01_Register_To_System() {
		homePage.clickToRegisterLink();
		registerPage=new RegisterPageObject(driver);
		registerPage.enterToFirstNameTextbox("Fernando");
		registerPage.enterToLastNameTextbox("Torres");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");
		registerPage.clickToRegisterButton();  
		Assert.assertTrue(registerPage.isSuccessMessageDisplay());
		registerPage.sleepInSecond(2);
		registerPage.clickToLoginlink();
		homePage=new HomePageObject(driver);
	}

	@Test
	public void Login_02_Login_To_System() {
		//homePage.clickToLoginLink();
		loginPage= new LoginPageObject(driver);
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickToLoginTextbox();
		homePage=new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	
	public int getRanDomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
	}

}
