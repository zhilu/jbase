package shi.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("testRegExBean")
public class TestRegEx {
	
	@Value("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String regEx;
	
	@Value("#{(testBean.email matches testRegExBean.regEx)== true ? '-Yes there is a match.' : '-No there is no match.' }")
	private String regExResult;
	
	@Value("#{ ('100' matches '\\d+') == true ? '-Yes this is digit.' : '-No this is not a digit.' }")
	private String numberResult;
 
	public String getRegEx() {
		return regEx;
	}

	public void setRegEx(String regEx) {
		this.regEx = regEx;
	}
	
	public String getRegExResult() {
		return regExResult;
	}

	public void setRegExResult(String regExResult) {
		this.regExResult = regExResult;
	}

	public String getNumberResult() {
		return numberResult;
	}

	public void setNumberResult(String numberResult) {
		this.numberResult = numberResult;
	}

	@Override
	public String toString() {
		return "TestRegex :  \n Does testBean.email match the ^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$ " 
	+ regExResult + "\n Is 100 a valid number ? " + numberResult;
				
	}

}