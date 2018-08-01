package shi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
//@Component
public class PointCutProfiler {
	
	@Pointcut("execution(* shi.aop.BusinessTarget.sayHello(..))")
	public void m1(){
		
	}
	@Pointcut("execution(* shi.aop.BusinessTarget.merryGoAround(..))")
	public void m2(){
		
	}
	@Pointcut("execution(* shi.aop.BusinessTarget.performTransaction(..))")
	public void m3(){
		
	}
	@Pointcut("execution(* shi.aop.BusinessTarget.throwException(..))")
	public void m4(){
		
	}

	@Before(value="m1()")
	public void logBeforeTxn(JoinPoint joinpoint) {
		System.out.println("Beginning execution for " + joinpoint.getSignature().getName());
	}

	@After(value="m1()")
	public void logAfterTxn(JoinPoint joinpoint) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
	}

	@Around("m2()")
	public void logAroundTxn(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Beginning execution for " + proceedingJoinPoint.getSignature().getName());
		proceedingJoinPoint.proceed();
		System.out.println("Execution completed for " + proceedingJoinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "m3()", returning = "retVal")
	public void logResultsAfterTxn(JoinPoint joinpoint, Object retVal) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Value being returned is " + retVal);
	}

	@AfterThrowing(pointcut = "m4()", throwing = "exception")
	public void logResultsAfterError(JoinPoint joinpoint, Throwable exception) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Error in Join Point due to : " + exception.getMessage());
		System.out.println("Advice complete");
	}
}
