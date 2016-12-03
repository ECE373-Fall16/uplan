import java.util.*;
import java.text.*;

public class FreeTime{

	private Date startTime;
	private Date endTime;


	public FreeTime(Date start, Date end){
		startTime = start;
		endTime = end;
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
}