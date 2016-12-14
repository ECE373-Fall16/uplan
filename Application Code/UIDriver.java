import java.util.*;

public class UIDriver {
    
	public static void main(String[] args){
	    UIHomepage home = new UIHomepage();
        Scanner scan = new Scanner(System.in);
        
        boolean exiting = home.loginPage();
        home.displaySchedule();
		while(!exiting){
			home.displayHomePage();
			int action = scan.nextInt();
			scan.nextLine();
			
			switch (action) {
			    case 1: home.displaySchedule();
				    break;
			    case 2: home.displayAssignments();
				    break;
			    case 3: home.displayEvents();
				    break;
			    case 4: home.addAssignment();
				    break;
			    case 5: home.addEvent();
				    break;
			    case 6: home.editAssignment();
				    break;
				case 7: home.editEvent();
				    break;
			    case 8: home.editProfile();
				    break;
				case 9: home.deleteEvent();
				    break;
				case 10: home.deleteAssignment();
				    break;
				case 11: home.deleteProfile();
				    break;
				case 12: home.refreshSchedule();
				    break;
			    case 13: home.logout();
			        exiting = home.loginPage();
			        break;
			    case 14: exiting = true;
			        break;
			    default: System.out.println("Invalid input, please select one of the listed options.");
				    break;
			}
		}
		
	}
}