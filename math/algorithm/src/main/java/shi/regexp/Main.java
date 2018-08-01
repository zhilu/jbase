package shi.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		repalceAtOrigin();
	}
	public static void useFind(){
		String regex = "\\w+"; // \w+
		String text  = "Mastering Regular Expressions";
		text.split("");
		Matcher m = Pattern.compile(regex).matcher(text);
		if(m.find(5)){
			 System.out.println("match [" + m.group() + "]");
		}
		
		while (m.find())
		   System.out.println("match [" + m.group() + "]");
	}
	
	public static void html(){
		String url   = "http://regex.info/blog";
		String regex = "(?x) ^(https?):// ([^/:]+) (?:(\\d+))?";
		System.out.println(Pattern.compile(regex).pattern());
		Matcher m = Pattern.compile(regex).matcher(url);

		if (m.find())
		{
		  System.out.print(
		      "Overall  [" + m.group()  + "]" +
		      " (from "    + m.start()  + " to " + m.end()  + ")\n" +

		      "Protocol [" + m.group(1) + "]" +
		      " (from "    + m.start(1) + " to " + m.end(1) + ")\n" +

		      "Hostname [" + m.group(2) + "]" +
		      " (from "    + m.start(2) + " to " + m.end(2) + ")\n"

		  );

		  // Group #3 might not have participated, so we must be careful here
		  if (m.group(3) == null)
		     System.out.println("No port; default of '80' is assumed");
		  else  {
		     System.out.print("Port is [" + m.group(3) + "] " +
		                      "(from " + m.start(3) + " to " + m.end(3) + ")\n");

		  }

		}
	}
	
	public static void gsub(){
		String text = "Before Java 1.5 was Java 1.4.2. After Java 1.5 is Java 1.6";
		String regex = "\\bJava\\s*1\\.5\\b";
		
		System.out.println(Pattern.compile(regex).pattern());
		Matcher m = Pattern.compile(regex).matcher(text);
		String result = m.replaceAll("Java 5.0");
		System.out.println(result);
	}
	
	
	/**
	 * 模仿replaceall
	 * @param m
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(Matcher m, String replacement)
	{
	   m.reset(); // Be sure to start with a fresh Matcher object
	   StringBuffer result = new StringBuffer(); // We'll build the updated copy here

	   while (m.find())
	       m.appendReplacement(result, replacement);

	   m.appendTail(result);
	   return result.toString(); // Convert result  to a string and return
	}
	
	
	/**
	 * 限制范围，模仿replaceall
	 * @param m
	 * @param replacement
	 * @return
	 */
	public static String replaceAllRegion(Matcher m, String replacement)
	{
	   Integer start = m.regionStart();
	   Integer end   = m.regionEnd();
	   m.reset().region(start, end); // Reset the matcher, but then restore the region

	   StringBuffer result = new StringBuffer(); // We'll build the updated copy here

	   while (m.find())
	       m.appendReplacement(result, replacement);

	   m.appendTail(result);
	   return result.toString(); // Convert to a String and return
	}
	
	public static void example(){

		String s = " 55 C ";
		Matcher m = Pattern.compile("(\\d+(?:\\.\\d*)?)C\\b").matcher(s);
		StringBuffer result = new StringBuffer(); // We'll build the updated copy here

		while (m.find())
		{
		  float celsius = Float.parseFloat(m.group(1));  // Get the number, as a number
		  int fahrenheit = (int) (celsius * 9/5 + 32);   // Convert to a Fahrenheit value
		  m.appendReplacement(result, fahrenheit + "F"); // Insert it
		}

		m.appendTail(result);
		System.out.println(result.toString()); // Display the result
	}

	public static void repalceAtOrigin(){
		StringBuilder text = new StringBuilder("It's SO very RUDE to shout!");
		Matcher m = Pattern.compile("\\b[\\p{Lu}\\p{Lt}]+\\b").matcher(text);

		while (m.find())
		   text.replace(m.start(), m.end(), m.group().toLowerCase());

		System.out.println(text);
	}

	public static void repalceUneqaul(){
		StringBuilder text = new StringBuilder("It's SO very RUDE to shout!");
		Matcher m = Pattern.compile("\\b[\\p{Lu}\\p{Lt}]+\\b").matcher(text);

		int matchPointer = 0;// First search begins at the start of the string
		while (m.find(matchPointer)) {
		   matchPointer = m.end(); // Next search starts from where this one ended
		   text.replace(m.start(), m.end(), "<b>"+ m.group().toLowerCase() +"</b>");
		   matchPointer += 7; // Account for having added '<b>' and '</b>'
		}

		System.out.println(text);
	}
}


