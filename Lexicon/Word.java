//Student number jamorran(17699381) Joel Andrew Morran
import java.io.*;

public class Word
{
   private String word;
   private int count;
   private String border;

   public Word( String word, int count)
   {
      this.word = word;
      this.count = count;
      border = "";
   }

   public String getWord()
   {
      return word;
   }

   public int getCount()
   {
      return count;
   }

   public void setCount(int count)
   {
      this.count = count;
   }
   //get bordering words aka neighbours
   public String getBorder()
   {
      return border;
   }
   //set bordering words aka neighbours
   public void setBorder(String border)
   {
      this.border = border;
   }

   //Override to compare Objects
   public boolean equals(Object o)
   {
      return ((Word)o).word.equals(word);
   }

   public String toString()
   {
      return word + "  " + count + " " + "[" + border + "]";
   }
}
