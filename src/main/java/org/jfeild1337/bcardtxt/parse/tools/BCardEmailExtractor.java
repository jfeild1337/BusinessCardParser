package org.jfeild1337.bcardtxt.parse.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;

/**
 * Extracts an email address from a piece of text. Uses a basic regex to perform
 * the matching. The default regex will not match 100% of valid email addresses,
 * since RFC 5322 is rather lenient regarding valid email address syntax. The 
 * default regex should be sufficient for finding a typical email address
 * in a typical business card, but it's possible to use your own (more complete)
 * regex by passing it in as an argument to the constructor.
 * 
 * @author jfeild
 */
public class BCardEmailExtractor implements ITextExtractor{

   private static final String EMAIL_REGEX_DEFAULT = "(?:^|\\W)([A-Z0-9._!$%+-]+@[A-Z0-9._-]+\\.[A-Z]{2,63})(?:$|\\W)"; //(?!\\S)";
   private String emailRegex;
   // We'll reuse the matcher vs. instantiating a new one for each piece of text 
   // to analyze
   private Matcher matcher;
   
   /**
    * Default constructor. Uses a basic regex for matching email addresses in
    * the most common format. 
    */
   public BCardEmailExtractor() 
   {
      emailRegex = EMAIL_REGEX_DEFAULT;
   }

   /**
    * Constructor. Allows the user to specify a custom regex for performing the
    * email address matching.
    * 
    * @param emailRegex custom regex to use for matching an email address
    */
   public BCardEmailExtractor(String emailRegex) 
   {
      this.emailRegex = emailRegex == null? EMAIL_REGEX_DEFAULT : emailRegex;
   }
   
   /**
    * Compiles the regex and instantiates the Matcher
    * @throws ParserInitException if there is an error compiling the regex
    */
   @Override
   public void init() throws ParserInitException 
   {
      try
      {
         Pattern p = Pattern.compile(this.emailRegex, Pattern.CASE_INSENSITIVE);
         this.matcher = p.matcher("");   
      }
      catch(Exception ex)
      {
         throw new ParserInitException("Error compiling regex", ex);
      }
   }

   /**
    * Extracts an email address from the specified text.
    * 
    * @param textToSearch text (possibly) containing an email address
    * @return the email address contained in the text if one was found, otherwise
    * null
    */
   @Override
   public String extractText(String textToSearch) 
   {
      matcher.reset(textToSearch);
      if(matcher.find())
      {
         return matcher.group().trim();
      }
      return null;
   }
   
}
