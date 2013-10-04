/************************************************
// Michael Meluso
// 10-25-2012
// CS 230
// Project 2
// Job Class
//**********************************************/

package tcnj.melusom2;

public class Job implements Comparable
{
	private int pages;
	private String name;
	private int[] time = {0,0};
		
	Job(int pages)
	{
		/**Returns a random int between 1 and 50, inclusive, to be the size of the job*/
		this.pages = pages;
	}
	
	/**Records start time for this job*/
	public void setStart(int m)
	{
		time[0] = m;
	}
	
	public void setEnd(int n)
	{
		time[1] = n;
	}
	
	public int getStart()
	{
		return time[0];
	}
	
	public int getEnd()
	{
		return time[1];
	}
	
	public int getPages()
	{
		return pages;
	}
	
	public void setName(String jobNumber)
	{
		name = jobNumber;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int compareTo(Object anotherJob) throws ClassCastException
	{
		if (!(anotherJob instanceof Job))
			throw new ClassCastException("A Job object is expected by this compareTo.");

		int result;
		int jobDifference = this.getPages() - ((Job)anotherJob).getPages();

		if (jobDifference > 0)
			result = 1;
		else if (jobDifference < 0)
			result = -1;
		else 
			result =  0;
		return result;
	}
	
	public String toString()
	{
		String result = name + " (" + pages + " pages) - printed " + time[0] + " - " + time[1] + " minutes"; 
	
		return result;
	}
}