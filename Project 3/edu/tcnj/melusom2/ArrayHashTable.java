/**Michael Meluso
11-21-2012
Project 3
ArrayHashTable class*/

package edu.tcnj.melusom2;

import edu.javafoundations.exceptions.EmptyCollectionException;
import java.util.LinkedList;
import java.util.ListIterator;

public class ArrayHashTable<T> 
{
	private int collisions;
	private int size;
	private int longChain = 0;
	private LinkedList<T> hashTable[];
	
	public ArrayHashTable(int size)
	{
		LinkedList<T>[] temp = (LinkedList<T>[]) new LinkedList[size];
		this.size = size;
		for (int i = 0; i < size; i++)
		{
			temp[i] = new LinkedList<T>();
		}
		hashTable = temp;
	}
	
	/**Returns the size of the Hash Table*/
	public int size()
	{
		return size;
	}
	
	/**Returns the number of collisions which occured while building the table*/
	public int numCollisions()
	{
		return collisions;
	}
	
	/**Returns length of longest chain*/
	public int getLongChain()
	{
		return longChain;
	}
	
	/**Returns true if the hash table is empty*/
	public boolean isEmpty()
	{
		boolean result;
		
		if (size == 0)
			result = true;
		else
			result = false;
		return result;
	}
	
	/**A string representation of the table, returning the elements inside*/
	public String toString()
	{	
		T pointer;
		String result = "";
		for (int i = 0; i < size; i++)
		{
				ListIterator<T> temp = hashTable[i].listIterator(0);
				while (temp.hasNext())
				{
					result += (temp.next().toString());
				}
		}
		return result;
	}
	
	/**Returns true if the hash table contains the specified element*/
	public boolean contains(T element) throws EmptyCollectionException
	{
		boolean exists = false;
		int i = 0;
		
		while (exists == false && i < size)
		{
			exists = hashTable[i].contains(element);
			i++;
		}
		
		return exists;
	}
	
	/**Returns a reference to the element specified if it exists in the table*/
	public T find(T element) throws EmptyCollectionException
	{
		boolean exists = false;
		T result;
		
		int i = 0;
		
		while (exists == false && i < size)
		{
			exists = hashTable[i].contains(element);
			i++;
		}
	
		if (exists == false)
			result = null;
		else
			result = hashTable[i].get(hashTable[i].indexOf(element));
	
		return result;
	}
	
	/**Adds specified element to the hash table based on its hashcode*/
	public void add(T element)
	{	
		int hashCode = (element.hashCode())%size;
		
		if (hashTable[hashCode].size() == 0)
			hashTable[hashCode].add(element);
		
		else
		{
			collisions++;
			hashTable[hashCode].add(element);
			if (hashTable[hashCode].size() > longChain)
				longChain = hashTable[hashCode].size();
		}	
	}
	
	/**Removes and returns the specified element if it exists in the table*/
	public T remove(T element) throws EmptyCollectionException
	{
		boolean exists = false;
		T result;
		
		int i = 0;
		
		while (exists == false && i < size)
		{
			exists = hashTable[i].contains(element);
			i++;
		}
	
		if (exists == false)
			result = null;
		else
			result = hashTable[i].remove(hashTable[i].indexOf(element));
	
		return result;
	}
}