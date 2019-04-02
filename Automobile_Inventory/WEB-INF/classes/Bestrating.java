import java.io.*;

/* 
	Bestrating class is a helperclass to store ratings of a product used for DataAnalytics feature.
   	This class has two variables productname and rating.
*/
   	
public class Bestrating
{
	String productname ;
	String rating;

	public  Bestrating(String productname,String rating)
	{
		
		this.productname = productname ;
	    this.rating = rating;
	}

	public String getProductname(){
	 return productname;
	}

	public String getRating () {
	 return rating;
	}
}