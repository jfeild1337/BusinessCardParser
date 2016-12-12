package org.jfeild1337.bcardtxt.parse.tools;

import java.util.ArrayList;
import org.jfeild1337.bcardtxt.parse.testhelper.AbstractBCardTextExtractorTest;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;
import org.jfeild1337.bcardtxt.testhelper.BCardDataEntity;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for BCardPhoneNumberExtractor
 * 
 * @author jfeild
 */
public class BCardPhoneNumberExtractorTest extends AbstractBCardTextExtractorTest{
         
   public BCardPhoneNumberExtractorTest() {
      
   }
   
   

   /**
    * Test of init method, of class BCardPhoneNumberExtractor.
    * Validates that a ParserInitException is thrown when an invalid regex is
    * supplied
    */
   @Test(expected=ParserInitException.class)
   public void testInit() throws Exception {
      System.out.println("init");
      System.out.println("init");
      BCardPhoneNumberExtractor instance = new BCardPhoneNumberExtractor("(?&%*#@($SDJKPA");
      instance.init();
   }

   /**
    * Test of extractText method, of class BCardPhoneNumberExtractor. 
    * Reads in the BCardDataEntities as defined in the test data XML file, and 
    * for each entity, passes the raw text to the BCardPhoneNumberExtractor, 
    * and compares the output to the expected values as defined in the XML.
    */
   @Test
   public void testExtractText() throws JAXBException, ParserInitException {
      System.out.println("extractText");      
      BCardPhoneNumberExtractor instance = new BCardPhoneNumberExtractor();
      instance.init();
      List<BCardDataEntity> inputList = fileReader.readTestDataFromXML(inputFileValidText);
      
      List<String> errorList = new ArrayList<>();
      for(BCardDataEntity testData: inputList)
      {         
         String textToSearch = testData.getInputData();      
         String expResult = testData.getExpectedPhone();
         String result = instance.extractText(textToSearch);
         if(!expResult.equals(result))
         {            
            errorList.add(formatErrorMsg(expResult, result));
         }
      }
      printList(errorList, "ERRORS:");
      assertEquals(errorList.size(), 0);
   }
   
}
