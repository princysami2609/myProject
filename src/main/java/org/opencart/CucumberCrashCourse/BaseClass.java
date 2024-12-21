package org.opencart.CucumberCrashCourse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.Action;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class BaseClass {
	// Instance variable to access other method
		WebDriver driver;
	//1. browserLaunch use camelcase for method naming, driver.get("https://letcode.in/alert");
	//**Input--String return--No return
	 
	public void browserLaunch(String Url) {
		driver = new ChromeDriver();
		driver.get(Url);
		driver.manage().window().maximize();	
	}
	
	public void firefoxLaunch(String http) {
		driver = new FirefoxDriver();
		driver.get(http);
		driver.manage().window().maximize();

	}
	
//2. findElement by id, input: id "String", return WebElement
	
public WebElement findElementid(String id) {
		
		WebElement elementId = driver.findElement(By.id(id));
		return elementId;
}

//3. findElement by name, input: name "String", return WebElement

public WebElement findElementName(String name) {
		
		WebElement elementName = driver.findElement(By.name(name));
		return elementName;
}

//4. findElement by xpath, input: xpath: String, return webelement
	
	public WebElement findElementXpath(String xpath) {
		
		WebElement elementXpath = driver.findElement(By.xpath(xpath));
		return elementXpath;
	}
	
//5. SendValues Syntax: WebElement.sendKeys("Character ") input	//WebElement and Character "String" so input : (Webelement, String)
	// and no return its Void.
	
	public void sendValues(WebElement element, String value) {
			
		element.sendKeys(value);

	}
//6. to Read value from Excel Method: getDataFromExcel, input: FilePath: String, Sheetname: String, int RowNo, intCellNo
	//return: CellData, to access excel File, workbook, sheet, row , cell
	
	public String getDataFromExcel(String pathname, String Sheetname, int RowNo, int CellNo) throws IOException {
		
		//declare return type, default value for string is null
		String cellData = null;
		
		File file = new File(pathname);
		FileInputStream fileinputstream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileinputstream);
		Sheet sheet = workbook.getSheet(Sheetname);
		Row row = sheet.getRow(RowNo);
		Cell cell = row.getCell(CellNo);
// condition to check cell type String, number in number either date or number using if else loop
	//getCellType() method to find celltype
		int cellType = cell.getCellType();
		
		if (cellType==1)
		{
			cellData = cell.getStringCellValue();
		}
		
		if(cellType==0)
		{
			// date 
			if(DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				//to change date format
				SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
				cellData = dateFormat.format(dateCellValue);
			}
			
			else
			{
				double numericCellValue = cell.getNumericCellValue();
				long l = (long) numericCellValue;
				cellData = String.valueOf(l);
			}
		}
		return cellData;
	}
//7.getText	syntax: webelement.getText, input: webElement, return: no return, ** doubt
	
	public String getText(WebElement element) {
		String getText = element.getText();	
		System.out.println(getText);
		return getText;
	}
	
// 8. getAttribute:	input - WebElement element ID, and attribute name , return attribute value- string
	public String getAttirbute(WebElement element1, String attributeName) {
		String attributeValue = element1.getAttribute("attributeName");
		System.out.println(attributeValue);
		return attributeValue;

	}
	
//9. clickElement syntax: WebElement.click, input: WebElement return no return	
	public void clickElement(WebElement element) {
	    element.click();
	}
	
//10 closeSession
	
	public void closeSession() {
		driver.quit();

	}
	
// 11 close no return
	
	public void close() {
		driver.close();
	}
	
		
// 12.Action class moveToElement(), input Webelement e.g. xpath, return: no return
	public void moveToElement(WebElement elemenet) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(elemenet).build().perform();

	}
	
//13. Action class rightClick, input WebElement, return no return, how to declare
	// to reduce repeat coding

	public void rightClick(WebElement element) {
	
		Actions actions = new Actions(driver);
		actions.contextClick(element).build().perform();

	}
	 
//14.Action Class, dragAndDrop
	public void dragAndDrop(WebElement source, WebElement destination) {
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).perform();
	}
	
// 15.	Alert acceptAlert Simple Alert
	
	public void acceptAlert() {
	
		Alert alert = driver.switchTo().alert();
		alert.accept();	

	}
// 16 dismissAlert
	public void dismissAlert() {
		
		Alert alert = driver.switchTo().alert();
				alert.dismiss();

	}
	
//17 promptAlert driver.switchTo().alert().sendKeys("Key test");	
	//driver.switchTo().alert().accept(); , input String, return void
	public void promptAlert(String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
		alert.accept();

	}
	
// 18 getCurrentUrl
	
	public String getCurrentUrl() {
		
		return driver.getCurrentUrl();

	}
	
//19  navigateTo
	public void navigateTo(String URL) {
		
		driver.navigate().to(URL);

	}
//20 backToUrl
	public void backToUrl() {
		
		driver.navigate().back();

	}
//21 forward
	
	public void forward() {
		
		driver.navigate().forward();
	}
	
	//22. refresh
	
	public void refresh() {
		
	 driver.navigate().refresh();

	}
	//23. waits ImplicitWait int seconds, datatype long, no return 
	public void implicitWait(long seconds) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	//24. Explicit wait, VisibilityOfElement, input: webelement, time seconds, return webelement
	
	public WebElement visibilityOfElement(WebElement element, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		WebElement untill = wait.until(ExpectedConditions.visibilityOf(element));
		return untill;			
	}
	
	//25. Wait ElementToBeClickable
	public WebElement waitElementToBeClickable(WebElement element, long seconds) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
		WebElement untill = wait.until(ExpectedConditions.elementToBeClickable(element));
		return untill;
	}
	
	//26. Wait for ElementToBeSelected 
	
	public Boolean waitElementToBeSelected(WebElement element1, long seconds) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		Boolean untilElementSelected = wait.until(ExpectedConditions.elementToBeSelected(element1));
		return untilElementSelected;
	}

	//27. Select Class selectByVisibleText, input webelement, String text, return - no return
	public void selectByVisibleText(WebElement element, String dropdowntext) {
		
		Select select = new Select(element);
		select.selectByVisibleText(dropdowntext); 
		
	}
	
	//28. selectByValue input webelement, String value, return - no return
	public void selectByValue(WebElement element, String Value) {
		 Select select = new Select(element);
		 select.selectByValue(Value);
	}
	
	//29 selectByIndex input webelement, int index, return - no return
	public void selectByIndex(WebElement element, int index) {
		
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	// 30 isMutiple, input WebElement element, return boolean 
	public boolean isMutiple(WebElement element) {
		Select select = new Select(element);
		boolean multiple = select.isMultiple();
		return multiple;
	}
	
	//31. getAllOptions
	public int getAllOptions(WebElement element) {
		
		Select select = new Select(element);
		List<WebElement> allOptions = select.getOptions();
		int size = allOptions.size();
		
		for (int i = 0; i < allOptions.size(); i++) {
			WebElement eachElement = allOptions.get(i);
			System.out.println(eachElement.getText());
		}
    return size;
	}

	
	//32. deselectByValue input WebElement , String value
	public void deselectByValue(WebElement element, String value) {
		
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	
	//33.Frame , switchToFrameByIndex input: String name or by ID
	
	public void switchToFrameByName(String nameOrID) {
		driver.switchTo().frame(nameOrID);

	}
	
	//34. switchToFrameByValue input String value
	
	public void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}
	
	//35. outOfFrame no input no return , only action done
	
	public void outOfFrame() {
		driver.switchTo().defaultContent();
	}
	
	//36. switchToParentFrame
	public void switchToParentFrame() {
		driver.switchTo().parentFrame();

	}
	//37. Windowhandle, to getAllWindowsID input : no input, Return: list of windowhandle which will ne 
	//unique and so its return type is string
	public Set<String> getAllWindowsID() {
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		return windowHandles;
	}
	
	//38. CurrentWindow getCurrentWindowHandle
	public String getCurrentWindowHandle() {
		String windowHandle = driver.getWindowHandle();
		System.out.println(windowHandle);
		return windowHandle;
	}
	
	//39.switching windows
	
	public void switchingWindow(String parentWindow) {
		Set<String> allWindow = driver.getWindowHandles();
		System.out.println(allWindow);
				
		for (String eachWindow : allWindow) {
			if (!parentWindow.equals(eachWindow)) {
				driver.switchTo().window(eachWindow);				
			}
		}
	}
	
	//40. Table , getRowCount intput WebElement, String, to pass tagname "tr" for tablerow 
	
	public int getRowCount(WebElement table) {
	List<WebElement> tableRows = table.findElements(By.tagName("tr"));
	System.out.println(tableRows.size());
	return tableRows.size();
	
	}
	//41. Table, getColumnCount input WebElement, String tagname "td" for table column
	
	public int getColumnCount(WebElement table) {
		List<WebElement> tableColumn = table.findElements(By.tagName("td"));
			System.out.println(tableColumn.size());
		return tableColumn.size();
		}
  //42. to find no of heading in table, findTableData
	public int findTableData(WebElement table) {
		 List<WebElement> tableData = table.findElements(By.tagName("th"));
		 System.out.println(tableData.size());
		 return tableData.size();
	}
	
 //43. MouseActions keyDown, // AWT, Abstract Window Toolkit Exception
	public void pressDownArrow() throws AWTException {
		Robot m = new Robot();
		m.keyPress(KeyEvent.VK_DOWN);
		m.keyRelease(KeyEvent.VK_DOWN);
	}
	
//44. MouseActions keyUp
	public void pressUpArrow() throws AWTException {
		 Robot m = new Robot();
		 m.keyPress(KeyEvent.VK_UP);
		 m.keyPress(KeyEvent.VK_UP);
	}
//45.MouseActions Enter
	
	public void pressEnter() throws AWTException {
		Robot m = new Robot();
		m.keyPress(KeyEvent.VK_ENTER);
		m.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//46. for thread sleep
	public void sleep(long milliseconds) throws InterruptedException {
		
		Thread.sleep(milliseconds);

	}
	//47.Screenshot
	
	public void takeScreensShot(String filePath) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("filePath");
		FileUtils.copyFile(source, dest);
	}
	
}
