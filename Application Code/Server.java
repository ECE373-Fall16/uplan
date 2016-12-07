import org.apache.xmlrpc.*;
import java.util.*;
import java.sql.*;
import java.text.*;

public class Server {
    
    private int alCounter = 0;
    private int elCounter = 0;
    private DataBase data = new DataBase();
    private static int PORT = 8095;
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
    private TimeZone timezone = TimeZone.getTimeZone("EST");
    private static long hourInMS = 3600000;


    public Vector display(String user, LinkedList<CalendarEvent> calendarList){
        try{
            Vector values = new Vector();
            System.out.println("\nPrinting list...");
            for(int k = 0; k < calendarList.size(); k++){
                //System.out.println("[" + k + "]" + calendarList.get(k).toString());
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
            System.err.println( "ServerValidate: " + e.getClass().getName() + ": " + e.getMessage() );
        }

        return returnValue;
    }
   
   
    public Vector addAssignment(String name, String username, String className, String date, String comp, String pri, String appPri){
        int valid = 1;
        try{
            valid = data.createAssignment(name, username, className, date, comp, pri, appPri);

        }catch( Exception e ){
            System.err.println( "ServerAddAssign: " + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }
      
        //return as vector
        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
   
   
    public Vector addEvent(String name, String username, String days, String startTime, String endTime, String loc){
        int valid = 1;
        try{
            valid = data.createEvent(name,username,days,startTime,endTime,loc);

        }catch( Exception e ){
            System.err.println( "ServerAddEvent:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }
 
        //return vector
        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
   
   
    public Vector createAccount(String username, String name, String email, String password, String bedtime){
        int valid = 1;
        try{
            valid = data.createUser(username, name, email, password, bedtime);

        } catch (Exception e){
            System.err.println( "ServerCreateAccount:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }


    public Vector deleteEvent(String name, String username){
        int valid = 1;
        try{
            valid = data.removeEvent(name, username);

        } catch (Exception e){
            System.err.println( "ServerDeleteEvent:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
    
    
    public Vector deleteAssignment(String name, String username){
        int valid = 1;
        try{
            valid = data.removeAssignment(name, username);

        } catch (Exception e){
            System.err.println( "ServerDeleteEvent:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
    
    
    public Vector deleteAccount(String username){
        int valid = 1;
        try{
            valid = data.removeProfile(username);

        } catch (Exception e){
            System.err.println( "ServerDeleteEvent:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
    
    
    public Vector updateAssignment(String assignmentName, String type, String newName, String user){
        int valid = 1;
        try{
            valid = data.updateAssignment(assignmentName, type, newName, user);

        } catch (Exception e){
            System.err.println( "ServerUpdateAssignment:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
    
    
    public Vector updateEvent(String eventName, String type, String newName, String user){
        int valid = 1;
        try{
            valid = data.updateEvent(eventName, type, newName, user);

        } catch (Exception e){
            System.err.println( "ServerUpdateEvent:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
    
    
    public Vector updateProfile(String type, String newName, String user){
        int valid = 1;
        try{
            valid = data.updateProfile(type, newName, user);

        } catch (Exception e){
            System.err.println( "ServerUpdateProfile:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        Vector returnValue = new Vector();
        returnValue.add(valid);

        return returnValue;
    }
    
   
    public Vector scheduleAlgo(String username) throws SQLException{

        Vector returnValues = new Vector();
        
        try{
            LinkedList<CalendarEvent> calendarList = new LinkedList<CalendarEvent>();
            LinkedList<FreeTime> freeblocks = new LinkedList<FreeTime>();
            LinkedList<Assignment> assignList = data.getAssignmentList(username);
            LinkedList<Event> tempEventList = new LinkedList<Event>();
            tempEventList = data.getEventList(username);
            LinkedList<Event> eventList = modifyEventList(tempEventList);
            
            ListIterator iter2 = eventList.listIterator();
            int index3;
            while(iter2.hasNext()){
                index3 = iter2.nextIndex();
                CalendarEvent calTemp = eventToCal(eventList.get(index3));
                calendarList = addToCalList(calTemp, calendarList);
                iter2.next();
            }

            assignList = orderAssignmentList(assignList);
            freeblocks = findFreeTime(calendarList, username);

            ListIterator iter1 = freeblocks.listIterator();
            while (iter1.hasNext()){
                //System.out.println(freeblocks.get(iter1.nextIndex()).toString());
                iter1.next();
            }
            
            //At this point we have the event list converted into the CalendarEvent list.
            //Also we have calculated the total free time for each day based off this list
            //and have saved it in a freetime list. Lastly we have an ordered assignment
            //list based on which assignments should be scheduled first for each day so 
            //they have enough time to be completed based on hours to completetion

            int index = 0;

            while(index < freeblocks.size()){                         //goes day by day through freetime
                
                FreeTime curBlock = freeblocks.get(index);
                assignList = orderAssignmentList(assignList);
                LinkedList<Assignment> tempAssign = assignList;
                ListIterator assignIter = assignList.listIterator();
                int curBlockFreeTime = getFreeTimeHours(curBlock);
                //System.out.println(curBlockFreeTime);        
                CalendarEvent curCalEvent;
                
                while(assignIter.hasNext() && curBlockFreeTime > 0){      //goes assignment by assignment per day
                    
                    Assignment curAssign = tempAssign.get(assignIter.nextIndex());
                    int hoursToComp = Integer.parseInt(curAssign.getCompletionTime());
                    int daysTillDue = getDaysTillDue(curAssign);
                    int workHours = hoursToComp/daysTillDue + 1;        //hours should be worked on that day
                    
                    if(workHours > curBlockFreeTime)
                        workHours = curBlockFreeTime;

                    java.util.Date assignStart = curBlock.getStartTime();           //starttime = starttime of freetime
                    Calendar end = dateToCalendar(assignStart);  
                    int hourOfDay = end.get(Calendar.HOUR_OF_DAY);
                    end.set(Calendar.HOUR_OF_DAY, hourOfDay + workHours);   //endtime = starttime + workhours
                    
                    java.util.Date assignEnd = end.getTime();
                    tempAssign.get(assignIter.nextIndex()).setCompletionTime(Integer.toString(hoursToComp - workHours));  //modify assignment with reduced completiontime
                    
                    curCalEvent = assignmentToCal(curAssign, assignStart, assignEnd);   //create cal event
                    addToCalList(curCalEvent, calendarList);        //add to cal list
                    
                    FreeTime temp = useFreeTime(curCalEvent, freeblocks.get(index));

                    Calendar tempCal = dateToCalendar(temp.getStartTime());
                    int hour = tempCal.get(Calendar.HOUR_OF_DAY);

                    if(hour != 0)
                        freeblocks.set(index, temp);           //modify freetime for next assignment
                    else
                        freeblocks.remove(index);
                    
                    curBlockFreeTime = getFreeTimeHours(freeblocks.get(index));

                    assignIter.next();
                
                }

                index++;
            
            }


            
            ListIterator iter = calendarList.listIterator();
            while (iter.hasNext()){
                System.out.println(calendarList.get(iter.nextIndex()).toString());
                iter.next();
            }

            System.out.println("\nPrinting list...");
            for(int k = 0; k < calendarList.size(); k++){
                //System.out.println("[" + k + "]" + calendarList.get(k).toString());
                returnValues.add(calendarList.get(k).getName());
                returnValues.add(df.format(calendarList.get(k).getStartTime()));
                returnValues.add(df.format(calendarList.get(k).getEndTime()));
                returnValues.add(calendarList.get(k).getLocation());
            }
            System.out.println();

        } catch (Exception e){
            System.err.println( "Serverschedule algo:" + e.getClass().getName() + ": " + e.getMessage() );
        }

        return returnValues;

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
            currentCal = dateToCalendar(currentDate);
            startCal = dateToCalendar(eve.getStart());
            endCal = dateToCalendar(eve.getEnd());
            
            weekOfYear = startCal.get(Calendar.WEEK_OF_YEAR);

            eventStartTimeHour = startCal.get(Calendar.HOUR_OF_DAY);
            eventStartTimeMin = startCal.get(Calendar.MINUTE);
            eventEndTimeHour = endCal.get(Calendar.HOUR_OF_DAY);
            eventEndTimeMin = endCal.get(Calendar.MINUTE);
            
            Calendar tempStart = Calendar.getInstance(timezone);
            Calendar tempEnd = Calendar.getInstance(timezone);
            
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

                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week2);
                
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

                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week2);

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

                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week2);
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

                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week2);                
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

                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week2);
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

                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week2);
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

                startTime = tempStart.getTime();
                endTime = tempEnd.getTime();
                
                Event week2 = new Event(eve.getEventName(),"",startTime, endTime, eve.getLocation());
                sepEvents.add(week2);
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
    
    
    public CalendarEvent assignmentToCal(Assignment assign, java.util.Date startTime, java.util.Date endTime) throws ParseException{
        CalendarEvent c = null;
        try{
            String name = assign.getAssignName();
            String loc = "ASSIGNMENT";
            c = new CalendarEvent(name,startTime,endTime,loc);

        } catch (Exception e){
            System.err.println( "ServerAssignToCal:" + e.getClass().getName() + ": " + e.getMessage() );
        }

        return c;
    }


    public LinkedList<CalendarEvent> addToCalList(CalendarEvent curCal, LinkedList<CalendarEvent> curList){
        Calendar calToAdd = Calendar.getInstance();
        Calendar curCalendar = Calendar.getInstance();
        calToAdd = dateToCalendar(curCal.getStartTime());
        
        ListIterator calListIter = curList.listIterator();
        Boolean added = false;
        int correctIndex = 0;
        int index;
        
        while(calListIter.hasNext() && !added){
            index = calListIter.nextIndex();
            curCalendar = dateToCalendar(curList.get(index).getStartTime());
            if(calToAdd.compareTo(curCalendar) < 0){          //after curList object
                correctIndex = index;
                added = true;
            }
            calListIter.next();
        }

        if(added){
            curList.add(correctIndex, curCal);
        }

        if(curList.size() == 0){
            curList.addFirst(curCal);
            added = true;
        }

        if(!added){
            curList.addLast(curCal);
            added = true;
        }
        return curList;
    }


    public LinkedList<Event> modifyEventList(LinkedList<Event> tempEventList) throws ParseException{
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
                    eventList.addLast(splitEvents.get(index2));
                    splitIter.next();
                }

            }
            else
                eventList.add(temp);
            iter1.next();
        }

        return eventList;
    }


    public LinkedList<Assignment> orderAssignmentList(LinkedList<Assignment> assignList){
        LinkedList<Assignment> tempList = new LinkedList<Assignment>();
        ListIterator assignIter = assignList.listIterator();

        Assignment curAssign;
        int appPriority;

        while(assignIter.hasNext()){
            int index = assignIter.nextIndex();
            curAssign = assignList.get(index);
            double hoursToComp = (double)Integer.parseInt(curAssign.getCompletionTime());
            double userPriority = (double)Integer.parseInt(curAssign.getPriority());
            double hoursLeft = (double)findHoursTillDue(curAssign);
            if(hoursLeft != -1){
                appPriority = (int)(hoursToComp*userPriority/hoursLeft*1000);
                if(appPriority > 0){
                    curAssign.setAppPriority(Integer.toString(appPriority));
                    tempList = addToAssignList(tempList, curAssign);
                }
            }
            assignIter.next();
        }

        return tempList;
    }


    public LinkedList<Assignment> addToAssignList(LinkedList<Assignment> assignList, Assignment assign){
        ListIterator iter = assignList.listIterator();
        LinkedList<Assignment> temp = assignList;
        Assignment curAssign;

        boolean added = false;
        int assignAppPri = Integer.parseInt(assign.getAppPriority());  //app priority for assignment brought in
        int curAppPri;                                                   //app priority for iterator assignment
        int correctIndex = 0;

        while(iter.hasNext() && !added){
            int iterIndex = iter.nextIndex();
            curAssign = assignList.get(iterIndex);
            curAppPri = Integer.parseInt(curAssign.getAppPriority());
            if(assignAppPri > curAppPri){
                correctIndex = iterIndex;
                added = true;
            }
            iter.next();
        }

        if(added == true)
            assignList.add(correctIndex, assign);

        if(added == false){                     //for if there is nothing in the list to begin with or it belongs at end
            temp.addLast(assign);
            added = true;
        }

        return assignList;
    }


    public int findHoursTillDue(Assignment assign){
        Calendar curCal = Calendar.getInstance();
        Calendar dueCal = Calendar.getInstance();

        java.util.Date dueDate = assign.getDueDate();
        dueCal = dateToCalendar(dueDate);

        int hoursTillDue;
        int x = curCal.get(Calendar.DAY_OF_YEAR) + 1;
        int y = dueCal.get(Calendar.DAY_OF_YEAR) - 1;
        int days = y - x + 1;
        int w = 24 - curCal.get(Calendar.HOUR_OF_DAY);
        int z = dueCal.get(Calendar.HOUR_OF_DAY);

        if(days == -1){
            hoursTillDue = w - z;
            if(hoursTillDue <= 0)
                return -1;
        }

        hoursTillDue = (days)*24 + w + z;

        return hoursTillDue;
    }


    public LinkedList<FreeTime> findFreeTime(LinkedList<CalendarEvent> calList, String user) throws SQLException{
        LinkedList<FreeTime> freeTimeList = new LinkedList<FreeTime>();
        int[] bedTime = data.getBedTime(user);
        Calendar endTime = Calendar.getInstance();
        Calendar priorEndTime = Calendar.getInstance();
        ListIterator<CalendarEvent> calListIter = calList.listIterator();

        int dayOfWeek;
        int priorDayOfWeek = 0;
        int calListIndex;
        java.util.Date endOfEvent;
        java.util.Date startOfFree;
        java.util.Date endOfFree;
        FreeTime newFreeTime;

        while(calListIter.hasNext()){           //runs through calendar list for end of each event
            
            //finds end time of current event
            calListIndex = calListIter.nextIndex();
            endOfEvent = calList.get(calListIndex).getEndTime();    //get date
            endTime = dateToCalendar(endOfEvent);                   //translate to EST calendar
            
            
            dayOfWeek = endTime.get(Calendar.DAY_OF_WEEK);    //used to know when switching days
            if(priorDayOfWeek == 0){
                priorDayOfWeek = dayOfWeek;
            }

            if(dayOfWeek != priorDayOfWeek){            //just passed last event of the day
                
                //adds 30 minute buffer to start time
                priorEndTime.add(Calendar.MINUTE, 30);
                startOfFree = priorEndTime.getTime();
                
                
                priorEndTime.set(Calendar.HOUR_OF_DAY, bedTime[0]+12);      //Bedtime always in PM
                priorEndTime.set(Calendar.MINUTE, bedTime[1]);
                
                
                endOfFree = priorEndTime.getTime();
                newFreeTime = new FreeTime(startOfFree, endOfFree);
                freeTimeList.add(newFreeTime);
                
                priorDayOfWeek++;
                if(priorDayOfWeek == 8){
                    priorDayOfWeek = 1;
                }
            }
            
            priorEndTime = dateToCalendar(endTime.getTime());           //old end time is now prior end time
            
            calListIter.next();
        }
        return freeTimeList;
    }


    public int getFreeTimeHours(FreeTime curDay){
        Calendar start = dateToCalendar(curDay.getStartTime());
        Calendar end = dateToCalendar(curDay.getEndTime());

        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int hours = endHour - startHour;

        /*System.out.println(curDay.toString());
        System.out.println("startHour: " + startHour + " endHour: " + endHour + " hour: " + hours);
        System.out.println("");*/

        return hours;
    }


    public int getDaysTillDue(Assignment assign){
        Calendar curCal = Calendar.getInstance(timezone);
        Calendar dueCal = dateToCalendar(assign.getDueDate());

        int curDayOfYear = curCal.get(Calendar.DAY_OF_YEAR);
        int dueDayOfYear = dueCal.get(Calendar.DAY_OF_YEAR);

        int days = dueDayOfYear - curDayOfYear + 1;

        return days;

    }


    private FreeTime useFreeTime(CalendarEvent workTime, FreeTime block){
        
        Calendar cal = dateToCalendar(workTime.getEndTime());       //calendar objects for end times of calEvent and freeTime
        Calendar free = dateToCalendar(block.getEndTime());
        boolean delete = false;              
        
        int calHour = cal.get(Calendar.HOUR_OF_DAY);
        int freeHour = free.get(Calendar.HOUR_OF_DAY);
        int hour = freeHour - calHour;                      //difference in hours of day

        if(hour <= 0){
            free.set(Calendar.HOUR_OF_DAY, 0);
            block.setStartTime(free.getTime());
            delete = true;
        }
        else if(hour == 1){
            int calMin = cal.get(Calendar.MINUTE);
            int freeMin = free.get(Calendar.MINUTE);
            int min = freeMin - calMin;

            if(min < 0){
                free.set(Calendar.HOUR_OF_DAY, 0);
                block.setStartTime(free.getTime());
                delete = true;
            }
            else
                delete = false;
        }
        else
            delete = false;

        if(delete == false){
            free = cal;
            block.setStartTime(free.getTime());
        }

        return block;
    }

    
    private Calendar dateToCalendar(java.util.Date date){          //converts to calendar of EST
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        df.setCalendar(cal);
        df.setTimeZone(timezone);
        cal = df.getCalendar();
        
        return cal;
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