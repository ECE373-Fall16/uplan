import java.util.*;

public class UIHomepage {
	
	private Scanner scan = new Scanner(System.in);
	private Client client = new Client();     //client for creating accounts
	private Client user;
	
	public UIHomepage(){
        
	}
	
	public void login(String userName){
	    user = new Client(userName);
	    user.login(userName);
	}
	
	public void logout(){
	    user.logout();
	}
	
	public void displayHomePage(){
	    //user.display();
	    System.out.println("\nUser: " + user.getUserName());
		System.out.println("Select one option from below:");
		System.out.println("1) Display Schedule");
		System.out.println("2) Display Assignments");
		System.out.println("3) Display Events");
		System.out.println("4) Add Assignment");
		System.out.println("5) Add Event");
		System.out.println("6) Edit Assignment");	
		System.out.println("7) Edit Event");
		System.out.println("8) Edit Profile");
		System.out.println("9) Delete Event");
		System.out.println("10) Delete Assignment");
		System.out.println("11) Delete Profile");
		System.out.println("12) Refresh Schedule");
		System.out.println("13) Logout");
		System.out.println("14) Exit Program");
	}
	

	public void displaySchedule(){
		LinkedList<CalendarEvent> calList = client.display();
		ListIterator iter = calList.listIterator();
		int index = 0;
		while(iter.hasNext()){
			index = iter.nextIndex();
			CalendarEvent tempCal = calList.get(index);
			System.out.println(index + " " + tempCal.toString());
			iter.next();
	   	}
	}

	public void displayAssignment(){
		LinkedList<Assignment> assignList = client.getAssignmentList();
		ListIterator iter = assignList.listIterator();
		while(iter.hasNext()){
			Assignment curAssign = assignList.get(iter.nextIndex());
			System.out.println(curAssign.toString());
			iter.next();
		}
	}

	public void displayEvents(){
		LinkedList<Event> eventList = client.getEventList();
		ListIterator iter = eventList.listIterator();
		while(iter.hasNext()){
			Assignment curEvent = eventList.get(iter.nextIndex());
			System.out.println(curEvent.toString());
			iter.next();
		}
	}

	public void addAssignment(){
		System.out.println("Name of Assignment:");
		String nameAssign = scan.nextLine();
		System.out.println("Name of Class:");
		String nameClass = scan.nextLine();
		System.out.println("Due Date (MM/DD/YY):");
		String dueDate = scan.nextLine();
		System.out.println("Due Hour (HH:MM:am/pm):")
		String dueHour = scan.nextLine();
		System.out.println("Estimates Time needed for Completion (Hours):");
		String completionTime = scan.nextLine();
		System.out.println("Priority of Assignment (1-3):");
		String priority = scan.nextLine();

		client.addAssignment(nameAssign, nameClass, dueDate, dueHour, completionTime, priority, "");
		
	}
	

	public int editAssignment(){
		System.out.println("Enter Name of Assignment:");
		String assignmentName = scan.nextLine();
		System.out.println("Select number of item that needs to be updated: ");
		System.out.println("1) Due Date");
		System.out.println("2) Completion Time");
		System.out.println("3) Priority");
		String input = scan.nextLine();
		String type = "";

		if(input.equals("1")){
			type = "DUE";
			System.out.println("Enter new due date (MM/DD/YY):")
			String newDate = scan.nextLine();
			System.out.println("Enter new due hour (HH:MMam/pm):")
			String newData = scan.nextLine();
		}
		else if(input.equals("2")){
			type = "HOURSTOCOMPLETION";
			System.out.println("Enter new completion time:");
			String newCompTime = scan.nextLine();
		}
		else if(input.equals("3")){
			type = "PRIORITY";
			System.out.println("Enter new priority (1-3):");
			String newPri = scan.nextLine();
		}
		else{
			System.out.println("Invalid input");
			return 0;
		}
		client.updateAssignment(assignmentName, type, newDate);
		return 1;
	}
	

	public void addEvent(){
		System.out.println("Name of Event:");
		String nameEvent = scan.nextLine();
		System.out.println("Enter Days of the Week (SuMoTuWeThFrSa):");
		String days = scan.nextLine();
		System.out.println("Start Date (MM/DD/YY):");
		String startDate = scan.nextLine();
		System.out.println("Start Hour (HH:MMam/pm): ")
		String startHour = scan.nextLine();
		System.out.println("End Hour (HH:MMam/pm:")
		String endHour = scan.nextLine();
		System.out.println("Location:");
		String location = scan.nextLine();
		
		client.addEvent(nameEvent, days, startDate, startHour, startDate, endHour, location);
		//create event object using values
	}
	

	public void editEvent(){
		System.out.println("Enter Name of Event:");
		String assignmentName = scan.nextLine();
		System.out.println("Select number of item that needs to be updated: ");
		System.out.println("1) Repeated Days");
		System.out.println("2) Start Date");
		System.out.println("3) Start Hour");
		System.out.println("4) End Hour");
		String input = scan.nextLine();
		String type = "";

		if(input.equals("1")){
			type = "DAYS";
			System.out.println("Enter new repeated days (SuMoTuWeThFrSa):")
			String newDate = scan.nextLine();
		}
		else if(input.equals("2")){
			type = "";
			System.out.println("Enter new start date (MM/DD/YY):");
			String newCompTime = scan.nextLine();
		}
		else if(input.equals("3")){
			type = "";
			System.out.println("Enter new start time (HH:MMam/pm");
			String newPri = scan.nextLine();
		}
		else if(input.equals("4")){
			type = "";
			System.out.println("Enter new end time (HH:MMam/pm)");
			String newDate = scan.nextLine();
		}
		else{
			System.out.println("Invalid input");
			return 0;
		}
		client.updateAssignment(assignmentName, type, newDate);
		return 1;
	}
	

	public int editProfile(){
		//get info for profile
		System.out.println("Enter 1 to change bedtime or 2 to change waketime: ")
		String input = scan.nextLine();
		String type = "";
		if(input.equals("1"))
			type = "BEDTIME";
		else if(input.equals("2"))
			type = "WAKETIME";
		else{
			System.out.println("Invalid input");
			return 0;
		}
		System.out.println("Enter new time (HH:MMam/pm");
		String nameDate = scan.nextLine();
		client.updateProfile(type, newDate);
		return 1;
	}

	public void deleteAssignment(){
		System.out.println("Enter name of assignment you wish to delete:")
		String assignName = scan.nextLine();
		client.deleteAssignment(assignName);
	}

	public void deleteEvent(){
		System.out.println("Enter name of event you wish to delete:")
		String eventName = scan.nextLine();
		client.deleteAssignment(eventName);
	}

	public void deleteProfile(){
		client.deleteProfile();
	}
	
	public void refreshSchedule(){
		LinkedList<CalendarList> calList = client.schedule();
		ListIterator iter = calList.listIterator();
		while(iter.hasNext()){
			index = iter.nextIndex();
			CalendarEvent tempCal = calList.get(index);
			System.out.println(index + " " + tempCal.toString());
			iter.next();
		}
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
	    System.out.println("Approximate Bedtime (HH:MMam/pm): ");
	    String bedtime = scan.nextLine();
	    System.out.println("Approximate Waketime (HH:MMam/pm):")
	    String waketime = scan.nextLine();
	    client.createAccount(username, name, email, password, bedtime, waketime);
	}
	
	
	//Opening Screen
	public boolean LoginPage(){
	    System.out.println("Create an Account (0), Login (1) or Exit Program(2)");
		String login = scan.nextLine();
		if(login.equals("0")){
		    createProfile();
	    }
	    if(login.equals("1")){
	        return(true);          //exit program
	    }
		System.out.println("What are your credentials?\nUserName:");
		String userName = scan.nextLine();
		login(userName);
		//System.out.println("Password:");
		//String password = scan.nextLine();
		
		//check to see if they are valid
		
		return false;         //dont exit program
	}
	
}