/************************************************
// Michael Meluso
// 10-25-2012
// CS 230
// Project 2
// PrintManager Class
//**********************************************/

package tcnj.melusom2;

import javafoundations.*;
import javafoundations.exceptions.*;
import java.util.*;
import java.io.*;

public class PrintManager
{
	final int PPM = 10;
	LinkedQueue<Job> onePrint = new LinkedQueue<Job>();
	LinkedQueue<Job> twoPrint = new LinkedQueue<Job>();
	LinkedQueue<Job> threePrint = new LinkedQueue<Job>();

	Job one; /**First job to be printed*/
	
	int pages;
	Random rgen = new Random();
	
	String results = "";
	
	PrintManager()
	{		
		/**Fills 3 identical queues with 100 randomly generated jobs*/
		for (int i = 0; i < 100; i++)
		{
			pages = rgen.nextInt(50) + 1;
			onePrint.enqueue((new Job(pages)));
			twoPrint.enqueue((new Job(pages)));
			threePrint.enqueue((new Job(pages)));
		}
		one = new Job(rgen.nextInt(50) + 1);
	}
	
	public void startPrint() throws IOException
	{
		/**Outputs to file*/
		FileWriter fw = new FileWriter("printerAnalysis.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter outFile = new PrintWriter(bw);
	
		/**Prints all jobs on 1, 2, and 3 printers*/
		int first = onePrinter();
		int second = twoPrinters(first);
		int third = threePrinters(second);
		
		/**Output file*/
		outFile.println(results);
		outFile.close();
	}
	
	public int onePrinter()
	{		
		LinkedQueue<Job> queue = new LinkedQueue<Job>(); /**Queue for sending jobs to printer*/
		LinkedQueue<Job> printQueue = new LinkedQueue<Job>(); /**Printer output*/
		Job[] array = new Job[100]; /**Array to sort jobs*/
		Job temp; /**Temp job for moving jobs between queues*/
		
		/**Fill array with values of 100; default value*/
		for (int i = 0; i < 100; i++)
			array[i] = new Job(100);
		
		array[0] = one;
		queue.enqueue(array[0]);
		
		int jobNumber = 0;
		int minute = 1; /**Time keeper*/
		int pages; /**Number of pages being printed*/	
							
		while (queue.isEmpty() != true)
		{
			pages = (queue.first()).getPages();
			(queue.first()).setName("J" + String.valueOf(jobNumber));
			(queue.first()).setStart(minute);
			
			/**This while runs once every 'minute'*/
			while (pages >= 0)
			{
				pages -= PPM;
				
				if (!(minute >= 100))
					array[minute] = onePrint.dequeue();
					
				minute++;
			}
			(queue.first()).setEnd(minute);
			
			jobNumber++;

			temp = queue.dequeue();
			printQueue.enqueue(temp);
			
			/**array[0] handled; set value to 100 so it moves to the end*/
			array[0] = new Job(100);
			
			/**Sorts array in its current state*/
			Arrays.sort(array);
			
			if (!(array[0].getPages() == 100))
				queue.enqueue(array[0]);
		}
		results += "1-Printer Analysis\n============\n";
		results += printQueue.toString();
		results += "Total job time: " + minute + " minutes";
		System.out.println(results);
		
		return minute;
	}
	
	public int twoPrinters(int first)
	{
		LinkedQueue<Job> queue = new LinkedQueue<Job>(); /**Queue for sending jobs to printer*/
		LinkedQueue<Job> printerQueue1 = new LinkedQueue<Job>(); /**Queue for printing jobs in printer 1*/
		LinkedQueue<Job> printerQueue2 = new LinkedQueue<Job>(); /**Queue for printing jobs in printer 2*/
		Job[] array = new Job[100]; /**Array for sorting jobs*/
		Job temp; /**Temp job for moving jobs between queues*/
		
		/**Fill array with values of 100; default value*/
		for (int i = 0; i < 100; i++)
			array[i] = new Job(100);
		
		System.out.println(twoPrint.first());
		
		array[0] = one;
		queue.enqueue(array[0]);
		
		int jobNumber = 0;
		int minute1 = 1, minute2 = 1; /**Minutes each printer is printing*/
		int i = 0; /**Array index*/
		int pages1, pages2; /**Number of pages each printer is printing*/
		
		int second;	/**2 printer total time*/		
							
		while (queue.isEmpty() != true)
		{
			/**If job should be printed by printer 1*/
			if ((queue.first()).getPages() <= 25)
			{
				pages1 = (queue.first()).getPages();
				(queue.first()).setName("J" + String.valueOf(jobNumber));
				(queue.first()).setStart(minute1);
				
				while (pages1 >= 0)
				{
					pages1 -= PPM;
				
					if (!(i >= 100))
						array[i] = twoPrint.dequeue();
					i++;
					minute1++;
				}
				(queue.first()).setEnd(minute1);
				temp = queue.dequeue();
				printerQueue1.enqueue(temp);
			}
			
			/**If job should be printed by printer 2*/
			else
			{
				pages2 = (queue.first()).getPages();
				(queue.first()).setName("J" + String.valueOf(jobNumber));
				(queue.first()).setStart(minute2);
				
				while (pages2 >= 0)
				{
					pages2 -= PPM;
				
					if (!(i >= 100))
						array[i] = twoPrint.dequeue();
					i++;
					minute2++;
				}
				(queue.first()).setEnd(minute2);
				temp = queue.dequeue();
				printerQueue2.enqueue(temp);
			}

			jobNumber++;
			
			/**array[0] handled; set value to 100 so it moves to the end*/
			array[0] = new Job(100);
			
			/**Sorts array in its current state*/
			Arrays.sort(array);
			
			/**Enqueue next job to be printed*/
			if (!(array[0].getPages() == 100))
				queue.enqueue(array[0]);
		}
		
		/**Determine total time*/
		if (minute1 > minute2)
			second = minute1;
		else
			second = minute2;
		
		results += "\n\n2-Printer Analysis\n============\n\t\t\t Printer 1 (size 1-25) \t\t\t\t\t\t Printer 2 (size 26-50)\n";	
		
		/**Separates jobs under their respective printer for output*/		
	
		while ((printerQueue1.isEmpty() != true) && (printerQueue2.isEmpty() != true))
		{
			results += (printerQueue1.first()).toString() + "\t\t" + (printerQueue2.first()).toString() + "\n";
			printerQueue1.dequeue();
			printerQueue2.dequeue();				
		}
	
		results += "Total job time: " + second + " minutes (" + (100*(first-second)/first) + "% speed-up)";
		System.out.print(results);		
		
		return second;
	}
	
	public int threePrinters(int second)
	{
		LinkedQueue<Job> queue = new LinkedQueue<Job>(); /**Queue for sending jobs to printer*/
		LinkedQueue<Job> printerQueue1 = new LinkedQueue<Job>(); /**Queue for printing jobs in printer 1*/
		LinkedQueue<Job> printerQueue2 = new LinkedQueue<Job>(); /**Queue for printing jobs in printer 2*/
		LinkedQueue<Job> printerQueue3 = new LinkedQueue<Job>(); /**Queue for printing jobs in printer 3*/
		Job[] array = new Job[100]; /**Array for sorting jobs*/
		Job temp; /**Temp job for transferring jobs between queues*/
		
		/**Fill array with values of 100; default value*/
		for (int i = 0; i < 100; i++)
			array[i] = new Job(100);
		
		array[0] = one;
		queue.enqueue(array[0]);
		
		int jobNumber = 0;
		int minute1 = 1, minute2 = 1, minute3 = 1; /**Minutes each printer is printing*/
		int i = 0; /**Array index*/
		int pages1, pages2, pages3; /**Pages being printed by each printer*/
		
		int third; /**Total minutes*/	
							
		while (queue.isEmpty() != true)
		{
			/**If job should be printed by Printer 1*/
			if ((queue.first()).getPages() <= 17)
			{
				pages1 = (queue.first()).getPages();
				(queue.first()).setName("J" + String.valueOf(jobNumber));
				(queue.first()).setStart(minute1);
				
				while (pages1 >= 0)
				{
					pages1 -= PPM;
				
					if (!(i >= 100))
						array[i] = threePrint.dequeue();
					i++;
					minute1++;
				}
				(queue.first()).setEnd(minute1);
				temp = queue.dequeue();
				printerQueue1.enqueue(temp);
			}
			
			/**If job should be printed by Printer 2*/
			else if ((queue.first()).getPages() >= 18 && (queue.first()).getPages() <= 34)
			{
				pages2 = (queue.first()).getPages();
				(queue.first()).setName("J" + String.valueOf(jobNumber));
				(queue.first()).setStart(minute2);
				
				while (pages2 >= 0)
				{
					pages2 -= PPM;
				
					if (!(i >= 100))
						array[i] = threePrint.dequeue();
					i++;
					minute2++;
				}
				(queue.first()).setEnd(minute2);
				temp = queue.dequeue();
				printerQueue2.enqueue(temp);
			}
			
			/**If job should be printed by Printer 3*/
			else
			{
				pages3 = (queue.first()).getPages();
				(queue.first()).setName("J" + String.valueOf(jobNumber));
				(queue.first()).setStart(minute3);
				
				while (pages3 >= 0)
				{
					pages3 -= PPM;
				
					if (!(i >= 100))
						array[i] = threePrint.dequeue();
					i++;
					minute3++;
				}
				(queue.first()).setEnd(minute3);
				temp = queue.dequeue();
				printerQueue3.enqueue(temp);
			}

			jobNumber++;
			
			/**array[0] handled; set value to 100 so it moves to the end*/
			array[0] = new Job(100);
			
			/**Sorts array in its current state*/
			Arrays.sort(array);
			
			/**Enqueue new job to print*/
			if (!(array[0].getPages() == 100))
				queue.enqueue(array[0]);
		}
		
		/**Determine total minutes*/
		if (minute1 > minute2)
		{
			third = minute1;
			if (minute1 > minute3)
				third = minute1;
			else 
				third = minute3;
		}
		
		else if (minute2 > minute1)
		{
			if (minute2 > minute3)
				third = minute2;
			else
				third = minute3;
		}
		
		else
		{
			if (minute3 > minute1)
				third = minute3;
			else 
				third = minute1;
		}
		
		results += "\n\n3-Printer Analysis\n============\n\t\t\t Printer 1 (size 1-17) \t\t\t\t\t\t Printer 2 (size 18-34)" +
		"\t\t\t\t\t\t Printer 3 (size 35-50)\n";
		
		/**Separates jobs under their respective printer for output*/
		while ((printerQueue1.isEmpty() != true) && (printerQueue2.isEmpty() != true) && (printerQueue3.isEmpty() != true))
		{
			results += (printerQueue1.first()).toString() + "\t\t" + (printerQueue2.first()).toString() + "\t\t" + (printerQueue3.first()).toString() + "\n";
			printerQueue1.dequeue();
			printerQueue2.dequeue();
			printerQueue3.dequeue();
		}
		
		results += "Total job time: " + third + " minutes (" + (100*(second-third)/second) + "% speed-up)";
		System.out.println(results);
		
		return third;
	}
}