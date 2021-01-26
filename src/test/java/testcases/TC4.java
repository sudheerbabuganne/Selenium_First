package testcases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import common.BaseTest;
import pages.HomePage;
import pages.LoginPage;

public class TC4 extends BaseTest{
	LoginPage loginpage;
	HomePage homepage;
	
	@Test
	public void invalid_login() throws InterruptedException
	{
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginpage=new LoginPage(getDriver());
		loginpage.login("Admin", "admin124");
		homepage=new HomePage(getDriver());
		homepage.logout();		
	}

}
