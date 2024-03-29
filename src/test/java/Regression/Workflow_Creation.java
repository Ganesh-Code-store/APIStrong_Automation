package Regression;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Workflow_Creation extends BaseClass {
	JavascriptExecutor js=(JavascriptExecutor)driver;
	public static String workflowname="";
	
	void CreateWorkFlow() throws InterruptedException
	{
		driver.findElement(By.xpath(dropdownSubMenureport)).click();
		driver.findElement(By.xpath(Workflows)).click();
		Thread.sleep(2000);

		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath(Gotit)).click();

		WebDriverWait wait=new WebDriverWait(driver,20);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(btnCreateProjectPopup))).click();
		//driver.findElement(By.id(btnCreateProjectPopup)).click();
		Thread.sleep(3000);
		
		 workflowname="Flow"+Math.ceil((Math.random()*100));
		
		driver.findElement(By.id(newprojectname)).sendKeys(workflowname);
		driver.findElement(By.id(btnCreateAF)).click();
		Thread.sleep(3000);
		try
		{
			driver.findElement(By.xpath(ErrorAlertMsg));
			js.executeScript("window.scrollBy(0,1000)");

			driver.findElement(By.id(btnCreateProjectPopup)).click();
			Thread.sleep(3000);
			 workflowname="Flow"+Math.ceil((Math.random()*1000));

			driver.findElement(By.id(newprojectname)).sendKeys(workflowname);
			driver.findElement(By.id(btnCreateAF)).click();
			System.out.println("Workflow created!");

			Thread.sleep(3000);	
		}
		catch(Exception e)
		{
			System.out.println("Workflow created!");

		}
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//a[@title='"+this.workflowname+"']")).click();	
		
	}
	 void AddAPIsToWorkflow(int n) throws InterruptedException
	{
		Actions act=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		for(int i=1;i<=n;i++)
		{
			act.dragAndDrop(driver.findElement(By.xpath("//li[@class='sidebar-row ui-sortable-handle']"+"['"+i+"']")), driver.findElement(By.xpath(dragArea))).perform();
		}
		Thread.sleep(2000);
		driver.findElement(By.id(SaveFlow)).click();	
		Thread.sleep(2000);
	
	}

	 public void executeFlow()
	 {
			driver.findElement(By.id(ExecFlow)).click();	
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	 }
	 
	public static void main(String[] args) {
		try
		{
			new BaseClass().setup();
			BaseClass.Login("workflow@mailinator.com", "test12");			new Workflow_Creation().CreateWorkFlow();
			new Workflow_Creation().AddAPIsToWorkflow(5);
			new Workflow_Creation().executeFlow();
			
			driver.quit();	
		}
		catch(Exception e)
		{
			System.out.println(e);
			driver.quit();
		}
		

	}

}
