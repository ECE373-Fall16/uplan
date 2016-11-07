import java.util.*;
import org.apache.xmlrpc.*;

public class Client {
    
    public Client(){
        
    }
    
    public void addEvent(String name, String day, int startTime, int endTime, String location){
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:8082/RPC2"); 
            Vector params = new Vector();
            //params.addElement("ae");
            params.addElement(name);          
            params.addElement(day);         
            params.addElement(startTime); 
            params.addElement(endTime);
            params.addElement(location);         

            Vector returnValue = (Vector)server.execute("sample.addEvent", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
      }  

    }
    
    
    public void addAssignment(String name, String classname, int daysleft, int priority, int hours){
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:8082/RPC2"); 
            Vector params = new Vector();
            //params.addElement("aa");
            params.addElement(name);
            params.addElement(classname);
            params.addElement(daysleft);
            params.addElement(priority);
            params.addElement(hours);
  
            Vector returnValue = (Vector)server.execute("sample.addAssignment", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }        
    }

    
    
    /*public void editEvent(String name){
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2"); 
            Vector params = new Vector();;
            params.addElement(eventname[i]);
             Vector returnValue = (Vector)server.execute("sample.add", params);
        	    int size = ((Vector)returnValue).size();
        	    Integer intValue = (Integer)returnValue.get(0); 
        	    Double  doubleValue = (Double)returnValue.get(1);
                String  stringValue = (String)returnValue.get(2);
        
              } 
              catch (Exception exception) {
                  System.err.println("Client: " + exception);
              }
              Vector eventinfo = (Vector)server.execute("//servername.function", params);
              Iterator itr = v.iterator();
              String name = (String)returnValue.get(0);
              String day = (String)returnValue.get(1);
              String start = (String)returnValue.get(2);
              String end = (String)returnValue.get(3);
              String location = (String)returnValue.get(4);
             
                //SEND TO THE USER
              
            
        
    }
     
    public void editAssignment(String name){
        try {
                 XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2"); 
                 Vector params = new Vector();
                 char[] eventname = name.toCharArray();
                 for(int i = 0; i<eventname.length; i++){
                     params.addElement(eventname[i]);
                 }
        
              Vector returnValue = (Vector)server.execute("sample.add", params);
        	    int size = ((Vector)returnValue).size();
        	    Integer intValue = (Integer)returnValue.get(0); 
        	    Double  doubleValue = (Double)returnValue.get(1);
                String  stringValue = (String)returnValue.get(2);
        
              } catch (Exception exception) {
                 System.err.println("Client: " + exception);
              }
               
              Vector assignmentinfo = (Vector)server.execute("//servername.function", params);
              Iterator itr = v.iterator();
              String name = (String)returnValue.get(0);
              String classname = (String)returnValue.get(1);
              String days = (String)returnValue.get(2);
              String duration = (String)returnValue.get(3);
              String priority = (String)returnValue.get(4);
             
                //SEND TO THE USER
              
        
    }*/

    
    /*public void getSchedule(){
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2"); 
            Vector params = new Vector();
            char[] eventname = name.toCharArray();
            for(int i = 0; i<eventname.length; i++)
                params.addElement(eventname[i]);
        
            Vector returnValue = (Vector)server.execute("sample.add", params);
        	int size = ((Vector)returnValue).size();
        	Integer intValue = (Integer)returnValue.get(0); 
        	Double  doubleValue = (Double)returnValue.get(1);
            String  stringValue = (String)returnValue.get(2);
                
            } catch (Exception exception) {
                System.err.println("Client: " + exception);
            }
              
            Integer size = (Integer)returnValue.get(0);
            Calender calObject = new CalenderObject();
            CalenderObject[] calArray = new calArray[size];
              
              //calObject((String)returnValue.get(0),(String)returnValue.get(1),(String)returnValue.get(2));
              
             String name = (String)returnValue.get(0);
              String time = (String)returnValue.get(1);
              String location = (String)returnValue.get(2);
                       
              String[] object = new String[]; 
    
              
                //SEND TO THE USER 
    }*/
   
   public void createAccount(String name, String username, String email, String password, int bedtime){
       try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:8082/RPC2"); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
            params.addElement(email);
            params.addElement(password);
            params.addElement(bedtime);
  
            Vector returnValue = (Vector)server.execute("sample.createAccount", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }        
   }
}