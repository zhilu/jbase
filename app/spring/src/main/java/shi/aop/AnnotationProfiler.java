package shi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationProfiler {

	@Before(value="@annotation(LogHello)")
	public void logBeforeTxn(JoinPoint joinpoint) {
		System.out.println("Beginning execution for " + joinpoint.getSignature().getName());
	}

	@After(value="@annotation(LogHello)")
	public void logAfterTxn(JoinPoint joinpoint) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
	}

	@Around("@annotation(LogT)")
	public void logAroundTxn(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Beginning execution for " + proceedingJoinPoint.getSignature().getName());
		proceedingJoinPoint.proceed();
		System.out.println("Execution completed for " + proceedingJoinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "@annotation(LogR)", returning = "retVal")
	public void logResultsAfterTxn(JoinPoint joinpoint, Object retVal) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Value being returned is " + retVal);
	}

	@AfterThrowing(pointcut = "@annotation(LogE)", throwing = "exception")
	public void logResultsAfterError(JoinPoint joinpoint, Throwable exception) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Error in Join Point due to : " + exception.getMessage());
		System.out.println("Advice complete");
	}
}
