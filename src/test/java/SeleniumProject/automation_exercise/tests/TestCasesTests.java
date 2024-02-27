package SeleniumProject.automation_exercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.TestCasesPage;
import testcomponents.BaseTest;

public class TestCasesTests extends BaseTest {

	TestCasesPage tcp;
	
	@Test
	public void NavigateToPage() throws InterruptedException {
		tcp = homepage.goToTestCases();
		Assert.assertEquals(tcp.getPageURL(), "https://www.automationexercise.com/test_cases");
		Assert.assertEquals(tcp.getTestCasesPgHeader(), "TEST CASES");
	}
	
	
	@Test (dependsOnMethods = {"NavigateToPage"})
	public void BrowseTestCases() throws InterruptedException {
		for (int i = 0; i < tcp.getNumerOfTestCases(); i++) {
			tcp.openTestCase(i);
			tcp.closeTestCase(i);
		}
		
		//get list of test cases
		//iterate thru the test cases
		//for each tc
			//open if not collapsed
			//close if collapsed			
	}
}
