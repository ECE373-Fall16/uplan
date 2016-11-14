import java.util.*;
import org.apache.xmlrpc.*;

public class Client {
    
    private String username;
    private static String SERVER_ADDR = "http://localhost:8085/RPC2";
    
    public Client(){
        ;
    }
    public Client(String user){
        username = user;
    }
    
    public String getUserName(){
        return username;
    }
    
    public void login(String user){
        username = user;
        System.out.println("You are logged in as: " + username);
    }
    
    public void logout(){
        username = null;
    }
    
    public void display(){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR);
            Vector params = new Vector();
            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.display", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
      } 
    }
    
    public void addEvent(String name, String day, int startTime, int endTime, String location){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name); 
            params.addElement(username);
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
    
    
    public void addAssignment(String name, String classname, String dueDate, int priority, int hours){
        try {
            
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
            params.addElement(classname);
            params.addElement(dueDate);
            params.addElement(priority);
            params.addElement(hours);
  
            Vector returnValue = (Vector)server.execute("sample.addAssignment", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }        
    }
    
    public void deleteEvent(String name){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
  
            Vector returnValue = (Vector)server.execute("sample.deleteEvent", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          } 
    }
    
    public void deleteAssignment(String name){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
  
            Vector returnValue = (Vector)server.execute("sample.deleteAssignment", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          } 
    }
    
    public void deleteAccount(String user){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(user);
  
            Vector returnValue = (Vector)server.execute("sample.deleteAccount", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }
          if(user.equals(username)){
              username = null;
          }
    }
    
    public void updateAssignment(String name, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(newName);
            params.addElement(username);
            /*
            params.addElement(className);
            params.addElement(dueDate);
            String comp = Integer.toString(toCompletion);
            params.addElement(comp);
            String pri = Integer.toString(priority);
            params.addElement(pri);
            //*/
            Vector returnValue = (Vector)server.execute("sample.updateAssignment", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }
    }
    
    public void printLists(){
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(username);
            Vector returnValue = (Vector)server.execute("sample.scheduleAlgo", params);
        } catch (Exception e){
            System.err.println("Client: " + e);
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
                 XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
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
              
        
    }

    
    /*public void getSchedule(){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
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
   
   
   
   public void createAccount(String user, String name, String email, String password, int bedtime){
       //username = user;
       try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(user);
            params.addElement(name);
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