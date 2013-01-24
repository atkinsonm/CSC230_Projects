/***********************************************************************************************************
Michael Meluso
9-23-2012
CS 230
Project 1
Response class

Abstract class, which is the parent for all Response types. Each log entry is made into an object in the 
Response family.
***********************************************************************************************************/

import java.util.Scanner;

public abstract class Response
{
	protected int statusCode;
	protected long bytesReturned;
	protected String clientInfo;
	protected String requestType;

	/**Response constructor; used to initialize common data by all children*/
	Response (int statusCode, long bytesReturned, String clientInfo, String requestType)
	{
		this.statusCode = statusCode;
		this.bytesReturned = bytesReturned;
		try
		{
			this.clientInfo = (clientInfo.substring(2, (clientInfo.length() - 2))).trim();
		}
		catch (StringIndexOutOfBoundsException problem)
		{
			this.clientInfo = ("none provided");
		}
		this.requestType = (requestType.substring(2, 6)).trim();	
	}
	
	/**Abstract toString to be implemented by child classes*/
	public abstract String toString();
	
	/**Calculates the type of request the client is sending. Returns 0 for "GET" requests and 1 for "POST" requests.*/ 
	public int calcRequestType()
	{
		int result = 2;
		if ((requestType.charAt(0)) == ('G'))
		{
			result = 0;		
		}
		else if ((requestType.charAt(0)) == ('P'))
		{
			result = 1;
		}
		return result;
	}
	
	public String getClientInfo()
	{
		return clientInfo;
	}
	
	public String getRequestType()
	{
		return requestType;
	}

	public long getBytes()
	{
		return bytesReturned;
	}
	
	public int getCodes()
	{
		return statusCode;
	}
	
	public String getInfo()
	{
		return clientInfo;
	}
}