package shi.aop;

import org.springframework.stereotype.Component;

@Component
public class BusinessTarget {
	@LogT
	public String performTransaction(String arg) {
		System.out.println("Performing Txn for: " + arg);
		return "Transaction " + arg + " Successful";
	}

	@LogR
	public void merryGoAround() {
		System.out.println("Running merryGoAround for Business");
	}

	@LogHello
	public void sayHello() {
		System.out.println("Hello says Hello..");
	}

	@LogE
	public void throwException() {
		System.out.println("Business class about throw an NPE..");
		throw new NullPointerException("THrowing custom Exception");
	}
}
