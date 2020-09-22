package spring.mvc.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggerAspectJ {

	private static final Logger LOGGER=LogManager.getLogger(LoggerAspectJ.class);
	
	@Before(value = "execution(* spring.mvc.controller.HelloController.*(..))")
	public void logerBefore(JoinPoint joinPoint) {
		LOGGER.info("Before method: "+joinPoint.getSignature().getName());
	}
	
	@After(value = "execution(* spring.mvc.controller.HelloController.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		LOGGER.info("After method: "+joinPoint.getSignature().getName());
	}
	
	@AfterReturning(pointcut = "execution(* spring.mvc.controller.HelloController.*(..))",returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		LOGGER.info("After return method: "+joinPoint.getSignature().getName());
		LOGGER.info("Method returned value is : "+result);
	}
	
	@AfterThrowing(pointcut = "execution(* spring.mvc.controller.HelloController.*(..))",throwing = "error")
	public void logThrow(JoinPoint joinPoint,Throwable error) {
		LOGGER.info("Exception in method : "+joinPoint.getSignature().getName());
		LOGGER.error("Exception is : "+error);
	}
}
