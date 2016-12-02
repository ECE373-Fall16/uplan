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
    
    
    public LinkedList<Event> splitEvent(Event eve) throws ParseException{
        LinkedList<Event> sepEvents = new LinkedList<Event>();
        String repeatDays = eve.getDays();
        char[] days = repeatDays.toCharArray();
        char tempDays[] = new char[2];
        int size = days.length;
        df.setTimeZone(TimeZone.getTimeZone("EST"));
                
        int counter = 0;
        int tempIndex = 0;
        while (counter < size){
            tempDays[0] = days[counter++];
            tempDays[1] = days[counter++];
             
            String dayOfWeek = new String(tempDays);
            int dayNum;
            int weekOfYear;
            int eventStartTimeHour;
            int eventStartTimeMin;
            int eventEndTimeHour;
            int eventEndTimeMin;
            java.util.Date startTime;
            java.util.Date endTime;
            
            //create calendar objects based on current date, start date, end date
            Calendar currentCal = Calendar.getInstance();
            java.util.Date currentDate = currentCal.getTime();
            String tempDate = df.format(currentDate);
            currentDate = df.parse(tempDate);
            
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            currentCal.setTime(currentDate);
            startCal.setTime(eve.getStart());
            endCal.setTime(eve.getEnd());
            
            weekOfYear = startCal.get(Calendar.WEEK_OF_YEAR);
            eventStartTimeHour = startCal.get(Calendar.HOUR_OF_DAY);
            eventStartTimeMin = startCal.get(Calendar.MINUTE);
            eventEndTimeHour = endCal.get(Calendar.HOUR_OF_DAY);
            eventEndTimeMin = endCal.get(Calendar.MINUTE);
            
            Calendar tempStart = currentCal;
            Calendar tempEnd = currentCal;
            
            if(dayOfWeek.equals("Su")){
                
                tempStart.set(Calendar.DAY_OF_WEEK, 1);
                tempEnd.set(Calendar.DAY_OF_WEEK, 1);
                tempStart.set(Calendar.HOUR_OF_DAY, eventStartTimeHour);
                tempStart.set(Calendar.MINUTE, eventStartTimeMin);                
                tempEnd.set(Calendar.HOUR_OF_DAY, eventEndTimeHour);
                tempEnd.set(Calendar.MINUTE, eventEndTimeMin);
                
                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week1 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
                tempStart.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                tempEnd.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
            }
            
            else if(dayOfWeek.equals("Mo")){
                tempStart.set(Calendar.DAY_OF_WEEK, 2);
                tempEnd.set(Calendar.DAY_OF_WEEK, 2);
                tempStart.set(Calendar.HOUR_OF_DAY, eventStartTimeHour);
                tempStart.set(Calendar.MINUTE, eventStartTimeMin);                
                tempEnd.set(Calendar.HOUR_OF_DAY, eventEndTimeHour);
                tempEnd.set(Calendar.MINUTE, eventEndTimeMin);
                
                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week1 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
                tempStart.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                tempEnd.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
            }
            
            else if(dayOfWeek.equals("Tu")){
                tempStart.set(Calendar.DAY_OF_WEEK, 3);
                tempEnd.set(Calendar.DAY_OF_WEEK, 3);
                tempStart.set(Calendar.HOUR_OF_DAY, eventStartTimeHour);
                tempStart.set(Calendar.MINUTE, eventStartTimeMin);                
                tempEnd.set(Calendar.HOUR_OF_DAY, eventEndTimeHour);
                tempEnd.set(Calendar.MINUTE, eventEndTimeMin);
                
                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week1 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
                tempStart.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                tempEnd.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
            }
            
            else if(dayOfWeek.equals("We")){
                tempStart.set(Calendar.DAY_OF_WEEK, 4);
                tempEnd.set(Calendar.DAY_OF_WEEK, 4);
                tempStart.set(Calendar.HOUR_OF_DAY, eventStartTimeHour);
                tempStart.set(Calendar.MINUTE, eventStartTimeMin);                
                tempEnd.set(Calendar.HOUR_OF_DAY, eventEndTimeHour);
                tempEnd.set(Calendar.MINUTE, eventEndTimeMin);
                
                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week1 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
                tempStart.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                tempEnd.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);                
            }
            
            else if(dayOfWeek.equals("Th")){
                tempStart.set(Calendar.DAY_OF_WEEK, 5);
                tempEnd.set(Calendar.DAY_OF_WEEK, 5);
                tempStart.set(Calendar.HOUR_OF_DAY, eventStartTimeHour);
                tempStart.set(Calendar.MINUTE, eventStartTimeMin);                
                tempEnd.set(Calendar.HOUR_OF_DAY, eventEndTimeHour);
                tempEnd.set(Calendar.MINUTE, eventEndTimeMin);
                
                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week1 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
                tempStart.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                tempEnd.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
            }
            
            else if(dayOfWeek.equals("Fr")){
                tempStart.set(Calendar.DAY_OF_WEEK, 6);
                tempEnd.set(Calendar.DAY_OF_WEEK, 6);
                tempStart.set(Calendar.HOUR_OF_DAY, eventStartTimeHour);
                tempStart.set(Calendar.MINUTE, eventStartTimeMin);                
                tempEnd.set(Calendar.HOUR_OF_DAY, eventEndTimeHour);
                tempEnd.set(Calendar.MINUTE, eventEndTimeMin);
                
                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week1 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
                tempStart.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                tempEnd.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
            }
            
            else if(dayOfWeek.equals("Sa")){
                tempStart.set(Calendar.DAY_OF_WEEK, 7);
                tempEnd.set(Calendar.DAY_OF_WEEK, 7);
                tempStart.set(Calendar.HOUR_OF_DAY, eventStartTimeHour);
                tempStart.set(Calendar.MINUTE, eventStartTimeMin);                
                tempEnd.set(Calendar.HOUR_OF_DAY, eventEndTimeHour);
                tempEnd.set(Calendar.MINUTE, eventEndTimeMin);
                
                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week1 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
                
                tempStart.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                tempEnd.set(Calendar.WEEK_OF_YEAR, weekOfYear+1);
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week1);
            }
        
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