package br.com.poupex.teste.selenium.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportListener implements IReporter {
	
	private ExtentReports extent;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-m-ss");
	private String outputDir = System.getProperty("user.dir", "D:/Relatório de execução Selenium") + "/Relatorios/" + sdf.format(new Date());
	private static final String ExtentReport = System.getProperty("user.dir") + "/evidencias";

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(outputDir + "/Relatorio.html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
  
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
  
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        System.out.println("Relatório gerado em "+outputDirectory);
        extent.flush();
        extent.close();
    }
	
	private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
  
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(getNomeTeste(result));
  
                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));
                
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
  
                String message = "Test " + status.toString().toLowerCase() + "ed";
  
                if (result.getThrowable() != null) {
                    message = result.getThrowable().getMessage();
                }
                
                try {
					Files.move(getDefaultPrintScreen(result) , getNewPrintRepository(result));
				} catch (IOException e) {
					e.printStackTrace();
				}
                test.log(status, message);
                test.log(LogStatus.INFO, "Evidência: " + getNewPrintRepository(result).getAbsolutePath());
                
                extent.endTest(test);
            }
        }
    }

	private File getNewPrintRepository(ITestResult result) {
		return new File(outputDir+"/"+result.getMethod().getMethodName()+ ".png");
	}

	private File getDefaultPrintScreen(ITestResult result) {
		return new File(ExtentReport + "/"+ result.getMethod().getMethodName()+ ".png");
	}
	
	private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
	
	private String getNomeTeste(ITestResult result) {
		String nomeTeste;
		if(result.getTestName() != null) {
			nomeTeste = result.getTestName();
		} else {
			nomeTeste = result.getMethod().getMethodName();
		}
		return nomeTeste;
	}

}
