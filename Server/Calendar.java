import java.util.*;

public class Calendar{
    
    private String name;
    private String time;
    private String location;
    
    public Calendar(String name1, String time1, String loc){
        name = name1;
        time = time1;
        location = loc;
    }
    
    public String getName(){
        return name;
    }

}