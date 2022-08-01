package PageObjects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import dev.failsafe.internal.util.Assert;


public class AllCouldwisersPage extends BaseClass {
	
	public static String nameXpath="//div[@class=\"inner\"]//h3";
	public static String cloudwiseTag="//div[@class=\"container\"]//h4[text()=\"Cloudwise\"]";
	public static String deptNameXpath= "//div[@class=\"inner\"]//ancestor::div[@class=\"wpb_wrapper\"]//div[@class=\"flip-box-front\"]//h3";
	
	
	static String buildDeptXpath(String dept)
	{
		return "//h3[text()=\""+dept+"\"]//ancestor::div[@class=\"wpb_wrapper\"]//div[@class=\"flip-box-front\"]";
	}
	
	static String whoWeAreButtonXpath(String dept)
	{
		return "//h3[text()=\""+dept+"\"]//ancestor::div[@class=\"wpb_wrapper\"]//div[@class=\"flip-box-back\"]//a[contains(@class,\"nectar-button\")]";
	}
	
	
	static String buildMenuOptionPageXpath(String menuName)
	{
		return "//p[@id=\"breadcrumbs\"]//*[text()=\""+menuName+"\"]";
	}
	
	public static boolean pageNavigationValidation(String menuName)
	{
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildMenuOptionPageXpath(menuName)))).isDisplayed();
		} catch (Exception e) {
			System.out.println("Will try again");
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildMenuOptionPageXpath(menuName)))).isDisplayed();
		}
	}
	
	
	public static HashSet<String> getDuplicateNameSet() 
	{
		HashMap<String,Integer> map= new HashMap<String,Integer>();
		HashSet<String> set = new HashSet<String>();
		Actions action = new Actions(driver);
		try {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cloudwiseTag)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(deptNameXpath)));
			List<WebElement> elements = driver.findElements(By.xpath(deptNameXpath));
			String deptNames[] = new String[elements.size()];
			
			for(int i=0; i<elements.size(); i++)
			{
				//System.out.println("Dept Name: "+elements.get(i).getText());
				deptNames[i] = elements.get(i).getText();
			}
			
			for(int j=0; j < deptNames.length ;j++)
			{
				String xpathForDept= buildDeptXpath(deptNames[j]);
				WebElement elm = driver.findElement(By.xpath(xpathForDept));
				action.moveToElement(elm).perform();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(whoWeAreButtonXpath(deptNames[j]))));
				
				WebElement whoWeAreButton = driver.findElement(By.xpath(whoWeAreButtonXpath(deptNames[j])));
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].click()", whoWeAreButton);
				
				
				List<WebElement> nameElems = driver.findElements(By.xpath(nameXpath));
				for(WebElement elem: nameElems)
				{
					if(map.containsKey(elem.getText()))
					{
						map.put(elem.getText(), map.get(elem.getText())+1);
					    set.add(elem.getText());
					}
					else
					{
						map.put(elem.getText(), 1);
					}
				}
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildMenuOptionPageXpath("Alle Cloudwisers")))).click();
			}
			System.out.println("All employees name with count(duplicate): "+map);
			return set;
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
			Assert.isTrue(false, "Employee details not found");
			return set;
		}
		
	}

}
