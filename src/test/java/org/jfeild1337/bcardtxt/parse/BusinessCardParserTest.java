package org.jfeild1337.bcardtxt.parse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;
import org.jfeild1337.bcardtxt.parse.testhelper.AbstractBCardTextExtractorTest;
import org.jfeild1337.bcardtxt.testhelper.BCardDataEntity;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jfeild
 */
public class BusinessCardParserTest extends AbstractBCardTextExtractorTest{
   
   public BusinessCardParserTest() {
   }

   

   /**
    * Tests the getContactInfo() method in a loop. Reads in the BCardDataEntities
    * as defined in the test data XML file, and for each entity, passes the
    * raw text to the BusinessCardParser, and compares the output to the expected
    * values as defined in the XML.
    */
   @Test
   public void testGetContactInfoBulk() throws ParserInitException, JAXBException 
   {
      System.out.println("testGetContactInfoBulk:getContactInfo");
      BusinessCardParser instance = new BusinessCardParser();
      instance.init();
      List<BCardDataEntity> inputList = fileReader.readTestDataFromXML(inputFileValidText);
      
      ArrayList<String> errorList = new ArrayList<>();
      for(BCardDataEntity testData: inputList)
      {         
         String textToSearch = testData.getInputData();      
         ContactInfo expResult = BCardDataEntity.toContactInfo(testData);
         ContactInfo result = instance.getContactInfo(textToSearch);
         if(!expResult.equals(result))
         {
            errorList.add("Expected: " + expResult.toString() + "\nReceived: " + result.toString());
         }
      }
      printList(errorList, "ERRORS:");
      assertEquals(errorList.size(), 0);
   }
   
}
