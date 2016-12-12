
package org.jfeild1337.bcardtxt.driver;

import java.util.ArrayList;
import java.util.Scanner;
import org.jfeild1337.bcardtxt.driver.ui.BCardAppManager;
import org.jfeild1337.bcardtxt.parse.BusinessCardParser;
import org.jfeild1337.bcardtxt.parse.ContactInfo;
import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;

/**
 * Application for using the BusinessCardParser
 * 
 * @author jfeild
 */
public class BCardParserApp {
   private static final int NUM_ARGS = 1;
   private static final String CMD_HELP = "-h";
   private static final String CMD_GUI = "-g";
   private static final String CMD_CONSOLE = "-c";
   
   private static final String FLAG_QUIT = "quit";
   private static final int NUM_BLANK_LINES = 2;
   
   private final BusinessCardParser bCardParser;

   public BCardParserApp() throws ParserInitException 
   {
      bCardParser = new BusinessCardParser();
      bCardParser.init();
   }
   
   private void printConsoleInstructions()
   {
      System.out.println("\nEnter a block of text followed by two blank lines:");
      System.out.println("  (Enter 'quit' without quotes and press Enter twice to exit)");
   }
   
   /**
    * Runs the app in command-line mode. Allows the user to enter a block of text 
    * followed by two blank lines and then parses the text. Exits when the user
    * enters 'quit' followed by two blank lines
    */
   public void runOnCmdLine()
   {
      Scanner consoleScan = new Scanner(System.in);
      ArrayList<String> input = new ArrayList<>();
      
      int blankLineCount = 0;
      boolean quitReqReceived = false;
      boolean quitRequestAcknowledged = false;
      
      String line = null;  
      printConsoleInstructions();
      do
      {         
         line = consoleScan.nextLine();
         if(line.isEmpty())
         {
            blankLineCount++;
            if(blankLineCount == NUM_BLANK_LINES)
            {
               if(quitReqReceived)
               {
                  System.out.println("Exiting on user request"); 
                  quitRequestAcknowledged = true;
               }
               else
               {
                  consoleParseInput(input);
                  blankLineCount = 0;
                  input.clear();
                  printConsoleInstructions();
               }
            }            
         }
         else
         {
            //We only track consecutive blank lines, so a non-blank line clears
            //the blank line count
            blankLineCount = 0;
            if(line.trim().equals(FLAG_QUIT))
            {
               quitReqReceived = true;
            }
            else
            {
               quitReqReceived = false;
               input.add(line);
            }
         }
      }while(!quitRequestAcknowledged);
   }
   
   /**
    * Formats the given list of strings into a single, newline-delimited string,
    * passes that string to the BusinessCardParser, and then prints the parsed
    * output to the screen.
    * 
    * @param input ArrayList of strings entered by the user
    */
   void consoleParseInput(ArrayList<String> input)
   {
      if(input.isEmpty())
      {
         System.out.println("No input to parse");
      }
      else
      {
         StringBuilder outputBuilder = new StringBuilder();
         for(String line: input)
         {
            outputBuilder.append(line);
            outputBuilder.append("\n");
         }
         String formattedText = outputBuilder.toString();
         ContactInfo contactInfo = bCardParser.getContactInfo(formattedText);
         System.out.println("Parsed:");
         System.out.println("  Name: " + contactInfo.getName());
         System.out.println("  Email: " + contactInfo.getEmailAddress());
         System.out.println("  Phone: " + contactInfo.getPhoneNumber());
      }
      
   }
   
   /**
    * Runs the UI.
    */
   public void runWithGui()
   {
      BCardAppManager uiMgr = new BCardAppManager(bCardParser);
      uiMgr.run();
   }
   
   private static void printUsage()
   {
      StringBuilder usageTextBuilder = new StringBuilder("java -jar bcard_parser_app.jar CMD");
      usageTextBuilder.append("\n  CMD:");
      usageTextBuilder.append("\n    -h   display this help message and exit");
      usageTextBuilder.append("\n    -c   run app in console mode");
      usageTextBuilder.append("\n    -g   run app in GUI mode");
      System.out.println(usageTextBuilder.toString());
   }
   
   /**
    * Run the app
    * @param args 
    */
   public static void main(String[] args)  {
      try
      {
         switch(args.length)
         {
            case 0:
               printUsage();
               System.exit(1);
               break;
            case 1:
               if(args[0].equals(CMD_HELP))
               {
                  printUsage();
                  System.exit(0);
               }
               else if(args[0].equals(CMD_GUI))
               {
                  BCardParserApp app = new BCardParserApp();
                  app.runWithGui();
               }
               else if(args[0].equals(CMD_CONSOLE))
               {
                  BCardParserApp app = new BCardParserApp();
                  app.runOnCmdLine();
               }
               else
               {
                  printUsage();
                  System.exit(1);
               }
               break;
            default:
               printUsage();
               System.exit(1);  
         }
      }
      catch(ParserInitException e)
      {
         System.out.println("Error initializing application:");
         System.out.println(e.getCause());
         System.out.println(e.getMessage());
      }

   }
   
}
