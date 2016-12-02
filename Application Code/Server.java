import org.apache.xmlrpc.*;
import java.util.*;
import java.sql.*;
import java.text.*;

public class Server {
    //private LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();
    //private LinkedList<Event> eventList = new LinkedList<Event>();
    private int alCounter = 0;
    private int elCounter = 0;
    private DataBase data = new DataBase();
    private LinkedList<CalendarEvent> calendarList;
    private static int PORT = 8082;
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);


   public Vector display(String user){
       try{
           //data.display(user);
           Vector values = new Vector();
           System.out.println("\nPrinting list...");
           for(int k = 0; k < calendarList.size(); k++){
               System.out.println("[" + k + "]" + calendarList.get(k).toString());
               values.add(calendarList.get(k).getName());
               values.add(df.format(calendarList.get(k).getStartTime()));
               values.add(df.format(calendarList.get(k).getEndTime()));
               values.add(calendarList.get(k).getLocation());
           }
           System.out.println();
           return values;
       } catch (Exception e){
           System.err.println( "ServerDisplay: " + e.getClass().getName() + ": " + e.getMessage() );
       }
        return new Vector();
   }
   
   public Vector validateUser(String username, String password){
       Vector returnValue = new Vector();
       try{
           int validation = data.valUser(username, password);
           returnValue.add(validation);
       } catch (Exception e){
           System.err.println( "Validate: " + e.getClass().getName() + ": " + e.getMessage() );
       }
       return returnValue;
   }
   
   
   public Vector addAssignment(String name, String username, String className, String date, String comp, String pri){
       try{
           data.createAssignment(name, username, className, date, comp, pri);
       }catch( Exception e ){
           System.err.println( "ServerAddAssign: " + e.getClass().getName() + ": " + e.getMessage() );
       }
      
       //return as vector
       Vector returnValue = new Vector();
       returnValue.add(name);
       returnValue.add(className);
       returnValue.add(date);
       returnValue.add(comp);
       returnValue.add(pri);
       return returnValue;
   }
   
   
   public Vector addEvent(String name, String username, String days, String startTime, String endTime, String loc){
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
    
    
    public Vector updateAssignment(String assignmentName, String type, String newName, String user){
        try{
            data.updateAssignment(assignmentName, type, newName, user);
        } catch (Exception e){
            System.err.println( "UpdateAssignment:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return new Vector();
    }
    
    
    public Vector updateEvent(String eventName, String type, String newName, String user){
        try{
            data.updateEvent(eventName, type, newName, user);
        } catch (Exception e){
            System.err.println( "UpdateEvent:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return new Vector();
    }
    
    
    public Vector updateProfile(String type, String newName, String user){
        try{
            data.updateProfile(type, newName, user);
        } catch (Exception e){
            System.err.println( "UpdateProfile:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return new Vector();
    }
    
    
    /*
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
                
                char[] listOfDays = tempEvent.getDays().toCharArray();
                int dayRepeats = listOfDays.length/2;             //number of times event is repeated
                //System.out.println("number of days: " + dayRepeats);
                for(int i = 0; i < dayRepeats; i++){                    //used to make multiple objects for repeat events
                    String thisDay = "" + listOfDays[2*i] + listOfDays[2*i+1];
                    tempCal = eventToCal(tempEvent);
                    tempCal.setDay(thisDay);
                
                    System.out.println(tempCal.toString());
                    
                    calIndex = 0;
                    addedEvent = false;
                    if(calList.size() == 0 && eventList.size() != 0){       //first cal object
                        //System.out.println("Initial add: " + tempCal.getName() + " " + tempCal.getDay() + " " + tempCal.getStartTime());
                        calList.add(tempCal);
                        addedEvent = true;
                    }
                    
                    while(calIndex < calList.size() && !addedEvent){             //runs through calList for each event in eventList until event added
                        
                        iterCal = calList.get(calIndex);        //runs through existing calendar objects in list
                        String calIterDays = iterCal.getDay();
                        String calIterStart = iterCal.getStartTime();
                        String calDays = tempCal.getDay();
                        String calStart = tempCal.getStartTime();

                        int calTime = sortInt(calDays,calStart);
                        int calIterTime = sortInt(calIterDays,calIterStart);
                        //System.out.println("Index: " + calIndex + " calTime: " + calTime + " calIterTime " + calIterTime);
                        if(calIndex == 0){
                            if(calTime < calIterTime){
                                //System.out.println("Adding to front: " + tempCal.getName() + " " + tempCal.getDay() + " " + tempCal.getStartTime());
                                calList.addFirst(tempCal);
                                addedEvent = true;
                            }
                            
                        }
                        else if(calTime < calIterTime && !addedEvent){              //add calendar event just before calIter passes tempCal
                            //System.out.println("Adding before " + iterCal.getName() + ": " + tempCal.getName() + tempCal.getDay() + tempCal.getStartTime());
                            calList.add(calIndex, tempCal);
                            addedEvent = true;
                        }
                        if(calList.get(calIndex) == calList.getLast()){         //end of calList, add object at end of list
                            //System.out.println("Adding to end: " + tempCal.getName() + tempCal.getDay() + tempCal.getStartTime());
                            calList.addLast(tempCal);
                            addedEvent = true;
                        }

                        calIndex++;
                    }
                    if(addedEvent){
                        calendarList = calList;
                        //System.out.println("added");
                        display(user);
                    }
                    System.out.println();       //line between debug prints for adding events to calendarlist
                }

                eventIndex++; 
            }
            //displays just events in calendar list
            //calendarList = calList;
            //display(user);
            
            //Add the assignments here//
            //true Scheduling Algorithm Starts Here//
            int dayCnt = 0;
            int timeCnt = 1600;
            int index;
            boolean fullSchedule = false;
            while(assignmentIndex < assignmentListSize && !fullSchedule){                                              //runs through assignmentList  
                tempAssignment = assignmentList.get(assignmentIndex);
                
                
                if((assignmentIndex+1)%3 == 0){                   //3 assignments per day
                    dayCnt++;
                    timeCnt = 1600;
                }
                switch (dayCnt){
                    case 0: tempCal = assignmentToCal(tempAssignment,timeCnt,"Mo");
                        index = findEndOfDay(calList, "Mo");
                        //System.out.println("Adding Assignment to Monday: " + tempCal.getName() + " " + tempCal.getDay() + " " + tempCal.getStartTime());
                        calList.add(index,tempCal);
                        break;
                    case 1: tempCal = assignmentToCal(tempAssignment,timeCnt,"Tu");
                        index = findEndOfDay(calList, "Tu");
                        //System.out.println("Adding Assignment to Tuesday: " + tempCal.getName() + " " + tempCal.getDay() + " " + tempCal.getStartTime());
                        calList.add(index,tempCal);
                        break;
                    case 2: tempCal = assignmentToCal(tempAssignment,timeCnt,"We");
                        index = findEndOfDay(calList, "We");
                        //System.out.println("Adding Assignment to Wednesday: " + tempCal.getName() + " " + tempCal.getDay() + " " + tempCal.getStartTime());
                        calList.add(index,tempCal);
                        break;
                    case 3: tempCal = assignmentToCal(tempAssignment,timeCnt,"Th");
                        index = findEndOfDay(calList, "Th");
                        //System.out.println("Adding Assignment to Thursday: " + tempCal.getName() + " " + tempCal.getDay() + " " + tempCal.getStartTime());
                        calList.add(index,tempCal);
                        break;
                    case 4: tempCal = assignmentToCal(tempAssignment,timeCnt,"Fr");
                        index = findEndOfDay(calList, "Fr");
                        //System.out.println("Adding Assignment to Friday: " + tempCal.getName() + " " + tempCal.getDay() + " " + tempCal.getStartTime());
                        calList.add(index,tempCal);
                        break;
                    default: fullSchedule = true;
                        break;
                }
                
                timeCnt = timeCnt + 200;
                assignmentIndex++;
            }
            
            //True Scheduling Algorithm Stops Here//
            
            calendarList = calList;
            display(user);

            
        } catch (Exception e){
            System.err.println( "schedule algo:" + e.getClass().getName() + ": " + e.getMessage() );
        }
 
        return new Vector();
        
    }
    */
    /*
    private int sortInt(String day, String start){
        int dayVal;
        if(day.equals("Su")){
            dayVal = 0;
        }
        else if(day.equals("Mo")){
            dayVal = 1;
        }
        else if(day.equals("Tu")){
            dayVal = 2;
        }
        else if(day.equals("We")){
            dayVal = 3;
        }
        else if(day.equals("Th")){
            dayVal = 4;
        }
        else if(day.equals("Fr")){
            dayVal = 5;
        }
        else if(day.equals("Sa")){
            dayVal = 6;
        }
        else{
            dayVal = 0;//default Sunday
        }
        int startVal = Integer.parseInt(start);
        return startVal+dayVal;
    }
    */
   
   public void scheduleAlgo(String username) throws SQLException{
        try{
            LinkedList<Event> tempEventList = new LinkedList<Event>();
            tempEventList = data.getEventList(username);
            LinkedList<Event> eventList = new LinkedList<Event>();
            ListIterator iter1 = tempEventList.listIterator();
            int index1;
            Event temp;
            while(iter1.hasNext()){
                index1 = iter1.nextIndex();
                temp = tempEventList.get(index1);
                if(!temp.getDays().equals("")){
                    LinkedList<Event> splitEvents = splitEvent(temp);
                    ListIterator splitIter = splitEvents.listIterator();
                    int index2;
                    while(splitIter.hasNext()){
                        index2 = splitIter.nextIndex();
                        eventList.addLast(tempEventList.get(index2));
                    }

                }
                else
                    eventList.add(temp);
                iter1.next();
            }
            
            ListIterator iter2 = eventList.listIterator();
            int index3;
            while(iter2.hasNext()){
                index3 = iter2.nextIndex();
                CalendarEvent calTemp = eventToCal(tempEventList.get(index3));
                calendarList.add(calTemp);
            }
        
        } catch (Exception e){
            System.err.println( "schedule algo:" + e.getClass().getName() + ": " + e.getMessage() );
        }    
    }
    
    public LinkedList<Event> splitEvent(Event eve){
        LinkedList<Event> sepEvents = new LinkedList<Event>();
        String repeatDays = eve.getDays();
        char[] days = repeatDays.toCharArray();
        char tempDays[] = new char[2];
        int size = days.length;
                
        int counter = 0;
        int tempIndex = 0;
        while (counter < size){
            tempDays[0] = days[counter++];
            tempDays[1] = days[counter++];
             
            String dayOfWeek = new String(tempDays);
            if(dayOfWeek.equals("Su"))
                dayOfWeek = "Sunday";
            else if(dayOfWeek.equals("Mo"))
                dayOfWeek = "Monday";
            else if(dayOfWeek.equals("Tu"))
                dayOfWeek = "Tuesday";
            else if(dayOfWeek.equals("We"))
                dayOfWeek = "Wednesday";
            else if(dayOfWeek.equals("Th"))
                dayOfWeek = "Thursday";
            else if(dayOfWeek.equals("Fr"))
                dayOfWeek = "Friday";
            else if(dayOfWeek.equals("Sa"))
                dayOfWeek = "Saturday";
            
            //Event splitEvent = new Event(eve.getName(), "", )
        
        }
        return sepEvents;
    }
    
    
    public CalendarEvent eventToCal(Event eve){
        String name = eve.getEventName();
        java.util.Date start = eve.getStart();
        java.util.Date end = eve.getEnd();
        String loc = eve.getLocation();
        CalendarEvent c = new CalendarEvent(name, start, end, loc);
        
        return c; 
    }
    
    
    public CalendarEvent assignmentToCal(Assignment ass, String startTime, String endTime) throws ParseException{
        CalendarEvent c = null;
        try{
            String name = ass.getAssignName();
            java.util.Date start = df.parse(startTime);
            java.util.Date end = df.parse(endTime);
            String loc = "ASSIGNMENT";
            c = new CalendarEvent(name,start,end,loc);
        } catch (Exception e){
            System.err.println( "assigntocal:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        return c;
    }
    
    /*
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
                found = true;
            }
            
            calIndex++;
        }
        return returnInt;
    }
    */
    

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