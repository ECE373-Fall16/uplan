import java.util.*;
import java.text.*;

public class CalendarEvent{
    
    private int id;
    private String name;
    private Date starttime;
    private Date endtime;
    private String location;
    private boolean display;
    private TimeZone timezone = TimeZone.getTimeZone("EST");
    

    public CalendarEvent(String name1, Date start, Date end, String loc, boolean dis, int x){
        name = name1;
        starttime = start;
        endtime = end;
        location = loc;
        id = x;
    }
    

    public String toString(){
        return name + " | " + starttime + " | " + endtime + " | " + location;
    }
    
    public String toStringEST(){
        Calendar start = Calendar.getInstance();
        start.setTime(starttime);
        Calendar end = Calendar.getInstance();
        end.setTime(endtime);
        start.setTimeZone(timezone);
        end.setTimeZone(timezone);
        String startDate = start.get(Calendar.MONTH) + "/" + start.get(Calendar.DAY_OF_MONTH) + "/" + start.get(Calendar.YEAR);
        String endDate = end.get(Calendar.MONTH) + "/" + end.get(Calendar.DAY_OF_MONTH) + "/" + end.get(Calendar.YEAR);
        String startTime = start.get(Calendar.HOUR_OF_DAY) + ":" + start.get(Calendar.MINUTE);
        String endTime = end.get(Calendar.HOUR_OF_DAY) + ":" + end.get(Calendar.MINUTE);
        return name + " | " + getDayOfWeek() + " " + startDate + " " + startTime + " | " + endDate + " " + endTime + " | " + location; 
    }
    

    public String getName(){
        return name;
    }
    

    public void setName(String name1){
        name = name1;
    }
    

    public Date getStartTime(){
        return starttime;
    }
    

    public void setStartTime(Date time){
        starttime = time;
    }
    

    public Date getEndTime(){
        return endtime;
    }
    

    public void setEndTime(Date time){
        endtime = time;
    }
    

    public String getLocation(){
        return location;
    }
    
    
    public void setLocation(String loc){
        location = loc;
    }


    public boolean getDisplay(){
        return display;
    }


    public void setDisplay(boolean dis){
        display = dis;
    }


    public int getID(){
        return id;
    }


    public void setID(int x){
        id = x;
    }
    
    public String getDayOfWeek(){
        Calendar start = Calendar.getInstance();
        start.setTime(starttime);
        start.setTimeZone(timezone);
        
        int day = start.get(Calendar.DAY_OF_WEEK);
        
        switch (day){
            case Calendar.SUNDAY:   return "Sunday";
            case Calendar.MONDAY:   return "Monday";
            case Calendar.TUESDAY:   return "Tuesday";
            case Calendar.WEDNESDAY:   return "Wednesday";
            case Calendar.THURSDAY:   return "Thursday";
            case Calendar.FRIDAY:   return "Friday";
            case Calendar.SATURDAY:   return "Saturday";
            default:    return "Invalid Day";
        }
    }

}