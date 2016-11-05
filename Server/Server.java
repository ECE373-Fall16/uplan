import org.apache.xmlrpc.*;
import java.util.*;

public class Server { 

   public Vector add(String identifier, String nameA, String nameC, int date, int comp, int pri){  //Assignment
      if(identifier.equals("aa")){
          addAssignment(nameA, nameC, date, comp, pri);
      }
      Vector returnValue = new Vector();
      //returnValue.addElement(new String(name));
      //returnValue.addElement(new Double(y));
      //returnValue.addElement(new String("hello world"));
      System.out.println("calling add Assignment");
      return returnValue;
   }
   
   public Vector add(String identifier, String nameE, String days, int time, String location){  //event
       if(identifier.equals("ae")){
           addEvent(nameE, days, time, location);
       } 
       Vector returnValue = new Vector();
       return returnValue;
   }
   
   public void addAssignment(String nameA, String nameC, int date, int comp, int pri){
       Assignment a = new Assignment(nameA, nameC, date, comp, pri);
       System.out.println(a.getAssignName());
       //send this to the database
   }
   
   public void addEvent(String name, String days, int time, String loc){
       Event e = new Event(name, days, time, loc);
       System.out.println(e.getEventName());
       //send to database
   }

   public static void main (String [] args){
   
      try {
         WebServer server = new WebServer(8082);
         server.addHandler("sample", new Server());
         server.start();
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}