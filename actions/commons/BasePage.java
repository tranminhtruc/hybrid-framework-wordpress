package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageIUs.nopcommerce.BasePageUI;
import pageObjects.nopCommerce.AboutUsPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.NewsPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.ShoppingCartPageObject;
import pageObjects.nopCommerce.SiteMapPageObject;

public class BasePage {
	public static BasePage getBasePage() {
		return new BasePage();
	}
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCod(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardtoPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public void switchToWindow(WebDriver driver, String parentID) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindows : allWindow) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void CloseAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	private void clickToElement(WebElement element) {
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	public void clickToElement(WebDriver driver, String locator, String... values) {
		getWebElement(driver, getDynamicLocator(locator, values)).click();
	}

	public void senkeyToElement(WebDriver driver, String locator, String value, String... values) {
		WebElement element = getWebElement(driver, getDynamicLocator(locator, values));
		element.clear();
		element.sendKeys(value);
	}
	public void senkeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public void selectedItemInDropdown(WebDriver driver, String locator, String valueItem) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(valueItem);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator, String valueItem) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public void selectItemCustomDropdown(WebDriver driver, String locator, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, locator).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		List<WebElement> allItems = getListWebElement(driver, childItemLocator);
		for (WebElement item : allItems) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			sleepInSecond(1);
			clickToElement(item);
			sleepInSecond(1);
			break;
		}
	}

	public String getElementText(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, getDynamicLocator(locator, values)).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver,locator).getText().trim();
	}

	public String getElementAttributeByValue(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getAttribute("value");
	}
	public String getElementAttributeByName(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	public int getElementNumber(WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}

	public void checkToCheckboxOrRadioButton(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckBox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}

	}

	public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, getDynamicLocator(locator, values)).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	public void switchtoDefaulContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}

	public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targerLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targerLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... values) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicLocator(locator, values)), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.srollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location='" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStype = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStype);
	}

	public void ClickToElementByJS(WebDriver driver, String locator, String... values) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicLocator(locator, values));
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	public void ClickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void senkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value','" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryload = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return docment.readState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryload) && explicitWait.until(jsLoad);
	}

	public String getElemnetValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status=(boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"underfined\" && arguments[0].natuaralWidth>0", getWebElement(driver, locator));
		if (status) {
			return true;				
		}else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver,String locator, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
	}
	public void waitForElementVisible(WebDriver driver,String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitForElementInVisible(WebDriver driver,String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitForElementClickble(WebDriver driver,String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	public void waitForElementClickble(WebDriver driver,String locator, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
	}
	
	public void waitForListElementVisible(WebDriver driver,String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}
	public String getDynamicLocator(String locator, String... values) {
		return String.format(locator, (Object[])values);
	}
	/*Common Page Object*/
	public ShoppingCartPageObject openShoppingPage(WebDriver driver) {
		waitForElementClickble(driver, BasePageUI.SHOPPING_CART_PAGE_LINK);
		clickToElement(driver, BasePageUI.SHOPPING_CART_PAGE_LINK);
		return PageGeneratorManager.getShoppingCartPageObject(driver);
	}
	public HomePageObject openHomePage(WebDriver driver) {
		waitForElementClickble(driver, BasePageUI.HOME_PAGE_IMG);
		clickToElement(driver, BasePageUI.HOME_PAGE_IMG);
		return PageGeneratorManager.getHomePage(driver);
	}
	public AboutUsPageObject openAboutUsPage(WebDriver driver) {
		waitForElementClickble(driver, BasePageUI.ABOUT_US_PAGE_LINK);
		ClickToElementByJS(driver, BasePageUI.ABOUT_US_PAGE_LINK);
		return PageGeneratorManager.getAboutUsPage(driver);
	}
	public SiteMapPageObject openSiteMapPage(WebDriver driver) {
		waitForElementClickble(driver, BasePageUI.SITE_MAP_LINK);
		clickToElement(driver, BasePageUI.SITE_MAP_LINK);
		return PageGeneratorManager.getSiteMapPageObject(driver);
	}
	public NewsPageObject openNewsPage(WebDriver driver) {
		waitForElementClickble(driver, BasePageUI.NEWS_PAGE_LINK);
		clickToElement(driver,BasePageUI.NEWS_PAGE_LINK);
		return PageGeneratorManager.getNewsPageObject(driver);
	}
	/*Dynamic locator one way - page ít*/
	public BasePage openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickble(driver, BasePageUI.FOOTER_PAGE_LINK_BY_NAME, pageName);
		clickToElement(driver, BasePageUI.FOOTER_PAGE_LINK_BY_NAME, pageName);
		
		if(pageName.equals("Shopping cart")) {
			return PageGeneratorManager.getShoppingCartPageObject(driver);
		}else if (pageName.equals("Sitemap")) {
			return PageGeneratorManager.getSiteMapPageObject(driver);
		}else if (pageName.equals("About us")) {
			return PageGeneratorManager.getAboutUsPage(driver);
		}else if (pageName.equals("News")) {
			return PageGeneratorManager.getNewsPageObject(driver);
		}else {
			throw new RuntimeException("Please input the correct page name!");
		}
	}
	/*Dynamic locator two way - page nhiều*/
	public void openFooterPageName(WebDriver driver, String pageName) {
		waitForElementClickble(driver, BasePageUI.FOOTER_PAGE_LINK_BY_NAME, pageName);
		clickToElement(driver, BasePageUI.FOOTER_PAGE_LINK_BY_NAME, pageName);
	}
	private long shorTimeout=5;
	private long longTimeout=30;

}
