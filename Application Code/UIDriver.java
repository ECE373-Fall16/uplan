import java.util.*;

public class UIDriver {
    
	public static void main(String[] args){
	    HomePage home = new HomePage();
        Scanner scan = new Scanner(System.in);
        
        boolean exiting = home.openingScreen();
        home.displaySchedule();
		//HomePage home = new HomePage();
		while(!exiting){
			home.displayHomePage();
			int action = scan.nextInt();
			scan.nextLine();
			
			switch (action) {
			    case 1: home.displaySchedule();
				    break;
			    case 2: home.addAssignment();
				    break;
			    case 3: home.editAssignment();
				    break;
			    case 4: home.addEvent();
				    break;
			    case 5: home.editEvent();
				    break;
			    case 6: home.editProfile();
				    break;
			    case 7: home.refreshSchedule();
				    break;
			    case 8: home.logout();
			        exiting = home.openingScreen();
			        break;
			    case 9: exiting = true;
			        break;
			    default: System.out.println("Invalid input, please select one of the listed options.");
				    break;
			}
		}
		
	}
}