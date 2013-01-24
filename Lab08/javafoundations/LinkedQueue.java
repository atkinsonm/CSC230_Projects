// 	by Paul Nathan and Michael Meluso, lab 8

package javafoundations;
import javafoundations.exceptions.EmptyCollectionException;

public class LinkedQueue<T> implements Queue<T>
{
	private int count; 
	private LinearNode<T> front, rear;
	
	public LinkedQueue()
	{
		count = 0;
		front = rear = null;
	}
	
	public void enqueue (T element)
	{
		LinearNode<T> node = new LinearNode<T>(element);
		
		if(count == 0) front = node;
		else rear.setNext(node);
		rear = node;
		count++;
	}

	public T dequeue() throws EmptyCollectionException 
	{
        if(count ==0)
			throw new EmptyCollectionException("No elements in queue");
        LinearNode<T> result = front;
        front = front.getNext();
        count--;
        return result.getElement();
       
    }
	
	public T first() throws EmptyCollectionException
	{
        if(count == 0) throw new EmptyCollectionException("No elements in queue");
        return front.getElement();
    }

	public boolean isEmpty()
    {
        if(count == 0) return true;
        return false;
            
    }

	public int size() 
    {
        return count;
    }
    
	public String toString()
	{
        String outputString = "";
        outputString += "<front of queue>\n";
        
        LinearNode<T> current = front;
        while(current != null){
            outputString += current.getElement().toString() + "\n"; 
            current = current.getNext();
        }
        
        outputString += "<rear of queue>";
        return outputString;
    }
}