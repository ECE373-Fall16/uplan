import java.util.*;

public class UIDriver {

	public static void main(String[] args){
		
		HomePage h = new HomePage();
		Scanner scan = new Scanner(System.in);
		System.out.println("Create an Account (0) or Login (1)");
		boolean login = scan.nextInt();
		if(!login){
		    h.createAccount();
	    }
		System.out.println("What are your credentials?\nUserName:");
		String userName = scan.nextLine();
		System.out.println("Password:");
		String password = scan.nextLine();
		
		//check to see if they are valid
		
		HomePage home = new HomePage();
		while(true){
			home.displayHomePage();
			int action = scan.nextInt();
			
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
			default: System.out.println("Invalid input, please select one of the listed options.");
				break;
			}
		}
		
	}
}