import java.util.*;

public class Event{
    
    private String eventName;
    private String days;
    private String startTime;
    private String endTime;
    private String location;
    
    public Event(String name, String d, String start, String end, String loc){
        eventName = name;
        days = d;
        startTime = start;
        endTime = end;
        location = loc;
    }
    
    public String toString(){
        return eventName + " " + days + " " + startTime + " " + endTime + " " + location;
    }
    
    public String getEventName(){
        return eventName;
    }
    
    public void setName(String name){
        eventName = name;
    }
    
    public String getDays(){
        return days;
    }
    
    public void setDays(String d){
        days = d;
    }
    
    public String getStart(){
        return startTime;
    }
    
    public String getEnd(){
        return endTime;
    }
    
    public void setStart(String time){
        startTime = time;
    }
    
    public void setEnd(String time){
        endTime = time;
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setLocation(String loc){
        location = loc;
    }
}