import java.util.*;

public class Event{
    
    private String eventName;
    private String days;
    private int startTime;
    private int endTime;
    private String location;
    
    public Event(String name, String d, int start, int end, String loc){
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
    
    public int getStart(){
        return startTime;
    }
    
    public int getEnd(){
        return endTime;
    }
    
    public void setStart(int time){
        startTime = time;
    }
    
    public void setEnd(int time){
        endTime = time;
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setLocation(String loc){
        location = loc;
    }
}