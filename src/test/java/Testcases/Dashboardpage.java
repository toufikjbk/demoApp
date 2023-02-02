package Testcases;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.ExcelRead;

import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.*;

public class Dashboardpage {

	WebDriver driver;

	@BeforeSuite
	public void setup() {
		System.setProperty("Webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/toufi/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
	}
	
	
	@AfterSuite
	public void teardown() {
		driver.close();
	}
	
	
	@Test (priority = 1)
	public void verifylangaugesCount() {
		List<WebElement> languages = driver.findElements(By.xpath("//h3"));
		Assert.assertEquals(languages.size(), 4);
	}

	
	@Test (priority = 2)
	public void verifylanguageNames() throws EncryptedDocumentException, InvalidFormatException, IOException {
		SoftAssert obj1=new SoftAssert();
		List<WebElement> languages = driver.findElements(By.xpath("//h3"));
		String[][] courses = ExcelRead.ExcelReader("course");
		for (int i = 0; i < languages.size(); i++) {
      
			if (!languages.get(i).getText().equals(courses[i][0])) 
				obj1.assertTrue(false);	
		}
		obj1.assertAll();
		}
		
	
	@Test (priority = 3)
	public void verifyCoursesNames() throws EncryptedDocumentException, InvalidFormatException, IOException {
		SoftAssert obj2=new SoftAssert();
		List<WebElement> languages = driver.findElements(By.xpath("//h3"));
		List<WebElement> courseNames=driver.findElements(By.xpath("//h3/following-sibling::p"));
		String[][] courses = ExcelRead.ExcelReader("course");
		for (int i = 0; i < languages.size(); i++) {
      
			if (languages.get(i).getText().equals(courses[i][0])) {
				if (!courseNames.get(i).getText().equals(courses[i][1])) {
					obj2.assertTrue(false);
				}	
			}
			
		}
		obj2.assertAll();
		}
		

	@Test (priority = 4)
	public void verifyCourseLinksCount() throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<WebElement> courseLinks = driver.findElements(By.linkText("More info"));
		Assert.assertEquals(courseLinks.size(), 4);
	}

	
	@Test(priority = 5)
	public void verifyCoursesLinksEnabled() {
		SoftAssert obj3=new SoftAssert();
		List<WebElement> courseLinks = driver.findElements(By.linkText("More info"));
		for (WebElement courseLink : courseLinks) {
			if (courseLink.isEnabled());
			else
				obj3.assertTrue(false);
		}
		obj3.assertAll();
	}

	
	@Test(priority = 6)
	public void verifyWindowsCount() {
		List<WebElement> courseLinks = driver.findElements(By.linkText("More info"));
		for (WebElement courseLink : courseLinks) {
			courseLink.click();
		}
		Set<String> handles = driver.getWindowHandles();
		Assert.assertEquals(handles.size(), 5);
	}

	
	@Test(priority = 7)
	public void verifyWindowsHandle() { 
		List<WebElement> courseLinks = driver.findElements(By.linkText("More info"));
		for (WebElement courseLink : courseLinks) {
			courseLink.click();
		}
		Set<String> handles = driver.getWindowHandles();
		String mainWindowhandle = driver.getWindowHandle();
		for (String handle : handles) {
			if (!handle.equals(mainWindowhandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(mainWindowhandle);
		Assert.assertEquals(driver.getWindowHandle(), mainWindowhandle);

	}

	
	@Test(priority = 8)
	public void verifymenusCount() throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<WebElement> menusLinks = driver.findElements(By.xpath("//ul[@class='sidebar-menu']//a"));	
		Assert.assertEquals(menusLinks.size(), 6);
	}

	
	@Test (priority = 9)
	public void verifymenusEnabled() {
		SoftAssert obj4=new SoftAssert();
		List<WebElement> menusLinks = driver.findElements(By.xpath("//ul[@class='sidebar-menu']//a"));
		for (WebElement menuLink : menusLinks) {
			if(!menuLink.isEnabled())
				obj4.assertTrue(false);
		}
		obj4.assertAll();
	}
	

	@Test (priority = 10)
	public void verifymenusText() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		SoftAssert obj5=new SoftAssert();
		List<WebElement> menusLinks=driver.findElements(By.xpath("//ul[@class='sidebar-menu']//a"));			
		String [][] menus=ExcelRead.ExcelReader("menu");
		
		for (int i=0;i<menusLinks.size();i++) {		
		if(!menusLinks.get(i).getText().equals(menus[i][0])) {
			obj5.assertTrue(false);
		}
		}
		obj5.assertAll();	
	}
	
	
	@Test (priority = 11)
	public void verifymenus() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		SoftAssert obj6=new SoftAssert();
		List<WebElement> menusLinks=driver.findElements(By.xpath("//ul[@class='sidebar-menu']//a"));		
		String menutext[]=new String[menusLinks.size()];
		
		for (int i=0;i<menusLinks.size();i++) {		
		menutext[i]=menusLinks.get(i).getText();	
		}
		
		String [][] menus=ExcelRead.ExcelReader("menu");
		
		for (int i=0;i<menusLinks.size();i++) {		
			driver.findElement(By.linkText(menutext[i])).click();
			if(!driver.getTitle().equals(menus[i][1])) {
		     	obj6.assertTrue(false);
			}
			}
          obj6.assertAll();		
	}
	
	
	@Test(priority = 12)
	public void verfifyLogout(){	
		driver.findElement(By.linkText("LOGOUT")).click();
		String logoutMsg=driver.findElement(By.xpath("//p[text()='Logout successfully']")).getText();
		Assert.assertEquals(logoutMsg, "Logout successfully");
	}
}
