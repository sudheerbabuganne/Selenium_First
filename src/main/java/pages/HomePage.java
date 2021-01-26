package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage{
	
	public WebDriver driver;
	public WebDriverWait mywait;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		mywait=new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	//public WebDriverWait mywait=new WebDriverWait(getDriver(), 10);
	
	@FindBy(xpath="//a[@id='welcome']")
	WebElement welcome_link;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logout_link;
	
	public void logout()
	{
		mywait.until(ExpectedConditions.visibilityOf(welcome_link));
		welcome_link.click();
		mywait.until(ExpectedConditions.visibilityOf(logout_link));
		logout_link.click();
	}
	

}
