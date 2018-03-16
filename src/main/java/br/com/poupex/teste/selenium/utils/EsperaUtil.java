package br.com.poupex.teste.selenium.utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe responsável por realizar as esperas por elementos na tela. O
 * implicitlyWait () e WebDriverWait () não funcionam bem juntos no mesmo teste.
 * Foi anulado o implicitlyWait () antes de chamar WebDriverWait porque
 * implicitlyWait () também define o tempo de espera do "driver.findElement ()".
 * Evitando problemas com requisições AJAX.
 * 
 * @link http://chon.techliminal.com/ajax_wait/#/intro
 * 
 * @author vinicius.souza
 * 
 */
public class EsperaUtil {

    private static final int TEMPO_SEGUNDOS = 1000;

    private static final long IMPLICIT_TIME_OUT = 10;
    private static final long IMPLICIT_TIME_OUT_ZERO = 0L;

    private static final long POOL_TIME = 2L;
    private static final long TIME_OUT = 30;

    private static Logger LOG = LoggerFactory.getLogger(EsperaUtil.class);

    public static boolean esperaPorTextoNoElemento(WebDriver driver, final By by, String texto) {    	
        boolean retorno = false;
        zerarTimeOut(driver);
        espere(1000);
        try {
            Wait<WebDriver> wait = getWait(driver);

            retorno = wait.until(ExpectedConditions.textToBePresentInElementLocated(by,
                    texto));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return retorno;
    }


    public static WebElement esperaElementoClicavelDOM(WebDriver driver, By by) {
        WebElement element = null;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return element;
    }
    

    public static List<WebElement> esperaElementosVisiveis(WebDriver driver, WebElement... elements) {
        List<WebElement> elementos = null;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            elementos = wait.until(ExpectedConditions.visibilityOfAllElements(Arrays.asList(elements)));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return elementos;
    }

    public static boolean esperaElementoInvisivel(WebDriver driver, By by) {
        boolean retorno = false;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            retorno = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return retorno;
    }

    public static WebElement esperaElementoClicavel(WebDriver driver, By by) {
        WebElement element = null;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return element;
    }

    public static Boolean esperaElementoSelecionavel(WebDriver driver, By by) {
        Boolean retorno = null;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            retorno = wait.until(ExpectedConditions.elementToBeSelected(by));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return retorno;
    }

    public static Boolean esperaElementoSelecionavel(WebDriver driver,
            WebElement elemento) {
        Boolean retorno = null;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            retorno = wait.until(ExpectedConditions.elementToBeSelected(elemento));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return retorno;
    }

    public static WebElement esperaElementoPresente(WebDriver driver, By by) {
        WebElement element = null;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return element;
    }

    public static WebElement esperaElementoVisivel(WebDriver driver, By by) {
        WebElement element = null;
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return element;
    }

    public static WebElement esperaElementoVisivel(WebDriver driver, WebElement elemento) {
        zerarTimeOut(driver);
        try {
            Wait<WebDriver> wait = getWait(driver);
            elemento = wait.until(ExpectedConditions.visibilityOf(elemento));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            restaurarTimeOut(driver);
        }
        return elemento;
    }

    private static void restaurarTimeOut(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIME_OUT, TimeUnit.SECONDS);
    }

    private static void zerarTimeOut(WebDriver driver) {
        espere(1); // 1 Segundo
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIME_OUT_ZERO, TimeUnit.SECONDS);
    }

    private static Wait<WebDriver> getWait(WebDriver driver) {
    	 
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(TIME_OUT))
                .pollingEvery(Duration.ofSeconds(POOL_TIME))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait;
    }

    public static void espere(int tempoSegundos) {
    	LOG.warn("Esse método deveria ser evitado, devido ao Thread.sleep n�o garantir que vai levar " + tempoSegundos + " para executar");
        try {
            Thread.sleep(tempoSegundos * TEMPO_SEGUNDOS);
        } catch (InterruptedException e) {
            LOG.error("Erro ao realizar espera por tempo: " + tempoSegundos + " segundos");
        }
    }
}