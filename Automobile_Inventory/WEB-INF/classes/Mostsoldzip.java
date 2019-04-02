import java.io.*;

/* MostsoldZip class is a helper class for the trending feature it stores the zipcod and count of the products sold at the zipcode */

public class Mostsoldzip
{
	String zipcode ;
	String count;
	public  Mostsoldzip(String zipcode,String count)
	{		
		this.zipcode = zipcode ;
	    this.count = count;
	}
	public String getZipcode(){
	 	return zipcode;
	}
	public String getCount () {
	 	return count;
	}
}