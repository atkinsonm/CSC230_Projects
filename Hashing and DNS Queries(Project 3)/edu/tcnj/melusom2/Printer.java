/**Michael Meluso
11-21-2012
Project 3 
Printer Class*/

package edu.tcnj.melusom2;

import java.io.*;

public class Printer
{
	PrintWriter exceptFile;
	PrintWriter outFile;
	
	public Printer() throws IOException
	{
		FileWriter fw = new FileWriter("exceptions.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		exceptFile = new PrintWriter(bw);
		
		FileWriter fw2 = new FileWriter("output.txt");
		BufferedWriter bw2 = new BufferedWriter(fw2);
		outFile = new PrintWriter(bw2);	
	}
	
	public void addException(String message)
	{
		exceptFile.println(message);
	}
	
	public void addOutput(String text)
	{
		outFile.println(text);
	}

	public void finish()
	{
		exceptFile.close();
		outFile.close();
	}
}