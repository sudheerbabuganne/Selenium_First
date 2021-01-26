package common;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;





public class BaseTest {
	
	ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	
	String nodeurl="http://localhost:4444/wd/hub";
	
	public void setDriver(String browserName)
	{
		if(browserName.equalsIgnoreCase("firefox")) {
			
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName("firefox");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/"+"geckodriver.exe");
			driver.set(new FirefoxDriver());
			/*try {
				driver.set( new RemoteWebDriver(new URL(nodeurl), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName("chrome");
			ChromeOptions options=new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/"+"chromedriver.exe");
			driver.set(new ChromeDriver(options));
			/*try {
				driver.set( new RemoteWebDriver(new URL(nodeurl), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
				
	}
	
	public WebDriver getDriver()
	{
		return driver.get();
	}
	

	@BeforeMethod
	@Parameters({"browserName"})
	public void initialize(String browserName)
	//public void initialize()
	{
		//extentTest.get().info(browserName+" is launched");
		//setDriver(GetVariables.browser);
		setDriver(browserName);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		
		String path=System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+result.getMethod().getMethodName()+".png";
		File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		getDriver().quit();
	}
	
	
	
	
	
	
	

}
