import java.util.*;

public class Event{
    
    String eventName;
    String days;
    int dayTime;
    String location;
    
    public Event(String name, String d, int time, String loc){
        eventName = name;
        days = d;
        dayTime = time;
        location = loc;
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
    
    public int getTime(){
        return dayTime;
    }
    
    public void setTime(int time){
        dayTime = time;
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setLocation(String loc){
        location = loc;
    }
}