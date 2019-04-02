
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {    
    Accessory accessory;
    Mercedes mercedes;
    Bmw bmw;
    Audi audi;
    Jeep jeep;
    Hyundai hyundai;
    Toyota toyota;
    Porsche porsche;
    Lincoln lincoln;
    HeadLight headlight;
    Rims rims;
    Windowtint windowtint;
    Backlight backlight;
    Exhaust exhaust;
    static HashMap<String,Accessory> accessories;
    static HashMap<String,Mercedes> mercedess;
    static HashMap<String,Bmw> bmws;
    static HashMap<String,Audi> audis;
    static HashMap<String,Jeep> jeeps;
    static HashMap<String,Hyundai> hyundais;
    static HashMap<String,Toyota> toyotas;
    static HashMap<String,Porsche> porsches;
    static HashMap<String,Lincoln> lincolns;
    static HashMap<String,HeadLight> headlights;
    static HashMap<String,Rims> rimss;
    static HashMap<String,Windowtint> windowtints;
    static HashMap<String,Backlight> backlights;
    static HashMap<String,Exhaust> exhausts;
    String consoleXmlFileName;
    HashMap<String,String> accessoryHashMap;
    String elementValueRead;
    String currentElement="";
    public SaxParserDataStore()
    {
    }
    public SaxParserDataStore(String consoleXmlFileName) {
        this.consoleXmlFileName = consoleXmlFileName;
        accessories=new HashMap<String, Accessory>();
        mercedess=new HashMap<String, Mercedes>();
        bmws=new HashMap<String, Bmw>();
        audis=new HashMap<String, Audi>();
        jeeps=new HashMap<String, Jeep>();
        hyundais=new HashMap<String, Hyundai>();
        toyotas=new HashMap<String, Toyota>();
        porsches=new HashMap<String, Porsche>();
        lincolns= new HashMap<String,Lincoln>();
        headlights= new HashMap<String,HeadLight>();
        rimss= new HashMap<String,Rims>();
        windowtints= new HashMap<String,Windowtint>();
        backlights= new HashMap<String,Backlight>();
        exhausts= new HashMap<String,Exhaust>();
        accessoryHashMap=new HashMap<String,String>();
        parseDocument();
    }

    //parse the xml using sax parser to get the data
    private void parseDocument() 
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
        {
        SAXParser parser = factory.newSAXParser();
        parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
    
    // when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
        if(elementName.equalsIgnoreCase("mercedes")){
            currentElement="mercedes";
            mercedes= new Mercedes();
            mercedes.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("bmw")){
            currentElement="bmw";
            bmw= new Bmw();
            bmw.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("audi")){
            currentElement="audi";
            audi= new Audi();
            audi.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("jeep")){
            currentElement="jeep";
            jeep= new Jeep();
            jeep.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("hyundai")){
            currentElement="hyundai";
            hyundai= new Hyundai();
            hyundai.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("toyota")){
            currentElement="toyota";
            toyota= new Toyota();
            toyota.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("porsche")){
            currentElement="porsche";
            porsche= new Porsche();
            porsche.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("lincoln")){
            currentElement="lincoln";
            lincoln= new Lincoln();
            lincoln.setId(attributes.getValue("id"));
        }
        if(elementName.equalsIgnoreCase("headlight")){
            currentElement="headlight";
            headlight= new HeadLight();
            headlight.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("rims")){
            currentElement="rims";
            rims= new Rims();
            rims.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("windowtint")){
            currentElement="windowtint";
            windowtint= new Windowtint();
            windowtint.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("backlight")){
            currentElement="backlight";
            backlight= new Backlight();
            backlight.setId(attributes.getValue("id"));
        }

        if(elementName.equalsIgnoreCase("exhaust")){
            currentElement="exhaust";
            exhaust= new Exhaust();
            exhaust.setId(attributes.getValue("id"));
        }

        if (elementName.equals("accessory") &&  !currentElement.equals("mercedes") )
        {
            currentElement="accessory";
            accessory=new Accessory();
            accessory.setId(attributes.getValue("id"));
        }
    }

    // when xml end element is parsed store the data into respective hashmap for mercedes,bmw etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
        if (element.equals("mercedes")) {   
            mercedess.put(mercedes.getId(),mercedes);
            return;
        }
        if (element.equals("bmw")) {    
            bmws.put(bmw.getId(),bmw);
            return;
        }
        if (element.equals("audi")) {   
            audis.put(audi.getId(),audi);
            return;
        }
        if (element.equals("jeep")) {   
            jeeps.put(jeep.getId(),jeep);
            return;
        }
        if (element.equals("hyundai")) {    
            hyundais.put(hyundai.getId(),hyundai);
            return;
        }
        if (element.equals("toyota")) { 
            toyotas.put(toyota.getId(),toyota);
            return;
        }
        if (element.equals("porsche")) {    
            porsches.put(porsche.getId(),porsche);
            return;
        }
        if (element.equals("lincoln")) {    
            lincolns.put(lincoln.getId(),lincoln);
            return;
        }
        if (element.equals("headlight")) {  
            headlights.put(headlight.getId(),headlight);
            return;
        }
        if (element.equals("rims")) {   
            rimss.put(rims.getId(),rims);
            return;
        }
        if (element.equals("windowtint")) { 
            windowtints.put(windowtint.getId(),windowtint);
            return;
        }
        if (element.equals("backlight")) {  
            backlights.put(backlight.getId(),backlight);
            return;
        }
        if (element.equals("exhaust")) {    
            exhausts.put(exhaust.getId(),exhaust);
            return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
            accessories.put(accessory.getId(),accessory);       
            return; 
        }
        if (element.equals("accessory") && currentElement.equals("mercedes")) 
        {
            accessoryHashMap.put(elementValueRead,elementValueRead);
        }
        if (element.equalsIgnoreCase("accessories") && currentElement.equals("mercedes")) {
            mercedes.setAccessories(accessoryHashMap);
            accessoryHashMap=new HashMap<String,String>();
            return;
        }
        if (element.equalsIgnoreCase("image")) {            
            if(currentElement.equals("mercedes"))
                mercedes.setImage(elementValueRead);
            if(currentElement.equals("bmw"))
                bmw.setImage(elementValueRead);
            if(currentElement.equals("audi"))
                audi.setImage(elementValueRead);
            if(currentElement.equals("jeep"))
                jeep.setImage(elementValueRead);
            if(currentElement.equals("hyundai"))
                hyundai.setImage(elementValueRead);
            if(currentElement.equals("toyota"))
                toyota.setImage(elementValueRead);
            if(currentElement.equals("porsche"))
                porsche.setImage(elementValueRead);
            if(currentElement.equals("lincoln"))
                lincoln.setImage(elementValueRead);
            if(currentElement.equals("headlight"))
                headlight.setImage(elementValueRead);
            if(currentElement.equals("rims"))
                rims.setImage(elementValueRead);
            if(currentElement.equals("windowtint"))
                windowtint.setImage(elementValueRead);
            if(currentElement.equals("backlight"))
                backlight.setImage(elementValueRead);
            if(currentElement.equals("exhaust"))
                exhaust.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setImage(elementValueRead);          
            return;
        }
        if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("mercedes"))
                mercedes.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("bmw"))
                bmw.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("audi"))
                audi.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("jeep"))
                jeep.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("hyundai"))
                hyundai.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("toyota"))
                toyota.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("porsche"))
                porsche.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("lincoln"))
                lincoln.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("headlight"))
                headlight.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("rims"))
                rims.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("windowtint"))
                windowtint.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("backlight"))
                backlight.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("exhaust"))
                exhaust.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
                accessory.setDiscount(Double.parseDouble(elementValueRead));          
            return;
        }
        if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("mercedes"))
                mercedes.setCondition(elementValueRead);
            if(currentElement.equals("bmw"))
                bmw.setCondition(elementValueRead);
            if(currentElement.equals("audi"))
                audi.setCondition(elementValueRead);
            if(currentElement.equals("jeep"))
                jeep.setCondition(elementValueRead);
            if(currentElement.equals("hyundai"))
                hyundai.setCondition(elementValueRead);
            if(currentElement.equals("toyota"))
                toyota.setCondition(elementValueRead);
            if(currentElement.equals("porsche"))
                porsche.setCondition(elementValueRead);
            if(currentElement.equals("lincoln"))
                lincoln.setCondition(elementValueRead);
            if(currentElement.equals("headlight"))
                headlight.setCondition(elementValueRead);
            if(currentElement.equals("rims"))
                rims.setCondition(elementValueRead);
            if(currentElement.equals("windowtint"))
                windowtint.setCondition(elementValueRead);
            if(currentElement.equals("backlight"))
                backlight.setCondition(elementValueRead);
            if(currentElement.equals("exhaust"))
                exhaust.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setCondition(elementValueRead);          
            return;  
        }
        if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("mercedes"))
                mercedes.setRetailer(elementValueRead);
            if(currentElement.equals("bmw"))
                bmw.setRetailer(elementValueRead);
            if(currentElement.equals("audi"))
                audi.setRetailer(elementValueRead);
            if(currentElement.equals("jeep"))
                jeep.setRetailer(elementValueRead);
            if(currentElement.equals("hyundai"))
                hyundai.setRetailer(elementValueRead);
            if(currentElement.equals("toyota"))
                toyota.setRetailer(elementValueRead);
            if(currentElement.equals("porsche"))
                porsche.setRetailer(elementValueRead);
            if(currentElement.equals("lincoln"))
                lincoln.setRetailer(elementValueRead);
            if(currentElement.equals("headlight"))
                headlight.setRetailer(elementValueRead);
            if(currentElement.equals("rims"))
                rims.setRetailer(elementValueRead);
            if(currentElement.equals("windowtint"))
                windowtint.setRetailer(elementValueRead);
            if(currentElement.equals("backlight"))
                backlight.setRetailer(elementValueRead);
            if(currentElement.equals("exhaust"))
                exhaust.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setRetailer(elementValueRead);          
            return;
        }
        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("mercedes"))
                mercedes.setName(elementValueRead);
            if(currentElement.equals("bmw"))
                bmw.setName(elementValueRead);
            if(currentElement.equals("audi"))
                audi.setName(elementValueRead);
            if(currentElement.equals("jeep"))
                jeep.setName(elementValueRead);
            if(currentElement.equals("hyundai"))
                hyundai.setName(elementValueRead);
            if(currentElement.equals("toyota"))
                toyota.setName(elementValueRead);
            if(currentElement.equals("porsche"))
                porsche.setName(elementValueRead);
            if(currentElement.equals("lincoln"))
                lincoln.setName(elementValueRead);
            if(currentElement.equals("headlight"))
                headlight.setName(elementValueRead);
            if(currentElement.equals("rims"))
                rims.setName(elementValueRead);
            if(currentElement.equals("windowtint"))
                windowtint.setName(elementValueRead);
            if(currentElement.equals("backlight"))
                backlight.setName(elementValueRead);
            if(currentElement.equals("exhaust"))
                exhaust.setName(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setName(elementValueRead);          
            return;
        }   
        if(element.equalsIgnoreCase("price")){
            if(currentElement.equals("mercedes"))
                mercedes.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("bmw"))
                bmw.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("audi"))
                audi.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("jeep"))
                jeep.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("hyundai"))
                hyundai.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("toyota"))
                toyota.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("porsche"))
                porsche.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("lincoln"))
                lincoln.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("headlight"))
                headlight.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("rims"))
                rims.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("windowtint"))
                windowtint.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("backlight"))
                backlight.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("exhaust"))
                exhaust.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
                accessory.setPrice(Double.parseDouble(elementValueRead));          
            return;
        }
        if(element.equalsIgnoreCase("quantity")){
            if(currentElement.equals("mercedes"))
                mercedes.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("bmw"))
                bmw.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("audi"))
                audi.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("jeep"))
                jeep.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("hyundai"))
                hyundai.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("toyota"))
                toyota.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("porsche"))
                porsche.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("lincoln"))
                lincoln.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("headlight"))
                headlight.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("rims"))
                rims.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("windowtint"))
                windowtint.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("backlight"))
                backlight.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("exhaust"))
                exhaust.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
                accessory.setQuantity(Double.parseDouble(elementValueRead));          
            return;
         }
         if (element.equalsIgnoreCase("manufacturerRebate")) {
            if(currentElement.equals("mercedes"))
                mercedes.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("bmw"))
                bmw.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("audi"))
                audi.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("jeep"))
                jeep.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("hyundai"))
                hyundai.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("toyota"))
                toyota.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("porsche"))
                porsche.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("lincoln"))
                lincoln.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("headlight"))
                headlight.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("rims"))
                rims.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("windowtint"))
                windowtint.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("backlight"))
                backlight.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("exhaust"))
                exhaust.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setManufacturerRebate(elementValueRead);          
            return;
        }
    }
    //get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    /////////////////////////////////////////
    //       Kick-Start SAX in main       //
    ////////////////////////////////////////
    
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
        String TOMCAT_HOME = System.getProperty("catalina.home");   
        new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Automobile_Inventory\\ProductCatalog.xml");
    } 
}
