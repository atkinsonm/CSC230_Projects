/** David Piccolella Michael Meluso Lab 6
 * Driver class utilizes different sorting methods to sort an array of people.
 */

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.text.*;
import java.net.URL;

public class Driver6
{
    public static void main(String[] args) throws IOException
    {
       //Scanner fileScan= new Scanner(new File("namesWithBloodType.txt"));
       Scanner scan= new Scanner(System.in);
       Person[] list= new Person[100000];
       int count=0;
       //String first = fileScan.nextLine();
       float runtime1=0;
       float runtime2=0;
       float bubbletime=0;
       float selectiontime=0;
       float insertiontime=0;
       float mergetime=0;
       float quicktime=0;

       String website= "http://s3.amazonaws.com/depasquale/datasets/namesWithBloodType.txt";

       URL url = new URL(website);
      Scanner urlscan = new Scanner(url.openStream());

       while (urlscan.hasNextLine())
       {

           String line= urlscan.nextLine();
           int ind1=0;
           int ind2=0;
           int ind3=0;
           int ind4=0;
           int ind5=0;
           int ind6=0;


           ind1= line.indexOf("|");
           ind2= line.indexOf("|", ind1+1);
           ind3= line.indexOf("|", ind2+1);
           ind4= line.indexOf("|", ind3+1);
           ind5= line.indexOf("|", ind4+1);
           ind6=line.indexOf("|", ind5+1);
	   String rhesus;
           String state= line.substring(ind4+1, ind5);
           String bloodtype= line.substring(ind6+1);
	   if(bloodtype.length()==3)
	     rhesus=bloodtype.substring(2);
           else
	     rhesus=bloodtype.substring(1);
           Person pers= new Person(state, bloodtype, rhesus);
           list[count]=pers;

           count++;



       }



       System.out.println("Please enter the number of elements to sort");
       int number= scan.nextInt();

       Person[] copy1= new Person[number];
       Person[] copy2= new Person[number];
       Person[] copy3= new Person[number];
       Person[] copy4= new Person[number];
       Person[] copy5= new Person[number];


      for(int counter=0; counter<number; counter++)
	{
		copy1[counter]=list[counter];
	}

      /** Implements the bubble sort method*/
      runtime1=System.nanoTime();
      Sorting.bubbleSort(copy1);
      runtime2=System.nanoTime();
      copy1=null;
      bubbletime= runtime2-runtime1;
      int bubblecount= Person.counter;
      Person.counter=0;
      
      for(int counter=0; counter<number; counter++)
	{
		copy2[counter]=list[counter];
	}

      /** Implements the insertion sort method */
      runtime1=System.nanoTime();
      Sorting.insertionSort(copy2);
      runtime2=System.nanoTime();
      copy2=null;
      insertiontime= runtime2-runtime1;
      int insertioncount=Person.counter;
      Person.counter=0;

      for(int counter=0; counter<number; counter++)
	{
		copy3[counter]=list[counter];
	}

      /** Implements the selection sort method*/
      runtime1=System.nanoTime();
      Sorting.selectionSort(copy3);
      runtime2=System.nanoTime();
      copy3=null;
      selectiontime= runtime2-runtime1;
      int selectioncount=Person.counter;
      Person.counter=0;

      for(int counter=0; counter<number; counter++)
	{
		copy4[counter]=list[counter];
	}

      /** Implements the merge sort method*/
      runtime1=System.nanoTime();
      Sorting.mergeSort(copy4, 0, number-1);
      runtime2=System.nanoTime();
      copy4=null;
      mergetime= runtime2-runtime1;
      int mergecount=Person.counter;
      Person.counter=0;


      for(int counter=0; counter<number; counter++)
	{
		copy5[counter]=list[counter];
	}

      /** Implements the quick sort method*/
      runtime1=System.nanoTime();
      Sorting.quickSort(copy5, 0, number-1);
      runtime2=System.nanoTime();
      copy5=null;
      quicktime= runtime2-runtime1;
      int quickcount=Person.counter;
      Person.counter=0;


      System.out.println("Sorting results for (" + number +" Person objects)");
      System.out.println();
      System.out.println("Sort Name      # Comparisons          # Nanoseconds");
      System.out.println("---------------------------------------------------");
      float [] numbers= new float[5];
      numbers[0]= bubbletime;
      numbers[1]= selectiontime;
      numbers[2]=insertiontime;
      numbers[3]=mergetime;
      numbers[4]=quicktime;

      Arrays.sort(numbers);


     NumberFormat form = NumberFormat.getInstance();
      
      boolean bubble=false;
      boolean select= false;
      boolean merge=false;
      boolean insertion=false;
      boolean quick=false;
     
     /** Prints a table of output based on the time it took for each sort to be performed. */
     for(int counters=4; counters>=0; counters--)
     {
         if(numbers[counters]==bubbletime && bubble==false)
         {
             bubble=true;
             System.out.println("bubble\t " + form.format(bubblecount) + "\t     " + form.format(bubbletime));
         }

         if(numbers[counters] == selectiontime && select==false)
         {
             select=true;
             System.out.println("selection      " + form.format(selectioncount) + "\t     " +form.format(selectiontime));
         }

         if (numbers[counters] == mergetime && merge==false)
         {
             merge=true;
             System.out.println("merge\t  " + form.format(mergecount) + "\t\t  " +form.format(mergetime));
         }

         if (numbers[counters] == insertiontime && insertion==false)
         {
             insertion=true;
             System.out.println("insertion      "+ form.format(insertioncount) + "\t     " + form.format(insertiontime));
         }

         if (numbers[counters] == quicktime && quick==false)
         {
             quick=true;
             System.out.println("quick\t  "+ form.format(quickcount) + "\t\t   " + form.format(quicktime));
         }
     }



      
    }
}



