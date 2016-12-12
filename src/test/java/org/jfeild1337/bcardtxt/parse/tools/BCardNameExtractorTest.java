package org.jfeild1337.bcardtxt.parse.tools;

import org.jfeild1337.bcardtxt.parse.testhelper.AbstractBCardTextExtractorTest;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;
import org.jfeild1337.bcardtxt.testhelper.BCardDataEntity;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for BCardNameExtractor
 * 
 * @author jfeild
 */
public class BCardNameExtractorTest extends AbstractBCardTextExtractorTest{
   
   public BCardNameExtractorTest() {
   }
      
   /**
    * Test of init method, of class BCardPhoneNumberExtractor.
    * Validates that a ParserInitException is thrown when an invalid model file path is
    * supplied
    */
   @Test(expected=ParserInitException.class)
   public void testInit() throws Exception {
      System.out.println("init");
      BCardNameExtractor instance = new BCardNameExtractor("/path/to/invalid/file");
      instance.init();
   }

   /**
    * Test of extractText method, of class BCardNameExtractor.
    * Reads in the BCardDataEntities as defined in the test data XML file, and 
    * for each entity, passes the raw text to the BCardNameExtractor, 
    * and compares the output to the expected values as defined in the XML.
    */
   @Test
   public void testExtractText() throws ParserInitException, JAXBException {
      System.out.println("extractText");      
      BCardNameExtractor instance = new BCardNameExtractor();
      instance.init();
      instance.init();
      List<BCardDataEntity> inputList = fileReader.readTestDataFromXML(inputFileValidText);
      
      ArrayList<String> errorList = new ArrayList<>();
      for(BCardDataEntity testData: inputList)
      {         
         String textToSearch = testData.getInputData();      
         String expResult = testData.getExpectedName();
         String result = instance.extractText(textToSearch);
         if(!result.equals(expResult))
         {
            errorList.add(formatErrorMsg(expResult, result));
         }
      }
      printList(errorList, "ERRORS:");
      assertEquals(errorList.size(), 0);
      
   }
   
   
   
}
