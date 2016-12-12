
package org.jfeild1337.bcardtxt.parse.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;
import org.jfeild1337.bcardtxt.parse.exceptions.ParserInitException;

/**
 * Extracts a name from a piece of text, using Apache's OpenNLP library for
 * performing Named Entity Recognition (NER). The extractText() method splits
 * the data into sentences based on line breaks, checks each sentence for
 * a name using OpenNLP, and returns the first match. This approach is by no 
 * means bulletproof, and will fail if the person's company name is both listed
 * before the person's name and contains a person's name. OpenNLP, using the 
 * default model, is only looking for a name and has no context. The only good
 * workaround would be to create and train a new model for OpenNLP and use that.
 * Apache provides documentation for how to do that here: 
 * http://opennlp.apache.org/documentation/1.6.0/manual/opennlp.html#tools.namefind.training
 * And you can also refer to this blog post: 
 * http://nishutayaltech.blogspot.in/2015/07/writing-custom-namefinder-model-in.html
 * And...if you wanted to be REALLY serious and train the model using data mined 
 * from Wikipedia, you could follow this article:
 * https://www.nuxeo.com/blog/mining-wikipedia-with-hadoop-and-pig-for-natural-language-processing/
 * 
 * @author jfeild
 */
public class BCardNameExtractor implements ITextExtractor{

   public static final String MODEL_FILE = "data/model/en-ner-person.bin";
      
   private String modelFileName;
   private TokenNameFinderModel model = null;
   private NameFinderME nameFinder = null;
   
   /**
    * Default constructor. Sets the model file to be the default, as defined by
    * MODEL_FILE
    */
   public BCardNameExtractor() 
   {
      modelFileName = MODEL_FILE;      
   }

   /**
    * Constructor. Sets the model file to the filename specified. Allows users 
    * to supply their own customized model files to OpenNLP's name finding 
    * algorithm.
    * See the Apache documentation for more info:
    * http://opennlp.apache.org/documentation/1.6.0/manual/opennlp.html#tools.namefind.recognition
    * 
    * @param modelFileName path to the model file. 
    */
   public BCardNameExtractor(String modelFileName) 
   {
      this.modelFileName = modelFileName;
   }
   
   /**
    * Initializes the model to be used for named entity recognition.
    * Must be called regardless of which constructor is used.
    * 
    * @throws ParserInitException if there are any errors reading the model file
    */
   @Override
   public void init() throws ParserInitException
   {
      InputStream modelIn = null;
      try
      {
         modelIn  = new FileInputStream(modelFileName);
         try 
         {
            this.model = new TokenNameFinderModel(modelIn);
            this.nameFinder = new NameFinderME(model);
         }
         catch (IOException e) 
         {            
            throw new ParserInitException("Error reading model file", e);
         }
         finally 
         {            
            try 
            {
              modelIn.close();
            }
            catch (IOException e){}            
         }
      }
      catch(FileNotFoundException fnf)
      {         
         throw new ParserInitException("Could not find model file", fnf);
      }      
   }
   
     
   /**
    * Given a piece of text, extracts a person's name from it if a name is found
    * by Apache's OpenNLP library. Since this is for parsing business card data,
    * the first name that is encountered will be returned. Obviously, this means
    * that if the company name is listed before the person's name and the company's
    * name contains a person's name, then the wrong name will be returned. 
    * 
    * @param textToSearch Text to search in
    * @return the first name match returned by OpenNLP, or null if no names were
    * found
    */
   @Override
   public String extractText(String textToSearch) 
   {
      String[] sentences = textToSearch.split("\\r?\\n");
      for(String s: sentences)
      {
         String[] tokenizedSentence = SimpleTokenizer.INSTANCE.tokenize(s);
         Span nameSpans[] = nameFinder.find(tokenizedSentence); 
         String[] names = (Span.spansToStrings(nameSpans, tokenizedSentence));
         if(names.length > 0)
         {
            nameFinder.clearAdaptiveData(); 
            // As a result of the tokenization, the resulting name will have
            // commas and periods preceded by whitespace, i.e., John J . Jingleheimer , PhD
            // so we need to replace space + comma|period with just comma|period
            String nameToReturn = names[0].replaceAll("(\\s+)([.,]\\s+)", "$2");
            return nameToReturn;
         }
      } 
      nameFinder.clearAdaptiveData();
      return null;
   }
   
}
