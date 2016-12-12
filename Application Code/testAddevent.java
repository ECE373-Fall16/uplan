public class testAddEvent{   
public static void main(String[] args) {
	
    	String username = args[0];	
    	String password = args[1];
    	String email = args[2];
    	String fullname = args[3];
    	String bedtime = args[4];
    	String waketime = args[5];
		String event = args[6];	
        String day = args[7];
        String startDay = args[8];
        String startHour = args[9];
        String endDay = args[10];
        String endHour = args[11];
        String location = args[12];
        		
        Print toScreen = new Print();
        toScreen.setEvent(event);
        toScreen.setDay(day);
        toScreen.setstartDay(startDay);
        toScreen.setstartHour(startHour);
        toScreen.setendDay(endDay);
        toScreen.setendHour(endHour);
        toScreen.setLocation(location);
        toScreen.setUsername(username);
        toScreen.setPassword(password);
        toScreen.setEmail(email);
        toScreen.setFullname(fullname);
        toScreen.setBedtime(bedtime);
        toScreen.setWaketime(waketime);
        
        toScreen.printAddEvent();
        
     }
}
