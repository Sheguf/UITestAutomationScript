package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Base {
	public static Properties pro;
	public static WebDriver driver;    // Driver define on global level
	
	
	static {
		
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir") +"/src/test/java/resources/env.properties");
		    pro = new Properties();
		    pro.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Before
	public void setup() {
		
	String browserName = pro.getProperty("browser");   //chrome
	if(browserName.equals("chrome")) {
		 driver = new ChromeDriver();
	}else if(browserName.equals("firefox")) {
		driver = new FirefoxDriver();
	}
	
	driver.get(pro.getProperty("url"));
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	@After
	public void tearDown(Scenario s) throws IOException {
		if(s.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot)driver;
		    File src = ts.getScreenshotAs(OutputType.FILE);
		    FileHandler.copy(src, new File("Screenshots/"+s.getName()+".png"));
		}
		driver.quit();
		
	}
	
	public void selectDropDownByText(WebElement ele, String value) {
		Select s = new Select(ele);
		s.selectByVisibleText(value);
	}
	
	public void selectDropDownByIndex(WebElement element, int number) {
		Select s = new Select(element);
		s.deselectByIndex(number);
	}
	
	public void selectDropDownByValue(WebElement elem, String value) {
		Select s = new Select(elem);
		s.selectByValue(value);
	}
	
	public void mouseHover(WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele);
	}
	
	public void waitForExpectedElementVisibility(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForExpectedElementByClick(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable((By)ele));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ele")));
	}
	
	public void Alert(WebElement ele) {
		Alert a = driver.switchTo().alert();
		a.accept();
	}
	
	public void JavascriptExecutorClick(WebElement firstLink) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",firstLink);
		js.executeScript("arguments[0].scrollIntoView(true;",firstLink);
		
	}
	
	
	public void selectBootstrapDropDown(List<WebElement>list, String expectedValue) {
		for(WebElement e: list) {
			
			String s = e.getText();
			if(s.equals(expectedValue)) {
				e.click();
				break;
			}
		}
	}
}
