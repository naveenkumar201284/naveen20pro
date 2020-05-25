package com.test.profinch;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.utility.Utility;

import filloreader.FilloReader;
import filloreader.Xl_Reader;
import screenshot.ScreenShot;

public class CashWithDrawal extends Utility{
	
/*	private WebDriver driver;
	
	public CashWithDrawal(){
		driver=new Utility().driver;
	}*/
public static final Logger log = Logger.getLogger(CashWithDrawal.class.getName());
	
	ScreenShot ss;
	List<Map<String, String>> pram = new ArrayList<Map<String, String>>();    
	String filepath=System.getProperty("user.dir") + "\\src\\main\\java\\datamanager\\";
	String driverpath=System.getProperty("user.dir") + "\\Resources\\Drivers\\";
	FilloReader fillo;
	public String account;
	Utility util=new Utility();
	private static String xlpath="C:\\Users\\naveen.kumar\\eclipse-workspace\\pfz\\src\\main\\java\\datamanager\\loginData.xlsx";
	Xl_Reader xldata = new Xl_Reader(xlpath); 
	private WebDriverWait wait;

	
	@BeforeTest
	public void setup() throws Exception {
		
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\naveen.kumar\\Desktop\\chrome81\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 50);

	
			driver.get("http://192.168.4.84:8099/FinFlowz/#/finflowz/login");
			
			pram=new FilloReader().getTestDataInListMap(new File("C:\\Users\\naveen.kumar\\eclipse-workspace\\naveen123\\proFInflow\\src\\main\\java\\datamanager\\loginData.xlsx"),"FinFlowLogin", "select * from %s"); 

		//	for (Map<String, String> data : pram) {
				
/*			String username=data.get("Username");
			driver.findElement(By.name("username")).sendKeys(username);
			log.info(username);
			
			String password=data.get("Password");
			driver.findElement(By.name("password")).sendKeys(password);
			log.info(password);*/
			
			String un = xldata.getCellData("FinFlowLogin", "Username", 2);
			driver.findElement(By.name("username")).sendKeys(un);

			String pwd = xldata.getCellData("FinFlowLogin", "Password", 2);
			driver.findElement(By.name("password")).sendKeys(pwd);

			//driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
			WebElement loginbutton = driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", loginbutton);
			Thread.sleep(5000);
			util.setUp();
		//}
	}
	
	@Test
	public void CashWithDrawalCreation() throws Exception {
		log.info("This is a test. Don't worry.");
		String TestCaseID = null;

		ss = new ScreenShot();
		pram=new FilloReader().getTestDataInListMap(new File("C:\\Users\\naveen.kumar\\eclipse-workspace\\naveen123\\proFInflow\\src\\main\\java\\datamanager\\loginData.xlsx"),"CashWithDrawal", "select * from %s where Runmode='Yes'");
		for (Map<String, String> data : pram) {

			try {

			test=extent.createTest("Testing Cash withdrawal Screen");

			TestCaseID=data.get("TestCaseID");


			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='apptitle'][contains(text(),'Teller')]")))).click();
			log.info("Clicked on teller button icon");

		/*	boolean searchIconPresence = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//strong[text()='Open']")))).isEnabled();

			//boolean searchIconPresence = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//strong[text()='Open']")))).isEnabled();

			if (searchIconPresence==true) {
			WebElement ele=	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//mat-icon[contains(text(),'menu')]"))));
			ele.click();
			log.info("Clicked on Menu icon");
			}*/
			
			//WebElement sendIssue =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-card[@class='mat-card']//div//input")));
			//sendIssue.click();
			
			
		/*	By by = By.xpath("//mat-card[@class='mat-card']//div//input");
			WebDriverWait w = new WebDriverWait(driver, 40);
			WebElement element = w.until(ExpectedConditions.elementToBeClickable(by));
			element.click();*/
			
			
			WebElement element = driver.findElement(By.xpath("//mat-card[@class='mat-card']//div//input"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);


			//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//strong[text()='Open']")))).click();
			Thread.sleep(7000);

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//mat-icon[contains(text(),'menu')]")))).click();;

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(text(),'Cash withdrawal')]")))).click();
			log.info("Clicked on Cash withdrawal");
				
				
				String accnumb=data.get("AccountNumber");
				if(accnumb != null && !accnumb.isEmpty()) {
				driver.findElement(By.name("accountNumber")).sendKeys(accnumb);
				passFailScreenshot("FinFlowz");
				test.pass("Enter the Account Number");
				log.info(accnumb);
				}
		
				
				String amount=data.get("Amount");
				if(amount != null && !amount.isEmpty()) {
				driver.findElement(By.xpath("//input[@ng-reflect-placeholder='Amount']")).sendKeys(amount);
				passFailScreenshot("FinFlowz");
				test.pass("Enter the Amount");
				log.info(amount);
				}
				
				
				String slipNumber=data.get("SlipNumber");
				if(slipNumber != null && !slipNumber.isEmpty()) {
				driver.findElement(By.name("slipNumber")).sendKeys(slipNumber);
				passFailScreenshot("FinFlowz");
				test.pass("Enter the Slip Number");
				log.info(slipNumber);
				}
				
				
				String remark=data.get("Remark");
				if(remark != null && !remark.isEmpty()) {
				driver.findElement(By.name("remarks")).sendKeys(remark);
				passFailScreenshot("FinFlowz");
				test.pass("Enter the Remark");
				log.info(remark);
				}
				
				String signature=data.get("Signature");
				if(signature != null && !signature.isEmpty()) {
				driver.findElement(By.xpath("//mat-select[@name='signatureVerified']")).sendKeys(signature);
				passFailScreenshot("FinFlowz");
				test.pass("Select the Signature");
				log.info(signature);
				}
				
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("NextBtn")))).click();
				log.info("Click on NextButton");
				Thread.sleep(3000);
				
				
				String waiveoff=data.get("Waiveroff");
				if(waiveoff != null && !waiveoff.isEmpty() && waiveoff.equalsIgnoreCase("Yes")) {
				driver.findElement(By.xpath("//mat-checkbox[@class='mat-checkbox mat-accent ng-untouched ng-pristine ng-valid']")).click();
				passFailScreenshot("FinFlowz");
				test.pass("Select the WaiverOff"); 
				log.info(waiveoff);
				}
				
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("NextBtn")))).click();
				log.info("Click on NextButton");
				Thread.sleep(3000);
				
				
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("NextBtn")))).click();
				log.info("Click on NextButton");
				Thread.sleep(3000);
				
				String Upload=data.get("UploadDocument");
				     	if(Upload != null && !Upload.isEmpty() && Upload.equalsIgnoreCase("Yes")) {

						Robot robot= new Robot();
						driver.findElement(By.xpath("//span[contains(text(),'Choose file')]")).click();
						robot.setAutoDelay(2000);
						//StringSelection stringSelection = new StringSelection("C:\\Users\\naveen.kumar\\Pictures\\payslipdummy.png");
						StringSelection stringSelection = new StringSelection(data.get("UploadPath"));

						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
				
						robot.keyPress(KeyEvent.VK_CONTROL);			
						robot.keyPress(KeyEvent.VK_V);
					
				
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_V);

						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
							
						String identity=data.get("UploadIdentity");
						if(identity != null && !identity.isEmpty()) {
						driver.findElement(By.xpath("//th[contains(text(),'Purpose')]/following::mat-select[1]")).sendKeys(identity);
						passFailScreenshot("FinFlowz");
						test.pass("Select the Identity"); 
						log.info(identity);
						}
						
						String documenttype=data.get("DocumentType");
						if(documenttype != null && !documenttype.isEmpty()) {
						driver.findElement(By.xpath("//th[contains(text(),'Document Type')]/following::mat-select[2]")).sendKeys(documenttype);
						driver.findElement(By.xpath("//span[contains(text(),'Identity Proof')]")).click();
						passFailScreenshot("FinFlowz");
						test.pass("Select the Document Type"); 
						log.info(documenttype);
						}
	     
						//driver.findElement(By.xpath("//th[contains(text(),'Purpose')]/following::mat-select[1]")).sendKeys("Identity");
						//driver.findElement(By.xpath("//th[contains(text(),'Document Type')]/following::mat-select[2]")).sendKeys("Identity Proof");
						driver.findElement(By.xpath("//mat-icon[contains(text(),'cloud_upload')]")).click();
						Thread.sleep(3000);
				
				}
				
					
				
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("NextBtn")))).click();
				log.info("Click on NextButton");
				
				
				String save=data.get("Save");
				if(save != null && !save.isEmpty() && save.equalsIgnoreCase("Yes")) {
					driver.findElement(By.xpath("//mat-icon[contains(text(),'save')]")).click();//save
				passFailScreenshot("FinFlowz");
				test.pass("Record Save Successfully"); 
				log.info(save);
				}
				
				
				account = driver.findElement(By.name("transactionReferenceNumber")).getAttribute("value");

				
				Fillo fillo=new Fillo();
				Connection connection=fillo.getConnection("C:\\Users\\naveen.kumar\\eclipse-workspace\\naveen123\\proFInflow\\src\\main\\java\\datamanager\\loginData.xlsx");
				String strQuery="Update CashWithDrawal Set TranactionID="+"'"+account+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
				connection.executeUpdate(strQuery);
				connection.close();
				
				String draft=data.get("Draft");
				if(draft != null && !draft.isEmpty() && draft.equalsIgnoreCase("Yes")) {
				driver.findElement(By.xpath("//mat-icon[contains(text(),'drafts')]")).click();//draft
				passFailScreenshot("FinFlowz");
				test.pass("Record Moved to Draft"); 
				log.info(draft);
				}
				
				
				String discard=data.get("Discard");
				if(discard != null && !discard.isEmpty() && draft.equalsIgnoreCase("Yes")) {
				driver.findElement(By.xpath("//mat-icon[contains(text(),'delete_outline')]")).click();
				passFailScreenshot("FinFlowz");
				test.pass("Record Discarded Successfuly"); 
				log.info(discard);
				}
				
			//error pop up "//notifier-container/ul/li"  or "//p[contains(text(),'E_FORM_ERROR - Form has errors!')]"
				Thread.sleep(5000);
				//driver.findElement(By.xpath("//mat-icon[text()='home']")).click();
				
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[contains(text(),'home')]"))).click();;
			ss.CaptureScreenShot(driver, data.get("TestCaseID"));
			passFailScreenshot("FinFlowz");
			test.pass("Execution Pass");
		    extent.flush();

			}
			catch(Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[contains(text(),'home')]"))).click();;
				ss.CaptureScreenShot(driver, data.get("TestCaseID"));
				passFailScreenshot("FinFlowz");
				test.fail("Execution failed");
			    extent.flush();

			continue;

			}
		}
		
/*		pram=new FilloReader().getTestDataInListMap(new File(filepath + "loginData.xlsx"),"FinFlowLogin", "select * from %s");
		
		driver.findElement(By.xpath("//mat-icon[contains(text(),'account_circle')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();*/
		
		/*		Thread.sleep(3000);
		String un = xldata.getCellData("FinFlowLogin", "Username", 3);
		driver.findElement(By.name("username")).sendKeys(un);

		String pwd = xldata.getCellData("FinFlowLogin", "Password", 3);
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();*/

	
	}

	@AfterTest
	public void teardown() {
		driver.close();
    	}
		
	}