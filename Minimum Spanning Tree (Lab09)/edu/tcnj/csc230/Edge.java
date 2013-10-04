/**Michael Meluso and David Piccolella
Lab 9
Edge Class*/

package edu.tcnj.csc230;

class Edge implements Comparable
{
	private int weight;
	private char first;
	private char second;

	public Edge(char first, char second, int weight)
	{
		this.first = first;
		this.second = second;
		this.weight = weight;
	}

	public int getWeight()
	{
		return weight;
	}

	public char getFirst()
	{
		return first;
	}

	public char getSecond()
	{
		return second;
	}

	public String toString()
	{
		return "(" + first + ", " + second + ", " + weight + ")"; 
	}
	
	public int compareTo(Object anotherEdge)
	{
		int result;
		if (this.weight == ((Edge)anotherEdge).getWeight())
			result = 0;
		else if (this.weight < ((Edge)anotherEdge).getWeight())
			result = -1;
		else
			result = 1;
		return result;
	}
}