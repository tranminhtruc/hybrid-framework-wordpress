package hahalolo.post;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Login_BasePage_01 {
	WebDriver driver;
	BasePage basepage;
	String projectLocation = System.getProperty("user.dir");
	By usernameTextboxBy= By.id("accountId"); //input[@id='accountId']
	By passwordTextboxBy= By.id("password");
	By signupButtonBy= By.xpath("//button[@class='MuiButtonBase-root MuiButton-root jss28 jss26 jss33 MuiButton-contained MuiButton-containedPrimary MuiButton-fullWidth']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();	
		basepage=new BasePage();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	@BeforeMethod
	public void BeforeMethod() {
		basepage.openPageUrl(driver, "https://accounts.hahalolo.com/sign-in/?appId=production_newsfeed_app_id&redirect=https%3A%2F%2Fwww.hahalolo.com%2F");
		basepage.sleepInSecond(1);
		}
	@Test
	public void Login_01_Empty_Email_Username() {
		basepage.senkeyToElement(driver, "//input[@id='accountId']", "");
		basepage.clickToElement(driver,"//button[@class='MuiButtonBase-root MuiButton-root jss28 jss26 jss33 MuiButton-contained MuiButton-containedPrimary MuiButton-fullWidth']");
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@id='accountId-helper-text']")).isDisplayed());
		basepage.isElementDisplay(driver, "//p[@id='accountId-helper-text']");
	}
	
	@Test
	public void Login_02_UserName_Not_Exist() {
		basepage.senkeyToElement(driver, "//input[@id='accountId']", "auto"+getRanDomNumber()+"@gmail.com");
		basepage.senkeyToElement(driver, "//input[@id='password']", "123456");
		basepage.clickToElement(driver,"//button[@class='MuiButtonBase-root MuiButton-root jss28 jss26 jss33 MuiButton-contained MuiButton-containedPrimary MuiButton-fullWidth']");
		
		String userAndPasswordNotExitMessage= basepage.getElementText(driver, "//span[contains(text(),'Tên tài khoản hoặc mật khẩu không chính xác')]");
		Assert.assertEquals(userAndPasswordNotExitMessage,"Tên tài khoản hoặc mật khẩu không chính xác");
	}

	@Test
	public void Login_03_Empty_Password() {
		basepage.senkeyToElement(driver, "//input[@id='accountId']", "auto"+getRanDomNumber()+"@gmail.com");
		basepage.senkeyToElement(driver, "//input[@id='password']", "");
		basepage.clickToElement(driver,"//button[@class='MuiButtonBase-root MuiButton-root jss28 jss26 jss33 MuiButton-contained MuiButton-containedPrimary MuiButton-fullWidth']");
		Assert.assertEquals(basepage.getElementText(driver, "//span[contains(text(),'Mật khẩu là bắt buộc')]"), "Mật khẩu là bắt buộc");
		
	}

	@Test
	public void Login_04_Valid_Email_Password() {
		basepage.senkeyToElement(driver, "//input[@id='accountId']", "tranminhtruc1510@gmail.com");
		basepage.senkeyToElement(driver, "//input[@id='password']", "123456");
		basepage.clickToElement(driver,"//button[@class='MuiButtonBase-root MuiButton-root jss28 jss26 jss33 MuiButton-contained MuiButton-containedPrimary MuiButton-fullWidth']");
		basepage.isElementDisplay(driver, "//div[@class='MuiAutocomplete-root jss15 MuiAutocomplete-hasPopupIcon']");
		
	}


	public int getRanDomNumber() {
		Random rand=new Random();
		return rand.nextInt(99999);
	}
	@AfterClass
	public void afterClass() {
	}

}


