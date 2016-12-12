
package org.jfeild1337.bcardtxt.parse.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;

/**
 * Extracts a phone number from a piece of text.
 * The default behavior is to match a number denoted as a office phone (not fax,
 * cell, etc). The default regex matches the pattern for US phone numbers only.
 * This default behavior can be changed by passing in a custom regex to the 
 * constructor.
 * 
 * @author jfeild
 */
public class BCardPhoneNumberExtractor implements ITextExtractor{

   private String phoneNumRegex;
   // We'll reuse the matcher vs. instantiating a new one for each piece of text 
   // to analyze
   private Matcher matcher;

   /**
    * Constructor. Allows the user to specify a custom regex for phone number 
    * matching
    * 
    * @param phoneNumRegex custom regex for matching a phone number
    */
   public BCardPhoneNumberExtractor(String phoneNumRegex) {
      this.phoneNumRegex = phoneNumRegex;      
   }
   
   /**
    * Default constructor. Sets member variables to null. init() must be called 
    * to build the regex and create the matcher.
    */
   public BCardPhoneNumberExtractor()
   {
      this.phoneNumRegex = null;     
   }
   
   /**
    * Compiles the regex and instantiates the Matcher
    * @throws ParserInitException if there is an error compiling the regex
    */
   @Override
   public void init() throws ParserInitException 
   {
            
      if(phoneNumRegex == null)
      {
         phoneNumRegex = buildDefaultRegex();
      }
      try
      {
         Pattern p = Pattern.compile(phoneNumRegex, Pattern.CASE_INSENSITIVE);
         this.matcher = p.matcher("");   
      }
      catch(Exception ex)
      {
         throw new ParserInitException("Error compiling regex", ex);
      }
   }

   /**
    * Builds the default regex.
    * @return the default regex
    */
   private String buildDefaultRegex()
   {      
      StringBuilder regexBuilder = new StringBuilder(200);
      
      //Make sure we're not starting with a flag for fax/cell/home/etc
      regexBuilder.append("^(?!((^|.*\\W)(f(ax|acsimile)?|c(ell)?|h(ome)?|m(obile)?))([:-=])?)(?:.*)");
      //regexBuilder.append("^(?!(.*\\W(f(ax|acsimile)?|c(ell)?|h(ome)?|m(obile)?))([:-=])?)(?:.*)");
      //optional long distance code and area code as defined by NANP
      //(see https://en.wikipedia.org/wiki/North_American_Numbering_Plan)
      regexBuilder.append("(^|\\W)((\\+?1\\s*([.-]\\s*)?)?(\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*([.-]\\s*)?)?");
      //exchange code and subscriber number
      regexBuilder.append("([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*([.-]\\s*)?([0-9]{4})");
      //optional extension:
      regexBuilder.append("(\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?");
      //Make sure we're not ending with a flag for fax/cell/home/etc
      regexBuilder.append("(?!(.*\\W)(f(ax|acsimile)?|c(ell)?|h(ome)?|m(obile)?)|(\\((f(ax|acsimile)?|c(ell)?|h(ome)?|m(obile)?)\\)))");
      return regexBuilder.toString();
   }   
   
   /**
    * Extracts a phone number from the specified text.
    * 
    * @param textToSearch text (possibly) containing a phone number
    * @return the phone number contained in the text if one was found, otherwise
    * null
    */
   @Override
   public String extractText(String textToSearch) 
   {
      String[] lines = textToSearch.split("\\r?\\n");
      for(String line: lines)
      {         
         matcher.reset(line);
         if(matcher.find())
         {
            return formatPhoneNumber(matcher.group());
         }
      }
      return null;
   }
   
   /**
     * Parses the given phone number string and creates a new string containing
     * only the numeric characters from it. For example, for the phone number 
     * (410)555-1234, this will return 4105551234.
     * 
     * @param rawPhoneNumber phone number string, possibly containing 
     * non-numeric characters
     * @return copy of rawPhoneNumber with all non-numeric characters removed
     */
    private String formatPhoneNumber(final String rawPhoneNumber)
    {
        //Set all non-numerics to empty text...
        String formattedNum = rawPhoneNumber.replaceAll("[^0-9]", "");
        return formattedNum;
    }
   
}
