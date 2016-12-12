# BusinessCardParser
Simple library and application for extracting contact info from business card text.

## What It Does
Given a piece of text, the BusinessCardParser attempts to extract a person's name, *primary* phone number, and email address. The default implementation is simple, but is designed in such a way that it can be easily adapted to use custom parsing logic. It is designed to be used as a library, but can also be run as an application (in either GUI or terminal mode).

## How It Works
The parsing logic to extract names makes use of [Apache's OpenNLP library](https://opennlp.apache.org/), using the default model for English names. The logic for extracting phone numbers and email addresses uses regular expressions.

The BusinessCardParser class takes in a string containing the business card data, and returns the extracted data in an instance of the ContactInfo class. The actual parsing logic is handled by three separate classes: BCardEmailExtractor, BCardNameExtractor, and BCardPhoneNumberExtractor. These classes all implement the ITextExtractor interface, which defines two methods: `init()` and `extractText()`. The BusinessCardParser class sees these helper classes merely as objects that extend ITextExtractor, and so it is possible to subclass ITextExtractor to create a better or more customized replacement for any or all of the existing parsers. The BusinessCardParser takes instances of ITextExtractor in its constructor to allow the default parsing behavior to be overridden without having to modify this project's source code. 

## Build/Usage Instructions
Requires [maven](http://maven.apache.org/install.html) and Java 1.8.

### Build
1. Clone the repo or download the source
2. From the root of the source directory, open a terminal and run `mvn compile package`
3. When the build completes, copy the `BusinessCardTextParser-<VERSION>.jar` to the root directory (this is to get the jar to the same directory level as the data folder. That folder contains the OpenNLP model file which the BCardNameExtractor needs for performing named entity recognition)
 * Linux: `cp target/BusinessCardTextParser-<VERSION>.jar .`
 * Windows: `copy target\BusinessCardTextParser-<VERSION>.jar .`
 
### Run
1. Open a terminal/command prompt in the directory where both the BusinessCardTextParser jar and the data folder reside
  * To run in GUI mode, enter: `java -jar BusinessCardTextParser-<VERSION>.jar -g`
    * Paste the input text into the large text field, and click the Parse Text button
  * To run in terminal mode, enter: `java -jar BusinessCardTextParser-<VERSION>.jar -c`
    * Enter the data to be parsed, followed by two blank lines, i.e.:  
    `Jimmy Smith [Enter]`    
    `SYMM3TRY LTD [Enter] `   
    `Lead Systems Engineer [Enter]`  
    `(410)555-1234 [Enter]`  
    `jimmy@symm3try.com [Enter]`  
    `[Enter]`      
    `[Enter]`
    * The output will be printed to the screen

## Usage as a Library
The BusinessCardParser can be used as a library, and allows you to completely replace the logic for email, phone number, and name extraction. The classes used to extract each item are classes that implement the ITextExtractor interface. It is possible to create your own parsing classes that implement this interface and substitute them for the parsers used by this class. You can also use the existing classes, but change their behavior by passing arguments to the classes' constructors that specify custom regular expressions (for the phone number and email parser) or a custom NLP model filename (for the name finder). (Note that if you use the completely default implementation, you need to ensure that the `data/` folder is present at the same level as the app incorporating the BusinessCardParser library, since the BCardNameExtractor looks for `data/model/en-ner-person.bin` as its model if you don't give it a custom file path.)  

*Example:* Use custom classes for performing the data extraction. Assume you have created MyNewEmailExtractor, MyNewPhoneNumberExtractor, and MyNewNameExtractor classes that implement ITextExtractor and do incredible things in the `extractText()` method. To use these classes instead of the default classes, you would do:  
```java
MyNewEmailExtractor myEmailEx = new MyNewEmailExtractor();
MyNewPhoneNumberExtractor myPhoneNumberEx = new MyNewPhoneNumberExtractor();
MyNewNameExtractor myNameEx = new MyNewNameExtractor();
BusinessCardParser cardParser = new BusinessCardParser(myNameEx, myPhoneNumberEx, myEmailEx);
cardParser.init();
```
Now when you call `cardParser.getContactInfo()`, it will use the extractText() methods of your custom classes.


## Limitations
Right now, the single-regex-based logic for parsing phone numbers only handles US-style numbers. The regex also doesn't fare so well when the number and its qualifiers (marking as phone, cell, fax, etc.) are embedded in other text. Currently the internal workaround for this is to split the input data string on the newline separators and evaluate each line, which works fairly well, but requires that the data that is passed in contains the newline characters.  



