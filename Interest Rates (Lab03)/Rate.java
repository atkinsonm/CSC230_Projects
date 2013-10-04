/*************************************************************************************************************
Michael Meluso, Patrick Cheng
CSC 230
Lab 3 - Interfaces
Rate class

Holds interest rate and dates for data in URL.
*************************************************************************************************************/

public class Rate implements Comparable
{
	private float interestRate;
	private int day, month, year;
	
	/**Rate constructor*/
	Rate (int day, int month, int year, float interestRate)
	{
	
		if (year < 13)
			year += 2000;
		else 
			year += 1900;
			
		this.day = day;
		this.month = month;
		this.year = year;
		this.interestRate = interestRate; 
	}

	public String toString()
	{
		return("Interest rate on " + month + "/" + day + "/" + year + ": " + interestRate);
	}

	/**Looks for lowest rate or most recent date if rates are identical*/
	public int compareTo(Object anotherRate) throws ClassCastException 
	{	
		if (!(anotherRate instanceof Rate))
			throw new ClassCastException("A Rate object is expected by this compareTo.");

		float rateDifference = this.getRate() - ((Rate) anotherRate).getRate(); 
		
		if (rateDifference < 0)
			rateDifference = -1;
		else if (rateDifference > 0)
			rateDifference = 1;
			
		else
		{
			if (this.year > ((Rate) anotherRate).getYear())
				rateDifference = -1;
			else if (this.year < ((Rate) anotherRate).getYear())
				rateDifference = 1;
			else 
			{
				if (this.month > ((Rate) anotherRate).getMonth())
					rateDifference = -1;
				else if (this.month < ((Rate) anotherRate).getMonth())
					rateDifference = 1;
				else 
				{
					if (this.day > ((Rate) anotherRate).getDay())
						rateDifference = -1;
					else
						rateDifference = 1;
				}
			}
		}
		
		return (int)rateDifference;
	}

	public float getRate()
	{
		return interestRate;
	}

	public int getYear()
	{
		return year;
	}

	public int getMonth()
	{
		return month;
	}

	public int getDay()
	{
		return day;
	}
}
