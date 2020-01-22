public class PrintLine
{  
    public static void main(String args[]) 
	{
	 int rowBase = 19;
    int colBase = 1;
    int length = 20;
  int rowIncrement = 0;
   int colIncrement = 1;
   String spaces = "";
   String start = "";
 for(int k = 0; k < rowBase; ++k) //down / start
     {
        System.out.println();
     }
   
   for(int i = 0; i < colBase; i++) // right /start
   {
   
   start += " ";
   }
   System.out.print(start);
   
   
for(int row = 0; row < length; row++) //length /direction
{
    if(rowIncrement == 1 && colIncrement == 1 )
	{
    System.out.println(spaces+"*");		
	System.out.print(start);	
    spaces += " ";
	}
	else if(rowIncrement == 0 && colIncrement == 1)
	{
	System.out.print(" *");
	if(colIncrement != 1)
	{
	System.out.print(start);
	}
    }
	else if(rowIncrement == 1 && colIncrement == 0)
	{
	System.out.println(" *");
	System.out.print(start);
	}
		
}
}
}
		