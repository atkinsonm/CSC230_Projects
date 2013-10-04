//********************************************************************
//  LinkedStack.java       Java Foundations
//
//  Represents a linked implementation of a stack.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class LinkedStack<T> implements Stack<T>
{
   private int count;
   private LinearNode<T> top;

   //-----------------------------------------------------------------
   //  Creates an empty stack using the default capacity.
   //-----------------------------------------------------------------
   public LinkedStack()
   {
      count = 0;
      top = null;
   }

   //-----------------------------------------------------------------
   //  Removes the element at the top of this stack and returns a
   //  reference to it. Throws an EmptyCollectionException if the
   //  stack contains no elements.
   //-----------------------------------------------------------------
   public T pop() throws EmptyCollectionException
   {
      if (count == 0)
         throw new EmptyCollectionException ("Pop operation failed. "
            + "The stack is empty.");

      T result = top.getElement();
      top = top.getNext();
      count--;

      return result;
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this stack.
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "<top of stack>\n";
      LinearNode current = top;

      while (current != null)
      {
         result += current.getElement() + "\n";
         current = current.getNext();
      }

      return result + "<bottom of stack>";
   }

   public void push (T nextObj) 
   {
	LinearNode<T> nextNode = new LinearNode(nextObj);

	if (count != 0)
		nextNode.setNext(top);
	top = nextNode;
	count++;
   }
   
   public T peek () throws EmptyCollectionException
   {
	if (count == 0)
		throw new EmptyCollectionException("Peek operation failed. The stack is empty");

	return top.getElement(); 
   }
   
   public boolean isEmpty()
   {
	return (count == 0); 
   }

   public int size()
   {
	return count; 
   }
}
