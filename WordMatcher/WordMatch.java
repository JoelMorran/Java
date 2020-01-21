//Student number jamorran(17699381) Joel Andrew Morran
import java.util.*;
import java.io.*;
public class WordMatch
{
   private static AVLTree<String> word = new AVLTree<String>();
   private static ArrayList<String> uniqueBorderWords = new ArrayList<String>();
   
   public static void main(String[] args) throws FileNotFoundException
   {
      read(args[0]);
      writeFile(args[1]);
      patternMatching(args[2], args[3], args[1]);
   }

   public static void read(String doc)	throws	FileNotFoundException 
   {
	  //Read in the files
      Scanner file = new Scanner(new File(doc));
      while(file.hasNextLine())
      {
         String str = file.next();
         readFiles(str);
         System.out.println("The file has been successfully read in file : "+ str);
      }
      System.out.println("All files read in");
   }

   public static void readFiles(String fileName) throws FileNotFoundException
   {
      Scanner file = new Scanner(new File(fileName));
      StringBuilder sb = new StringBuilder();
      while(file.hasNextLine())  
      {
         String line = file.nextLine();		
         String rep = line.replaceAll("[^a-zA-Z\\s]", "");	
         rep = rep.toLowerCase();
         rep = rep.trim();
         if(!rep.equals(""))
         {						
            sb.append(rep+" ");
         }							
      }

      String[] array = sb.toString().split("\\s");

      for(String str: array)
      {
         str = str.trim();
         if(!str.equals(""))
         {
            if(!uniqueBorderWords.contains(str))
            {
               uniqueBorderWords.add(str);
            }
            word.insertElement(str);
         }
      }
   }

   public static void writeFile(String fileName)
   {
      try
      {
         PrintWriter outfile = new PrintWriter(new FileWriter(fileName, false));
         System.out.println("Finding bordering words");

         String[] uniqueWords = new String[uniqueBorderWords.size()];
         uniqueBorderWords.toArray(uniqueWords);
         for(String s: uniqueWords)
         {
            word.findingBorders(s);
         }
         word.fixBorder();		 
         word.sortBorder();
         word.displayElements(outfile);
         System.out.println("Successfully written words to the file : "+ fileName);
         outfile.close();
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
   }

   public static void patternMatching( String in1, String out2, String out1)
   {
      try
      {
         ArrayList<String> patterns = new ArrayList<String>();
         Scanner sc1 = new Scanner(new File(out1));
         while(sc1.hasNextLine())
         {
            String strPat = sc1.nextLine();
            int i = strPat.indexOf('[');
            strPat = strPat.substring(0, i).trim();
            patterns.add(strPat);
         }

         PrintWriter outfile = new PrintWriter(new FileWriter(out2, false));
         Scanner patternFile = new Scanner(new File(in1));

         while(patternFile.hasNextLine())
         {
            String file = patternFile.nextLine();
            file = file.trim();
            outfile.println(file);
            if(patterns!=null && patterns.size() > 0)
            {
               boolean check = false;
               for(String str : patterns)
               {
                  boolean search = true;
                  int index = str.indexOf(' ');
                  String subStr = str.substring(0, index);
                  search = match(subStr, file, 0, 0);

                  if(search)
                  {
                     outfile.println(str);
                     check = true;
                  }
               }
               if(check == false)
               {
                  outfile.println("No words in the lexicon match the pattern");
               }
            }
            outfile.println("********************************************************************");
         }

         System.out.println("The patterns matches have been written to file: " + out2);
         outfile.close();
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
   }

   private static boolean match(String string, String pattern, int StringStartingPointer, int patternStartingPointer)	 
   {
      int patternPointer = patternStartingPointer; 																	
      int stringPointer = StringStartingPointer;																		
      int patternLength = pattern.length();

      if (patternLength == 1) 
      { 
         //Checks if first character is *
         if (pattern.charAt(0) == '*')  																
         {    
            return true; 																				
         }
      }

      int stringLength = string.length(); 	
      //Set wild card to false
      boolean wildCardCheck = false; 																	

      while (true) 
      {		
         //Check to see if end of string or pattern occurred and check if the string
         //is greater than the string length 
         if ((stringPointer >= stringLength) == true)																	 
         {   
            //Check that that patterns index is less than pattern length 
            //and check that the patterns at the patterns index have a * in the pattern
            while ((patternPointer < patternLength) && (pattern.charAt(patternPointer) == '*')) 										
            {	
               patternPointer++; 																					
            }
            //True if the patterns index is less than or equal to pattern length
            return patternPointer >= patternLength; 																		
         }
         //True if end of the pattern
         if (patternPointer >= patternLength)																					 
         {         
            return false; 
         }

         char patternP = pattern.charAt(patternPointer);    															

         if (wildCardCheck == false)																	 
         {   
            if (patternP == '?') 
            { 																						
               stringPointer++; 
               patternPointer++;
               continue;																					
            }

            if (patternP == '*') 
            {																			
               char nextPattern = 0;           															
               if (patternPointer + 1 < patternLength) 
               {																						
                  nextPattern = pattern.charAt(patternPointer + 1); 													
               }
               //Having two * is the same as having one
               if (nextPattern == '*') 
               {  
                  patternPointer++;																					
                  continue;
               }
               //If no pattern is found then
               int i;
               patternPointer++;   																			

               for (i = string.length(); i >= stringPointer; i--) 
               {
                  //Check if the remaining pattern characters match the pattern characters of the String
                  if (match(string, pattern, i, patternPointer) == true) 
                  {																					
                     return true;
                  }
               }
               //if no matches
               return false;																			
            }
         } 
         else 
         {
            wildCardCheck = false;									 
         }

         if (patternP != string.charAt(stringPointer)) 															
         {									
            return false;
         }

         stringPointer++; 
         patternPointer++;													
      }
   }		
}