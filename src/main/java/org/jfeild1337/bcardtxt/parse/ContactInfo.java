package org.jfeild1337.bcardtxt.parse;

import java.util.Objects;

/**
 * This class contains the contact information for a business card (name, phone,
 * and email address).
 * 
 * @author jfeild
 */
public class ContactInfo {

    private String name;
    private String phoneNumber;
    private String emailAddress;
    
    public ContactInfo() {
    }

    /**
     * Returns the full name of the individual (eg. John Smith)
     * 
     * @return individual's name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Returns the phone number formatted as a sequence of digits (eg., 
     * 4105551234)
     * 
     * @return phone number as sequence of digits
     */
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
    /**
     * Returns the email address.
     * 
     * @return the email address
     */
    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    /**
     * Sets the name to the specified value
     * 
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Sets the phone number to the specified string.
     *      * 
     * @param phoneNumber String representing the phone number
     */
    public void setPhoneNumber(final String phoneNumber) 
    {        
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the email address to the specified value
     * 
     * @param emailAddress email address to set
     */
    public void setEmailAddress(String emailAddress) 
    {
        this.emailAddress = emailAddress;
    }
        
   @Override
   public String toString() {
      return "ContactInfo{" + "name=" + name + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress + '}';
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 17 * hash + Objects.hashCode(this.name);
      hash = 17 * hash + Objects.hashCode(this.phoneNumber);
      hash = 17 * hash + Objects.hashCode(this.emailAddress);
      return hash;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final ContactInfo other = (ContactInfo) obj;
      if (!Objects.equals(this.name, other.name)) {
         return false;
      }
      if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
         return false;
      }
      if (!Objects.equals(this.emailAddress, other.emailAddress)) {
         return false;
      }
      return true;
   }
    
    
    
    
}
