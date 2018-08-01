package shi.regexp;

public class SimpleRegexTest {
	  public static void main(String[] args)
	  {
	     String myText = "this is my 1st test string";
	     String myRegex = "\\d+\\w+";  // This provides for  \d+\w+
	     java.util.regex.Pattern p = java.util.regex.Pattern.compile(myRegex);
	     java.util.regex.Matcher m = p.matcher(myText);
	     System.out.println(p.pattern());
	     if (m.find()) {
	         String matchedText = m.group();
	         int    matchedFrom = m.start();
	         int    matchedTo   = m.end();
	         System.out.println("matched [" + matchedText + "] " +
	                            "from " + matchedFrom +
	                            " to " + matchedTo + ".");
	     } else {
	         System.out.println("didn't match");
	     }
	  }
	}