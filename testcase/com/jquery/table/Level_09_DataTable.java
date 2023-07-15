package com.jquery.table;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

public class Level_09_DataTable extends BaseTest{
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDrivers(browserName, urlValue);
		homePage= PageGeneratorManager.getHomePage(driver);
	}
	//@Test
	public void Table_01_Paging() {
		homePage.opentPageByNumber("15");
		Assert.assertTrue(homePage.isPageActiveByNumber("15"));
		homePage.opentPageByNumber("20");
		Assert.assertTrue(homePage.isPageActiveByNumber("20"));
		homePage.opentPageByNumber("7");
		Assert.assertTrue(homePage.isPageActiveByNumber("7"));
	}
	@Test
	public void Table_02_Search() {
		//search by Female
		homePage.inputToHeaderTextboxByLabel("Females","276880");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.areRowRecordDisplayed("276880","Angola","276472","553353"));
		homePage.refreshPage(driver);
		//search by Country
		homePage.inputToHeaderTextboxByLabel("Country","Angola");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.areRowRecordDisplayed("276880","Angola","276472","553353"));
		homePage.refreshPage(driver);
		//search by Male
		homePage.inputToHeaderTextboxByLabel("Males","276472");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.areRowRecordDisplayed("276880","Angola","276472","553353"));
		homePage.refreshPage(driver);
		//search by Total
		homePage.inputToHeaderTextboxByLabel("Total","553353");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.areRowRecordDisplayed("276880","Angola","276472","553353"));
		homePage.refreshPage(driver);
	}
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	private HomePageObject homePage;

}
