package org.uma.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.response.Response;

public class ReportManager {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;
    private static final Logger logger = LogManager.getLogger(ReportManager.class);

    @BeforeSuite
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\test-reports\\" + repName); // specify location of the report

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report
        sparkReporter.config().setReportName("Pet Store Users API"); // name of the report

        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Pest Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environemnt", "QA");

        extent.setSystemInfo("ucon", "navan"); // Potential typo: "Pest" instead of "Pet"
    }

    @Test
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());

        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());
        test.createNode(result.getName());

        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.createNode(result.getName());

        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

    // Add this method to log the response in the Extent Report
    public void logResponseToReport(Response response, ITestResult result) {
        if (test != null) { // Check if a test is currently running
            test.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
            test.log(Status.INFO, "Response Headers: " + response.headers().toString());
            test.log(Status.INFO, "Response Body: <pre>" + response.getBody().prettyPrint() + "</pre>"); // Use <pre> for formatted output
            if (result.getStatus() == ITestResult.FAILURE){
                test.log(Status.FAIL, "Test Failed with response");
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test Passed with response");
            }

        } else {
            logger.warn("Response logging attempted outside of a test context.");
        }
    }
}