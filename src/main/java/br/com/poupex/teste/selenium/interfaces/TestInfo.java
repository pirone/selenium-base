package br.com.poupex.teste.selenium.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Pedro Henrique
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TestInfo {
	
	/**
	 * ID interno do caso de teste que será atualizado no testlink
	 */
	int testCaseId();

}
