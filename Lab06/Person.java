/** David Piccolella Michael Meluso Lab 6
 * Person class represents information including blood type and home state of patients.
 * Implements the comparable interface
 */

public class Person implements Comparable
{

    private String state;
    private String bloodType;
    private String rhesus;
    public static int counter;
    public Person(String estate, String ebloodType, String erhesus)
    {
        state= estate;
        bloodType=ebloodType;
	rhesus=erhesus;

    }

    public String toString()
    {
        return state+ " " + " " +bloodType;

    }

    public String getState()
    {
	return state;
    }

    public String getBloodType()
    {
      return bloodType;
    }

    public String getRhesus()
    {
       return rhesus;
    }

    public int compareTo(Object otherPerson) throws ClassCastException
    {
	int result=0;
        counter++;

        /** First compares people according to their state */
	if(this.state.compareTo(((Person)otherPerson).getState())>0)
	  {
	     result=1;

	  }
          else
            if(this.state.compareTo(((Person)otherPerson).getState())<0)
	    {
	     result=-1;

	    }
            else
            {  /**If states are the same, compares blood types */
	       if(this.bloodType.compareTo(((Person)otherPerson).getBloodType())>0)
               {
		result=1;

	       }

	       if(this.bloodType.compareTo(((Person)otherPerson).getBloodType())<0)
               {
		result=-1;

	       }
               else
               { /** If blood types are the same, compares rhesus values*/
                 if(this.rhesus.compareTo(((Person)otherPerson).getRhesus())>0)
                 {
		   result=-1;

	         }

	         if(this.rhesus.compareTo(((Person)otherPerson).getRhesus())<0)
                 {
		   result=1;

	         }

               }
	     }


	return result;

    }

   
}