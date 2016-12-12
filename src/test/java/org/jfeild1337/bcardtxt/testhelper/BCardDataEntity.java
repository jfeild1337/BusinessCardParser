package org.jfeild1337.bcardtxt.testhelper;

import javax.xml.bind.annotation.XmlRootElement;
import org.jfeild1337.bcardtxt.parse.ContactInfo;

/**
 * XML entity class. Holds data to be parsed from an input file for testing. 
 * Holds the business card data and the expected values for name, phone number,
 * and email that the parsing logic should retrieve.
 * 
 * @author jfeild
 */
@XmlRootElement(name="dataEntry")
public class BCardDataEntity {
   
   private String inputData;
   private String expectedEmail;
   private String expectedPhone;
   private String expectedName;

   public BCardDataEntity() {
   }

   public String getInputData() 
   {
      return inputData;
   }

   public void setInputData(String inputData) 
   {
      this.inputData = inputData;
   }

   public String getExpectedEmail() 
   {
      return expectedEmail;
   }

   public void setExpectedEmail(String expectedEmail) 
   {
      this.expectedEmail = expectedEmail;
   }

   public String getExpectedPhone() 
   {
      return expectedPhone;
   }

   public void setExpectedPhone(String expectedPhone) 
   {
      this.expectedPhone = expectedPhone;
   }

   public String getExpectedName() 
   {
      return expectedName;
   }

   public void setExpectedName(String expectedName) 
   {
      this.expectedName = expectedName;
   }

   @Override
   public String toString() {
      return "BCardDataEntity{" + "inputData=" + inputData + ", expectedEmail=" + expectedEmail + ", expectedPhone=" + expectedPhone + ", expectedName=" + expectedName + '}';
   }
   
   /**
    * Returns the string representation of just the 'expected' value fields
    * @return string representation of the 'expected' value fields
    */
   public String expectedValuesToString()
   {
      return "BCardDataEntity{expectedEmail=" + expectedEmail + ", expectedPhone=" + expectedPhone + ", expectedName=" + expectedName + '}';
   }
   
   /**
    * Utility method for translating a BCardDataEntity object to a ContactInfo 
    * object. Creates a ContactInfo object and sets its name, phone number, and 
    * email fields to the corresponding expected value fields of the provided
    * BCardDataEntity object.
    * 
    * @param entity BCardDataEntity with expected value fields set
    * @return ContactInfo whose fields have been set to the expected value 
    * fields of entity
    */
   public static ContactInfo toContactInfo(BCardDataEntity entity)
   {
      ContactInfo contactInfo = new ContactInfo();
      
      contactInfo.setEmailAddress(entity.getExpectedEmail());
      contactInfo.setPhoneNumber(entity.getExpectedPhone());
      contactInfo.setName(entity.getExpectedName());
      
      return contactInfo;
   }
   
   
}
