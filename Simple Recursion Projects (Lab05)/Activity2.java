/** Warren Smith and Mike Meluso
    9/26/12
    CSC230-01
    lab5: Activity2.java
*/

import java.util.*;
import java.io.*;

public class Activity2
{
	public static int val = 0;
	public static String[] vowelArray = {"a", "e", "i", "o", "u"};

	public static void main(String[] args)
	{
		
		/** Prompt user */
		System.out.println("Enter a word: ");
		
		/** Create Scanner object */
		Scanner scan = new Scanner(System.in);	

		/** Get number of vowels */
		int numOfVowels = vowels(scan.next(), -1);

		/** Print result */
		System.out.println("");
		System.out.println("Number of vowels = " + numOfVowels);
	}

	public static int vowels(String str, int position)
	{
		int pos = ++position;

		if(pos < str.length())
		{
			for(int i = 0; i < vowelArray.length; i++)
			{
				if(Character.toString(str.charAt(pos)).equalsIgnoreCase(vowelArray[i]))
				{
					val++;
				}
			}
			/** Recursive Part */
			return vowels(str, pos);
		}
		return val;
	}
}








