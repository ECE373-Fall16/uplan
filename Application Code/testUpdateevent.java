public class testUpdateevent{
	public static void main(String[] args){
		
		 	String username = args[0];	
	        String password = args[1];
	        String email = args[2];
	        String fullname = args[3];
	        String bedtime = args[4];
	        String waketime = args[5];
	        String event = args[6];
	        String type = args[7];
	        String newName = args[8];
	        
	        Print toScreen = new Print();
	        toScreen.setUsername(username);
	        toScreen.setPassword(password);
	        toScreen.setEmail(email);
	        toScreen.setFullname(fullname);
	        toScreen.setBedtime(bedtime);
	        toScreen.setWaketime(waketime);
	        toScreen.setEvent(event);
	        toScreen.setType(type);
	        toScreen.setnewName(newName);
	        
	        toScreen.printUpdateEvent();
	        
	}
}