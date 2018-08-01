package shi.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("ternaryOperatorBean")
public class TernaryOperator {

	@Value("#{numbersBean.a < numbersBean.d ? true : false}")
	private boolean check;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	@Override
	public String toString(){
		return "TernaryOperator, checking if numbersBean.a is less than numbersBean.d : " + check;
	}
}