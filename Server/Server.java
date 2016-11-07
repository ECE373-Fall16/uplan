import org.apache.xmlrpc.*;
import java.util.*;

public class Server {
    LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();
    LinkedList<Event> eventList = new LinkedList<Event>();
    int alCounter = 0;
    int elCounter = 0;

Assingment a;
Event e;
int h;
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
   
   public void addAssignment(String nameA, String nameC, int date, int comp, int pri){
       Assignment a = new Assignment(nameA, nameC, date, comp, pri);
       System.out.println("Assignment name is: " + a.getAssignName());
       //send this to the database
       
       //add to list in server(volatile data)
       assignmentList.add(a);
       alCounter++;
       System.out.println(alCounter + " Assignments\n" + "Contents of assignment list are now: " + assignmentList);
       for(Assignment element : assignmentList){
           System.out.println(element.toString());
       }
       System.out.println();
   }
   
   public void addEvent(String name, String days, int time, String loc){
       Event e = new Event(name, days, time, loc);
       System.out.println(e.getEventName());
       //send to database
       
       //add to list in sever(volatile data)
       eventList.add(e);
       elCounter++;
       System.out.println(elCounter + " Events\n" + "Contents of event list are now: " + eventList);
       for(Event element : eventList){
           System.out.println(element.toString());
       }
       System.out.println();
   }
   
   

   public static void main (String [] args){
   
      try {
         WebServer server = new WebServer(8080);
         server.addHandler("sample", new Server());
         server.start();
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}