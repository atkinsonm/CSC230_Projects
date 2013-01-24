/*************************************************************************************************************
Michael Meluso
9-23-2012
CS 230
Project 1
SuccessResponse class

Represents a log entry which according to the code returned was considered successful. Inherits from Response.
*************************************************************************************************************/

public class SuccessResponse extends Response
{
	String ip, dateTime;
	
	/**SuccessResponse constructor; uses Response constructor*/
	SuccessResponse(int statusCode, long bytesReturned, String clientInfo, String requestType, String ip, String dateTime)
	{
		super(statusCode, bytesReturned, clientInfo, requestType);
		this.ip = ip.trim();
		this.dateTime = dateTime;	
	}
	
	/**Returns information about this log*/
	public String toString()
	{
		return ("\nSuccessful log information: \nDate and time: " + dateTime + "\nIP address: " + ip + "\nCode returned: " + statusCode +
		"\nType of request: " + requestType + "\nBytes returned to client: " + bytesReturned + "\nClient information: " + clientInfo);
	}
	
	public String getIP()
	{
		return ip;
	}
	
	public String getDateTime()
	{
		return dateTime;
	}
}