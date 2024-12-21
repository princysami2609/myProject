package org.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\feature\\Loginpage.feature", 
					glue = "org.stepdefinition",
					dryRun = true)

public class TestRunner extends AbstractTestNGCucumberTests{

}
