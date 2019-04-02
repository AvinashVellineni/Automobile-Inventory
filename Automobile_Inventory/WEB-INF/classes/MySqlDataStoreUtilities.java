import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
 
/* Used to store productdetails,orders and registration information in MySQL database */

public class MySqlDataStoreUtilities
{
	static Connection conn = null;
	static String message;
	public static String getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartdatabase","root","hello");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			message="unsuccessful";
	     	return message;
		}
		catch(Exception e)
		{
			message=e.getMessage();
			return message;
		}
	}
	//InsertProducts in MySQL
	public static void Insertproducts()
	{
		try{
			getConnection();
			String truncatetableacc = "delete from Product_accessories;";
			PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
			pstt.executeUpdate();
			String truncatetableprod = "delete from  Productdetails;";
			PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
			psttprod.executeUpdate();
			String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity,manufacturerRebate)" +
			"VALUES (?,?,?,?,?,?,?,?,?,?);";
			for(Map.Entry<String,Mercedes> entry : SaxParserDataStore.mercedess.entrySet())
			{   
				String name = "mercedes";
		        Mercedes mercedes = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,mercedes.getId());
				pst.setString(3,mercedes.getName());
				pst.setDouble(4,mercedes.getPrice());
				pst.setString(5,mercedes.getImage());
				pst.setString(6,mercedes.getRetailer());
				pst.setString(7,mercedes.getCondition());
				pst.setDouble(8,mercedes.getDiscount());
				pst.setDouble(9,mercedes.getQuantity());
				pst.setString(10,mercedes.getManufacturerRebate());				
				pst.executeUpdate();	
				try{
					HashMap<String,String> acc = mercedes.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					for(Map.Entry<String,String> accentry : acc.entrySet())
					{
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1,mercedes.getId());
						pstacc.setString(2,accentry.getValue());
						pstacc.executeUpdate();
					}
				}catch(Exception et){
					et.printStackTrace();
				}	
			}
			for(Map.Entry<String,Bmw> entry : SaxParserDataStore.bmws.entrySet())
			{   
				String name = "bmw";
		        Bmw bmw = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,bmw.getId());
				pst.setString(3,bmw.getName());
				pst.setDouble(4,bmw.getPrice());
				pst.setString(5,bmw.getImage());
				pst.setString(6,bmw.getRetailer());
				pst.setString(7,bmw.getCondition());
				pst.setDouble(8,bmw.getDiscount());
				pst.setDouble(9,bmw.getQuantity());
				pst.setString(10,bmw.getManufacturerRebate());				
				pst.executeUpdate();					
			}
			for(Map.Entry<String,Audi> entry : SaxParserDataStore.audis.entrySet())
			{   
				String name = "audi";
		        Audi audi = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,audi.getId());
				pst.setString(3,audi.getName());
				pst.setDouble(4,audi.getPrice());
				pst.setString(5,audi.getImage());
				pst.setString(6,audi.getRetailer());
				pst.setString(7,audi.getCondition());
				pst.setDouble(8,audi.getDiscount());
				pst.setDouble(9,audi.getQuantity());
				pst.setString(10,audi.getManufacturerRebate());				
				pst.executeUpdate();				
			}
			for(Map.Entry<String,Jeep> entry : SaxParserDataStore.jeeps.entrySet())
			{   
				String name = "jeep";
		        Jeep jeep = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,jeep.getId());
				pst.setString(3,jeep.getName());
				pst.setDouble(4,jeep.getPrice());
				pst.setString(5,jeep.getImage());
				pst.setString(6,jeep.getRetailer());
				pst.setString(7,jeep.getCondition());
				pst.setDouble(8,jeep.getDiscount());
				pst.setDouble(9,jeep.getQuantity());
				pst.setString(10,jeep.getManufacturerRebate());				
				pst.executeUpdate();			
			}
			for(Map.Entry<String,Hyundai> entry : SaxParserDataStore.hyundais.entrySet())
			{   
				String name = "hyundai";
		        Hyundai hyundai = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,hyundai.getId());
				pst.setString(3,hyundai.getName());
				pst.setDouble(4,hyundai.getPrice());
				pst.setString(5,hyundai.getImage());
				pst.setString(6,hyundai.getRetailer());
				pst.setString(7,hyundai.getCondition());
				pst.setDouble(8,hyundai.getDiscount());
				pst.setDouble(9,hyundai.getQuantity());
				pst.setString(10,hyundai.getManufacturerRebate());				
				pst.executeUpdate();				
			}
			for(Map.Entry<String,Toyota> entry : SaxParserDataStore.toyotas.entrySet())
			{   
				String name = "toyota";
		        Toyota toyota = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,toyota.getId());
				pst.setString(3,toyota.getName());
				pst.setDouble(4,toyota.getPrice());
				pst.setString(5,toyota.getImage());
				pst.setString(6,toyota.getRetailer());
				pst.setString(7,toyota.getCondition());
				pst.setDouble(8,toyota.getDiscount());
				pst.setDouble(9,toyota.getQuantity());
				pst.setString(10,toyota.getManufacturerRebate());				
				pst.executeUpdate();				
			}
			for(Map.Entry<String,Porsche> entry : SaxParserDataStore.porsches.entrySet())
			{   
				String name = "porsche";
		        Porsche porsche = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,porsche.getId());
				pst.setString(3,porsche.getName());
				pst.setDouble(4,porsche.getPrice());
				pst.setString(5,porsche.getImage());
				pst.setString(6,porsche.getRetailer());
				pst.setString(7,porsche.getCondition());
				pst.setDouble(8,porsche.getDiscount());
				pst.setDouble(9,porsche.getQuantity());
				pst.setString(10,porsche.getManufacturerRebate());				
				pst.executeUpdate();				
			}
			for(Map.Entry<String,Lincoln> entry : SaxParserDataStore.lincolns.entrySet())
			{   
				String name = "lincoln";
		        Lincoln lincoln = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,lincoln.getId());
				pst.setString(3,lincoln.getName());
				pst.setDouble(4,lincoln.getPrice());
				pst.setString(5,lincoln.getImage());
				pst.setString(6,lincoln.getRetailer());
				pst.setString(7,lincoln.getCondition());
				pst.setDouble(8,lincoln.getDiscount());
				pst.setDouble(9,lincoln.getQuantity());
				pst.setString(10,lincoln.getManufacturerRebate());				
				pst.executeUpdate();				
			}
			for(Map.Entry<String,HeadLight> entry : SaxParserDataStore.headlights.entrySet())
			{   
				String name = "headlight";
		        HeadLight headlight = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,headlight.getId());
				pst.setString(3,headlight.getName());
				pst.setDouble(4,headlight.getPrice());
				pst.setString(5,headlight.getImage());
				pst.setString(6,headlight.getRetailer());
				pst.setString(7,headlight.getCondition());
				pst.setDouble(8,headlight.getDiscount());
				pst.setDouble(9,headlight.getQuantity());
				pst.setString(10,headlight.getManufacturerRebate());				
				pst.executeUpdate();					
			}
			for(Map.Entry<String,Rims> entry : SaxParserDataStore.rimss.entrySet())
			{   
				String name = "rims";
		        Rims rims = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,rims.getId());
				pst.setString(3,rims.getName());
				pst.setDouble(4,rims.getPrice());
				pst.setString(5,rims.getImage());
				pst.setString(6,rims.getRetailer());
				pst.setString(7,rims.getCondition());
				pst.setDouble(8,rims.getDiscount());
				pst.setDouble(9,rims.getQuantity());
				pst.setString(10,rims.getManufacturerRebate());				
				pst.executeUpdate();				
			}
			for(Map.Entry<String,Windowtint> entry : SaxParserDataStore.windowtints.entrySet())
			{   
				String name = "windowtint";
		        Windowtint windowtint = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,windowtint.getId());
				pst.setString(3,windowtint.getName());
				pst.setDouble(4,windowtint.getPrice());
				pst.setString(5,windowtint.getImage());
				pst.setString(6,windowtint.getRetailer());
				pst.setString(7,windowtint.getCondition());
				pst.setDouble(8,windowtint.getDiscount());
				pst.setDouble(9,windowtint.getQuantity());
				pst.setString(10,windowtint.getManufacturerRebate());				
				pst.executeUpdate();					
			}
			for(Map.Entry<String,Backlight> entry : SaxParserDataStore.backlights.entrySet())
			{   
				String name = "backlight";
		        Backlight backlight = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,backlight.getId());
				pst.setString(3,backlight.getName());
				pst.setDouble(4,backlight.getPrice());
				pst.setString(5,backlight.getImage());
				pst.setString(6,backlight.getRetailer());
				pst.setString(7,backlight.getCondition());
				pst.setDouble(8,backlight.getDiscount());
				pst.setDouble(9,backlight.getQuantity());
				pst.setString(10,backlight.getManufacturerRebate());				
				pst.executeUpdate();				
			}
			for(Map.Entry<String,Exhaust> entry : SaxParserDataStore.exhausts.entrySet())
			{   
				String name = "exhaust";
		        Exhaust exhaust = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,exhaust.getId());
				pst.setString(3,exhaust.getName());
				pst.setDouble(4,exhaust.getPrice());
				pst.setString(5,exhaust.getImage());
				pst.setString(6,exhaust.getRetailer());
				pst.setString(7,exhaust.getCondition());
				pst.setDouble(8,exhaust.getDiscount());
				pst.setDouble(9,exhaust.getQuantity());
				pst.setString(10,exhaust.getManufacturerRebate());				
				pst.executeUpdate();					
			}
			for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
			{   
				String name = "accessories";
		        Accessory acc = entry.getValue();				
				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,acc.getId());
				pst.setString(3,acc.getName());
				pst.setDouble(4,acc.getPrice());
				pst.setString(5,acc.getImage());
				pst.setString(6,acc.getRetailer());
				pst.setString(7,acc.getCondition());
				pst.setDouble(8,acc.getDiscount());
				pst.setDouble(9,acc.getQuantity());
				pst.setString(10,acc.getManufacturerRebate());			
				pst.executeUpdate();
			}
			
		}catch(Exception e)
		{
	  		e.printStackTrace();
		}
	} 

	//getMercedes

	public static HashMap<String,Mercedes> getMercedes()
	{	
		HashMap<String,Mercedes> hm=new HashMap<String,Mercedes>();
		try 
		{
			System.out.print("Inside getMercedes");
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"mercedes");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Mercedes trk = new Mercedes(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
					try
					{
						String selectaccessory = "Select * from Product_accessories where productName=?";
						PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
						pstacc.setString(1,rs.getString("Id"));
						ResultSet rsacc = pstacc.executeQuery();						
						HashMap<String,String> acchashmap = new HashMap<String,String>();
						while(rsacc.next())
						{    
							if (rsacc.getString("accessoriesName") != null){							
							 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
							 trk.setAccessories(acchashmap);
							}							 
						}					
					}
					catch(Exception e)
					{
							e.printStackTrace();
					}
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getBmws

	public static HashMap<String,Bmw> getBmws()
	{	
		HashMap<String,Bmw> hm=new HashMap<String,Bmw>();
		try 
		{
			System.out.print("Inside getBmws");
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"bmw");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Bmw trk = new Bmw(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getBmws

	public static HashMap<String,Audi> getAudis()
	{	
		HashMap<String,Audi> hm=new HashMap<String,Audi>();
		try 
		{
			System.out.print("Inside getAudis");
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"audi");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Audi trk = new Audi(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//Getjeeps

	public static HashMap<String,Jeep> getJeeps()
	{	
		HashMap<String,Jeep> hm=new HashMap<String,Jeep>();
		try 
		{
			getConnection();
			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"jeep");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Jeep trk = new Jeep(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//gethyundai

	public static HashMap<String,Hyundai> getHyundais()
	{	
		HashMap<String,Hyundai> hm=new HashMap<String,Hyundai>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"hyundai");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Hyundai trk = new Hyundai(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//gettoyotas

	public static HashMap<String,Toyota> getToyotas()
	{	
		HashMap<String,Toyota> hm=new HashMap<String,Toyota>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"toyota");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Toyota trk = new Toyota(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getporches

	public static HashMap<String,Porsche> getPorsches()
	{	
		HashMap<String,Porsche> hm=new HashMap<String,Porsche>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"porsche");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Porsche trk = new Porsche(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getlincolns

	public static HashMap<String,Lincoln> getLincolns()
	{	
		HashMap<String,Lincoln> hm=new HashMap<String,Lincoln>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"lincoln");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Lincoln trk = new Lincoln(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getheadlights

	public static HashMap<String,HeadLight> getHeadlights()
	{	
		HashMap<String,HeadLight> hm=new HashMap<String,HeadLight>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"headlight");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	HeadLight trk = new HeadLight(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getrims

	public static HashMap<String,Rims> getRims()
	{	
		HashMap<String,Rims> hm=new HashMap<String,Rims>();
		try 
		{			
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"rims");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Rims trk = new Rims(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getwindowtints

	public static HashMap<String,Windowtint> getWindowtints()
	{	
		HashMap<String,Windowtint> hm=new HashMap<String,Windowtint>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"windowtint");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Windowtint trk = new Windowtint(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	public static HashMap<String,Backlight> getBacklights()
	{	
		HashMap<String,Backlight> hm=new HashMap<String,Backlight>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"backlight");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Backlight trk = new Backlight(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getexhausts

	public static HashMap<String,Exhaust> getExhausts()
	{	
		HashMap<String,Exhaust> hm=new HashMap<String,Exhaust>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"exhaust");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	Exhaust trk = new Exhaust(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getnewcars

	public static HashMap<String,Newcars> getNewcars()
	{	
		HashMap<String,Newcars> hm=new HashMap<String,Newcars>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from Productdetails where (ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=?) AND (productCondition=?)";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"mercedes");
			pst.setString(2,"bmw");
			pst.setString(3,"audi");
			pst.setString(4,"hyundai");
			pst.setString(5,"jeep");
			pst.setString(6,"toyota");
			pst.setString(7,"porsche");
			pst.setString(8,"lincoln");
			pst.setString(9,"New");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	Newcars trk = new Newcars(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//getusedcars

	public static HashMap<String,Usedcars> getUsedcars()
	{	
		HashMap<String,Usedcars> hm=new HashMap<String,Usedcars>();
		try 
		{
			getConnection();			
			String selecttrackers="select * from Productdetails where (ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=? OR ProductType=?) AND (productCondition=?)";
			PreparedStatement pst = conn.prepareStatement(selecttrackers);
			pst.setString(1,"mercedes");
			pst.setString(2,"bmw");
			pst.setString(3,"audi");
			pst.setString(4,"hyundai");
			pst.setString(5,"jeep");
			pst.setString(6,"toyota");
			pst.setString(7,"porsche");
			pst.setString(8,"lincoln");
			pst.setString(9,"Used");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	Usedcars trk = new Usedcars(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//addproducts
	public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
	{
		String msg = "Product is added successfully";
		try{			
			getConnection();
			String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity,manufacturerRebate)" +
			"VALUES (?,?,?,?,?,?,?,?,?,?);";
			   
			String name = producttype;	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			pst.setDouble(9,100);
			pst.setString(10,"No");
			pst.executeUpdate();
			System.out.println(prod);
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();		
			}		
		}
		catch(Exception e)
		{
			msg = "Erro while adding the product";
			e.printStackTrace();			
		}
		return msg;
	}

	//updateproducts
	public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount)
	{ 
	    String msg = "Product is updated successfully";
		try{			
			getConnection();
			String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;" ;		        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setString(7,productId);
			pst.executeUpdate();
		}
		catch(Exception e)
		{
			msg = "Product cannot be updated";
			e.printStackTrace();			
		}
	 	return msg;	
	}

	//deleteproducts
	public static String deleteproducts(String productId)
	{   String msg = "Product is deleted successfully";
		try
		{			
			getConnection();
			String deleteproductsQuery ="Delete from Productdetails where Id=?";
			PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
			pst.setString(1,productId);			
			pst.executeUpdate();
		}
		catch(Exception e)
		{
				msg = "Proudct cannot be deleted";
		}
		return msg;
	}

	//Retrieves accessories
	public static HashMap<String,Accessory> getAccessories()
	{	
		HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
		try 
		{
			getConnection();			
			String selectAcc="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectAcc);
			pst.setString(1,"accessories");
			ResultSet rs = pst.executeQuery();		
			while(rs.next())
			{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), acc);
					acc.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;			
	}

	//DeletesOrder
	public static void deleteOrder(int orderId,String orderName)
	{
		try
		{			
			getConnection();
			String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
			PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
			pst.setInt(1,orderId);
			pst.setString(2,orderName);
			pst.executeUpdate();
			MySqlDataStoreUtilities.Updateproductquantity();
		}
		catch(Exception e)
		{
				
		}
	}

	//Inserts new order into database
	public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,String emailid)
	{
		try
		{		
			getConnection();			
			String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo,deliverydate,emailid) "
			+ "VALUES (?,?,?,?,?,?,?,?);";	
				
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
			//set the parameter for each column and execute the prepared statement
			pst.setInt(1,orderId);
			pst.setString(2,userName);
			pst.setString(3,orderName);
			pst.setDouble(4,orderPrice);
			pst.setString(5,userAddress);
			pst.setString(6,creditCardNo);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 14);
			Date date = cal.getTime();
			String DATE_FORMAT = "MM/dd/yyyy"; 
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
			String deliverydate = sdf.format(date);
			pst.setString(7,deliverydate);
			pst.setString(8,emailid);
			pst.execute();
			MySqlDataStoreUtilities.Updateproductquantity();
		}
		catch(Exception e)
		{
		
		}		
	}

	//Retrieve an order from the MySQL Database
	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
	{	
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();			
		try
		{					
			getConnection();
	        //select the table 
			String selectOrderQuery ="select * from customerorders";			
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			ResultSet rs = pst.executeQuery();	
			ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
			while(rs.next())
			{
				if(!orderPayments.containsKey(rs.getInt("OrderId")))
				{	
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(rs.getInt("orderId"), arr);
				}
				ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));	
				//add to orderpayment hashmap
				OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"));
				listOrderPayment.add(order);						
			}						
		}
		catch(Exception e)
		{
			
		}
		return orderPayments;
	}

	//Retrieves all orders from the database
	public static Map getAllProductSold()
		{

			getConnection();
			Map<OrderItem,String> map= new HashMap<OrderItem,String>();
			String query = "select *,count(*) AS quantity FROM customerorders GROUP BY orderName ORDER BY orderName ASC ;";
			try
			{
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				OrderItem order=null;
				while(rs.next())
				{
					order = new OrderItem();
					order.setName(rs.getString("orderName"));
					order.setPrice(rs.getDouble("orderPrice"));
					map.put(order,rs.getString("quantity"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return map;
		}

		//Get productDetails
		public static HashMap<String,Product> getData()
		{
			HashMap<String,Product> hm=new HashMap<String,Product>();
			try
			{
				getConnection();
				Statement stmt=conn.createStatement();
				String selectCustomerQuery="select * from  Productdetails";
				ResultSet rs = stmt.executeQuery(selectCustomerQuery);
				while(rs.next())
				{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
					hm.put(rs.getString("Id"), p);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			return hm;			
		}

		//getDaily sales data
		public static TreeMap getDailySales()
			{
				TreeMap<String,String> map= new TreeMap<String,String>();
				String query = "select deliverydate,SUM(OrderPrice) AS quantity from customerorders group by deliverydate ORDER BY CAST(deliverydate As DATETIME);";
				try
				{
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						Calendar cal = Calendar.getInstance();
						String DATE_FORMAT = "MM/dd/yyyy"; 
						SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
						java.util.Date deliveryDate = sdf.parse(rs.getString("deliveryDate"));
						cal.setTime(deliveryDate);
						cal.add(Calendar.DATE, -14);
						java.util.Date date = cal.getTime();
						String purchaseDate = sdf.format(date);
						map.put(purchaseDate,rs.getString("quantity"));
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return map;
			}

		// Insert a new user
		public static void insertUser(String username,String password,String repassword,String usertype)
		{
			try
			{	
				getConnection();
				String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
				+ "VALUES (?,?,?,?);";							
				PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,username);
				pst.setString(2,password);
				pst.setString(3,repassword);
				pst.setString(4,usertype);
				pst.execute();
			}
			catch(Exception e)
			{
			
			}	
		}

		//Update the productQuantity

		public static void Updateproductquantity(){
			try
			{	
				getConnection();
				Map<OrderItem,String> map = new HashMap<OrderItem,String>();
				map = MySqlDataStoreUtilities.getAllProductSold();
				if(map!=null){
					Iterator it = map.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry entry = (Map.Entry) it.next();
						OrderItem key = (OrderItem) entry.getKey();
						String value = (String) entry.getValue();
						double d = Double.parseDouble(value);
						double qnew= 100-d;
						String updateProductQuantityQurey = "UPDATE Productdetails SET quantity=? where productName =?;" ;			        			
						PreparedStatement pst = conn.prepareStatement(updateProductQuantityQurey);
						pst.setDouble(1,qnew);
						pst.setString(2,key.getName());
						pst.executeUpdate();
					}	
				}
				
			}
			catch(Exception e)
			{
			
			}	
		}

		//getAllProducts
		public static HashMap<String,Product> getAllProducts(){
			HashMap<String,Product> hm=new HashMap<String,Product>();
			try 
			{
				getConnection();				
				String selecttrackers="select * from  Productdetails";
				PreparedStatement pst = conn.prepareStatement(selecttrackers);
				ResultSet rs = pst.executeQuery();			
				while(rs.next())
				{	
						Product trk = new Product(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("quantity"),rs.getString("manufacturerRebate"));
						hm.put(rs.getString("Id"), trk);
						trk.setId(rs.getString("Id"));
				}
			}
			catch(Exception e)
			{
			}
			return hm;		

		}

		//getallmanufacturerrebate as yes products
		public static HashMap<String,Product> getAllManufactureRebateProducts(){
			HashMap<String,Product> hm=new HashMap<String,Product>();
			try 
			{
				getConnection();				
				String selecttrackers="select * from  Productdetails where manufacturerRebate=?";
				PreparedStatement pst = conn.prepareStatement(selecttrackers);
				pst.setString(1,"Yes");
				ResultSet rs = pst.executeQuery();			
				while(rs.next())
				{	
					Product trk = new Product(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("quantity"),rs.getString("manufacturerRebate"));
					hm.put(rs.getString("Id"), trk);
					trk.setId(rs.getString("Id"));
				}
			}
			catch(Exception e)
			{
			}
			return hm;	
		}

		//get all the registered users
		public static HashMap<String,User> selectUser()
		{	
			HashMap<String,User> hm=new HashMap<String,User>();
			try 
			{
				getConnection();
				Statement stmt=conn.createStatement();
				String selectCustomerQuery="select * from  Registration";
				ResultSet rs = stmt.executeQuery(selectCustomerQuery);
				while(rs.next())
				{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
					hm.put(rs.getString("username"), user);
				}
			}
			catch(Exception e)
			{
			}
			return hm;			
		}

	
}	