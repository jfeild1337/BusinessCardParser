package org.jfeild1337.bcardtxt.parse.testhelper;

import java.util.List;
import javax.xml.bind.JAXBException;
import org.jfeild1337.bcardtxt.testhelper.XmlFileReader;
import org.junit.BeforeClass;

/**
 * Base test class. Provides setup and utility methods that are used by all the
 * project's test classes
 * @author jfeild
 */
public abstract class AbstractBCardTextExtractorTest {
   
   public static final String inputFileValidText = "src/test/resources/valid_input.xml";
   protected static XmlFileReader fileReader;
   
   
   @BeforeClass
   public static void setUpClass() throws JAXBException {   
      fileReader = new XmlFileReader();
   }
   
   protected String formatErrorMsg(String expVal, String actualVal)
   {
      return "Expected: " + expVal + "; Received: " + actualVal;
   }
   
   /**
    * For the given list, prints out each element, one per line, followed by
    * '---'. If a non-null value is provided for the 'header' parameter, this
    * will be printed out before the list elements.
    * 
    * @param toPrint List of strings to be printed
    * @param  header header to be printed once before all list elements are 
    * printed. IF set to null, no header will be printed.
    */
   protected void printList(List<String> toPrint, String header)
   {
      if(header != null && !toPrint.isEmpty())
      {
         System.out.println(header);
      }     
      
      for(String s: toPrint)
      {
         System.out.println(s);
         System.out.println("---");
      }
      
   }
   
}
