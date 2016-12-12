
package org.jfeild1337.bcardtxt.parse.exceptions;

/**
 * Exception to be thrown when a parsing class fails to initialize for some 
 * reason
 * @author jfeild
 */
public class ParserInitException extends Exception{

   public ParserInitException() {
   }

   public ParserInitException(String message) {
      super(message);
   }

   public ParserInitException(String message, Throwable cause) {
      super(message, cause);
   }

   public ParserInitException(Throwable cause) {
      super(cause);
   }
   
   
}
