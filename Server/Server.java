import org.apache.xmlrpc.*;
import java.util.*;

public class Server {
    LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();
    LinkedList<Event> eventList = new LinkedList<Event>();
    int alCounter = 0;
    int elCounter = 0;
    DataBase data = new DataBase();
    

/*
   public Vector add(String identifier, String nameA, String nameC, int date, int comp, int pri){  //Assignment
      System.out.println("calling add Assignment");
      if(identifier.equals("aa")){
          addAssignment(nameA, nameC, date, comp, pri);
      }
      Vector returnValue = new Vector();
      
      
      //returnValue.addElement(new String(name));
      //returnValue.addElement(new Double(y));
      //returnValue.addElement(new String("hello world"));
      return returnValue;
   }
   
   public Vector add(String identifier, String nameE, String days, int time, String location){  //event
       System.out.println("Calling add Event");
       if(identifier.equals("ae")){
           addEvent(nameE, days, time, location);
       } 
       Vector returnValue = new Vector();
       return returnValue;
   }
*/
   
   public Vector addAssignment(String name, String username, String className, String date, int comp, int pri){
       //Assignment a = new Assignment(name, className, date, comp, pri);
       //System.out.println("Assignment name is: " + a.getAssignName());
       //send this to the database
       
       //add to list in server(volatile data)
       //assignmentList.add(a);
       //data.createAssignment(name, username, className, date, comp, pri);
       //alCounter++;
       //System.out.println(alCounter + " Assignments\n" + "Contents of assignment list are now: " + assignmentList);
       for(Assignment element : assignmentList){
           System.out.println(element.toString());
       }
       System.out.println();
       
       //return as vector
       Vector returnValue = new Vector();
       returnValue.add(name);
       returnValue.add(className);
       returnValue.add(date);
       returnValue.add(comp);
       returnValue.add(pri);
       return returnValue;
   }
   
   public Vector addEvent(String name, String username, String days, int startTime, int endTime, String loc){
       Event e = new Event(name, days, startTime, endTime, loc);
       System.out.println(e.getEventName());
       //send to database
       
       //add to list in sever(volatile data)
       eventList.add(e);
       //data.createEvent(name, username, className, date, comp, pri);
       elCounter++;
       System.out.println(elCounter + " Events\n" + "Contents of event list are now: " + eventList);
       for(Event element : eventList){
           System.out.println(element.toString());
       }
       System.out.println();
       
       //return vector
       Vector returnValue = new Vector();
       returnValue.add(name);
       returnValue.add(days);
       returnValue.add(startTime);
       returnValue.add(endTime);
       returnValue.add(loc);
       return returnValue;
   }
   
   public Vector createAccount(String name, String username, String email, String password, int bedtime){
       try{
            data.createUser("David");
       } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
       return new Vector();
   }

   
    

   public static void main (String [] args){
   
      try {
         WebServer server = new WebServer(8085);
         server.addHandler("sample", new Server());
         server.start();
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}