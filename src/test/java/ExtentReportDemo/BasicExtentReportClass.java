package ExtentReportDemo;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import junit.framework.Assert;
public class BasicExtentReportClass {
	ExtentSparkReporter htmlRepoter;
	ExtentReports reports;
	ExtentTest Test;

	@BeforeTest
	public void StartReport()
	{
		htmlRepoter = new ExtentSparkReporter("ExtentReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlRepoter);

		// Add Environment Details
		reports.setSystemInfo("Computer Name", "localhost");
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("Tester", "S. MANJUNATHA");
		reports.setSystemInfo("Browser Name", "Chrome");

		// Configuration to change look and feel 
		htmlRepoter.config().setDocumentTitle("Extent Report Summary");
		htmlRepoter.config().setReportName("Test Report");
		htmlRepoter.config().setTheme(Theme.STANDARD);
		htmlRepoter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}

	@Test
	public void LaunchBrowserAndOpenURL()
	{
		//create Test
		Test = reports.createTest("Launch Browser And Open URL");
		Assert.assertTrue(true); // Test Passed 
	}
@Test
	public void VerifyTitle()
	{
		//create Test
		Test = reports.createTest("VerifyTitle");
		Assert.assertTrue(false); // Test failed 
	}

@Test
	public void VerifyLogo()
	{
		//create Test
		Test = reports.createTest("VerifyLogo");
		Assert.assertTrue(true); // Test passed
	}

@Test
	public void VerifyEmail()
	{
		//create Test
		Test = reports.createTest("VerifyEmail");
		throw new SkipException("Skipping this Test case with exception....!!!");
	}

@Test
	public void VerifyUserName()
	{
		//create Test
		Test = reports.createTest("Verify UserName");
		Assert.assertTrue(true); // Test passed
	}

	@AfterMethod
	public void getTestResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			Test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAIL", ExtentColor.RED));
			Test.fail(result.getThrowable());
		}
		else if (result.getStatus()==ITestResult.SUCCESS)
		{
			Test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASS", ExtentColor.GREEN));
		}
		else if (result.getStatus()==ITestResult.SKIP)
		{
			Test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIP", ExtentColor.YELLOW));
		}
	}
	
	@AfterTest
	public void TearDown()
	{
		reports.flush();
	}
}