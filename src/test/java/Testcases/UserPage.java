package Testcases;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserPage {
	
	WebDriver driver;

	@BeforeSuite
	public void setup() {
		System.setProperty("Webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/toufi/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.linkText("Users")).click();
		
	}
	
	
	@AfterSuite
	public void teardown() {
		driver.close();
	}
	
	@Test (priority = 1)
	public void verifyTitleOfPage() {	
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | User");		
	}
	
	@Test (priority = 2)
	public void verifyHeadingOfPage() {	
		String actualHeading=driver.findElement(By.xpath("//section[@class='content-header']//h1")).getText();
		Assert.assertEquals(actualHeading, "Users");		
	}
	

	@Test (priority = 3)
	public void verifyAdduserButton() {	
	    driver.findElement(By.xpath("//a//button[text()='Add User']")).click();
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Add User");		
	}
	
	
	@Test (priority = 4)
	public void verifyDeleteUserFunctionality() throws InterruptedException {	
		SoftAssert softAssert=new SoftAssert();
		List<WebElement> rows=driver.findElements(By.xpath("//tr[1]//following::tr"));
		
		for(WebElement row:rows) {
			List<WebElement> columns=row.findElements(By.xpath(".//td"));
			
			columns.get(7).findElement(By.xpath(".//a")).click();
			
			Alert alert=driver.switchTo().alert();
			
			if(alert.getText().equals("You can not delete Default User"))
			{
				alert.accept();
				boolean value=columns.get(1).isDisplayed();
				
				if(value) {
					softAssert.assertTrue(true);
				}
			}
			
			else
			{
				alert.accept();
				alert.accept();
				
				boolean value=columns.get(1).isDisplayed();
                   if(value) {
					softAssert.assertTrue(false);
                   }	
			}
			
			}		
		       softAssert.assertAll();
			}
		

	@Test(priority = 5)
	public void verfifyLogout(){	
		driver.findElement(By.linkText("LOGOUT")).click();
		String logoutMsg=driver.findElement(By.xpath("//p[text()='Logout successfully']")).getText();
		Assert.assertEquals(logoutMsg, "Logout successfully");
	}
	
	
	
	

}
