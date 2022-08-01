package PageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseClass {
	
	public static String useNecessaryCookiesOnlyXpath="//div[@id=\"CybotCookiebotDialogBodyButtons\"]//a[@id=\"CybotCookiebotDialogBodyButtonDecline\"]";
	public static String allowAllCookiesXpath="//div[@id=\"CybotCookiebotDialogBodyButtons\"]//a[@id=\"CybotCookiebotDialogBodyButtonAccept\"]";
	public static String toggleIconXpath="//div[@class=\"container\"]//div[contains(@class,\"std-menu\")]";
	public static String toggleIconId="toggle-nav";
	
	
	static String buildChangeLanguageXpath(String language)
	{
		return "//div[@id=\"mobile-menu\"]//li[contains(@class,\"lang-item\")]//img[@title=\""+language+"\"]";
	}
	
	static String buildDropDownXpath(String dropDown)
	{
		return "//div[@id=\"mobile-menu\"]//div[@class=\"container\"]//li[contains(@class,\"menu-item-has-children\")]//a[text()=\""+dropDown+"\"]//*[@class=\"icon-angle-down\"]";
	}
	
	static String buildDropMenuItemsXpath(String subMenu)
	{
		return "//div[@id=\"mobile-menu\"]//div[@class=\"container\"]//li[contains(@class,\"menu-item-has-children\")]//ul[@class=\"sub-menu\"]//*[text()=\""+subMenu+"\"]";
	}
	
	public static void useNecessaryCookiesOnly()
	{
		try {
			if(driver.findElement(By.xpath(useNecessaryCookiesOnlyXpath)).isDisplayed())
			{
				driver.findElement(By.xpath(useNecessaryCookiesOnlyXpath)).click();
			}
			
		} catch (Exception e) {
			System.out.println("Cookies already accepted");
		}
	}
	

	public static void allowAllCookies()
	{
		try {
			if(driver.findElement(By.xpath(allowAllCookiesXpath)).isDisplayed())
			{
				driver.findElement(By.xpath(allowAllCookiesXpath)).click();
			}
		} catch (Exception e) {
			System.out.println("Cookies already accepted");
		}
		
	}
	
	public  static void clickOnToggleIcon() 
	{
		try {
			
			Thread.sleep(5000);  // need to stable DOM elements 
			wait.until(ExpectedConditions.elementToBeClickable(By.id(toggleIconId)));
			WebElement toggleIcon = driver.findElement(By.id(toggleIconId));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", toggleIcon);
		}
		catch(Exception e)
		{
			System.out.println("Will try again to click on toggle icon");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toggleIconXpath))).sendKeys(Keys.ENTER);
		}
		
	}
	
	public static void changeLanguage(String language)
	{
		try {
			clickOnToggleIcon();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildChangeLanguageXpath(language)))).click();
			allowAllCookies();
		} catch (Exception e) {
			System.out.println("Will try again to click on language");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildChangeLanguageXpath(language)))).click();
			allowAllCookies();
		}
		
	}
	
	public static String getCurrentURL()
	{
		return driver.getCurrentUrl();
	}
	
	public static void clickOnDropDown(String dropDownName)
	{
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildDropDownXpath(dropDownName)))).click();
		} catch (Exception e) {
			System.out.println("Will try again to click on drop down");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildDropDownXpath(dropDownName)))).click();
		}
	}
	
	public static void clickOnDropDownSubMenu(String subMenu)
	{
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildDropMenuItemsXpath(subMenu)))).click();
		} catch (Exception e) {
			System.out.println("Will try again to click on drop down sub menu");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buildDropMenuItemsXpath(subMenu)))).click();
		}
		
	}
	
}
