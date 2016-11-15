import java.util.*;

public class UIDriver {

	public static void main(String[] args){
		HomePage home = new HomePage();
		Scanner scan = new Scanner(System.in);
		System.out.println("Create an Account (0) or Login (1)");
		int login = scan.nextInt();
		scan.nextLine();
		if(login!=1){
		    home.createProfile();
	    }
		System.out.println("What are your credentials?\nUserName:");
		String userName = scan.nextLine();
		home.login(userName);
		//System.out.println("Password:");
		//String password = scan.nextLine();
		
		//check to see if they are valid
		
		//HomePage home = new HomePage();
		boolean logout = false;
		while(!logout){
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
			        logout = true;
			        break;
			    default: System.out.println("Invalid input, please select one of the listed options.");
				    break;
			}
		}
		
	}
}