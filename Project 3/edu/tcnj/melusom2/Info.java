/**Michael Meluso
11-21-2012
Project 3
Info Class*/

package edu.tcnj.melusom2;

import org.xbill.DNS.*;
import java.lang.reflect.Array;

public class Info
{
	Record[] aList;
	Record[] mxList;
	String hostname;
	String domain;
	
	public Info(Record[] aList, Record[] mxList, String hostname, String domain) throws ClassCastException 
	{
		this.hostname = hostname;
		this.domain = domain;
		this.aList = aList;
		this.mxList = mxList;		
	}	
	
	/**Calculates a hashCode using the mid-square method. JavaFoundations p 694*/
	public int hashCode()
	{
		int code;
		
		if (aList != null)
		{
			code = ((int)((Math.pow((aList[0].getTTL()), 2))/1000));
		}
		
		else
		{
			code = ((int)(Math.pow((mxList[0].getTTL()), 2))/1000);
		}
		return code;
	}
	
	/**Builds a String containing the hostname and records for this Info*/
	public String toString()
	{
		String result = hostname + ": ";
		
		try
		{
			result += Array.getLength(aList) + " \"A\" records(s) and "; 
		}
		catch (NullPointerException noA)
		{
			result += "0 \"A\" records(s) and ";
		}
		
		try
		{
			result += Array.getLength(mxList) + " \"MX\" record(s) as follows:\n\t\t\t";
		}
		catch (NullPointerException nomx)
		{
			result += "0 \"MX\" record(s) as follows:\n\t\t\t";
		}
		
		if (aList != null)
		{
			for (int i = 0; i < Array.getLength(aList); i++)
				result += "A - " + aList[i].rdataToString() + "\n\t\t\t";
		}
		
		if (mxList != null)
		{
			for (int i = 0; i < Array.getLength(mxList); i++)
				result += "MX - " + mxList[i].rdataToString() + "\n\t\t\t";
		}
		result += "\n";
		return result;
	}
}