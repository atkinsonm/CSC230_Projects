/**Mike Meluso and Warren Smith
10-1-2012
Lab 5 - Activity1*/

import java.util.Scanner;

public class Activity1
{
	public static void main (String[] args)
	{
		int num;
		Scanner scan = new Scanner(System.in);

		System.out.print("Please enter a nonnegative integer: ");
		
		num = scan.nextInt();

		drawStars(num);		
	}

	/**Recursive method*/
	public static void drawStars(int numStars)
	{
		String results = "";
		
		if (numStars != 0)
		{

			for (int i = 1; i <= numStars; i++)
			{
				results += "*";
			}
			results += "\n";
			
			/**Prints stars decreasingly*/
			System.out.print(results);
			
			/**Recursive statement*/
			drawStars(numStars-1);
			
			/**Prints stars increasingly*/
			System.out.print(results);
		}
	}
}