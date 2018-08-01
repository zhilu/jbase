package shi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;

//@Aspect
//@Component
public class Profiler {

	@Before("execution(* shi.aop.BusinessTarget.sayHello(..))")
	public void logBeforeTxn(JoinPoint joinpoint) {
		System.out.println("Beginning execution for " + joinpoint.getSignature().getName());
	}

	@After("execution(* shi.aop.BusinessTarget.sayHello(..))")
	public void logAfterTxn(JoinPoint joinpoint) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
	}

	@Around("execution(* shi.aop.BusinessTarget.merryGoAround(..))")
	public void logAroundTxn(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Beginning execution for " + proceedingJoinPoint.getSignature().getName());
		proceedingJoinPoint.proceed();
		System.out.println("Execution completed for " + proceedingJoinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(* shi.aop.BusinessTarget.performTransaction(..))", returning = "retVal")
	public void logResultsAfterTxn(JoinPoint joinpoint, Object retVal) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Value being returned is " + retVal);
	}

	@AfterThrowing(pointcut = "execution(* shi.aop.BusinessTarget.throwException(..))", throwing = "exception")
	public void logResultsAfterError(JoinPoint joinpoint, Throwable exception) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Error in Join Point due to : " + exception.getMessage());
		System.out.println("Advice complete");
	}
}
