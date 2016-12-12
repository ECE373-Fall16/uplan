import java.util.*;
import java.text.*;

public class FreeTime{

	private Date startTime;
	private Date endTime;
	private TimeZone timezone = TimeZone.getTimeZone("EST");


	public FreeTime(Date start, Date end){
		startTime = start;
		endTime = end;
	}

    public String toString(){
        return "StartTime: " + startTime + " || EndTime: " + endTime;       
    }
    
    public String toStringEST(){
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        start.setTimeZone(timezone);
        end.setTimeZone(timezone);
        String startDateString = (start.get(Calendar.MONTH)+1) + "/" + start.get(Calendar.DAY_OF_MONTH) + "/" + start.get(Calendar.YEAR);
        String endDateString = (end.get(Calendar.MONTH)+1) + "/" + end.get(Calendar.DAY_OF_MONTH) + "/" + end.get(Calendar.YEAR);
        String startTimeString = start.get(Calendar.HOUR_OF_DAY) + ":" + start.get(Calendar.MINUTE);
        String endTimeString = end.get(Calendar.HOUR_OF_DAY) + ":" + end.get(Calendar.MINUTE);
        return  getDayOfWeek() + " " + startDateString + " " + startTimeString + " | " + getDayOfWeek() + " " + endDateString + " " + endTimeString; 
    }

	public Date getStartTime(){
		return startTime;
	}


	public void setStartTime(Date start){
		startTime = start;
	}


	public Date getEndTime(){
		return endTime;
	}


	public void setEndTime(Date end){
		endTime = end;
	}
	
	public String getDayOfWeek(){
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);
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