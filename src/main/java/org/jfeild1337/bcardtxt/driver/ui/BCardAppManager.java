/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jfeild1337.bcardtxt.driver.ui;

/**
 *
 * @author jfeild
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfeild1337.bcardtxt.parse.BusinessCardParser;
import org.jfeild1337.bcardtxt.parse.ContactInfo;

public class BCardAppManager {
   private BCardParseUI ui;
   private BusinessCardParser bCardParser;

   /**
    * Constructor. Takes in a BusinessCardParser to use for the text parsing.
    * NOTE: init() must have already been called on the BusinessCardParser before
    * passing it as an argument; otherwise errors will occur.
    * @param bCardParser 
    */
   public BCardAppManager(BusinessCardParser bCardParser) {
      this.bCardParser = bCardParser;
      ui = new BCardParseUI();      
   }

   /**
    * Sets up the UI and makes it visible
    */
   public void run()
   {
      setupParseListener();
      setupClearListener();

      ui.pack();
      ui.setVisible(true);
      ui.setLocationRelativeTo(null);

   }

   /**
    * Sets the Parse button to parse the input and set the 
    * output fields when clicked
    */
   private void setupParseListener()
   {
      ui.getBtnParse().addActionListener(new ActionListener() {			
         @Override
         public void actionPerformed(ActionEvent e) {
            String inputText = ui.getTxtInput().getText();
            ContactInfo contactInfo = bCardParser.getContactInfo(inputText);
            ui.getTxtParsedName().setText(contactInfo.getName());
            ui.getTxtParsedEmailAddr().setText(contactInfo.getEmailAddress());
            ui.getTxtParsedPhoneNumber().setText(contactInfo.getPhoneNumber());
         }
      });

   }

   /**
    * Sets the Clear button to clear all text fields when clicked
    */
   private void setupClearListener()
   {
      ui.getBtnClearAll().addActionListener(new ActionListener() {			
         @Override
         public void actionPerformed(ActionEvent e) {
            ui.getTxtInput().setText("");				
            ui.getTxtParsedName().setText("");
            ui.getTxtParsedEmailAddr().setText("");
            ui.getTxtParsedPhoneNumber().setText("");
         }
      });	
   }

}

