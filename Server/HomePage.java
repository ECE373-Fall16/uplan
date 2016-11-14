import java.util.*;

public class HomePage {
	
	private Scanner scan = new Scanner(System.in);
	private Client client = new Client();     //client for creating accounts
	private Client user;
	
	public HomePage(){
        
	}
	
	public void login(String userName){
	    user = new Client(userName);
	    user.login(userName);
	    user.display();
	}
	
	public void logout(){
	    user.logout();
	}
	
	public void displayHomePage(){
	    user.display();
	    System.out.println("\nUser: " + user.getUserName());
		System.out.println("Select one option from below:");
		System.out.println("1) Display Schedule");
		System.out.println("2) Add Assignment");
		System.out.println("3) Edit Assignment");
		System.out.println("4) Add Event");
		System.out.println("5) Edit Event");
		System.out.println("6) Delete Profile");
		System.out.println("7) Refresh Schedule");
		System.out.println("8) Logout");
	}
	/*
	public void displaySchedule(){
	   Calendar[] ca = user.displaySchedule();
	   int length = ca.length();
	   for(int i = 0; i < length; i++){
	       System.out.println("Name: " + ca[i].getName() + " Time: " + ca[i].getTime() + " Location: " + ca[i].getLocation());
	   }
	}//*/
	
	public void displaySchedule(){
	    user.display();
	}
	
	public void addAssignment(){
		System.out.println("Name of Assignment:");
		String nameAssign = scan.nextLine();
		System.out.println("Name of Class:");
		String nameClass = scan.nextLine();
		System.out.println("Days to Complete Assignment:");
		String dueDate = scan.nextLine();
		System.out.println("Estimates Time of Completion (Hours):");
		int completionTime = scan.nextInt();
		System.out.println("Priority of Assignment (1-3):");
		int priority = scan.nextInt();
		
		user.addAssignment(nameAssign, nameClass, dueDate, completionTime, priority);
		//create assignment object using values
		
	}
		
	public void editAssignment(){
		System.out.println("Enter Name of Assignment:");
		String assignmentName = scan.nextLine();
		
		//find assignment
		
		String nameAssign = "newDueDate";
		user.updateAssignment(assignmentName,nameAssign);
	}
	
	public void addEvent(){
		System.out.println("Name of Event:");
		String nameEvent = scan.nextLine();
		System.out.println("Enter Days of the Week(SuMoTuWeThFiSa):");
		String days = scan.nextLine();
		System.out.println("Start time:");
		int start = scan.nextInt();
		System.out.println("End time:");
		int end = scan.nextInt();
		System.out.println("Location:");
		String location = scan.nextLine();
		
		user.addEvent(nameEvent, days, start, end, location);
		//create event object using values
	}
	
	public void editEvent(){
		System.out.println("Enter Name of Event:");
		String findEvent = scan.nextLine();
		
		//find event
		
		//String nameEvent = 
	}
	
	public void editProfile(){
		//get info for profile
		System.out.println("Enter name of profile to delete:");
		String username = scan.nextLine();
		client.deleteAccount(username);
	}
	
	public void refreshSchedule(){
		//displaySchedule();
		user.printLists();
	}
	
	public void createProfile(){
	    System.out.println("Name: ");
	    String name = scan.nextLine();
	    System.out.println("Username: ");
	    String username = scan.nextLine();
	    System.out.println("Email: ");
	    String email = scan.nextLine();
	    System.out.println("Password: ");
	    String password = scan.nextLine();
	    System.out.println("Approximate Bedtime: ");
	    int bedtime = scan.nextInt();
	    client.createAccount(username, name, email, password, bedtime);
	}
	
}