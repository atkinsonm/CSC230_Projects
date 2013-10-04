import java.util.Scanner; /**Scanner object*/
import java.io.*; /**Imports file object to read in the URL file*/

public class Reader
{
	public void run() throws FileNotFoundException /**Method with counts the number of certain domains within the file.*/
	{
		File hostnames = new File ("hostnames.txt"); /**Input file*/
		Scanner scan = new Scanner(hostnames); /**Scans in file*/
		String current;
		int count = 0; /**Counter for number of URLS*/
		int com = 0;
		int org = 0;
		int edu = 0;
		int tumblrcom = 0;
		int none = 0;
		String info; /**Holds all information to be outputted*/
		
		while (scan.hasNextLine()) /**While the file has more information. Counts the lines by domain*/
		{
			count++;
			current = scan.nextLine();

			if (current.endsWith(".com"))
			{
				com++;
				if (current.endsWith("tumblr.com"))
					tumblrcom++;
			}
			else if (current.endsWith(".org"))
				org++;
			else if (current.endsWith(".edu"))
				edu++;
			else
				none++;
		}
		info = ("\nNumber of .org's: " + org + "\nNumber of .com's: " + com + "\nNumber of .edu's: " + edu 
		+ "\nNumber of other domains: " + none + "\nNumber .tumblr.com's: " + tumblrcom + "\nTotal number of items: " + count); 		
	
		System.out.print (info); /**Prints out information about domains in file*/
	}
}