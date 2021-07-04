package Reporting;

import TestSetup.TestSetup;
import Utilities.Log;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Reporting.ExtentManager.getTest;


public class TestListener extends TestSetup implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getDescription();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extent.setSystemInfo("Project Name","Aequilibrium Techinal Assignment");
        ExtentManager.extent.setSystemInfo("Candidate Name","Anant Jain");
        Reporting.ExtentManager.endTest();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("Testcase: " + getTestMethodName((iTestResult)));
    }


    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try {
            Log.info("Testcase " + getTestMethodName(iTestResult) + " passed");
            getTest().log(Status.PASS, MarkupHelper.createLabel(getTestMethodName(iTestResult) + " - " +
                    "Testcase passed", ExtentColor.GREEN));
        } catch (Exception e) {
            System.out.println("Something Went Wrong" + e);
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            Log.info("Testcase for " + getTestMethodName(iTestResult) + " failed.");
            getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage());
            getTest().log(Status.FAIL,
                    MarkupHelper.createLabel(getTestMethodName(iTestResult) + " - " + "Testcase failed",
                            ExtentColor.RED));
        } catch (Exception e) {
            System.out.println("Something Went Wrong" + e);
        }
    }
}