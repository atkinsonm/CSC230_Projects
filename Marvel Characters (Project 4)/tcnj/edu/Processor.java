/*****************************************
Michael Meluso
CSC230 
Project 4
Processor Class
*****************************************/

package tcnj.edu;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Processor
{
	private Scanner inputScan;
	private ArrayList<String> charArray = new ArrayList<String>();
	private ArrayList<String> issueArray = new ArrayList<String>();
	private UndirectedGraph graph;
	private Printer print;
	
	public void start() throws IOException
	{
		print = new Printer();
		createArrays();
		
		System.out.println("Building adjacency matrix...");
		print.addOutput("Building adjacency matrix...");
		graph = new UndirectedGraph(charArray, issueArray);
		System.out.println("Complete!");
		print.addOutput("Complete!");
		
		enterInput();
		print.finish();
	}
	
	public void createArrays() throws FileNotFoundException
	{	
		PriorityQueue<String> charList = new PriorityQueue<String>();
		PriorityQueue<String> issueList = new PriorityQueue<String>();
		String temp;
		inputScan = new Scanner(new File("marvelGraph.tsv"));
		
		System.out.println("Gathering data...");
		print.addOutput("Gathering data...");
		
		/**Input all characters and issues and sort them lexographically*/
		while (inputScan.hasNext())
		{
			inputScan.useDelimiter("\t");
			temp = (((inputScan.next()).replace("\"", "")).trim());
			
			if(!(charList.contains(temp)))
				charList.add(temp);
				
			inputScan.useDelimiter("\n");
			temp = (((inputScan.next()).replace("\"", "")).trim());
			
			if(!(issueList.contains(temp)))
				issueList.add(temp);
		}

		while(!(charList.isEmpty()))
		{
			charArray.add(charList.peek());
			charList.remove();		
		}
		
		while(!(issueList.isEmpty()))
		{
			issueArray.add(issueList.peek());
			issueList.remove();		
		}
		
		System.out.println("Complete!");
		print.addOutput("Complete!");
	}
	
	/**Accepts user input and queries the graph to receive output*/
	public void enterInput()
	{
		DecimalFormat df = new DecimalFormat("##,###");
		Scanner userScan = new Scanner(System.in);
		String input = "";
		String results;
		
		System.out.println("You may begin querying the graph. Type 'QUIT' to quit.");
		print.addOutput("You may begin querying the graph. Type 'QUIT' to quit.");
		
		userScan.nextLine();
		
		/**Commands issued by user must be echoed to output.txt*/
		do
		{
			results = "";
			input = userScan.nextLine();
			
			/**Adds user input to output.txt*/
			print.addOutput("\n" + input);	
			
			/**CHAR query*/
			if (input.contains("CHAR"))
			{
				try
				{
					if (charArray.contains(input.substring(6, (input.length()-1))))
					{
						results = "\n" + (input.substring(6, (input.length()-1))) + " appears in:";
						results += graph.charIssues(charArray.indexOf(input.substring(6, (input.length()-1))), issueArray);
					}
					else
						results = "\nThe character \"" + (input.substring(6, (input.length()-1))) + "\" could not be found.";
				}
				
				catch (StringIndexOutOfBoundsException wrongFmt)
				{
					results = "\nPlease stick to typing conventions, placing quotes around the issue title and character.";
				}
				
				results += "\nEnter another query of \'QUIT\' to quit.";	
				System.out.println(results);
				print.addOutput(results);
			}
			
			/**Issue query*/
			else if (input.contains("ISSUE"))
			{
				try
				{
					if (issueArray.contains(input.substring(7, input.indexOf("\"", 7))))
					{
						if(graph.issueContains(issueArray.indexOf(input.substring(7, input.indexOf("\"", 7))), 
						charArray.indexOf(input.substring((input.indexOf("\"", 7)+3),(input.length()-1)))))
						{
							results = "\nIssue " + (input.substring(7, input.indexOf("\"", 7))) + " contains an appearance by " + 
							(input.substring((input.indexOf("\"", 7)+3),(input.length()-1))) + ".";
						}
						
						else
						{
							results = "\nIssue " + (input.substring(7, input.indexOf("\"", 7))) + " does not contain an appearance by " + 
							(input.substring((input.indexOf("\"", 7)+3),(input.length()-1))) + ".";
						}
					}
				
					else 
						results = "\nThe issue \"" + (input.substring(7, input.indexOf("\"", 7))) + "\" could not be found.";
				}
				
				catch (ArrayIndexOutOfBoundsException noChar)
				{
					results = "\nThe character \"" + (input.substring((input.indexOf("\"", 7)+3),(input.length()-1))) + "\" could not be found.";
				}
				
				catch (StringIndexOutOfBoundsException wrongFmt)
				{
					results = "\nPlease stick to typing conventions, placing quotes around the issue title and character.";
				}
				
				results += "\nEnter another query of \'QUIT\' to quit.";	
				System.out.println(results);
				print.addOutput(results);
			}
			
			/**Size query*/
			else if (input.contains("SIZE"))
			{
				results = "The graph is comprised of " + df.format(charArray.size() + issueArray.size()) + 
				" vertices and " + df.format(graph.getSize()) + " edges.\nEnter another query of \'QUIT\' to quit.";
				System.out.println(results);
				print.addOutput(results);
			}
			
			/**DFS query*/
			else if (input.contains("DFS SMALL"))
			{
				Iterator<String> dfs = graph.iteratorDFS(issueArray, charArray);
				results = "The following issues contain appearances by 3 or fewer characters: ";
				
				while (dfs.hasNext())
				{
					results += "\n\t" + dfs.next();
				}
				results += "\nEnter another query of \'QUIT\' to quit.";
				System.out.println(results);
				print.addOutput(results);
			}
			
			/**Degrees query*/
			else if (input.contains("DEGREES"))
			{
				try
				{
					if (charArray.contains(input.substring(9, input.indexOf("\"", 9))))
					{
						if (charArray.contains(input.substring((input.indexOf("\"", 9)+3), (input.length()-1))))
						{
							results = (input.substring(9, input.indexOf("\"", 9))) + " and " + (input.substring((input.indexOf("\"", 9)+2), (input.length()-1)))
							+ " are separated by " + graph.BFS(charArray.indexOf((input.substring(9, input.indexOf("\"", 9)))),
							charArray.indexOf((input.substring((input.indexOf("\"", 9)+2), (input.length()-1)))), charArray, issueArray) + " degrees.";
						}
					
						else
							results = "\nThe character \"" + (input.substring((input.indexOf("\"", 9)+3), (input.length()-1))) + "\" could not be found.";
					}
					else
						results = "\nThe character \"" + (input.substring(9, input.indexOf("\"", 9))) + "\" could not be found.";
				}

				catch (StringIndexOutOfBoundsException wrongFmt)
				{
					results = "\nPlease stick to typing conventions, placing quotes around the issue title and character.";
				}
				
				results += "\nEnter another query of \'QUIT\' to quit.";	
				System.out.println(results);
				print.addOutput(results);
			}
			
			else if (!(input.equals("QUIT")))
			{
				System.out.println("Please enter valid input: ");
				print.addOutput("Please enter valid input: ");
			}
		}while (!(input.equals("QUIT")));
	}
}		
	
