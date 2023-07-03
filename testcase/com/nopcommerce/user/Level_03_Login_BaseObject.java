package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class Level_03_Login_BaseObject {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String emailAddress;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
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
		registerPage.ClickToContinue();
		registerPage.clickToLoginlink();
		homePage=new HomePageObject(driver);
	}

	@Test
	public void Login_02_Login_To_System() {
		//homePage.clickToLoginLink();
		loginPage= new LoginPageObject(driver);
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPassWordTextbox("123456");
		loginPage.clickToLoginButton();
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
