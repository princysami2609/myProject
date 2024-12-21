package org.stepdefinition;

import org.opencart.CucumberCrashCourse.BaseClass;
import org.openqa.selenium.By;

import io.cucumber.java.en.*;

public class LoginStepDefinition extends BaseClass{
	
	@Given("I am the OpenCart login page")
	public void i_am_the_open_cart_login_page() {
		
		browserLaunch("https://www.opencart.com/index.php?route=account/login");
		implicitWait(20);
	   
	}
	@Given("I have entered a vaild username {string} and password {string}")
	public void i_have_entered_a_vaild_username_and_password(String username, String password) {
	
		findElementXpath("//input[@name='email']").sendKeys(username);
		findElementXpath("//input[@name='password']").sendKeys(password);
		
	   
	}
	@When("I click on login button")
	public void i_click_on_login_button() {
		
	    findElementXpath("(//button[text()='Login'])[1]").click();
		
	}
	@Then("I should be logged in sucessfully")
	public void i_should_be_logged_in_sucessfully() {
	     
		System.out.println("Logged in successfully");
	}


//	@Given("I entered invaild {string} and {string}")
//	public void i_entered_invaild_and(String string, String string2) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("I should see an error message indicating {string}")
//	public void i_should_see_an_error_message_indicating(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
	
}
