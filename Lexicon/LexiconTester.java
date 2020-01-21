//Student number jamorran(17699381) Joel Andrew Morran
import java.io.*;
import java.util.*;

public class LexiconTester
{
   //Declare ArrayList of words
   private static ArrayList<Word> list;
   public static void main(String[] args)
   {
      //Scanner keyboard = new Scanner(System.in);

      //Call method to read file
      readIn();
      //Call method to sort and count words using selection sort
      selectionSortWordCount();		
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

   //Sort method
   public static<E extends Comparable<E>>
      void swap(List<E> list, int i, int j)
      {
         E temp = list.get(i);
         list.set(i, list.get(j));
         list.set(j, temp);
      }

   //Sort method
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

   //Sort method
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
}
