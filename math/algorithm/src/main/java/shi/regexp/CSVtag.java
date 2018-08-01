package shi.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVtag {
public static void main(String[] args) {
	
}

	public static void CSV(StringBuffer line){
		String regex = // Puts a double quoted field into group(1), an unquoted field into group(2).
			    "  \\G(?:^|,)                                    \n"+
			    "  (?:\n"+
			    "       # Either a double-quoted field . . . \n"+
			    "       \" # field's opening quote\n"+
			    "        (   [^\"]*+  (?: \"\" [^\"]*+ )*+  )\n"+
			    "       \" # field's closing quote\n"+
			    "   |# . . . or . . . \n"+
			    "       # some non-quote/non-comma text . . . \n"+
			    "       ( [^\",]*+ )\n"+
			    "  )\n";
	
			// Create a matcher for the CSV line of text, using the regex above.
			Matcher mMain = Pattern.compile(regex, Pattern.COMMENTS).matcher(line);
	
			// Create a matcher for  ã€Œ"" , with dummy text for the time being.
			Matcher mQuote = Pattern.compile("\"\"").matcher("");
	
			while (mMain.find())
			{
			    String field;
			    if (mMain.start(2) >= 0)
			        field = mMain.group(2); // The field is unquoted, so we can use it as is.
			    else
			        // The field is quoted, so we must replace paired double quotes with one double quote.
			        field = mQuote.reset(mMain.group(1)).replaceAll("\"");
	
			    // We can now work with field . . . 
			    System.out.println("Field [" + field + "]");
			}
	}
}
