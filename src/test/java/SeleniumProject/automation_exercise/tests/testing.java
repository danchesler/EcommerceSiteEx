package SeleniumProject.automation_exercise.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.TestCasesPage;
import testcomponents.BaseTest;

public class testing {
	
	@Test (expectedExceptions = { IndexOutOfBoundsException.class }, expectedExceptionsMessageRegExp = ".* allowed .*")
	public void test1() {
		int a = 0;
		int b = 5;
		System.out.println("end of test");
		throw new IndexOutOfBoundsException("not allowed test");
	}
	
	@Test (expectedExceptions = { IOException.class })
	public void test2() throws Exception {
		throw new Exception();
	}
	
}
