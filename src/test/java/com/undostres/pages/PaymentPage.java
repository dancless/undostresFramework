package com.undostres.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {
	public WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath="//div[@id='panel-title-card']")
	WebElement tarjetaOption;
	@FindBy(xpath="//input[@id='radio-c']//ancestor::td//span")
	WebElement newCardOption;
	@FindBy(xpath="//input[@id='cardnumberunique']")
	WebElement cardNumberInput;
	@FindBy(xpath="//input[@data-qa='mes-input']")
	WebElement monthInput;
	@FindBy(xpath="//input[@data-qa='expyear-input']")
	WebElement yearInput;
	@FindBy(xpath="//input[@data-qa='cvv-input']")
	WebElement cvvInput;
	@FindBy(xpath="//div[contains(@class,'correoElectronicoPaymentsRow')]//input")
	WebElement emailInput;
	@FindBy(xpath="//button[@id='paylimit']")
	WebElement payWithCardButton;
	@FindBy(xpath="//form[@id='loginForm']//input[@name='email']")
	WebElement accountEmailInput;
	@FindBy(xpath="//form[@id='loginForm']//input[@name='password']")
	WebElement accountPasswordInput;
	@FindBy(xpath="//div[@id='rc-anchor-container']//span[@role='checkbox']")
	WebElement accountNotARobotCheck;
	@FindBy(xpath="//iframe[@title='reCAPTCHA']")
	WebElement captchaIframe;
	WebElement captchaBox;
	@FindBy(xpath="//button[@name='loginbtn']")
	WebElement captchaLoginButton;
	
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		wait =  new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	public boolean payRecharge(String cardNumber, String cardCVV, String cardMonth, String cardYear, String email,
			String accountEmail, String accountPassword) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tarjetaOption));
		tarjetaOption.click();
		// Explicit wait to give time card section to load
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(newCardOption));
		newCardOption.click();
		wait.until(ExpectedConditions.elementToBeClickable(cardNumberInput));
		cardNumberInput.sendKeys(cardNumber);
		wait.until(ExpectedConditions.elementToBeClickable(monthInput));
		monthInput.sendKeys(cardMonth);
		wait.until(ExpectedConditions.elementToBeClickable(yearInput));
		yearInput.sendKeys(cardYear);
		wait.until(ExpectedConditions.elementToBeClickable(cvvInput));
		cvvInput.sendKeys(cardCVV);
		wait.until(ExpectedConditions.elementToBeClickable(emailInput));
		emailInput.sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(payWithCardButton));
		payWithCardButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(accountEmailInput));
		accountEmailInput.sendKeys(accountEmail);
		wait.until(ExpectedConditions.elementToBeClickable(accountPasswordInput));
		accountPasswordInput.sendKeys(accountPassword);
		// Changing to captcha iFrane
		driver.switchTo().frame(captchaIframe);
		wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector( "div.recaptcha-checkbox-border")))).click();
		// Going back to parent frame.
		driver.switchTo().parentFrame(); 
		wait.until(ExpectedConditions.elementToBeClickable(captchaLoginButton));
		captchaLoginButton.click();
		return true;
	}
}
