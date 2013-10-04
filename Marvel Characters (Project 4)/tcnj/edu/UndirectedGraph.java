/**********************************************
Michael Meluso
CSC230 
Project 4
UndirectedGraph Class

Implements an undirtected graph by creating 
a boolean array as an adjacency matrix.
**********************************************/

package tcnj.edu;

import javafoundations.*;
import java.io.*;
import java.util.*;

public class UndirectedGraph
{
	private Scanner scan;
	private boolean[][] adjMatrix = new boolean[7000][13000];
	private int edges = 0;
	
	UndirectedGraph(ArrayList<String> charArray, ArrayList<String> issueArray) throws FileNotFoundException
	{
		String temp1, temp2;
		scan = new Scanner(new File("marvelGraph.tsv"));
		
		for (int i = 0; i < 7000; i++)
		{
			for (int j = 0; j < 13000; j++)
			{
				adjMatrix[i][j] = false;
			}		
		}
		
		while (scan.hasNext())
		{
			scan.useDelimiter("\t");
			temp1 = (((scan.next()).replace("\"", "")).trim());
			scan.useDelimiter("\n");
			temp2 = (((scan.next()).replace("\"", "")).trim());
			
			/**Find correct indices and sets value to 'true', indicating character appeared in that issue*/ 
			adjMatrix[charArray.indexOf(temp1)][issueArray.indexOf(temp2)] = true;
			edges++;
		}		
	}
	
	/**Returns the list of issues in which this character appears.*/
	public String charIssues(int index, ArrayList<String> issueArray)
	{
		String results = "";
		
		for (int i = 0; i < 12849; i++)
		{
			if (adjMatrix[index][i] == true)
				results += "\n\t" + issueArray.get(i);
		}
		
		return results; 
	}
	
	/**Returns true if the character makes an appearance in this issue.*/
	public boolean issueContains(int iIndex, int cIndex)
	{
		boolean results = false;
	
		if (adjMatrix[cIndex][iIndex] == true)
		{
			results = true;
		}
	
		return results;
	}
	
	public int getSize()
	{
		return edges;
	}
	
	/**Breadth-First Search*/
	public int BFS(int startIndex, int endIndex, ArrayList<String> charArray, ArrayList<String> issueArray)
	{
		int count = 0, currentVertex;
		boolean found = false;
		LinkedQueue<Integer> traversalQueue= new LinkedQueue<Integer>();

		for (int j = 0; j < issueArray.size() && !found; j++)
		{
			if(adjMatrix[startIndex][j] != false)
			{
				traversalQueue.enqueue(j);
				found = true;
			}
		}
		
		while(!traversalQueue.isEmpty() && !found)
		{
			currentVertex = traversalQueue.dequeue();
			count++;

			if (adjMatrix[endIndex][currentVertex] != true)
			{	
			
				for (int vertexIndex=0; vertexIndex < charArray.size() && (vertexIndex != endIndex); vertexIndex++)
				{
					if(adjMatrix[vertexIndex][currentVertex] != false)
					{
						for (int i = 0; i < issueArray.size(); i++)
						{
							if ((adjMatrix[vertexIndex][i] != false && (i != currentVertex)))
							{
								traversalQueue.enqueue(i);
							}
						}
					
						if (vertexIndex == endIndex)
							found = true;	
					}
				}
			}
			
			else
				found = true;
		}
		return count;
	}
	
	/**Depth-First Search*/
	public Iterator<String> iteratorDFS(ArrayList<String>issueArray, ArrayList<String>charArray)
	{
		int startIndex = 0;
		int currentVertex;
		int num = 0;
		LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
		ArrayIterator<String> iter = new ArrayIterator<String>();
		boolean[] visited = new boolean[issueArray.size()];
		boolean found, found2;
		
		for(int vertexIdx = 0; vertexIdx < issueArray.size(); vertexIdx++)
			visited[vertexIdx] = false;

		traversalStack.push(startIndex);
		iter.add(issueArray.get(startIndex));
		visited[startIndex] = true;

		while(!traversalStack.isEmpty())
		{
			currentVertex = traversalStack.peek();
			found = false;
			found2 = false;
			num = 0;
			
			for(int vertexIdx = 0; vertexIdx < issueArray.size() && !found; vertexIdx++)
			{
				if ((adjMatrix[currentVertex][vertexIdx] != false) && !visited[vertexIdx])
				{
					num++;
					for (int i = 0; i < charArray.size(); i++)
					{
						if ((adjMatrix[i][vertexIdx] != false && (i != currentVertex)))
						{
							num++;
							traversalStack.push(i);
						}
					}
					if (num <= 3)
						iter.add(issueArray.get(vertexIdx));
						
					visited[vertexIdx] = true;
					found = true;	
				}
			}

			if (!found && !traversalStack.isEmpty())
				traversalStack.pop();
		}
		return iter;
	}
}
