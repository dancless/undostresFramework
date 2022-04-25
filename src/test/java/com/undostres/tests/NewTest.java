package com.undostres.tests;

import org.testng.annotations.Test;
import com.undostres.pages.HomePage;
import com.undostres.pages.PaymentPage;

import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
	public WebDriver driver;
	public HomePage homePage;
	public PaymentPage paymentPage;

	String number = "8465433546";
	String cardNumber = "4111111111111111";
	String cardMonth = "11";
	String cardYear = "25";
	String cardCVV = "111";
	String email = "test@test.com";
	String browser = "chrome";
	String url = "https://prueba.undostres.com.mx/";
	String account = "automationexcersise@test.com";
	String password = "123456";

	@BeforeTest
	public void beforeTest() {
		setup(browser);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test(priority = 1)
	public void selectNumberAndRecharge() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.selectNumberForRecharge();
		// Makes sure it gets to the payment page
		Assert.assertTrue(driver.getCurrentUrl().contains("payment"));
		paymentPage = new PaymentPage(driver);
		// assert payment
		Assert.assertTrue(paymentPage.payRecharge(cardNumber, cardCVV, cardMonth, cardYear, email, account, password));
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public void setup(String browser) { //
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

}
