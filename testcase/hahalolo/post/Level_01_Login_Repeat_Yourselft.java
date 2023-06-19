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

public class Level_01_Login_Repeat_Yourselft {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	By usernameTextboxBy= By.id("accountId");
	By passwordTextboxBy= By.id("password");
	By signupButtonBy= By.xpath("//button//span[text()='Đăng nhập']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	@BeforeMethod
	public void BeforeMethod() {
		driver.get("https://accounts.hahalolo.com/sign-in/?appId=production_newsfeed_app_id&redirect=https%3A%2F%2Fwww.hahalolo.com%2F");
	}
	@Test
	public void Login_01_Empty_Email_Username() {
		driver.findElement(usernameTextboxBy).sendKeys("");
		driver.findElement(signupButtonBy).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='accountId-helper-text']")).isDisplayed());
	}
	
	@Test
	public void Login_02_UserName_Not_Exist() {
		driver.findElement(usernameTextboxBy).sendKeys("auto"+getRanDomNumber()+"@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(signupButtonBy).click();
		String userAndPasswordNotExitMessage= driver.findElement(By.xpath("//span[contains(text(),'Tên tài khoản hoặc mật khẩu không chính xác')]")).getText();
		Assert.assertEquals(userAndPasswordNotExitMessage,"Tên tài khoản hoặc mật khẩu không chính xác");
	}

	@Test
	public void Login_03_Empty_Password() {
		driver.findElement(usernameTextboxBy).sendKeys("auto"+getRanDomNumber()+"@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(signupButtonBy).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Mật khẩu là bắt buộc')]")).getText(), "Mật khẩu là bắt buộc");
		
	}

	@Test
	public void Login_04_Valid_Email_Password() {
		driver.findElement(usernameTextboxBy).sendKeys("tranminhtruc1510@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(signupButtonBy).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='MuiAutocomplete-root jss15 MuiAutocomplete-hasPopupIcon']")).isDisplayed());
	}

	
	public int getRanDomNumber() {
		Random rand=new Random();
		return rand.nextInt(99999);
	}
	@AfterClass
	public void afterClass() {
	}

}
