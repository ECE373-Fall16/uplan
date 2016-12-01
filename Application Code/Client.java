//https://github.com/ECE373-Fall16/uplan.git

import java.util.*;
import org.apache.xmlrpc.*;

public class Client {
    
    private String username;
    private static String SERVER_ADDR = "http://localhost:8082/RPC2";
    
    
    public Client(){
        ;
    }
    
    
    public Client(String user){
        username = user;
    }
    
    
    public String getUserName(){
        return username;
    }
    
    
    public int login(String user, String pass){
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(user);
            params.addElement(password);
            Vector returnValue = (Vector)server.execute("sample.validateUser", params);
            if(returnValue.get(0) == 1){
                username = user;
                System.out.println("You are logged in as: " + username);
                return 1;
            }
            else{
                System.out.println("Invalid Username or Password");
                return 0;
            }
        } catch (Exception e){
            System.err.println("Login: " + exception);
        }
        return 0;
    }
    
    public void logout(){
        username = null;
    }
    
    
    public LinkedList<CalendarEvent> display(){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR);
            Vector params = new Vector();
            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.display", params);
            LinkedList<CalendarEvent> calList = vectorToCalList(returnValue);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
      } 
      return calList;
    }
    
    
    public void addEvent(String name, String day, String startTime, String endTime, String location){
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
    
    
    public void addAssignment(String name, String classname, String dueDate, String priority, String hours){
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
    
    
    public void updateAssignment(String assignmentName, String type, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(assignmentName);
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);
            Vector returnValue = (Vector)server.execute("sample.updateAssignment", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }
    }
    
    
    public void updateEvent(String eventName, String type, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(eventName);
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);
            Vector returnValue = (Vector)server.execute("sample.updateEvent", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }
    }
    
    
    public void updateProfile(String type, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);
            Vector returnValue = (Vector)server.execute("sample.updateProfile", params);
          } 
          catch (Exception exception) {
             System.err.println("Client: " + exception);
          }
    }
    
    
    public LinkedList<CalendarEvent> schedule(){         //Creates schedule and returns list
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(username);
            Vector returnValue = (Vector)server.execute("sample.scheduleAlgo", params);
            LinkedList<CalendarEvent> calList = vectorToCalList(returnValue);
        } catch (Exception e){
            System.err.println("Client: " + e);
        }
    }
    
    
    private LinkedList<CalendarEvent> vectorToCalList(Vector vectorList){       //going to be used to parse vector lists into Calendar object list or 2d array
        Iterator iter = vectorList.iterator();
        int calCount = 1;       //number of calendar objects
        CalendarEvent temp;
        LinkedList<CalendarEvent> calList = new LinkedList<CalendarEvent>;
        String name;
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        df.setTimeZone(TimeZone.getTimeZone("EST"));
        Date start;
        Date end;
        String loc;
        while(iter.hasNext()){
            name = iter.next();
            start = df.parse(iter.next());
            end = df.parse(iter.next());
            loc = iter.next();
            temp = new CalendarEvent(name, start, end, loc);
            calList.add(temp);
            calCount++;
        }
        return calList;
    }
    
   
    public void createAccount(String user, String name, String email, String password, int bedtime){
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