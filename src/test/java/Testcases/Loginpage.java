package Testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Loginpage {

	WebDriver driver;

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("file:///C:/Users/toufi/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
	}
	
	@AfterSuite
	public void teardown() {
		driver.close();
	}

	@Test (priority = 1)
	public void verifyTitleOfPage() {	
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");		
	}

	@Test (priority = 2)
	public void verifyLogo() {	
		boolean value= driver.findElement(By.tagName("img")).isDisplayed();
		Assert.assertTrue(value);
	}

	@Test (priority = 3)
	public void verifyLogoText() {	
		String logoText= driver.findElement(By.xpath("//b[text()='Java By Kiran']")).getText();
		Assert.assertEquals(logoText, "Java By Kiran");
	}

	@Test (priority = 4)
	public void verifyValidLogin() {	
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Dashboard");
	}

	@Test (priority = 5)
	public void verifyLoginWithWrongPass() {	
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.xpath("//button")).click();
		String error=driver.findElement(By.id("password_error")).getText();
		Assert.assertEquals(error, "Please enter password 123456");
	}

	@Test (priority = 6)
	public void verifyLoginWithWrongEmail() {	
		driver.findElement(By.id("email")).sendKeys("kira@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String error=driver.findElement(By.id("email_error")).getText();
		Assert.assertEquals(error, "Please enter email as kiran@gmail.com");
	}

	@Test (priority = 7)
	public void verifyInvalidLogin() {	
		driver.findElement(By.id("email")).sendKeys("kira@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.xpath("//button")).click();
		String emailError=driver.findElement(By.id("email_error")).getText();
		String passwordError=driver.findElement(By.id("password_error")).getText();
		String expEmailError="Please enter email as kiran@gmail.com";
		String expPasswordError="Please enter password 123456";
		Assert.assertTrue(emailError.equals(expEmailError) && passwordError.equals(expPasswordError));
	}


	@Test (priority = 8)
	public void verifyLoginWithBlankDetail() {	
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.xpath("//button")).click();
		String emailError=driver.findElement(By.id("email_error")).getText();
		String passwordError=driver.findElement(By.id("password_error")).getText();
		String expEmailError="Please enter email.";
		String expPasswordError="Please enter password.";
	    Assert.assertTrue(emailError.equals(expEmailError) && passwordError.equals(expPasswordError));
	}


	@Test(priority = 9)
	public void verifyRegisterLink() {	
		boolean value=driver.findElement(By.partialLinkText("Register")).isEnabled();
		Assert.assertTrue(value);
	}


	@Test(priority = 10)
	public void verifyRegisterPage() {	
		driver.findElement(By.partialLinkText("Register")).click();
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Registration Page");
	}
	
	@Test (priority = 11)
	public void verifyRegisterFunctionality() {	
		driver.findElement(By.partialLinkText("Register")).click();
		driver.findElement(By.id("name")).sendKeys("Toufik");
		driver.findElement(By.id("mobile")).sendKeys("789745458");
		driver.findElement(By.id("email")).sendKeys("toufik@gmail.com");
		driver.findElement(By.id("password")).sendKeys("aajan@12");
		driver.findElement(By.xpath("//button")).click();
		Alert alert=driver.switchTo().alert();
		String Registermessage=alert.getText();
		alert.accept();
		Assert.assertEquals(Registermessage, "User registered successfully.");
	}
	
	@Test (priority = 12)
	public void verifyAlreadyRegisterLinkEnabled() {	
		boolean value=driver.findElement(By.partialLinkText("Register")).isEnabled();
		Assert.assertTrue(value);	
	}

	@Test (priority = 13)
	public void verifyAlreadyRegisterLink() {	
		driver.findElement(By.partialLinkText("Register")).click();
		driver.findElement(By.linkText("I already have a membership")).click();	
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");		
	}

}
