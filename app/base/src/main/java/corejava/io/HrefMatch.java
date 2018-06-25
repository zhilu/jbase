package corejava.io;

import java.io.*;
import java.net.*;
import java.util.regex.*;

/**
 * This program displays all URLs in a web page by matching a regular expression that describes the
 * <a href=...> HTML tag. Start the program as <br>
 * java match.HrefMatch URL
 * 
 * Æ¥Åä²¢ÌáÈ¡ÍøÒ³ÖÐµÄÁ¬½Ó
 * @version 1.01 2004-06-04
 * @author Cay Horstmann
 */
public class HrefMatch
{
   public static void main(String[] args)
   {
      try
      {
         // get URL string from command line or use default
         String urlString;
         if (args.length > 0) urlString = args[0];
         else urlString = "http://spark.apache.org/docs/latest/mllib-guide.html";

         // open reader for URL
         InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());

         // read contents into string builder
         StringBuilder input = new StringBuilder();
         int ch;
         while ((ch = in.read()) != -1)
            input.append((char) ch);

         // search for all occurrences of pattern
         // <a + ¿Õ×Ö·û + href +  ¿Õ×Ö·û  + = + ¿Õ×Ö·û+  (ÒýºÅ+²»ÊÇÒýºÅµÄ×Ö·û+ÒýºÅ     ||  ²»ÊÇ¿Õ×Ö·ûµÄ×Ö·û  ) + ¿Õ×Ö·û + >
         String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
         Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(input);

         while (matcher.find())
         {
            int start = matcher.start();
            int end = matcher.end();
            String match = input.substring(start, end);
            System.out.println(match);
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (PatternSyntaxException e)
      {
         e.printStackTrace();
      }
   }
}
