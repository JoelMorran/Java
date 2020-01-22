public class PrintBox
{  
    public static void main(String args[]) 
	{
for (int x=0; x < 20; ++x) 
{
    for (int y=0; y < 30; ++y)
        if (x == 0 || x+1 == 20 || y == 0 || y+1 == 30) 
			{
            System.out.print(" *");
        } 
		else 
		{
            System.out.print("  ");
        }
    System.out.println();
}
}
}