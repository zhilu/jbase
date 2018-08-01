package shi.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("operatorsBean")

public class Operators {
	
		//Relational operators
	 
		@Value("#{numbersBean.a == 100}") //true
		private boolean equalTest;
	 
		@Value("#{numbersBean.a != numbersBean.b}") //true
		private boolean notEqualTest;
	 
		@Value("#{numbersBean.b < numbersBean.a}") //false
		private boolean lessThanTest;
	 
		@Value("#{numbersBean.c <= numbersBean.b}") //false
		private boolean lessThanOrEqualTest;
	 
		@Value("#{numbersBean.d > numbersBean.e}") //false
		private boolean greaterThanTest;
	 
		@Value("#{numbersBean.d >= numbersBean.c}") //true
		private boolean greaterThanOrEqualTest;
	 
		//Logical operators
	 
		@Value("#{numbersBean.a == 100 and numbersBean.b < 100}") //false
		private boolean andTest;
	 
		@Value("#{numbersBean.c == 150 or numbersBean.d < 250}") //true
		private boolean orTest;
	 
		@Value("#{!(numbersBean.e == 300)}") //false
		private boolean notTest;
	 
		//Mathematical operators
	 
		@Value("#{numbersBean.a + numbersBean.b}") //250
		private double addTest;
	 
		@Value("#{'hello' + '@' + 'world'}") //hello@world
		private String addStringTest;
	 
		@Value("#{numbersBean.d - numbersBean.c}") //50
		private double subtractionTest;
	 
		@Value("#{numbersBean.a * numbersBean.e}") //30000
		private double multiplicationTest;
	 
		@Value("#{numbersBean.e / numbersBean.a}") //3
		private double divisionTest;
	 
		@Value("#{numbersBean.e % numbersBean.b}") //0.0
		private double modulusTest ;
	 
		@Value("#{numbersBean.a ^ 2}") //10000
		private double exponentialPowerTest;

		public boolean isEqualTest() {
			return equalTest;
		}

		public void setEqualTest(boolean equalTest) {
			this.equalTest = equalTest;
		}

		public boolean isNotEqualTest() {
			return notEqualTest;
		}

		public void setNotEqualTest(boolean notEqualTest) {
			this.notEqualTest = notEqualTest;
		}

		public boolean isLessThanTest() {
			return lessThanTest;
		}

		public void setLessThanTest(boolean lessThanTest) {
			this.lessThanTest = lessThanTest;
		}

		public boolean isLessThanOrEqualTest() {
			return lessThanOrEqualTest;
		}

		public void setLessThanOrEqualTest(boolean lessThanOrEqualTest) {
			this.lessThanOrEqualTest = lessThanOrEqualTest;
		}

		public boolean isGreaterThanTest() {
			return greaterThanTest;
		}

		public void setGreaterThanTest(boolean greaterThanTest) {
			this.greaterThanTest = greaterThanTest;
		}

		public boolean isGreaterThanOrEqualTest() {
			return greaterThanOrEqualTest;
		}

		public void setGreaterThanOrEqualTest(boolean greaterThanOrEqualTest) {
			this.greaterThanOrEqualTest = greaterThanOrEqualTest;
		}

		public boolean isAndTest() {
			return andTest;
		}

		public void setAndTest(boolean andTest) {
			this.andTest = andTest;
		}

		public boolean isOrTest() {
			return orTest;
		}

		public void setOrTest(boolean orTest) {
			this.orTest = orTest;
		}

		public boolean isNotTest() {
			return notTest;
		}

		public void setNotTest(boolean notTest) {
			this.notTest = notTest;
		}

		public double getAddTest() {
			return addTest;
		}

		public void setAddTest(double addTest) {
			this.addTest = addTest;
		}

		public String getAddStringTest() {
			return addStringTest;
		}

		public void setAddStringTest(String addStringTest) {
			this.addStringTest = addStringTest;
		}

		public double getSubtractionTest() {
			return subtractionTest;
		}

		public void setSubtractionTest(double subtractionTest) {
			this.subtractionTest = subtractionTest;
		}

		public double getMultiplicationTest() {
			return multiplicationTest;
		}

		public void setMultiplicationTest(double multiplicationTest) {
			this.multiplicationTest = multiplicationTest;
		}

		public double getDivisionTest() {
			return divisionTest;
		}

		public void setDivisionTest(double divisionTest) {
			this.divisionTest = divisionTest;
		}

		public double getModulusTest() {
			return modulusTest;
		}

		public void setModulusTest(double modulusTest) {
			this.modulusTest = modulusTest;
		}

		public double getExponentialPowerTest() {
			return exponentialPowerTest;
		}

		public void setExponentialPowerTest(double exponentialPowerTest) {
			this.exponentialPowerTest = exponentialPowerTest;
		}
		
	@Override
	public String toString() {
		return "equalTest : " + equalTest + " \n"
				+ "notEqualTest : " + notEqualTest + " \n"
				+ "lessThanTest : " + lessThanTest + " \n"
				+ "lessThanOrEqualTest : " + lessThanOrEqualTest + " \n" 
				+ "greaterThanTest : " + greaterThanTest + " \n"
				+ "greaterThanOrEqualTest : " + greaterThanOrEqualTest + " \n"
				+ "andTest : " + andTest + " \n" 
				+ "orTest : " + orTest + " \n" 
				+ "notTest : " + notTest + " \n" 
				+ "addTest : " + addTest + " \n"
				+ "addStringTest : " + addStringTest + " \n"
				+ "subtractionTest : " + subtractionTest + " \n" 
				+ "multiplicationTest "	+ multiplicationTest + " \n"
				+ "divisionTest : " + divisionTest + " \n"
				+ "modulusTest : " + modulusTest + " \n"
				+ "exponentialPowerTest : " + exponentialPowerTest;
	}
}