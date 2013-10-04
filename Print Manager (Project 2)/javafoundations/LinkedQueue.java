/********************************************************************
// *Editted by Michael Meluso
// *10-25-2012
// *CS 230
// *Project 2
//
//  LinkedQueue.java       Java Foundations
//
//  Represents a linked implementation of a queue.
//******************************************************************/

package javafoundations;

import javafoundations.exceptions.*;

public class LinkedQueue<T> implements Queue<T>
{
   private int count;
   private LinearNode<T> front, rear;

   //-----------------------------------------------------------------
   //  Creates an empty queue.
   //-----------------------------------------------------------------
   public LinkedQueue()
   {
		count = 0;
		front = rear = null;
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the rear of this queue.
   //-----------------------------------------------------------------
   public void enqueue(T element)
   {
      LinearNode<T> node = new LinearNode<T>(element);

		if (count == 0)
			front = node;
		else
			rear.setNext(node);

		rear = node;
		count++;
   }
   
   /**Removes front element from the queue and returns it*/
   public T dequeue() throws EmptyCollectionException
   {
		LinearNode<T> temp;
		
		if (count == 0)
			throw new EmptyCollectionException("Dequeue operation failed. " + "The queue is empty.");
			
		temp = front;
		T result = front.getElement();
		front = front.getNext();		
		temp.setNext(null);
   
		count--;
		return result;
   }
   
   /**Returns front element in queue*/
   public T first() throws EmptyCollectionException
   {	
		if (count == 0)
			throw new EmptyCollectionException("First operation failed. " + "The queue is empty.");
			
		return front.getElement();   
   }
   
   /**Boolean returns true if no elements are in the queue*/
   public boolean isEmpty()
   {
		boolean temp = false;
		if (count == 0)
			temp = true;
		return temp;
   }
   
   /**Returns size of queue*/
   public int size()
   {
		return count;
   }
   
   /**Prints all elements currently in the queue*/
   public String toString() 
   { 
		String result = "";
		LinearNode current = front;
		
		while (current != null)
		{
			result += current.getElement() + "\n";
			current = current.getNext();		
		}
		return result;
	}
}
