import java.util.*;

public class CalendarEvent{
    
    private int id;
    private String name;
    private Date starttime;
    private Date endtime;
    private String location;
    private boolean display;
    

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

}