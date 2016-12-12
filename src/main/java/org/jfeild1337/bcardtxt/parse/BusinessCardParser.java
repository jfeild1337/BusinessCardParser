package org.jfeild1337.bcardtxt.parse;

import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;
import org.jfeild1337.bcardtxt.parse.tools.BCardEmailExtractor;
import org.jfeild1337.bcardtxt.parse.tools.BCardNameExtractor;
import org.jfeild1337.bcardtxt.parse.tools.BCardPhoneNumberExtractor;
import org.jfeild1337.bcardtxt.parse.tools.ITextExtractor;

/**
 * Parses text from a business card (ostensibly obtained via OCR), and parses
 * the person's name, email address, and phone number.
 * The classes used to extract each item are classes that implement the 
 * ITextExtractor interface. It is possible to create your own parsing classes
 * that implement this interface and substitute them for the parsers used by this
 * class. You can also use the existing classes, but change their behavior by
 * passing arguments to the classes' constructors that specify custom regular expressions
 * (for the phone number and email parser) or a custom NLP model filename (for 
 * the name finder). 
 * If using the default parsing classes with their default settings, ensure that
 * the text to be parsed is in business card format (the string has newline
 * separators to denote "lines" on the business card); otherwise, the name and
 * phone number extractors may not function as well as intended.
 * 
 * @author jfeild
 */
public class BusinessCardParser {
   
   private ITextExtractor nameExtractor;
   private ITextExtractor phoneNumberExtractor;
   private ITextExtractor emailAddressExtractor;

   /**
    * Constructor. Allows the user to specify custom extractor classes.
    * @param nameExtractor instance of class implementing ITextExtractor to perform
    * extraction of the person's name. Set this to null to use the default
    * @param phoneNumberExtractor instance of class implementing ITextExtractor 
    * to perform extraction of the phone number. Set this to null to use the default
    * @param emailAddressExtractor instance of class implementing ITextExtractor 
    * to perform extraction of the email address. Set this to null to use the default
    */
   public BusinessCardParser(ITextExtractor nameExtractor, 
           ITextExtractor phoneNumberExtractor, ITextExtractor emailAddressExtractor) {
      this.nameExtractor = nameExtractor == null ? new BCardNameExtractor() : nameExtractor;
      this.phoneNumberExtractor = phoneNumberExtractor  == null ? new BCardPhoneNumberExtractor() : phoneNumberExtractor;
      this.emailAddressExtractor = emailAddressExtractor == null ? new BCardEmailExtractor() : emailAddressExtractor;
   }
   
   /**
    * Default constructor. Uses BCardEmailExtractor, BCardNameExtractor, and
    * BCardPhoneNumberExtractor with their default settings for performing the 
    * text extraction. 
    */
   public BusinessCardParser()
   {
      nameExtractor = new BCardNameExtractor();
      phoneNumberExtractor = new BCardPhoneNumberExtractor();
      emailAddressExtractor = new BCardEmailExtractor();
   }
   
   /**
    * Initializes the parser classes
    * 
    * 
    * @throws org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException if
    * there are errors initializing any of the parser classes
    */
   public void init() throws ParserInitException
   {
      nameExtractor.init();
      phoneNumberExtractor.init();
      emailAddressExtractor.init();
   }
   
   /**
    * Parses the given business card text and extracts the person's name, email
    * address, and phone number. For best results, the text should be formatted
    * like a business card (with each entry on its own line) if using the default
    * parsing classes.
    * 
    * @param document the business card text to parse
    * @return ContactInfo instance containing the extracted name, phone number,
    * and email address. If any field was not parsed, it will be set to null.
    */
   public ContactInfo getContactInfo(String document)
   {
      ContactInfo parsedContactInfo = new ContactInfo();
      String extractedName = nameExtractor.extractText(document);
      String extractedEmailAddress = emailAddressExtractor.extractText(document);
      String extractedPhoneNumber = phoneNumberExtractor.extractText(document);
      
      parsedContactInfo.setName(extractedName);
      parsedContactInfo.setEmailAddress(extractedEmailAddress);
      parsedContactInfo.setPhoneNumber(extractedPhoneNumber);
      
      return parsedContactInfo;
   }
   
}
