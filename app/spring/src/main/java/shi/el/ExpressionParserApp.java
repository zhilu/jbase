package shi.el;


	import org.springframework.expression.Expression;
	import org.springframework.expression.ExpressionParser;
	import org.springframework.expression.spel.standard.SpelExpressionParser;
	import org.springframework.expression.spel.support.StandardEvaluationContext;
	 
	public class ExpressionParserApp {
		public static void main(String[] args) {
	 
			ExpressionParser parser = new SpelExpressionParser();
	 
			//literal expressions 
			Expression exp = parser.parseExpression("'Hello World'");
			String msg1 = exp.getValue(String.class);
			System.out.println(msg1);
	 
			//method invocation
			Expression exp2 = parser.parseExpression("'Hello World'.length()");  
			int msg2 = (Integer) exp2.getValue();
			System.out.println(msg2);
	 
			//Mathematical operators
			Expression exp3 = parser.parseExpression("100 * 2");  
			int msg3 = (Integer) exp3.getValue();
			System.out.println(msg3);
	 
			//create an test object
			Test test = new Test();
			//test EL with test object
			StandardEvaluationContext testContext = new StandardEvaluationContext(test);
	 
			//display the value of test.email property
			Expression exp4 = parser.parseExpression("email");
			String msg4 = exp4.getValue(testContext, String.class);
			System.out.println(msg4);
	 
			//test if test.email == 'Hello@javacodegeeks.com'
			Expression exp5 = parser.parseExpression("email == 'Hello@javacodegeeks.com'");
			boolean msg5 = exp5.getValue(testContext, Boolean.class);
			System.out.println(msg5);
	}
}