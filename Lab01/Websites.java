/**Michael Meluso, Sam Brougham
9-3-2012
CSC230
Lab 1, Websites Class (Driver)

Instantiates a Reader object which inputs a file with website URLS and calculates and prints the number of URLS with certain domain types

To Dr. Depasquale: I asked Sam if he preferred he or I to submit this lab, but he did not respond to my communication. I apologuize if this
is a duplicate.*/

import java.io.*;

public class Websites
{
	public static void main (String[] args ) throws FileNotFoundException
	{
		Reader reader1 = new Reader(); /**Creates new Reader object*/
		reader1.run(); /**Method which counts the number of certain domains within the file.*/
	}
}