package primary;


import org.apache.log4j.Logger;

public class Test {
	public static Logger log = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		
		log.info("info");
		log.error("error");
		
		log.error("error");
	}	
}
