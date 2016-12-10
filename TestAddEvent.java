public class TestAddEvent{   
public static void main(String[] args) {

		Client r = new Client();
	
        String name = r.nameOfEvent;	
        String day = r.day;
        String startDay = r.startDay;
        String startHour = r.startHour;
        String endDay = r.endDay;
        String endHour = r.endHour;
        String location = r.location;
        		
        Print toScreen = new Print();
        toScreen.setName(name);
        toScreen.setDay(Day);
        toScreen.setstartDay(startDay);
        toScreen.setstartHour(startHour);
        toScreen.setendDay(endDay);
        toScreen.setendHour(endHour);
        toScreen.setlocation(location);
        
        toScreen.printAddEvent();
        
     }
}