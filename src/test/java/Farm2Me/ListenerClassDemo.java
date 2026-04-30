package Farm2Me;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import com.aventstack.extentreports.*;
import Farm2Me.ExtentReportManager;

public class ListenerClassDemo implements ITestListener {

    public static ExtentReports extent = ExtentReportManager.getReportInstance();
    public static ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String path = ScreenshotUtil.captureScreenshot(result.getName());

        test.pass("Test Passed ✅");

        try {
            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String path = ScreenshotUtil.captureScreenshot(result.getName());

        test.fail("Test Failed ❌: " + result.getThrowable());

        try {
            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}