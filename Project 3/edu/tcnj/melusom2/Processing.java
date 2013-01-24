/**Michael Meluso
11-21-2012
Project 3
Processing Class*/

package edu.tcnj.melusom2;

import java.io.*;
import java.util.Scanner;
import org.xbill.DNS.*;
import edu.javafoundations.exceptions.*;
import java.text.DecimalFormat;

public class Processing
{
	Scanner scan = new Scanner(new File("hostnames.txt"));
	String hostname;
	String domain;
	boolean[] mkInfo = new boolean[2];
	Printer print;
	ArrayHashTable[] tableArray = new ArrayHashTable[5];
	int numE = 0;
	
	/**Sets up Hash Tables files for writing output*/
	public Processing() throws IOException
	{
		/**Creates an array of 5 ArrayHashTables, one for each implementation, being 200, 600, 1000, 1400, and 1800 cells*/
		tableArray[0] = new ArrayHashTable(200);
		tableArray[1] = new ArrayHashTable(600);
		tableArray[2] = new ArrayHashTable(1000);
		tableArray[3] = new ArrayHashTable(1400);
		tableArray[4] = new ArrayHashTable(1800);
		print = new Printer();
	}

	/**Scans in each line of input, and sends it to createInfo to be processed*/
	public void run() throws TextParseException, ClassCastException
	{
		while (scan.hasNext())
		{
			hostname = ((scan.next()).trim());
			createInfo(hostname);
			numE++;
		}
		calcResults();
	}
	
	/**Performs DNS quereies and creates Info objects*/
	public void createInfo(String hostname) throws TextParseException, ClassCastException
	{
		mkInfo[0] = false;
		mkInfo[1] = false;
		
		/**Lookup and Record classes come from DNS package*/
		Lookup aRecordLookup = new Lookup(hostname, Type.A);
		Record[] aRecords = aRecordLookup.run();
		if (aRecordLookup.getResult() != Lookup.SUCCESSFUL)
		{
			print.addException("An attempt to query the A record for " + hostname + " returned an unsuccessful result.");
			aRecords = null;
		}
		
		else
		{
			mkInfo[0] = true;
		}
		
		/**Takes last 2 parts of hostname as the domain name*/
		domain = hostname.substring((hostname.lastIndexOf(".",(hostname.lastIndexOf(".")-1)))+1);
		
		Lookup mxRecordLookup = new Lookup(domain, Type.MX);
		Record[] mxRecords = mxRecordLookup.run();
		if (mxRecordLookup.getResult() != Lookup.SUCCESSFUL)
		{
			print.addException("An attempt to query the MX record for " + domain + " returned an unsuccessful result.");
			mxRecords = null;
		}
		
		else
		{
			mkInfo[1] = true;
		}
		
		if ((mkInfo[0] == true)|| (mkInfo[1] == true))
		{
			Info hostInfo = new Info(aRecords, mxRecords, hostname, domain);
			
			for (int i = 0; i < 5; i++)
			{
				tableArray[i].add(hostInfo);
			}
		}
	}
	
	public void calcResults()
	{
		DecimalFormat decf = new DecimalFormat("##.000");
		String results = "Table Size\t\t# Elements\t\t# Collisions\t\t% Collisions\t\tLength of Longest Chain";
		results += "\n=============================================================================================\n";
		
		for (int i = 0; i < 5; i++)
		{
			results += tableArray[i].size() + "\t\t\t\t" + numE + "\t\t\t\t" + tableArray[i].numCollisions() + "\t\t\t\t\t" +
			decf.format((double)tableArray[i].numCollisions()/(double)numE*100) + "%\t\t\t\t\t" + tableArray[i].getLongChain();
			results += "\n";
		}
				
		results += "\n" + tableArray[4].toString();
		
		print.addOutput(results);
		print.finish();
	}
}