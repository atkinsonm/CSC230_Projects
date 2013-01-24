/****************************************************************************************************************
Michael Meluso
9-23-2012
CS 230
Project 1
ErrorResponse class

Represents a log entry which according to the code returned was considered unsuccessful. Inherits from Response.
***************************************************************************************************************/

public class ErrorResponse extends Response
{
	/**ErrorResponse constructor; uses Response constructor*/
	ErrorResponse(int statusCode, long bytesReturned, String clientInfo, String requestType)
	{
		super(statusCode, bytesReturned, clientInfo, requestType);
	}
	
	/**Returns information about this log*/
	public String toString()
	{
		return ("\nError log information: \nCode returned: " + statusCode + "\nType of request: " + requestType 
		+ "\nBytes returned to client: " + bytesReturned + "\nClient information: " + clientInfo);
	}
}