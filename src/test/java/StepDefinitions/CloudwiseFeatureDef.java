package StepDefinitions;

import PageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashSet;

import org.junit.Assert;

public class CloudwiseFeatureDef extends BaseClass {


	@Given("^launch \"([^\"]*)\" browser$")
	public void launch_browser(String browserName) {
		setDriver(browserName);
	}


	@When("^open webpage URL \"([^\"]*)\"$")
	public void user_opens_url(String url) {
	    navigateToURL(url);
	    HomePage.allowAllCookies();
	}
	
	@When("^select language as \"([^\"]*)\"$")
	public void select_lanuage_as(String language) throws InterruptedException {
		HomePage.changeLanguage(language);
	}
	
	@Then("^language should be changed as \"([^\"]*)\" in url$")
	public void language_should_be_changed_as_in_url(String lang) {
		String currentURL=HomePage.getCurrentURL();
		if(!currentURL.contains(lang))
		{
			Assert.fail();
		}
	}

	@Given("^go to \"([^\"]*)\" drop down$")
	public void go_to_drop_down(String dropDownName) {
		HomePage.clickOnToggleIcon();
		HomePage.clickOnDropDown(dropDownName);
	}
	
	@When("^click on \"([^\"]*)\" sub menu$")
	public void click_on(String subMenu) {
		HomePage.clickOnDropDownSubMenu(subMenu);
	}

	@Then("^shoud be navigated to \"([^\"]*)\" page$")
	public void shoud_be_navigated_to_page(String menuName) {
		boolean flag = AllCouldwisersPage.pageNavigationValidation(menuName);
		if(!flag)
		{
			Assert.fail();
		}
	}
	
	@And("^check duplicate name$")
	public void check_duplicate_name() 
	{
		HashSet<String> set=AllCouldwisersPage.getDuplicateNameSet();
		System.out.println("Final Duplicate Name Set: "+set);
		closeDriver();
	}
	
}
