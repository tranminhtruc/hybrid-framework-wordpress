package hahalolo.post;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.hahalolo.DashboardPageObject;
import pageObjects.hahalolo.LoginPageObject;

public class Level_04_Login_Multiple_Browser extends BaseTest {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=getBrowserDrivers(browserName);
	}

	@Parameters("url")
	@BeforeMethod
	public void BeforeMethod(String appurl) {
		driver.get(appurl);
		loginPage = new LoginPageObject(driver);
	}

	@Test
	public void Login_01_Empty_Email_Username() {
		loginPage.inputToUsernameTextbox("");
		loginPage.clickToSigninButton();
		loginPage.sleepInSecond(2);
		Assert.assertTrue(loginPage.isEmptyEmailErrorMessageDisplayed());
	}

	@Test
	public void Login_02_UserName_Not_Exist() {
		loginPage.inputToUsernameTextbox("auto" + getRanDomNumber() + "@gmail.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToSigninButton();
		loginPage.sleepInSecond(2);
		String userAndPasswordNotExitMessage = loginPage.getInvalidEmailErrorMessage();
		System.out.println(userAndPasswordNotExitMessage);
		// String userAndPasswordNotExitMessage= getElementText(driver, "//span[contains(text(),'Tên tài khoản hoặc mật khẩu không chính xác')]");
		Assert.assertEquals(userAndPasswordNotExitMessage, "Tên tài khoản hoặc mật khẩu không chính xác");
	}

	@Test
	public void Login_03_Empty_Password() {
		loginPage.inputToUsernameTextbox("auto" + getRanDomNumber() + "@gmail.com");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToSigninButton();
		loginPage.sleepInSecond(2);
		Assert.assertTrue(loginPage.isEmptyPasswordErrorMessageDisplayed());
	}

	@Test
	public void Login_04_Invalid_Password() {
		loginPage.inputToUsernameTextbox("tranminhtruc1510@gmail.com");
		loginPage.inputToPasswordTextbox("1234566");
		loginPage.clickToSigninButton();
		loginPage.sleepInSecond(2);
		Assert.assertTrue(loginPage.isInvalidPasswordErrorMessageDisplayed());
	}

	@Test
	public void Login_05_Valid_Email_Password() {
		loginPage.inputToUsernameTextbox("tranminhtruc1510@gmail.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToSigninButton();
		// Chuyển page theo business: từ login qua dashboard

		loginPage.sleepInSecond(4);
		dashboardPage = new DashboardPageObject(driver);
		Assert.assertTrue(dashboardPage.isDashboardHeaderTextDisplay());
	}

	
	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
