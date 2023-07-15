package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectLocation = GlobalConstants.PROJECT_LOCATION;
	private String osName= GlobalConstants.OS_NAME;

	protected WebDriver getBrowserDrivers(String browserName, String url) {
		Browser browser= Browser.valueOf(browserName.toUpperCase());
		if (browser==browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		
		} else if (browser==browser.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser==browser.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if (browser==browser.OPERA) {
			//WebDriverManager.operadriver().driverVersion("99.0.4788.77").setup();
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}else if (browser==browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOpt=new FirefoxOptions(); 
			firefoxOpt.addArguments("headless");
			firefoxOpt.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOpt);
		}else if (browser==browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOtp=new ChromeOptions();
			chromeOtp.addArguments("headless");
			chromeOtp.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromeOtp);
		}else if (browser==browser.COC_COC) {
			WebDriverManager.chromedriver().driverVersion("118.0.148").setup();
			ChromeOptions options=new ChromeOptions();
			options.setBinary("C:\\Users\\leduc\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		}
		else {
			throw new RuntimeException("Please input the browser name!");
		}
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		return driver;
	}
	protected WebDriver getBrowserDrivers(String browserName) {
		Browser browser= Browser.valueOf(browserName.toUpperCase());
		if (browser==browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser==browser.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser==browser.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if (browser==browser.OPERA) {
			//WebDriverManager.operadriver().driverVersion("99.0.4788.77").setup();
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}else if (browser==browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOpt=new FirefoxOptions(); 
			firefoxOpt.addArguments("headless");
			firefoxOpt.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOpt);
		}else if (browser==browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOtp=new ChromeOptions();
			chromeOtp.addArguments("headless");
			chromeOtp.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromeOtp);
		}else if (browser==browser.COC_COC) {
			WebDriverManager.chromedriver().driverVersion("118.0.148").setup();
			ChromeOptions options=new ChromeOptions();
			options.setBinary("C:\\Users\\leduc\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		}
			else {
			throw new RuntimeException("Please input the browser name!");
		}
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		return driver;
	}

	protected int getRanDomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	
	private void setBrowserDriverProperty() {
		String browserFolderPath= projectLocation+getDirectorySlash("browserDriver");
		
		if(isWindows()) {
			System.setProperty("webdriver.gecko.driver", browserFolderPath+"geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", browserFolderPath+"chromedriver.exe");
			System.setProperty("webdriver.edge.driver", browserFolderPath+"msedgedriver.exe");
		}
		if(isMac()) {
			System.setProperty("webdriver.gecko.driver", browserFolderPath +"geckodriver_mac");
			System.setProperty("webdriver.chrome.driver", browserFolderPath+"chromedriver_mac");
			System.setProperty("webdriver.edge.driver", browserFolderPath+"msedgedriver_mac");
		}else {
			System.setProperty("webdriver.gecko.driver", browserFolderPath+"geckodriver_linux");
			System.setProperty("webdriver.chrome.driver", browserFolderPath+"chromedriver_linux");
		}
	}
	
	private String getDirectorySlash(String folderName) {
		if (isMac()||isUnix()||isSolaris()) {
			folderName="/"+folderName+"/";
		}else if(isWindows()) {
			folderName="\\"+folderName+"\\";
		}else {folderName=null;}
		return folderName;}
	private boolean isWindows() {
		return (osName.toLowerCase().indexOf("win")>=0);
	}
	
	private boolean isMac() {
		return (osName.toLowerCase().indexOf("mac")>=0);
	}
	
	private boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix")>=0||osName.toLowerCase().indexOf("nux")>=0);
	}
	
	private boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos")>=0);
	}

}
