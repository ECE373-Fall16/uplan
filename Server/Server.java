import org.apache.xmlrpc.*;
import java.util.*;
import java.sql.*;

public class Server {
    //private LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();
    //private LinkedList<Event> eventList = new LinkedList<Event>();
    private int alCounter = 0;
    private int elCounter = 0;
    private DataBase data = new DataBase();
    private LinkedList<Calendar> calendarList;
    private static int PORT = 8085;


   public Vector display(String user){
       try{
           //data.display(user);
           System.out.println("\nPrinting list...");
           for(int k = 0; k < calendarList.size(); k++){
               System.out.println(calendarList.get(k).toString());
           }
           return new Vector();
       } catch (Exception e){
           System.err.println( "ServerDisplay: " + e.getClass().getName() + ": " + e.getMessage() );
       }
        return new Vector();
   }
   
   public Vector addAssignment(String name, String username, String className, String date, int comp, int pri){
       //Assignment a = new Assignment(name, className, date, comp, pri);
       //System.out.println("Assignment name is: " + a.getAssignName());
       //send this to the database
       
       //add to list in server(volatile data)
       //assignmentList.add(a);
       try{
       data.createAssignment(name, username, className, date, comp, pri);
   }catch( Exception e ){
       System.err.println( "ServerAddAssign: " + e.getClass().getName() + ": " + e.getMessage() );
   }


       //alCounter++;
       //System.out.println(alCounter + " Assignments\n" + "Contents of assignment list are now: " + assignmentList);
       /*for(Assignment element : assignmentList){
           System.out.println(element.toString());
       }
       System.out.println();*/
       
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
       //Event e = new Event(name, days, startTime, endTime, loc);
       //System.out.println(e.getEventName());
       //send to database
       try{
            data.createEvent(name,username,days,startTime,endTime,loc);
       }catch( Exception e ){
            System.err.println( "ServerAddEvent:" + e.getClass().getName() + ": " + e.getMessage() );
        }
 
        //return vector
       Vector returnValue = new Vector();
       returnValue.add(name);
       returnValue.add(days);
       returnValue.add(startTime);
       returnValue.add(endTime);
       returnValue.add(loc);
       return returnValue;
   }
   
   public Vector createAccount(String username, String name, String email, String password, int bedtime){
       try{
            data.createUser(username, name, email, password, bedtime);
       } catch (Exception e){
            System.err.println( "ServerCreateAccount:" + e.getClass().getName() + ": " + e.getMessage() );
        }
       return new Vector();
   }

    public Vector deleteEvent(String name, String username){
        try{
            data.removeEvent(name, username);
        } catch (Exception e){
            System.err.println( "DeleteEvent:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return new Vector();
    }
    
    public Vector deleteAssignment(String name, String username){
        try{
            data.removeAssignment(name, username);
        } catch (Exception e){
            System.err.println( "DeleteEvent:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return new Vector();
    }
    
    public Vector deleteAccount(String username){
        try{
            data.removeProfile(username);
        } catch (Exception e){
            System.err.println( "DeleteEvent:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return new Vector();
    }
    
    
    public Vector updateAssignment(String name, String newName, String user){
        try{
            data.updateAssignment(name,newName,user);
        } catch (Exception e){
            System.err.println( "UpdateAssignment:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return new Vector();
    }
    
    
    public Vector scheduleAlgo(String user) throws SQLException{
        try{
            LinkedList<Event> eventList = new LinkedList<Event>();
            LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();
            LinkedList<Calendar> calList = new LinkedList<Calendar>();
            eventList = data.getEventList(user);
            assignmentList = data.getAssignmentList(user);
            
            int eventListSize = eventList.size();
            int assignmentListSize = assignmentList.size();
            int calendarListSize = 0;
            
            System.out.println("eventList has " + eventList.size() + " events");
            System.out.println("assignmentList has " + assignmentList.size() + " assignments");
            
            //put eventlist into calendar list sorted
            
            //for assignment sort end of classes for day
            int suEnd = 1600;
            int moEnd = 1600;
            int tuEnd = 1600;
            int weEnd = 1600;
            int thEnd = 1600;
            int frEnd = 1600;
            int saEnd = 1600;
            
            int eventIndex=0;
            int assignmentIndex=0;
            Event tempEvent;
            Assignment tempAssignment;
            Calendar iterCal;
            Calendar tempCal;
            boolean addedEvent;
            boolean addedAssignment;
            int calIndex;
            while(eventIndex < eventListSize){              //runs through eventList  
                tempEvent = eventList.get(eventIndex);
                tempCal = eventToCal(tempEvent);
                
                //for sorting compare
                String eventDays = tempEvent.getDays();
                String eventStart = tempEvent.getStart();
                
                calIndex = 0;
                addedEvent = false;
                if(calList.size() == 0 && eventList.size() != 0){       //first cal object
                    System.out.println("Initial add: " + tempCal.getName());
                    calList.add(tempCal);
                    addedEvent = true;
                }
                
                while(calIndex < eventList.size() && !addedEvent){             //runs through calList for each event in eventList until event added
                    
                    iterCal = calList.get(calIndex);        //used to get values of each calendar object
                    
                    String calDays = iterCal.getDay();
                    String calStart = iterCal.getStartTime();
                    
                    int eventTime = sortInt(eventDays,eventStart);
                    int calTime = sortInt(calDays,calStart);
                    if(calTime >= eventTime && !addedEvent){              //add calendar event just before calTime passes eventTime
                        if(calIndex == 0){
                            System.out.println("Adding to front: " + tempCal.getName());
                          calList.addFirst(tempCal);
                        }
                        else{
                            System.out.println("Adding to index before " + calIndex + ": " + tempCal.getName());
                            calList.add(calIndex-1, tempCal);
                        }

                        addedEvent = true;
                    }
                    if(calList.get(calIndex) == calList.getLast()){         //end of calList, add object at end of list
                        calList.addLast(tempCal);
                        addedEvent = true;
                    }
                    
                    calIndex++;
                }
                eventIndex++; 
            }
            
            //Add the assignments here//
            int dayCnt = 0;
            int timeCnt = 1600;
            int index;
            boolean fullSchedule = false;
            while(assignmentIndex < assignmentListSize && !fullSchedule){                                              //runs through assignmentList  
                tempAssignment = assignmentList.get(assignmentIndex);
                //tempCal = assignmentToCal(tempAssignment);
                
                if((assignmentIndex+1)%3 == 0){                   //3 assignments per day
                    dayCnt++;
                    timeCnt = 1600;
                }
                switch (dayCnt){
                    case 0: tempCal = assignmentToCal(tempAssignment,timeCnt,"Mo");
                        index = findEndOfDay(calList, "Mo");
                        calList.add(index,tempCal);
                        break;
                    case 1: tempCal = assignmentToCal(tempAssignment,timeCnt,"Tu");
                        index = findEndOfDay(calList, "Tu");
                        calList.add(index,tempCal);
                        break;
                    case 2: tempCal = assignmentToCal(tempAssignment,timeCnt,"We");
                        index = findEndOfDay(calList, "We");
                        calList.add(index,tempCal);
                        break;
                    case 3: tempCal = assignmentToCal(tempAssignment,timeCnt,"Th");
                        index = findEndOfDay(calList, "Th");
                        calList.add(index,tempCal);
                        break;
                    case 4: tempCal = assignmentToCal(tempAssignment,timeCnt,"Fr");
                        index = findEndOfDay(calList, "Fr");
                        calList.add(index,tempCal);
                        break;
                    default: fullSchedule = true;
                        break;
                }
                
                timeCnt = timeCnt + 200;
                assignmentIndex++;
            }
            
            calendarList = calList;

            
        } catch (Exception e){
            System.err.println( "schedule algo:" + e.getClass().getName() + ": " + e.getMessage() );
        }
 
        return new Vector();
        
    }
    
    private int sortInt(String day, String start){
        int dayVal;
        if(day.equals("Su")){
            dayVal = 0;
        }
        else if(day.equals("Mo")){
            dayVal = 10000;
        }
        else if(day.equals("Tu")){
            dayVal = 20000;
        }
        else if(day.equals("We")){
            dayVal = 30000;
        }
        else if(day.equals("Th")){
            dayVal = 40000;
        }
        else if(day.equals("Fr")){
            dayVal = 50000;
        }
        else if(day.equals("Sa")){
            dayVal = 60000;
        }
        else{
            dayVal = 0;//default Sunday
        }
        int startVal = Integer.parseInt(start);
        return startVal+dayVal;
    }
    
    public Calendar eventToCal(Event eve){
        String name = eve.getEventName();
        String start = eve.getStart();
        String end = eve.getEnd();
        String day = eve.getDays();
        String loc = eve.getLocation();
        Calendar c = new Calendar(name, start, end, day, loc);
        
        return c;
        
    }
    
    public Calendar assignmentToCal(Assignment ass, int startTime, String dayOW){
        String name = ass.getAssignName();
        String start = Integer.toString(startTime);
        String end = Integer.toString(startTime+100);
        String day = dayOW;
        String loc = "ASSIGNMENT";
        Calendar c = new Calendar(name,start,end,day,loc);
        
        return c;
    }
    
    public int findEndOfDay(LinkedList<Calendar> calList, String day){
        int calIndex = 0;
        int returnInt = 0;
        boolean onDay = false;
        boolean found = false;
        Calendar cal;
        while(calIndex < calList.size() && !found){
            cal = calList.get(calIndex);
            if(cal.getDay().equals(day)){
                onDay = true;
            }
            if(onDay && !(cal.getDay().equals(day))){
                returnInt = calIndex;
            }
            
            calIndex++;
        }
        return returnInt;
    }
   
    

   public static void main (String [] args){
   
      try {
         WebServer server = new WebServer(PORT);
         server.addHandler("sample", new Server());
         server.start();
      } catch (Exception exception){
         System.err.println("JavaServer: " + exception);
      }
   }
}