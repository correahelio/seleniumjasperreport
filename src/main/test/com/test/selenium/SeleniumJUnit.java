package com.test.selenium;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.model.ImageBean;
import com.selenium.util.JasperUtil;

//JUnit class to execute Selenium Test
public class SeleniumJUnit {

	//Webdriver used by selenium
	private static WebDriver driver;
	
	//Image list to save all the evidences
	private static List<ImageBean> listImageBean = new ArrayList<ImageBean>();
	
	//Before starting the selenium test we need to configure the driver
	@BeforeClass
	public static void initConfig() {
		//Configuring the chrome driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		//Opening localhost page (index.jsp)
		driver.get("http://localhost:8080/seleniumjasperreport");
	}
	
	//After completing the test we should close and quit webdriver and create the pdf evidence
	@AfterClass
	public static void closeConfig() throws FileNotFoundException, JRException {
		driver.close();
		driver.quit();
		
		//Creating pdf evidence
		JasperUtil.createPdfEvidence("evidence.pdf", listImageBean);
	}
	
	@Test
	public void testIt() throws InterruptedException, JRException, IOException {
		//Thread sleep is necessary to wait for selenium to open the browser
		Thread.sleep(8000L);
		
		//At this moment the screen is already loaded
		//Taking the first field and typing the value "Helio"
		WebElement element = driver.findElement(By.xpath("//input[@id='firstField']"));
		element.sendKeys("Helio");
		
		//Thread.sleep is necessary since selenium runs very fast
		Thread.sleep(3000L);
		
		//Taking the first evidence with focus on div that has id = 'div'
		element = driver.findElement(By.xpath("//div[@id='div']"));
		takeScreenshotByElement(element, "Evidence Number 1");
		
		//Taking the second field and typing the value "Correa"
		element = driver.findElement(By.xpath("//input[@id='secondField']"));
		element.sendKeys("Correa");
		
		//Thread.sleep is necessary since selenium runs very fast
		Thread.sleep(3000L);

		//Taking another evidence with focus on div that has id = 'div'
		element = driver.findElement(By.xpath("//div[@id='div']"));
		takeScreenshotByElement(element, "Evidence Number 2");

		//Clicking on the "sendButton"
		driver.findElement(By.xpath("//input[@id='sendButton' and @type='button']")).click();
		
		//Thread sleep is necessary to appear the "alert-div" 
		Thread.sleep(2000L);
		
		//Taking another evidence with focus on div that has id = 'div'
		element = driver.findElement(By.xpath("//div[@id='div']"));
		takeScreenshotByElement(element, "Evidence Number 3");
		
		//Clicking on the "cancelButton"
		driver.findElement(By.xpath("//input[@id='cancelButton' and @type='button']")).click();
		
		//Thread sleep is necessary to disappear the "alert-div"
		Thread.sleep(2000L);
		
		//Taking another evidence of the entire screen
		takeScreenshot("Evidence Number 4");
	}
	
	//This method is used to take entire screen screenshot
	private void takeScreenshot(String screenshotDescription) throws FileNotFoundException {
		
		//Taking screenshot from selenium
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//Creating our bean and setting the image and imageName to be displayed in the report file
		ImageBean bean =  new ImageBean();
		bean.setImageName(screenshotDescription);
		bean.setImage(new FileInputStream(screenshot));

		//Adding to the list of beans
		listImageBean.add(bean);
	}
	
	//This method is used to take specific element screenshot
	private void takeScreenshotByElement(WebElement element, String screenshotDescription) throws IOException {
		
		//Taking element position
		Point p = element.getLocation();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		
		//Taking screen shot from selenium
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//Taking the image as buffer
		BufferedImage img = ImageIO.read(screenshot);
		
		//Creating another buffer in accordance with screenshot position
    	BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width, height);
    	
    	//Converting BuffferedImage to InputStream
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	ImageIO.write(dest, "jpg", os);
    	InputStream is = new ByteArrayInputStream(os.toByteArray());
		
		//Creating our bean and setting the image and imageName to be displayed in the report file
		ImageBean bean =  new ImageBean();
		bean.setImageName(screenshotDescription);
		bean.setImage(is);
		
		//Adding to the list of beans
		listImageBean.add(bean);
	}
	
}
