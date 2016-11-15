import java.util.*;

public class Calendar{
    
    private String name;
    private String starttime;
    private String endtime;
    private String location;
    private String day;
    
    public Calendar(String name1, String start, String end, String day1, String loc){
        name = name1;
        starttime = start;
        endtime = end;
        location = loc;
        day = day1;
    }
    
    public String toString(){
        return "Calendar: " + name + " " + starttime + " " + endtime + " " + location + " " + day;
    }
    
    public String getDay(){
        return day;
    }
    
    public void setDay(String day1){
        day = day1;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name1){
        name = name1;
    }
    
    public String getStartTime(){
        return starttime;
    }
    
    public void setStartTime(String time){
        starttime = time;
    }
    
    public String getEndTime(){
        return endtime;
    }
    
    public void setEndTime(String time){
        endtime = time;
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setLocation(String loc){
        location = loc;
    }

}