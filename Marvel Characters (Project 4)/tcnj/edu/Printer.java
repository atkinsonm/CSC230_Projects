/*****************************************
Michael Meluso
CSC230 
Project 4
PRinter Class
*****************************************/

package tcnj.edu;

import java.io.*;

public class Printer
{
	PrintWriter outFile;
	
	public Printer() throws IOException
	{		
		FileWriter fw = new FileWriter("output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		outFile = new PrintWriter(bw);	
	}
	
	public void addOutput(String text)
	{
		outFile.println(text);
	}

	public void finish()
	{
		outFile.close();
	}
}