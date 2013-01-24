package javafoundations;
import javafoundations.exceptions.*;

public class LinkedQueue<T> implements QueueADT<T>
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

      if (isEmpty())
         front = node;
      else
         rear.setNext (node);

      rear = node;
      count++;
   }

   
   public T dequeue() throws EmptyCollectionException
   {
       if(isEmpty())
         throw new EmptyCollectionException("Queue");

       T removed = front.getElement();
       front= front.getNext();
       count--;
       if(isEmpty())
        rear=null;
       
       return removed;
   }

      
   

   
   public T first() throws EmptyCollectionException
   {
      if(isEmpty())
        throw new EmptyCollectionException("Queue");
      
      return front.getElement();
   }

   
   public boolean isEmpty()
   {
      return (count == 0);
   }

   
   public int size()
   {
      return count;
   }

   
   public String toString()
   {
      String output = "";
      LinearNode<T> current = front;

      while (current != null)
      {
         output = output + (current.getElement()).toString() + "\n";
         current = current.getNext();
      }
      return output;
   }

}