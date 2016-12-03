import java.util.*;

public class Event{
    
    private String eventName;
    private String repeatedDays;
    private Date startTime;
    private Date endTime;
    private String location;
    

    public Event(String name, String d, Date start, Date end, String loc){
        eventName = name;
        repeatedDays = d;
        startTime = start;
        endTime = end;
        location = loc;
    }
    

    public String toString(){
        return eventName + " " + repeatedDays + " " + startTime + " " + endTime + " " + location;
    }
    

    public String getEventName(){
        return eventName;
    }
    

    public void setName(String name){
        eventName = name;
    }
    

    public String getDays(){
        return repeatedDays;
    }
    

    public void setDays(String d){
        repeatedDays = d;
    }
    

    public Date getStart(){
        return startTime;
    }
    

    public Date getEnd(){
        return endTime;
    }
    

    public void setStart(Date time){
        startTime = time;
    }
    

    public void setEnd(Date time){
        endTime = time;
    }
    

    public String getLocation(){
        return location;
    }
    

    public void setLocation(String loc){
        location = loc;
    }
    
}