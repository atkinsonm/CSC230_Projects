/***********************************************************************************************************
Michael Meluso
9-23-2012
CS 230
Project 1
Analyzer class

Analyzes the log, creates objects for log entries, processes certain bits of information, and prints them to 
the screen.
***********************************************************************************************************/
import java.util.*;
import java.io.*;
import java.text.*;

public class Analyzer 
{	
	final int SIZE = 40000;
	Response[] log = new Response[SIZE];
	private Response rPoint;
	int count = 0;
	
	private String currentLine, currentRequest, currentIP, currentInfo, currentDateTime;
	private int currentCode;
	private long currentBytes;
	private long byteCount = 0;
	
	private int[] requestTypeCount = {0, 0};
	private int[] codeCount = {0, 0, 0, 0, 0, 0};
	private int[] clientTypeCount = {0, 0, 0};

	/**Begins reading in log file and completes all instantiation of objects and analyzing.*/
	public void start() throws IOException
	{
		Scanner scan = new Scanner(new File("access_log.txt"));
		do
		{
			try
			{
				/*Reset currentBytes to 0 and scanner to default*/
				currentBytes = 0;			
				scan.useDelimiter(" ");
			
				currentIP = scan.next();
				
				/**2 -'s*/
				scan.next();
				scan.next();
				
				currentDateTime = (scan.next() + scan.next());
				
				scan.useDelimiter("\" ");
				currentRequest = scan.next();
				
				scan.reset();
				scan.skip("\"");
				currentCode = Integer.parseInt(scan.next());
				
				try 
				{
					currentBytes = Long.parseLong(scan.next());
				}
				
				catch (NumberFormatException noBytes)
				{
					currentBytes = 0;
				}
				
				/*an unimportant piece of data*/
				scan.next();
				
				scan.useDelimiter("\n");
				currentInfo = scan.next();
				
				scan.reset();
				if (currentCode < 400)
				{
					SuccessResponse sr = new SuccessResponse (currentCode, currentBytes, currentInfo, currentRequest, currentIP, currentDateTime);
					log[count] = sr;
				}
				else
				{
					ErrorResponse er = new ErrorResponse (currentCode, currentBytes, currentInfo, currentRequest);
					log[count] = er;
				}
			}
			
			/**If information is unavailable*/ 
			catch (NoSuchElementException blank)
			{
				scan.reset();
			}
			
				count++;
		} while (scan.hasNextLine());
		
		/**Analyze data and calculate results*/
		calcResults();
		
		/**Outputs to screen*/
		System.out.println(output());
		
		/**Outputs to file*/
		FileWriter fw = new FileWriter("apacheOut.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter outFile = new PrintWriter(bw);
		
		outFile.println(output());
		outFile.close();
	}

	/**Calculates all counts to be output later*/
	public void calcResults()
	{
		count = 0;
		try
		{
			for (int i = 0; i<=SIZE; i++)
			{
				rPoint = log[i];
				/**Counts the number of lines of input*/
				count++;
				
				/**Counts total numbers of GET and POST requests*/ 
				if ((rPoint.calcRequestType()) == 0)
					requestTypeCount[0]++;
				else
					requestTypeCount[1]++;
				
				/**Counts total number of bytes received by all clients*/
				byteCount += rPoint.getBytes();
				
				/**Counts the number of logs with certain codes returned*/
				if (rPoint.getCodes() < 200)
					codeCount[1]++;
				else if (rPoint.getCodes() < 300)
					codeCount[2]++;
				else if (rPoint.getCodes() < 400)
					codeCount[3]++;
				else if (rPoint.getCodes() < 500)
					codeCount[4]++;
				else
					codeCount[5]++;
				
				/**Counts number of certain client types*/
				if (rPoint.getClientInfo().contains("Windows")) 
					clientTypeCount[0]++;
				if (rPoint.getClientInfo().contains("Mozilla")) 
					clientTypeCount[1]++;
				if (rPoint.getClientInfo().contains("Googlebot")) 
					clientTypeCount[2]++;
			}
		}
		
		/**If all elements have been analyzed*/
		catch (NullPointerException done)
		{
			rPoint = log[SIZE-1];
		}
	}
	
	/**Generates output*/
	public String output()
	{
		DecimalFormat df = new DecimalFormat("#,###,###,###");
		DecimalFormat decf = new DecimalFormat("#,###,###,###.00");
		
		String results;
		results = ("Michael Meluso's Results:\n=========================\nTotal number of requests in the file: " + df.format(count-1));
		results += ("\nThe number of GET requests: " + df.format(requestTypeCount[0]));
		results += ("\nThe number of POST requests: " + df.format(requestTypeCount[1]));
		results += ("\nThe total number of bytes served: " + df.format(byteCount));
		results += ("\nThe number and percentage of pages producing various status categorizations: ");
		results += ("\n\t1xx Informational: " + df.format(codeCount[1]) + " (" + df.format((double)codeCount[1]/(double)count*100) + "%)");
		results += ("\n\t2xx Status: " + df.format(codeCount[2]) + " (" + decf.format((double)codeCount[2]/(double)count*100) + "%)");
		results += ("\n\t3xx Redirection: " + df.format(codeCount[3]) + " (" + decf.format((double)codeCount[3]/(double)count*100) + "%)");
		results += ("\n\t4xx Client Error: " + df.format(codeCount[4]) + " (" + decf.format((double)codeCount[4]/(double)count*100) + "%)");
		results += ("\n\t5xx Server Error: " + df.format(codeCount[5]) + " (" + df.format((double)codeCount[5]/(double)count*100) + "%)");
		results += ("\nThe percentage and number of Windows-based clients: " + decf.format((double)clientTypeCount[0]/(double)count*100) + 
		"% (" + df.format(clientTypeCount[0]) + ')');
		results += ("\nThe percentage and number of bad requests: " + decf.format(((double)codeCount[4] + (double)codeCount[5])/(double)count*100) +
		"% (" + df.format((codeCount[4] + codeCount[5])) + ')');
		results += ("\nThe percentage and number of clients that are Mozilla-based: " + decf.format((double)clientTypeCount[1]/(double)count*100) + 
		"% (" + df.format(clientTypeCount[1]) + ')');
		results += ("\nThe percentage and number of requests from the Googlebot: " + decf.format((double)clientTypeCount[2]/(double)count*100) + 
		"% (" + df.format(clientTypeCount[2]) + ')');
		return results;	
	}
}