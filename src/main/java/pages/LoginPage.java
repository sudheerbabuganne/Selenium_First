package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage  {
	
	public WebDriver driver;
	public WebDriverWait mywait;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		mywait=new WebDriverWait(driver, 10);
		PageFactory.initElements(driver,this);
	}
	
	//public WebDriverWait mywait=new WebDriverWait(getDriver(), 10);
	
	@FindBy(name="txtUsername")
	WebElement userName_txt;
	
	@FindBy(name="txtPassword")
	WebElement password_txt;
	
	@FindBy(name="Submit")
	WebElement submit_btn;
	
	public void login(String userName,String password)
	{
		mywait.until(ExpectedConditions.visibilityOf(userName_txt));
		userName_txt.sendKeys(userName);
		mywait.until(ExpectedConditions.visibilityOf(password_txt));
		password_txt.sendKeys(password);
		//mywait.until(ExpectedConditions.visibilityOf(submit_btn));
		submit_btn.click();
	}
	

}
