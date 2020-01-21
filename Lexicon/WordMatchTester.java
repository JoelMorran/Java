//Student number jamorran(17699381) Joel Andrew Morran
import java.io.*;
import java.util.*;

public class WordMatchTester
{
   //boolean quit = false;
   //Declare ArrayList of words
   private static ArrayList<Word> list;
   public static void main(String[] args)
   {
      //Call method to read file
      readIn();
      //Call method to sort and count words using selection sort
      selectionSortWordCount();		
      //Menu for the patterns
      menu();							
   }

   //File read method
   public static void readIn()
   {
      try
      {
         //Read in the first file
         Scanner file1 = new Scanner(new File("sample1-pp.txt"));
         //Initialize new string builder
         StringBuilder sb = new StringBuilder();

         //Check to make sure that program has not reached the end of the file
         while(file1.hasNextLine())
         {
            //Read in the file one line at a time
            String line = file1.nextLine();
            //This removes everything except for the characters 'A-Z' regardless
            //of case and any white space
            String rep = line.replaceAll("[^a-zA-z\\s]", "");
            //Makes everything lowercase
            rep = rep.toLowerCase();
            //Appends the line to the string builder so that the string builder 
            //holds the words from both the files separated by single white space
            sb.append(rep + " ");
         }

         //Read in the second file
         Scanner file2 = new Scanner(new File("sample2-zoo.txt"));

         //Check to make sure that program has not reached the end of the file
         while(file2.hasNextLine())
         {
            //Read in the file one line at a time
            String line = file2.nextLine();
            //This removes everything except for the characters 'A-Z' regardless
            //of case and any white space
            String rep = line.replaceAll("[^a-zA-z\\s]", "");
            //Makes everything lowercase
            rep = rep.toLowerCase();
            //Appends the line to the string builder so that the string builder 
            //holds the words from both the files separated by single white space
            sb.append(rep + " ");
         }

         //Write to a file
         PrintWriter pw = new PrintWriter(new FileWriter("sample3-wordlist.txt"));
         //Split the words in the string builder and save to the Array
         String Word[] = sb.toString().split("\\s");

         for(String word : Word)
         {
            //Check for white spaces
            if(word.trim().equals(""))
            {
               continue;
            }
            //Remove lingering jargon
            word = word.trim();
            //Write to file
            pw.println(word);
         }

         //Prints message of completion to user
         System.out.println("Sample3-wordlist.txt successfully created!!!");         
         //Close file
         file1.close();
         //Close file
         file2.close();
         //Close file
         pw.close();
      }
      //Catches general exceptions
      catch(Exception e)
      {
         System.out.println(e);
      }
   }

   //Sort method (Selection Sort)
   public static<E extends Comparable<E>> void swap(List<E> list, int i, int j)
   {
      E temp = list.get(i);
      list.set(i, list.get(j));
      list.set(j, temp);
   }

   //Sort method (Selection Sort)
   public static <E extends Comparable<E>> void selectionSort(List<E> list)
   {
      int left = 0;
      int right = list.size() - 1;
      for(int i = right; i >= left + 1; i--)
      {
         int mi = getIndexOfMaximum(list, left, i);
         swap(list, mi, i);
      }
   }

   //Sort method (Selection Sort)
   public static <E extends Comparable<E>> int getIndexOfMaximum(List<E> list, int low, int high)
   {
      int maxIndex = low; 
      for(int i = low + 1; i <= high; i++)
      {
         if (list.get(i).compareTo(list.get(maxIndex)) > 0)
         {
            maxIndex = i;
         }
      }
      return maxIndex;
   }

   //Method for counting the words in the file and finding its
   //bordering words and storing them in the ArrayList<Word>
   public static void selectionSortWordCount()
   {
      try
      {
         //Read each word one word per line from the file
         Scanner file3 = new Scanner(new File("sample3-wordlist.txt"));
         //Word count set to 0
         int count = 0;
         //Initialize new string builder
         StringBuilder s1 = new StringBuilder();
         //Initialize the ArrayList
         list = new ArrayList<Word>();

         //Check to make sure that program has not reached the end of the file
         while(file3.hasNextLine())
         {
            //Read in the words from the file
            String s = file3.nextLine();
            //Adds a white space between words
            s1.append(s + " ");
            //Increases count
            count++;	
         }

         //Split the words and store them in an array
         String[] wordArr3 = s1.toString().split("\\s");
         //Initialize the ArrayList
         List<String> l2 = new ArrayList<>();  
         //Convert the spilt words Array to an ArrayList for sorting
         l2 =  Arrays.asList(wordArr3);
         //Sort the ArrayList using selection sort
         selectionSort(l2);
         //Convert the sorted ArrayList back into an Array
         String[] wordArray = l2.toArray(new String[l2.size()]);
         //Create a word Array
         Word[] word = new Word[wordArray.length];

         //Set word count to 1
         int wordCount = 1;
         //Outer loop of pointer 1
         for(int i = 0; i < wordArray.length; i++)
         {
            //Inner loop of pointer 2
            for(int j = 0; j < wordArray.length; j++)
            {
               //Checks to see if both of the pointers are at the same point
               //then if true start next iteration
               if(i == j)
               {
                  continue;
               }
               else
               {
                  //checks if the Strings are equal
                  if(wordArray[j].equals(wordArray[i]))
                  { 
                     //Increase word count
                     wordCount++;
                  }
               }
            }
            //Add words and their count to Array
            word[i] = new Word(wordArray[i], wordCount);

            //Checks if ArrayList does not contain that Words object
            if(!list.contains(word[i]))
            {
               //Adds the word and its count to the ArrayList 
               list.add(word[i]);

            }
            //Sets the word count back to 1 for the next pass through the loop
            wordCount = 1;
         }

         //Creates new word Array
         Word[] array = new Word[list.size()];
         //Convert the ArrayList to an Array
         list.toArray(array);
         //Initialize new string builder
         StringBuilder s2 = new StringBuilder("");

         //Outer loop of pointer 1
         for(int i = 0; i < array.length; i++)
         {
            //Empty the string builder
            s2.delete(0, s2.length());

            //Inner loop of pointer 2
            for(int j = 0; j < array.length; j++)
            {
               //Checks to see if both of the pointers are at the same point
               //then if true start next iteration
               if(i == j)
               {
                  continue;
               }
               else
               {
                  //Variable to check that both words are bordering each other
                  int same = 0;
                  //Check to see if both words have the same length
                  if(array[i].getWord().length() == array[j].getWord().length())
                  {
                     for(int k = 0; k < array[i].getWord().length(); k++)
                     {
                        //Check to see if both characters are the same and if so increase count
                        if(array[i].getWord().charAt(k) == array[j].getWord().charAt(k))
                        {
                           same++;
                        }
                     }
                     //Checks to see if words differ by one character in length
                     if(same == array[i].getWord().length() - 1)
                     {
                        //Add word to the string builder
                        s2.append(array[j].getWord() + ", ");
                     }

                  }
               }
            }

            //converts the string builder into an string and saves it as a new string
            String s3 = s2.toString(); 

            //Condition to remove a lingering white space and ',' from the end of the bordering words
            //by splitting the string and remaking it
            if(s3.length() > 0)
            {		
               String sub3 = s3.substring(0, s3.length() - 2);
               s3 = sub3;
            }

            //Sets the bordering words for that word
            array[i].setBorder(s3);
         }

         //Print out the words their total re-occurrences and their bordering words and write them
         //to a file			
         PrintWriter outfile = new PrintWriter(new FileWriter("sample3-wordlist.txt"));
         for(Word w : array)
         {
            outfile.println(w.toString());
         }
         outfile.close();
      }
      //Catches general exceptions
      catch(Exception e)
      {
         System.out.println(e);
      }
   }

   //Method to check if a string matches a pattern
   public static void matchs(String searchString)			
   {
      try
      {
         //Create output file
         PrintWriter outfile = new PrintWriter(new FileWriter("sample4-results.txt", true)); 
         //Convert string to lowercase
         searchString = searchString.toLowerCase();	
         //Write string to the file
         outfile.println("The Pattern : "+ searchString);
         //Writes statement to the file
         outfile.println("may result in the output:");

         //Checks to see if the ArrayList is empty
         if(list != null && list.size() > 0)														
         {
            //Displays statement and string for the user 
            System.out.println("The Pattern : " + searchString);
            //Displays statement to the user
            System.out.println("may result in the output:");
            //Sets check to false
            boolean check = false;	
            //Loops over every word in the list
            for(Word s : list)															
            {
               //Sets match to true
               boolean match1 = true;															
               //Checks if theres a matching word and if so returns true
               match1 = match(s.getWord(), searchString, 0, 0);									
               if(match1)
               {	
                  //Displays strings to the user
                  System.out.println(s.getWord() + " " + s.getCount());
                  //Writes strings to the file
                  outfile.println(s.getWord() + " " + s.getCount());
                  //Sets check to true
                  check = true;													
               }


            }
            //If check is false there are no matching patterns	
            if(check == false)																	
            {
               //Writes statement to the file 
               System.out.println("No words in the lexicon match the pattern");
               //Writes statement to the file
               outfile.println("No words in the lexicon match the pattern");
            }
         }
         //Breaks up the output file and makes it easier to read
         outfile.println("********************************************************************");
         //Closes the file
         outfile.close();														
      }
      //Catches general exceptions
      catch(Exception e)
      {
         System.out.println(e);
      }
   }

   //Method to match the pattern with the characters in the words 
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

   //Method to display the test cases
   public static void menu()												
   {

      Scanner keyboard = new Scanner(System.in);
      String pattern;
      String choice = null;
      boolean quit = false;
      do {

         System.out.println("********************************************************************************");
         System.out.println("Please enter your choice from list of test cases:");
         System.out.println("A *** a?");
         System.out.println("B *** ?a?");
         System.out.println("C *** ?a");
         System.out.println("D *** ?a*");
         System.out.println("E *** *a?");
         System.out.println("F *** *a*");
         System.out.println("G *** a*");
         System.out.println("H *** *a");
         System.out.println("I *** w*s");
         System.out.println("J *** w?s");
         System.out.println("K *** ?a?e?");
         System.out.println("L *** *a*e*");
         System.out.println("M *** *a?e?");
         System.out.println("N *** ?a*e*");
         System.out.println("O *** *a*e?");
         System.out.println("P *** ?a?e*");
         System.out.println("Q *** a");
         System.out.println("R *** \" \"");
         System.out.println("S *** Make your own pattern: ");
         System.out.println("Z *** Quit");
         System.out.println("********************************************************************************");

         choice = keyboard.nextLine();
         choice = choice.toLowerCase();

         switch (choice) 
         {
            //Test for 2 letter patterns with wildcard "?" at the end
            case "a":
               pattern = "a?";				
               matchs(pattern);
               break;
               //Test for 3 letter patterns with wildcard "?" at the end and begining
            case "b":
               pattern = "?a?";			
               matchs(pattern);
               break;
               //Test for 2 letter patterns with wildcard "?" at the begging
            case "c":
               pattern = "?a";			
               matchs(pattern);
               break;
               //Test for patterns of any length with "a" as the second character and
               //with the wildcard "?" in the beggining and the wildcard "*" at 
               //the end
            case "d":
               pattern = "?a*";			
               matchs(pattern);
               break;
               //Test for patterns of any length with "a" as the second last 
               //character with the wildcard "*" at the begging and the wildcard "?"
               //at the end
            case "e":
               pattern = "*a?";			
               matchs(pattern);
               break;
               //Test for patterns of any length with the wildcard "*" at the
               //begging and the end
            case "f":
               pattern = "*a*";			
               matchs(pattern);
               break;
               //Test for patterns of any length with the wildcard "*" at the end
            case "g":
               pattern = "a*";			
               matchs(pattern);
               break;
               //Test for patterns of any length with the wildcard "*" at the
               //beggining
            case "h":
               pattern = "*a";			
               matchs(pattern);
               break;    
               //Test for patterns with "*" in the middle
               //i.e. was
            case "i":
               pattern = "w*s";			
               matchs(pattern);
               break;
               //Test for patterns with "?" in the middle
               //i.e. was
            case "j":
               pattern = "w?s";			
               matchs(pattern);
               break;
               //Test for patterns with "?" at the beggining, middle and end
               //i.e. taken
            case "k":
               pattern = "?a?e?";			
               matchs(pattern);
               break;
               //Test for patterns with "*" at the beggining, middle and end
               //i.e. taken
            case "l":
               pattern = "*a*e*";			
               matchs(pattern);
               break;
               //Test for patterns with "*" at the beggining with "?" at the
               //middle and end i.e. taken
            case "m":
               pattern = "*a?e?";			
               matchs(pattern);
               break;
               //Test for patterns with "?" at the beggining with "*" at the
               //middle and end i.e. taken
            case "n":
               pattern = "?a*e*";			
               matchs(pattern);
               break;
               //Test for patterns with "*" at the beggining and middle with
               //"?" at the end i.e. taken
            case "o":
               pattern = "*a*e?";			
               matchs(pattern);
               break;
               //Test for patterns with "?" at the beggining and middle with
               //"*" at the end i.e. taken
            case "p":
               pattern = "?a?e*";			
               matchs(pattern);
               break;
               //To test pattern with no wildcards
            case "q":
               pattern = "a";			
               matchs(pattern);
               break;
               //To test empty pattern and corresponding error message
            case "r":
               pattern = " ";			
               matchs(pattern);
               break;
               //Test user pattern
            case "s":
               pattern = keyboard.nextLine();	
               matchs(pattern);
               break;
               //Exit case
            case "z":
               quit = true;
               break;

               //Chosen option not found
            default:
               System.out.println("Invalid choice please try again");
               break;
         }
      } while(!quit);
   }
}
