/** Warren Smith and Mike Meluso
    9/26/12
    CSC230-01
    lab5: Activity3.java
*/

import java.util.*;
import java.io.*;

public class Activity3
{
    public static void main(String[] args)
    {
    	/** Creates scanner object and reads in x & y interger values */
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter x interger: ");	
		int num1 = scan.nextInt();
		System.out.println("Enter y interger: ");
		int num2 = scan.nextInt();
		
		/** Creates the new gcd object */
    	Activity3 run = new Activity3();
    	
		/** Prints out the gcd result */
		System.out.println("");
		System.out.println("gcd(" + num1 + "," + num2 + "): " + run.gcd(num1,num2));
    }

    int gcd(int x,int y)
    {
		/** Recursive method to determine gcd value */
		if(y==0)
    		return x;
    	else if(y > x)
    		return gcd(y,x);
    	else
    		return gcd(y,x%y);
    }
}

