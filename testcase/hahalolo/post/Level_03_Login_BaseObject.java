package hahalolo.post;

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
import pageObjects.hahalolo.DashboardPageObject;
import pageObjects.hahalolo.LoginPageObject;

public class Level_03_Login_BaseObject {
	WebDriver driver;
	BasePage basepage;
	String projectLocation = System.getProperty("user.dir");
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void BeforeMethod() {
		driver.get("https://accounts.hahalolo.com/sign-in/?appId=production_newsfeed_app_id&redirect=https%3A%2F%2Fwww.hahalolo.com%2F");
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
		//Chuyển page theo business: từ login qua dashboard
		
		loginPage.sleepInSecond(4);
		dashboardPage=new DashboardPageObject(driver);
		Assert.assertTrue(dashboardPage.isDashboardHeaderTextDisplay());
	} 


	public int getRanDomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
	}

}
