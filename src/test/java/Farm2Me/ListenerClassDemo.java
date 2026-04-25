package Farm2Me;

import org.testng.*;
import com.aventstack.extentreports.*;

public class ListenerClassDemo implements ITestListener {

    ExtentReports extent = ExtentReportManager.getReportInstance();
    ExtentTest test;

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}