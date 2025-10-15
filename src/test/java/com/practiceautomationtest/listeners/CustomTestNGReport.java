package com.practiceautomationtest.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustomTestNGReport implements ITestListener {

    private PrintWriter writer;
    private int totalTests = 0, passedTests = 0, failedTests = 0, skippedTests = 0;
    private List<Map<String, String>> testResults = new ArrayList<>();

    @Override
    public void onStart(ITestContext context) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            writer = new PrintWriter(new FileWriter("/reports/CustomTestNGReport_" + timestamp + ".html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passedTests++;
        totalTests++;
        saveTestResult(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedTests++;
        totalTests++;
        saveTestResult(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedTests++;
        totalTests++;
        saveTestResult(result, "SKIPPED");
    }

    private void saveTestResult(ITestResult result, String status) {
        Map<String, String> map = new HashMap<>();
        map.put("name", result.getMethod().getMethodName());
        map.put("status", status);
        map.put("class", result.getTestClass().getName());
        map.put("time", String.valueOf((result.getEndMillis() - result.getStartMillis()) / 1000.0) + "s");
        testResults.add(map);
    }

    @Override
    public void onFinish(ITestContext context) {
        writer.println("<html><head><title>Custom TestNG Report</title>");
        writer.println("<style>");
        writer.println("body {font-family: Arial, sans-serif; background-color: #f4f4f4;}");
        writer.println("h1 {color: #333;}");
        writer.println(".summary {margin-bottom: 20px;}");
        writer.println(".summary span {margin-right: 20px; font-weight: bold;}");
        writer.println("table {border-collapse: collapse; width: 100%;}");
        writer.println("th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}");
        writer.println("th {background-color: #4CAF50; color: white;}");
        writer.println(".PASS {background-color: #d4edda;}");
        writer.println(".FAIL {background-color: #f8d7da;}");
        writer.println(".SKIPPED {background-color: #fff3cd;}");
        writer.println("</style></head><body>");

        writer.println("<h1>Custom TestNG Report</h1>");
        writer.println("<div class='summary'>");
        writer.println("<span>Total: " + totalTests + "</span>");
        writer.println("<span>Passed: " + passedTests + "</span>");
        writer.println("<span>Failed: " + failedTests + "</span>");
        writer.println("<span>Skipped: " + skippedTests + "</span>");
        writer.println("</div>");

        writer.println("<table>");
        writer.println("<tr><th>Test Name</th><th>Class</th><th>Status</th><th>Time</th></tr>");
        for (Map<String, String> t : testResults) {
            writer.println("<tr class='" + t.get("status") + "'>");
            writer.println("<td>" + t.get("name") + "</td>");
            writer.println("<td>" + t.get("class") + "</td>");
            writer.println("<td>" + t.get("status") + "</td>");
            writer.println("<td>" + t.get("time") + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("</body></html>");
        writer.close();
    }
}