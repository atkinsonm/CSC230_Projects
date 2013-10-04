/**Michael Meluso
11-21-2012
Project 3
Driver Class*/

package edu.tcnj.melusom2;

import java.io.*;
import org.xbill.DNS.*;

public class Driver
{
	public static void main (String[] args) throws IOException, TextParseException, ClassCastException
	{
		Processing pm = new Processing();
		pm.run();	
	}
}