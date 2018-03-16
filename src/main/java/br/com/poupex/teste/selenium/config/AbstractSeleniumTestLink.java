package br.com.poupex.teste.selenium.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.Execution;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public abstract class AbstractSeleniumTestLink extends AbstractSeleniumTest {
	
	private static TestLinkTO testLinkTO = new TestLinkTO();
	public static TestLinkAPI testLinkAPI;
	
	@BeforeSuite
    public void beforeSuite() {
		super.beforeSuite();
		try {
			testLinkAPI = new TestLinkAPI(new URL(getTestLinkTO().TESTLINK_URL), getTestLinkTO().TESTLINK_KEY);
		} catch (TestLinkAPIException | MalformedURLException e) {
			e.printStackTrace();
		}
		getTestLinkTO().setCasoDeTesteId(testCaseId());
	}
	
	@AfterMethod
    protected void afterMethod(ITestResult result) {
		super.afterMethod(result);
		Execution execucao = testLinkAPI.getLastExecutionResult(getTestLinkTO().getPlanoDeTesteId(), testCaseId(), null);
		testLinkAPI.uploadExecutionAttachment(
                execucao.getId(), //executionId 
                "Teste Automatizado "+result.getMethod().getMethodName(), //title 
                "", //description  
                "Evidencia-"+result.getMethod().getMethodName()+".jpg", //fileName 
                "image/jpeg", //fileType
                printscreen.getPrintAsString()); //content
        System.out.println("Evidência anexada no TestLink com sucesso");
    }
	
	protected static void atualizaTestLink(Integer idCT, ExecutionStatus resultado) throws TestLinkAPIException, MalformedURLException {
		testLinkAPI.reportTCResult(idCT, null, getTestLinkTO().getPlanoDeTesteId(), resultado, getTestLinkTO().getBuildId(), null, null, false, null, null, null, null, true);
		System.out.println(idCT +" Atualizado com sucesso");
	}
	
	protected static void atualizaTestLink(Integer idCT, ExecutionStatus resultado, String msg) throws TestLinkAPIException, MalformedURLException {
		testLinkAPI.reportTCResult(idCT, null, getTestLinkTO().getPlanoDeTesteId(), resultado, getTestLinkTO().getBuildId(), null, msg, false, null, null, null, null, true);
		System.out.println(idCT +" Atualizado com sucesso");
	}
	
	public abstract Integer testCaseId();
	
	protected void registraSucesso() throws TestLinkAPIException, MalformedURLException {
		atualizaTestLink(testCaseId(), ExecutionStatus.PASSED);
	}
	
	protected void registraFalha() throws TestLinkAPIException, MalformedURLException {
		atualizaTestLink(testCaseId(), ExecutionStatus.FAILED);
	}
	
	protected void registraFalhaComMensagem(Exception e) throws TestLinkAPIException, MalformedURLException {
		atualizaTestLink(testCaseId(), ExecutionStatus.FAILED, e.getMessage());
		Assert.fail(e.getClass().getName()+" - "+e.getMessage(), e.getCause());
		e.printStackTrace();
	}
	
	protected void registraFalhaComMensagem(AssertionError e) throws TestLinkAPIException, MalformedURLException {
		atualizaTestLink(testCaseId(), ExecutionStatus.FAILED, e.getMessage());
		Assert.fail(e.getMessage(), e.getCause());
		e.printStackTrace();
	}

	protected static TestLinkTO getTestLinkTO() {
		return testLinkTO;
	}

	protected static void setTestLinkTO(TestLinkTO testLinkTO) {
		AbstractSeleniumTestLink.testLinkTO = testLinkTO;
	}


}
