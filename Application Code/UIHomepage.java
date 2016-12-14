import java.util.*;

public class UIHomepage {
	
	private Scanner scan = new Scanner(System.in);
	private Client client = new Client();     //client for creating accounts
	private Client user;
	
	public UIHomepage(){
        
	}
	
	public void login(){
		boolean valid = false;
		while(valid == false){
			System.out.println("Enter Username:");
			String userName = scan.nextLine();
			System.out.println("Enter Password:");
			String password = scan.nextLine();
			valid = client.login(userName, password);
			if(valid == false){
				System.out.println("Invalid login, enter 1 to try again or enter 2 to create account.");
				String input = scan.nextLine();
				if(input.equals("2"))
					createProfile();
			}
		}
	}
	
	public void logout(){
	    client.logout();
	    loginPage();
	}
	
	public void displayHomePage(){
	    //user.display();
	    //System.out.println("\nUser: " + user.getUserName());
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
		if(calList != null){
			ListIterator iter = calList.listIterator();
			Calendar pastCal = Calendar.getInstance();
			Calendar curCal = Calendar.getInstance();
			int index = 0;
			String month = "";
			String dayOfWeek = "";
			String dayOfMonth = "";
			int curDayOfYear = 0;
			int pastDayOfYear = 0;
		
			while(iter.hasNext()){
				index = iter.nextIndex();
				CalendarEvent tempCal = calList.get(index);
				if(index == 0){
					curCal.setTime(tempCal.getEndTime());
					month = client.getMonth(curCal.get(Calendar.MONTH));
					dayOfWeek = client.getDayOfWeek(curCal.get(Calendar.DAY_OF_WEEK));
					dayOfMonth = Integer.toString(curCal.get(Calendar.DAY_OF_MONTH));
					System.out.println(dayOfWeek + ", " + month + " " + dayOfMonth);
					System.out.println("-------------------------");
					System.out.println(index + " " + tempCal.toString());
					pastCal.setTime(tempCal.getEndTime());
					pastDayOfYear = pastCal.get(Calendar.DAY_OF_YEAR);
				}
				else{
					curCal.setTime(tempCal.getEndTime());
					curDayOfYear = curCal.get(Calendar.DAY_OF_YEAR);
					if(curDayOfYear == pastDayOfYear){
						System.out.println(index + " " + tempCal.toString());
					}
					else{
						System.out.println("");
						month = client.getMonth(curCal.get(Calendar.MONTH));
						dayOfWeek = client.getDayOfWeek(curCal.get(Calendar.DAY_OF_WEEK));
						dayOfMonth = Integer.toString(curCal.get(Calendar.DAY_OF_MONTH));
						System.out.println(dayOfWeek + ", " + month + " " + dayOfMonth);
						System.out.println("-------------------------");
						System.out.println(index + " " + tempCal.toString());
					}
					pastCal.setTime(tempCal.getEndTime());
					pastDayOfYear = pastCal.get(Calendar.DAY_OF_YEAR);
				}
				iter.next();
	   		}
	   	}
	   	scan.nextLine();
	}

	public void displayAssignments(){
		LinkedList<Assignment> assignList = client.getAssignmentList();
		ListIterator iter = assignList.listIterator();
		while(iter.hasNext()){
			Assignment curAssign = assignList.get(iter.nextIndex());
			System.out.println(curAssign.toString());
			iter.next();
		}
		System.out.println("");
		scan.nextLine();
	}

	public void displayEvents(){
		LinkedList<Event> eventList = client.getEventList();
		ListIterator iter = eventList.listIterator();
		while(iter.hasNext()){
			Event curEvent = eventList.get(iter.nextIndex());
			System.out.println(curEvent.toString());
			iter.next();
		}
		System.out.println("");
		scan.nextLine();
	}

	public int addAssignment(){
		System.out.println("Name of Assignment:");
		String nameAssign = scan.nextLine();
		System.out.println("Name of Class:");
		String nameClass = scan.nextLine();
		System.out.println("Due Date (MM/DD/YY):");
		String dueDate = scan.nextLine();
		if(dueDate.length() != 8)
			return 0;
		System.out.println("Due Hour (HH:MM:am/pm):");
		String dueHour = scan.nextLine();
		if(dueHour.length() != 7)
			return 0;
		System.out.println("Estimates Time needed for Completion (Hours):");
		String completionTime = scan.nextLine();
		System.out.println("Priority of Assignment (1-3):");
		String priority = scan.nextLine();

		client.addAssignment(nameAssign, nameClass, dueDate, dueHour, completionTime, priority, "");

		System.out.println("");

		return 1;
				
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
			System.out.println("Enter new due date (MM/DD/YY):");
			String newDate = scan.nextLine();
			if(newDate.length() != 8)
				return 0;
			System.out.println("Enter new due hour (HH:MMam/pm):");
			String newHour = scan.nextLine();
			if(newHour.length() != 7)
				return 0;
			client.updateAssignmentDate(assignmentName, newDate, newHour);
		}
		else if(input.equals("2")){
			type = "HOURSTOCOMPLETION";
			System.out.println("Enter new completion time:");
			String newCompTime = scan.nextLine();
			client.updateAssignment(assignmentName, type, newCompTime);
		}
		else if(input.equals("3")){
			type = "PRIORITY";
			System.out.println("Enter new priority (1-3):");
			String newPri = scan.nextLine();
			client.updateAssignment(assignmentName, type, newPri);
		}
		else{
			System.out.println("Invalid input");
			System.out.println("");
			return 0;
		}
		System.out.println("");
		return 1;
	}
	

	public int addEvent(){
		System.out.println("Name of Event:");
		String nameEvent = scan.nextLine();
		System.out.println("Enter Days of the Week (SuMoTuWeThFrSa):");
		String days = scan.nextLine();
		System.out.println("Start Date (MM/DD/YY):");
		String startDate = scan.nextLine();
		if(startDate.length() != 8)
			return 0;
		System.out.println("Start Hour (HH:MMam/pm): ");
		String startHour = scan.nextLine();
		System.out.println("End Hour (HH:MMam/pm:");
		String endHour = scan.nextLine();
		if(startHour.length() != 7 || endHour.length() != 7)
			return 0;
		System.out.println("Location:");
		String location = scan.nextLine();
		
		client.addEvent(nameEvent, days, startDate, startHour, startDate, endHour, location);

		System.out.println("");

		return 1;
		//create event object using values
	}
	

	public int editEvent(){
		System.out.println("Enter Name of Event:");
		String eventName = scan.nextLine();
		System.out.println("Select number of item that needs to be updated: ");
		System.out.println("1) Repeated Days");
		System.out.println("2) Start Date/times");
		System.out.println("3) Location");
		String input = scan.nextLine();
		String type = "";
		String newHour = "";
		String newData = "";

		if(input.equals("1")){
			type = "DAYS";
			System.out.println("Enter new repeated days (SuMoTuWeThFrSa):");
			newData = scan.nextLine();
			client.updateEvent(eventName, type, newData);
		}
		else if(input.equals("2")){
			System.out.println("Enter new start date (MM/DD/YY):");
			String newDate = scan.nextLine();

			System.out.println("Enter new start time (HH:MMam/pm");
			String newStartHour = scan.nextLine();

			System.out.println("Enter new end time (HH:MMam/pm)");
			String newEndHour = scan.nextLine();

			if(newStartHour.length() != 7 || newEndHour.length() != 7)
				return 0;

			client.updateEventDate(eventName, "START_TIME", newDate, newStartHour);
			client.updateEventDate(eventName, "END_TIME", newDate, newEndHour);
		}
		else if(input.equals("3")){
			type = "LOCATION";
			System.out.println("Enter new location of event: ");
			newData = scan.nextLine();
			client.updateEvent(eventName, type, newData);
		}
		else{
			System.out.println("Invalid input");
			System.out.println("");
			return 0;
		}

		System.out.println("");

		return 1;
	}
	

	public int editProfile(){
		//get info for profile
		System.out.println("Enter 1 to change bedtime or 2 to change waketime: ");
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
		String newDate = scan.nextLine();
		if(newDate.length() != 7)
			return 0;
		client.updateProfile(type, newDate);
		System.out.println("");
		return 1;
	}

	public int deleteAssignment(){
		System.out.println("Enter name of assignment you wish to delete:");
		String assignName = scan.nextLine();
		client.deleteAssignment(assignName);
		System.out.println("");

		return 1;
	}

	public int deleteEvent(){
		System.out.println("Enter name of event you wish to delete:");
		String eventName = scan.nextLine();
		client.deleteEvent(eventName);
		System.out.println("");

		return 1;
	}

	public int deleteProfile(){
		System.out.println("Enter your username:");
		String username = scan.nextLine();
		client.deleteAccount(username);
		System.out.println("");
		loginPage();

		return 1;
	}
	
	public void refreshSchedule(){
		LinkedList<Event> eventList = client.getEventList();
		if(eventList == null)
			System.out.println("You need to schedule an event before refreshing schedule");
		else{
			LinkedList<CalendarEvent> calList = client.schedule();
			ListIterator iter = calList.listIterator();
			Calendar pastCal = Calendar.getInstance();
			Calendar curCal = Calendar.getInstance();
			int index = 0;
			String month = "";
			String dayOfWeek = "";
			String dayOfMonth = "";
			int curDayOfYear = 0;
			int pastDayOfYear = 0;
			while(iter.hasNext()){
				index = iter.nextIndex();
				CalendarEvent tempCal = calList.get(index);
				if(index == 0){
					curCal.setTime(tempCal.getEndTime());
					month = client.getMonth(curCal.get(Calendar.MONTH));
					dayOfWeek = client.getDayOfWeek(curCal.get(Calendar.DAY_OF_WEEK));
					dayOfMonth = Integer.toString(curCal.get(Calendar.DAY_OF_MONTH));
					System.out.println(dayOfWeek + ", " + month + " " + dayOfMonth);
					System.out.println("-------------------------");
					System.out.println(index + " " + tempCal.toString());
					pastCal.setTime(tempCal.getEndTime());
					pastDayOfYear = pastCal.get(Calendar.DAY_OF_YEAR);
				}
				else{
					curCal.setTime(tempCal.getEndTime());
					curDayOfYear = curCal.get(Calendar.DAY_OF_YEAR);
					if(curDayOfYear == pastDayOfYear){
						System.out.println(index + " " + tempCal.toString());
					}
					else{
						System.out.println("");
						month = client.getMonth(curCal.get(Calendar.MONTH));
						dayOfWeek = client.getDayOfWeek(curCal.get(Calendar.DAY_OF_WEEK));
						dayOfMonth = Integer.toString(curCal.get(Calendar.DAY_OF_MONTH));
						System.out.println(dayOfWeek + ", " + month + " " + dayOfMonth);
						System.out.println("-------------------------");
						System.out.println(index + " " + tempCal.toString());
					}
					pastCal.setTime(tempCal.getEndTime());
					pastDayOfYear = pastCal.get(Calendar.DAY_OF_YEAR);
				}
				iter.next();
	   		}
	   	}
	   	System.out.println("");
	   	scan.nextLine();
	}
	

	public int createProfile(){
		boolean valid = false;
		String name = "";
		String username = "";
		String email = "";
		String password = "";
		String bedtime = "";
		String waketime = "";
		while(valid == false){
	    	System.out.println("Name: ");
	    	name = scan.nextLine();
	    	System.out.println("Username: ");
	    	username = scan.nextLine();
	    	System.out.println("Email: ");
	    	email = scan.nextLine();
	    	System.out.println("Password: ");
	    	password = scan.nextLine();
	    	System.out.println("Approximate Bedtime (HH:MMam/pm): ");
	    	bedtime = scan.nextLine();
	    	System.out.println("Approximate Waketime (HH:MMam/pm):");
	    	waketime = scan.nextLine();
	    	if(bedtime.length() != 7 || waketime.length() != 7){
	    		valid = false;
	    		System.out.println("Invalid input please redo.");
	    	}
	    	else
	    		valid = true;
	    }
	    client.createAccount(username, name, email, password, bedtime, waketime);

	    System.out.println("");

	    login();
	    System.out.println("");

	    return 1;
	}
	
	
	//Opening Screen
	public boolean loginPage(){
	    System.out.println("Create an Account (0) or Login (1)");
		String login = scan.nextLine();
		if(login.equals("0")){
		    createProfile();
	    }
	    else if(login.equals("1")){
	        login();          //exit program
	    }
	    else
	    	System.out.println("Invalid input");
		
		return false;         //dont exit program
	}
	
}