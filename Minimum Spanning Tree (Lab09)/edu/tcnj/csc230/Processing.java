/**Michael Meluso and David Piccolella
Lab 9
Processing Class*/

package edu.tcnj.csc230;

import java.util.*;
import java.io.*;
import javafoundations.*;

class Processing 
{
	Random rnd = new Random();	

	private String current;
	private char first;
	private char second;
	private int weight;

	Character[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'};

	Edge[][] array = new Edge[15][15];	

	public void run() throws IOException
	{
		int i1, i2, count = 0;
		Scanner scan = new Scanner(new File("lab9.inp"));
		while (scan.hasNext())
		{
			current = scan.nextLine();
			first = current.charAt(1);	
			second = current.charAt(4);
			weight = rnd.nextInt(8) + 2;

			Edge e1 = new Edge(first, second, weight);
			Edge e2 = new Edge(second, first, weight);

			i1 = (int)first - 'A';
			i2 = (int)second - 'A';

			array[i1][i2] = e1;
			array[i2][i1] = e2;
		}
		/**Print adjacency matrix*/
		matrix();

		/**Create iterators*/
		Iterator bfs = iteratorBFS(0);
		Iterator dfs = iteratorDFS(0);

		/**Print breadth-first search*/
		System.out.println("Breadth-First Search:");
		while (bfs.hasNext())
		{
			System.out.print(bfs.next() + " ");
		}

		/**Print depth-first search*/
		System.out.println("\n\nDepth-First Search:");
		while (dfs.hasNext())
		{
			System.out.print(dfs.next() + " ");
		}
		iteratorMST();
	}

	/**Adjacency Matrix*/
	public void matrix()
	{
		System.out.println("\nADJACENCY MATRIX");
		System.out.print("  A B C D E F G H I J K L M N O");
		
		for (int i = 0; i < 15; i++)
		{
			System.out.println();
			System.out.print((char)(i + 'A') + " ");
			for (int j = 0; j < 15; j++)
			{
				if (array[i][j] == null)
				{
					System.out.print("  ");
				} 
				else
					System.out.print(array[i][j].getWeight() + " ");
			}
		}
		System.out.print("\n\n");
	}

	/**Breadth-First Search*/
	public Iterator<Character> iteratorBFS(int startIndex)
	{
		int currentVertex;
		LinkedQueue<Integer> traversalQueue= new LinkedQueue<Integer>();
		ArrayIterator<Character> iter = new ArrayIterator<Character>();
	

		boolean[] visited = new boolean[15];
		for (int vertexIndex=0; vertexIndex< 15; vertexIndex++)
			visited[vertexIndex]=false;

		traversalQueue.enqueue(startIndex);
		visited[startIndex]= true;

		while(!traversalQueue.isEmpty())
		{
			currentVertex = traversalQueue.dequeue();
			iter.add(vertices[currentVertex]);

			for (int vertexIndex=0; vertexIndex<15; vertexIndex++)
				if((array[currentVertex][vertexIndex] != null) && !visited[vertexIndex])
                       		{
					traversalQueue.enqueue(vertexIndex);
					visited[vertexIndex] = true;
				}
		}
		return iter;
	}
	
	/**Depth-First Search*/
	public Iterator<Character> iteratorDFS(int startIndex)
	{
		int currentVertex;	
		LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
		ArrayIterator<Character> iter = new ArrayIterator<Character>();
		boolean[] visited = new boolean[15];
		boolean found;
		
		for(int vertexIdx = 0; vertexIdx < 15; vertexIdx++)
			visited[vertexIdx] = false;

		traversalStack.push(startIndex);
		iter.add(vertices[startIndex]);
		visited[startIndex] = true;

		while(!traversalStack.isEmpty())
		{
			currentVertex = traversalStack.peek();
			found = false;
			
			for(int vertexIdx = 0; vertexIdx < 15 && !found; vertexIdx++)
			{
				if ((array[currentVertex][vertexIdx] != null) && !visited[vertexIdx])
				{
					traversalStack.push(vertexIdx);
					iter.add(vertices[vertexIdx]);
					visited[vertexIdx] = true;
					found = true;	
				}
			}

			if (!found && !traversalStack.isEmpty())
				traversalStack.pop();
		}
		return iter;
	}

	/**Minimum Spanning tree*/
	public void iteratorMST()
	{
		int totalWeight = 0;
		boolean[] visited = new boolean[15];

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		ArrayIterator<Edge> iter = new ArrayIterator<Edge>();		

		for (int i = 0; i < 15; i++)
			visited[i] = false;		

		
		for (int j = 0; j < 15; j++)
		{
			for (int i = 0; i < 15; i++)
			{
				if (array[j][i] != null)
				{
					pq.add(array[j][i]);
				}
			}
		}

		while (pq.size() != 0)
		{
				if ((visited[pq.peek().getFirst() - 'A'] == false) || (visited[pq.peek().getSecond() - 'A'] == false))
				{
					totalWeight += pq.peek().getWeight();

					visited[pq.peek().getFirst() - 'A'] = true;
					visited[pq.peek().getSecond() - 'A'] = true;
					iter.add(pq.remove());						
				}

				else
					pq.remove();
		}

		/**Print Minimum Spanning Tree*/
		System.out.println("\n\nMinimum Spanning Tree:");
		while (iter.hasNext())
		{
			System.out.print(iter.next() + " ");
		}
		System.out.print(" Total weight: " + totalWeight);	
	}
}