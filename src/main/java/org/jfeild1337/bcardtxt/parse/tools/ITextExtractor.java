
package org.jfeild1337.bcardtxt.parse.tools;

import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;

/**
 * This is a simple interface that defines two methods: init() and 
 * extractText(). Its purpose is to allow users of the BusinessCardParser to 
 * implement customized procedures for extracting name, email, and phone number 
 * from the text.
 * 
 * @author jfeild
 */
public interface ITextExtractor {
 
   /**
    * Performs any setup/initialization operations required.
    *  
    */
   public void init() throws ParserInitException;
   
   /**
    * Searches the provided String for some embedded text. If it finds a match,
    * it returns the matched text; otherwise, it returns null.
    * For example, if the method is testing against the regex
    * "[hH][a-z]{4}\s+[Ww][a-z]{4}" and textToSearch
    * is "I am a hello world program", this method will return "hello world"
    * 
    * @param textToSearch text in which to search
    * @return the matching text if it is found, otherwise null
    */
   public String extractText(String textToSearch);
}
