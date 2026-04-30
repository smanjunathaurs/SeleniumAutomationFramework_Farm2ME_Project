package Farm2Me;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtil extends BaseTest {



	public static String captureScreenshot(String testName) {

		String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());

		String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";
		String fileName = testName + "_" + timestamp + ".png";

		String fullPath = folderPath + fileName;

		java.io.File src = ((org.openqa.selenium.TakesScreenshot) driver)
				.getScreenshotAs(org.openqa.selenium.OutputType.FILE);

		java.io.File dest = new java.io.File(fullPath);

		try {
			dest.getParentFile().mkdirs(); // create folder
			org.apache.commons.io.FileUtils.copyFile(src, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return relative path for report
		return "screenshots/" + fileName;
	}
}