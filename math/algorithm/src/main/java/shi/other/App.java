package shi.other;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		System.out.println(isReverse("12344321"));
	}
	
	public static boolean isReverse(String s){
		int half = s.length()/2;
		for (int i = 0; i < half; i++) {
			if(s.charAt(i) != s.charAt(s.length()-i-1)){
				return false;
			}
		}
		
		return true;
	}
}
