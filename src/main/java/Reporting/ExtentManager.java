package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static TestSetup.TestSetup.extTest;


public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter sparkReporter;

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    public synchronized static ExtentReports getReporter() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());

        if (extent == null) {
            extent = new ExtentReports();

            sparkReporter =
                    new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" +
                            "TestResults_" + timeStamp + ".html");
            try {
                sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return extTest.get();
    }

    public static synchronized void endTest() {
        getReporter().flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
        test = getReporter().createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized void printLogs(String logs) {
        getTest().log(Status.INFO, logs);
    }
}
