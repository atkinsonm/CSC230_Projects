/*************************************************************************************************************
Michael Meluso, Patrick Cheng
CSC 230
Lab 3 - Interfaces
RateDriver class

Reads in data from a URL, makes objects for the data, sorts it, and outputs the high, low, and specific data.
*************************************************************************************************************/

import java.net.URL;
import java.util.*;
import java.io.*;

public class RateDriver
{
	public static void main (String[] args) throws IOException
	{
		URL fedFunds = new URL ("http://s3.amazonaws.com/depasquale/datasets/fedfunds.txt");
		Scanner scan = new Scanner(fedFunds.openStream());				

		float interestRate;	
		int day, month, year;
		int count = 0;
		Rate[] rateArray = new Rate[21257];
			
		/**Scans in data and separates dates*/
		while (scan.hasNextLine()) 
		{	
			String line=scan.nextLine();
			int firstnum, secondnum;
			firstnum=line.indexOf("/");
			secondnum=line.lastIndexOf("/");
			if (firstnum==1)
			{
				month=Integer.parseInt(String.valueOf(line.charAt(0)));
			}
			else
			{
				month=Integer.parseInt(String.valueOf(line.charAt(firstnum-2))+line.charAt(firstnum-1));
			}
			if (line.charAt(secondnum-2)=='/')
			{
					day=Integer.parseInt((String.valueOf(line.charAt(secondnum-1))));
			}
			else
			{
				day=Integer.parseInt(String.valueOf(line.charAt(secondnum-2))+line.charAt(secondnum-1));
			}
			year=Integer.parseInt(String.valueOf(line.charAt(secondnum+1))+line.charAt(secondnum+2));
			if(line.charAt(line.length()-3)=='.')
			{
				interestRate=Float.parseFloat(line.substring(line.length()-5));
			}
			else if ((line.charAt(line.length()-2))=='.')
			{
				interestRate=Float.parseFloat(line.substring(line.length()-4));
			}
			else
			{
				interestRate=Float.parseFloat(line.substring(line.length()-2));
			}
			
			Rate rf = new Rate(day, month, year, interestRate);

			rateArray[count] = rf;
			count++;
		}
		
		/**Array sorter*/
		Arrays.sort(rateArray);
		
		/**Output*/
		System.out.println("Earliest, highest interest rate: " + rateArray[count-1]);
		System.out.println("Latest, lowest interest rate: " + rateArray[0]);
		findDate(3, 19, 1968, rateArray);
		findDate(9, 11, 2001, rateArray);
		findDate(8, 29, 1977, rateArray);
		findDate(11, 31, 1999, rateArray);
	}
	
	/**Method to find specific dates and output their information*/
	public static void findDate(int month, int day, int year, Rate[] rateArray)
	{
		boolean out = false;
		for (Rate r : rateArray)
		{
			if (((Rate)r).getYear() == year) 
			{
				if (((Rate)r).getMonth() == month)
				{
					if (((Rate)r).getDay() == day)
						System.out.println(r);
						out = true;
				}
			}
		}
		
		/**If no info found*/
		if (out == false)
			System.out.println("No information stored for " + month + "/" + day + "/" + year + "."); 
	}
}