import java.io.*;
import java.util.StringTokenizer;
/*+----------------------------------------------------------------------
||
||  Class: WordCount.java
||
||         Author: Spencer Klinge
||
||        Purpose:  To accept manually inputed, or commandline referenced
||					Text files, and out put the counts of Lines, Words, and
||					bytes, and output them in a user friendly format.
||         
||
||  Inherits From:  N/A
||
||     Interfaces:  N/A
||
|+-----------------------------------------------------------------------
||
||      Constants:  N/A
||
|+-----------------------------------------------------------------------
||
||   Constructors:  N/A
||
||  Class Methods:  N/A
||
||  Inst. Methods: private static void manuelToken(){
||				   private static String reader(String)
||				   private static void fileIterator()
||				   private static int lineCounter(String)
||				   private static int wordCounter(String)
||				   private static int byteCounter(String)
||				   private static void argChecter(String[])
||
++-----------------------------------------------------------------------*/
public class WordCount {
	   static File     fileRef;  //SHOULD THIS BE STATIC
       FileReader fileStream;
       static BufferedReader fileBuf;
       static int totalLineCount;
       static int totalWordCount;
       static int totalByteCount;
       static String[] fileNameList;
       static String fileName;
       

       /*---------------------------------------------------------------------
       |  Method: manuelToken()
       |
       |  Purpose: In the case where no commandline arguments are given, This method
       			   takes user input from the keyboard With the use of InputStreamReader
       			   and BufferedReader. it then processes the number of tokens, commas in
       			   this case to process each individual file name.
       |
       |  Pre-condition: Non, All errors throw exceptions and user given clear instructions.
       |
       |  Post-condition: Each text file will be easily referenceble.
       |
       |  Parameters: N/A
       |
       |  Returns:  'NONE'
       *-------------------------------------------------------------------*/
       
       private static void manuelToken(){
           System.out.print("This program determines the quantity of lines, words, and bytes" +
           		"in a file or files that you specify.");
           System.out.println();
           System.out.print("Please enter one or more file names, comma-separated:");
           BufferedReader keyboard= new BufferedReader( new InputStreamReader(System.in));
           try {
   			fileName= keyboard.readLine();
   		} catch (IOException e) {
   			System.out.println("error: no file names provided");
   			e.printStackTrace();
   			return;
   		}
           
           StringTokenizer toker= new StringTokenizer(fileName, ",");
           
           //System.out.println(toker.countTokens()+"");
           fileNameList= new String[toker.countTokens()];
           int n=0;
           while (toker.hasMoreTokens()) {
               fileNameList[n]= toker.nextToken().trim();
               n++;
           } 	   
       }
       
       /*---------------------------------------------------------------------
       |  Method: reader(String)
       |
       |  Purpose: Using the file name reference, reader outputs the
       |			   text in String format by constructing it first into a BufferedReader
       |			   then iterating it into a String builder Object. This makes it 
       |			   it much easier to work with.
       |
       |  Pre-condition: Receives a properly formatted fileName reference. 
       |
       |  Post-condition: Text will be in String Format
       |
       |  Parameters:
       |      fileName- The name of the file whose text is being transmuted into
       |					String format.
       |
       |  Returns: A String version of the file's text.
       *-------------------------------------------------------------------*/
       private static String reader(String fileName) throws IOException{
    	  StringBuilder build= new StringBuilder();
    	  fileRef = new File(fileName);
	       FileReader temp= new FileReader(fileRef);
	       fileBuf= new BufferedReader(temp);
	       int place= fileBuf.read();
	       while(place!=-1){
	    	   build.append((char)place);
	    	   place=fileBuf.read();
	       }
	       return build.toString();
	       
       }
       
       /*---------------------------------------------------------------------
       |  Method fileIterator
       |
       |  Purpose:  Handles the main operation of the class, printing the header
        			iterating through the file name list and and performing each
        			calculation to each file, all while adding the totals of these 
        			calculations to instance variables.
       | 			 
       |  Pre-condition:  N/A
       |
       |  Post-condition: The Output of the program will be completed
       |
       |  Parameters: N/A
       |
       |  Returns: 'NONE'
       *-------------------------------------------------------------------*/
       private static void fileIterator() throws IOException{
    	   System.out.println();
    	   System.out.println("   Lines     Words     Bytes");
    	   System.out.println("--------  --------  --------");
    	   for(int i=0; i< fileNameList.length;i++){
    		   String fileText= reader(fileNameList[i]);
    	
    		  int lines= lineCounter(fileText);
     		  totalLineCount += lines;
    		  int words= wordCounter(fileText);
    		  totalWordCount+=words;
    		  int bytes= byteCounter(fileText);
    		  totalByteCount += bytes;
    		  System.out.printf("%8d",lines);
    		  System.out.printf("%10d", words);
    		  System.out.printf("%10d",bytes);
    		  System.out.println("  " +fileNameList[i]);
    	   }//for
    	   System.out.println("-----------------------------------------");
    	   
    	   System.out.printf("%8d" , totalLineCount);
    	   System.out.printf("%10d" , totalWordCount);
    	   System.out.printf("%10d", totalByteCount);
    	   System.out.println("  " + "Totals");
    	   //System.out.printf("%12d"+totalWordCount);
    	   
    	   
    	   
    	   
    	   //TEXT PRINT FORMATTING
    	   
    	   
    	   }
       
       /*---------------------------------------------------------------------
       |  Method: LineCounter(String)
       |
       |  Purpose:  Calculates the number of lines in each file by iterating
       				character by character searching for DOS and UNIX style
       				new line characters.
       |
       |  Pre-condition: N/A
       |
       |  Post-condition:N/A
       |
       |  Parameters: String temp- the text of the file transmuted into String format.
       
       |  Returns: the number of lines in temp.
       *-------------------------------------------------------------------*/
       private static int lineCounter(String temp) throws IOException{
    	   int count=0;
    	   for(int i=0; i<temp.length(); i++){
    		   if(temp.charAt(i) == 13 && i+1<temp.length() && temp.charAt(i+1) == 10){//checks for \r followed by \n
    			   count ++;
    			   i++;
    		   }
    		   
    		   else{
    			   if(temp.charAt(i) == 10)
    				   count ++;
    		   }
    	  /* String aLine= temp.readLine();
    	   int count=0;
    	   while(aLine != null){
    		   count++;
    		   aLine= temp.readLine();*/
    	   }
    	   return count;
    	   
    	   
       }
       /*---------------------------------------------------------------------
       |  Method: WordCounter(String)
       |
       |  Purpose: calculates the number of words according to parameters
       |				established by the project description by iterating through
       |				the text charater by character.
       |
       |  Pre-condition: N/A
       |
       |  Post-condition: N/A
       |
       |  Parameters: String temp- the text of the file being iterated.
       |
       |  Returns: the number of words.
       *-------------------------------------------------------------------*/
       private static int wordCounter(String temp) throws IOException{
    	   int count=0;
    	   int start;
    	   for(int i=0; i < temp.length();i++){
    		   
    		   if(temp.charAt(i) >= 33 && temp.charAt(i) <= 126){//is current i a character?
    			   start=i;
    			   count++;
    		   
    			   while(temp.charAt(start)>=33 && temp.charAt(start) <= 126)
    				   start++;
    			   i=start;
    		   }
    		  
    		   
    		   
    		   
    	   }
    	   return count;

       }//wordCounter      
       /*---------------------------------------------------------------------
       |  Method: byteCounter
       |
       |  Purpose: One line method. Counts the number of bytes in temp by calling
       				the .length() method.
       |
       |  Pre-condition: N/A
       |
       |  Post-condition: N/a
       |
       |  Parameters: String temp- String file being iterated.
       |
       |  Returns: The nubmer of bytes.
       *-------------------------------------------------------------------*/
       private static int byteCounter(String temp) throws IOException{

    	   return temp.length();
       }
       
       /*---------------------------------------------------------------------
       |  Method: argChecer(String[])
       |
       |  Purpose: Called only if text file arguments are given in a command line
       			   call of this class. This method iterates through a reference to
       			   main's args[] parameter and adds each of those file reference to
       			   the fileNameList.
       |
       |  Pre-condition: The user called the class via commandline
       |
       |  Post-condition: fileNameList will contain properlly formatted file names
       |
       |  Parameters:
       |    String[] temp- a reference to mains args String array.
       |
       |  Returns: 'NONE'
       *-------------------------------------------------------------------*/
       private static void argChecher(String [] temp){
    	   fileNameList= new String[temp.length];
			for(int i=0; i < temp.length; i++){
				fileNameList[i]=temp[i];
			}
       }
       
       
       /*=============================================================================
       |   Assignment:  Program #6: WordCount.java
       |       Author:  Spencer Klinge (sklinge@email.arizona.edu)
       | Sect. Leader:  Lizzie
       |
       |       Course:  CSC227
       |   Instructor:  L. McCann
       |     Due Date:  March 11th, 2014 9:00 PM MSt
       |
       |  Description:  Reads in any number of text files that memory allows via textline.
       | 				This program takes files from the user manual inputed and via command 
       | 				line arguments. It then Processes Those text files into Strings using 
       | 				BufferedReader and String Builder.This class makes no use of Scanner.
       | 				Once converted to a string, The class Iterates the files to calculate 
       |				the number of bytes, lines, and words. It The returns these results to 
       |				the user is a formatted print.
       |                
       | Deficiencies: N/A
       *===========================================================================*/
	public static void main(String args[]) throws IOException{
		if(args.length != 0){//if Strig args contains arguments
			argChecher(args);
			}
		//Listmaker if
		else{
			manuelToken();
		}//List Maker Else
		fileIterator();
        
        
        
		

	}//main
}//class
