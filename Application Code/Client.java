import java.util.*;
import java.text.*;
import org.apache.xmlrpc.*;

public class Client {
    
    private String username;
    private static String SERVER_ADDR = "http://localhost:8095/RPC2";
    
    
    public Client(){
        ;
    }
    
    
    public Client(String user){
        username = user;
    }
    
    
    public String getUserName(){
        return username;
    }
    
    
    public boolean login(String user, String pass){
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            
            params.addElement(user);
            params.addElement(pass);
            
            Vector returnValue = (Vector)server.execute("sample.validateUser", params);
            
            if(Integer.parseInt(returnValue.get(0).toString()) == 1){
                username = user;
                System.out.println("You are logged in as: " + username);
                return true;
            }
            else{
                System.out.println("Invalid Username or Password");
                return false;
            }
        } catch (Exception e){
            System.err.println("ClientLogin: " + e);
        }
        return false;
    }
    

    public void logout(){
        username = null;
    }
    

    public int createAccount(String user, String name, String email, String password, String bedtime){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();

            params.addElement(user);
            params.addElement(name);
            params.addElement(email);
            params.addElement(password);
            params.addElement(bedtime);
  
            Vector returnValue = (Vector)server.execute("sample.createAccount", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } 
        catch (Exception exception) {
            System.err.println("ClientCreateAccount " + exception);
        } 

        return 0;       
    }
    
    
    public LinkedList<CalendarEvent> display(){
        LinkedList<CalendarEvent> calList = null;
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR);
            Vector params = new Vector();
            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.display", params);
            calList = vectorToCalList(returnValue);
        } catch (Exception exception) {
            System.err.println("ClientDisplay " + exception);
        } 
        return calList;
    }
    
    
    public int addEvent(String name, String day, String startTime, String endTime, String location){
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

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;

        } catch (Exception exception) {
            System.err.println("ClientAddEvent: " + exception);
        } 

        return 0; 
    }
    
    
    public int addAssignment(String name, String classname, String dueDate, String hours, String priority, String appPriority){
        try {
            
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
            params.addElement(classname);
            params.addElement(dueDate);
            params.addElement(hours);
            params.addElement(priority);
            params.addElement(appPriority);
  
            Vector returnValue = (Vector)server.execute("sample.addAssignment", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;

        } catch (Exception exception) {
            System.err.println("ClientAddEvent: " + exception);
        }

        return 0;     
    }
    
    
    public int deleteEvent(String name){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
  
            Vector returnValue = (Vector)server.execute("sample.deleteEvent", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;

        } catch (Exception exception) {
            System.err.println("ClientDeleteEvent: " + exception);
        } 

        return 0;
    }
    
    
    public int deleteAssignment(String name){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
  
            Vector returnValue = (Vector)server.execute("sample.deleteAssignment", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } catch (Exception exception) {
            System.err.println("ClientDeleteAssignment: " + exception);
        } 

        return 0;
    }
    
    
    public int deleteAccount(String user){
        int valid = 1;
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(user);
  
            Vector returnValue = (Vector)server.execute("sample.deleteAccount", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 0)
                valid = 0;
        } catch (Exception exception) {
            System.err.println("ClientDeleteAccount: " + exception);
        }
        if(user.equals(username)){
            username = null;
        }

        return valid;
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
        } catch (Exception exception) {
            System.err.println("ClientUpdateAssignment: " + exception);
        }
    }
    
    
    public int updateEvent(String eventName, String type, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(eventName);
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);
            
            Vector returnValue = (Vector)server.execute("sample.updateEvent", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } catch (Exception exception) {
            System.err.println("ClientUpdateEvent: " + exception);
        }

        return 0;
    }
    
    
    public int updateProfile(String type, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);
            
            Vector returnValue = (Vector)server.execute("sample.updateProfile", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } catch (Exception exception) {
            System.err.println("ClientUpdateProfile: " + exception);
        }

        return 0;
    }
    
    
    public LinkedList<CalendarEvent> schedule(){         //Creates schedule and returns list
        LinkedList<CalendarEvent> calList = null;
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(username);
            
            Vector returnValue = (Vector)server.execute("sample.scheduleAlgo", params);
            calList = vectorToCalList(returnValue);

            ListIterator calIter = calList.listIterator();
            while(calIter.hasNext()){
                System.out.println(calList.get(calIter.nextIndex()));
                calIter.next();
            }
            
        } catch (Exception e){
            System.err.println("ClientSchedule: " + e);
        }

        return calList;
    }
    
    
    private LinkedList<CalendarEvent> vectorToCalList(Vector vectorList) throws ParseException{       //going to be used to parse vector lists into Calendar object list or 2d array
        LinkedList<CalendarEvent> calList = new LinkedList<CalendarEvent>();
        Iterator iter = vectorList.iterator();
        int calCount = 1;       //number of calendar objects
        CalendarEvent temp;

        try{
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
            df.setTimeZone(TimeZone.getTimeZone("EST"));
            String name;
            Date start;
            Date end;
            String loc;
            int counter = 0;
            while(iter.hasNext()){
                name = iter.next().toString();
                start = df.parse(iter.next().toString());
                end = df.parse(iter.next().toString());
                loc = iter.next().toString();
                temp = new CalendarEvent(name, start, end, loc);
                calList.add(temp);
                calCount++;
            }

        } catch (Exception e){
            System.err.println("ClientVectorToCalList: " + e);
        }

        return calList;
    }
    
}