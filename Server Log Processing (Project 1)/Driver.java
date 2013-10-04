/***********************************************************************************************************
Michael Meluso
9-23-2012
CS 230
Project 1
Driver class

Processes a server log, creates objects for each log entry, and outputs information about the log.
***********************************************************************************************************/

import java.io.IOException;

public class Driver
{
	public static void main (String[] args) throws IOException
	{
		Analyzer analyze = new Analyzer();
		analyze.start();
	}
}