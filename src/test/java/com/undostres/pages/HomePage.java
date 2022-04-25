package com.undostres.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	public WebDriverWait wait;
	public WebDriver driver;
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	
	@FindBy(xpath="//input[@data-qa='celular-operator']")
	WebElement operadorInput;
	@FindBy(xpath="//input[@data-qa='celular-operator']//following-sibling::label[text()='Operador']//ancestor::ul//div[@class='suggestion']//li[@data-name='telcel']")
	WebElement operadorTelcel;
	@FindBy(xpath="//input[@suggestmobile='mobile-numbers']")
	WebElement numeroCelularInput;
	@FindBy(xpath="//input[@data-qa='celular-amount']")
	WebElement montoRecargaInput;
	@FindBy(xpath="//ul[contains(@class,'category-list')]//b[text()='$10']")
	WebElement montoRecarga10;
	@FindBy(xpath="//button[@data-qa='celular-pay']")
	WebElement siguienteButton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait =  new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	public boolean selectNumberForRecharge() {
		wait.until(ExpectedConditions.elementToBeClickable(numeroCelularInput));
		numeroCelularInput.sendKeys("8465433546");
		wait.until(ExpectedConditions.elementToBeClickable(operadorInput));
		operadorInput.click();
		wait.until(ExpectedConditions.elementToBeClickable(operadorTelcel));
		operadorTelcel.click();
		wait.until(ExpectedConditions.elementToBeClickable(montoRecargaInput));
		montoRecargaInput.click();
		wait.until(ExpectedConditions.elementToBeClickable(montoRecarga10));
		montoRecarga10.click();
		wait.until(ExpectedConditions.elementToBeClickable(siguienteButton));
		siguienteButton.click();
		boolean navigated = wait.until(ExpectedConditions.urlContains("payment"));
		return navigated;
	}
}
