package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Generate unique report name based on timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";

            // Create ExtentSparkReporter with the unique report name
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("OrangeHRM Test Report");
            reporter.config().setDocumentTitle("Automation Report");
            reporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Ajanthan");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}