import java.util.*;

public class CalendarEvent{
    
    private String name;
    private Date starttime;
    private Date endtime;
    private String location;
    

    public CalendarEvent(String name1, Date start, Date end, String loc){
        name = name1;
        starttime = start;
        endtime = end;
        location = loc;
    }
    

    public String toString(){
        return "Calendar: " + name + " " + starttime + " " + endtime + " " + location;
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

}