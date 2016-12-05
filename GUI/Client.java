import java.util.*;
import java.text.*;


public class Client {

	private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
	public Client() {
		
	}
	
	public LinkedList<CalendarEvent> display()throws ParseException{
		LinkedList<CalendarEvent> calList = new LinkedList<CalendarEvent>();
		
		Date start = df.parse("Friday, December 2, 2016 02:00:00 PM EST");
		Date end = df.parse("Friday, December 2, 2016 05:00:00 PM EST");
		CalendarEvent cal1 = new CalendarEvent("HW1", start, end, "ASSIGNMENT");
		calList.add(cal1);
		
		start = df.parse("Saturday, December 3, 2016 8:00:00 AM EST");
		end = df.parse("Saturday, December 3, 2016 11:00:00 AM EST");
		CalendarEvent cal2 = new CalendarEvent("HW2", start, end, "ASSIGNMENT");
		calList.add(cal2);
		
		start = df.parse("Friday, December 2, 2016 07:00:00 AM EST");
		end = df.parse("Friday, December 2, 2016 10:30:00 AM EST");
		CalendarEvent cal3 = new CalendarEvent("HW3", start, end, "ASSIGNMENT");
		calList.add(cal3);
		
		start = df.parse("Saturday, December 3, 2016 04:00:00 PM EST");
		end = df.parse("Saturday, December 3, 2016 08:00:00 PM EST");
		CalendarEvent cal4 = new CalendarEvent("HW4", start, end, "ASSIGNMENT");
		calList.add(cal4);
		
		start = df.parse("Friday, December 2, 2016 07:00:00 PM EST");
		end = df.parse("Friday, December 2, 2016 10:00:00 PM EST");
		CalendarEvent cal5 = new CalendarEvent("GET DRUNK", start, end, "EVENT");
		calList.add(cal5);

		return calList;
	}

	public boolean Login(String[] both) {
		if(both[0].equals("menziej")&&both[1].equals("password")){
			return true;
		}
		else{return false;}
	}

}
