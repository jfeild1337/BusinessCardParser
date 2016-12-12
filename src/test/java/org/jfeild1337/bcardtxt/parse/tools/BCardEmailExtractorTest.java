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
 * Test class for BCardEmailExtractor
 * @author jfeild
 */
public class BCardEmailExtractorTest extends AbstractBCardTextExtractorTest{
   
   public BCardEmailExtractorTest() {
   }
           
   /**
    * Test of init method, of class BCardEmailExtractor.
    * Validates that a ParserInitException is thrown when an invalid regex is
    * supplied
    */
   @Test(expected=ParserInitException.class)
   public void testInitInvalid() throws Exception {
      System.out.println("init");
      BCardEmailExtractor instance = new BCardEmailExtractor("(?&%*#@($SDJKPA");
      instance.init();      
   }

   /**
    * Test of extractText method, of class BCardEmailExtractor. Reads in the 
    * BCardDataEntities as defined in the test data XML file, and for each 
    * entity, passes the raw text to the BCardEmailExtractor, and compares the 
    * extraction output to the expected values as defined in the XML.
    */
   @Test
   public void testExtractTextBulkScenario() throws JAXBException, ParserInitException {
      System.out.println("testExtractTextBulkScenario:extractText");
      BCardEmailExtractor instance = new BCardEmailExtractor();
      instance.init();
      List<BCardDataEntity> inputList = fileReader.readTestDataFromXML(inputFileValidText);
      
      List<String> errorList = new ArrayList<>();
      for(BCardDataEntity testData: inputList)
      {         
         String textToSearch = testData.getInputData();      
         String expResult = testData.getExpectedEmail();
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
