public class testAddassign{   
public static void main(String[] args) {

	String username = args[0];	
	String password = args[1];
	String email = args[2];
	String fullname = args[3];
	String bedtime = args[4];
	String waketime = args[5];
	String assignment = args[6];	
    String classname = args[7];
    String duedate = args[8];
    String duetime = args[9];
    String hours = args[10];
    String priority = args[11];
    String apriority = args[12];
    
    Print toScreen = new Print();
    toScreen.setAssignment(assignment);
    toScreen.setClassname(classname);
    toScreen.setDueDate(duedate);
    toScreen.setDueTime(duetime);
    toScreen.setHours(hours);
    toScreen.setPriority(priority);
    toScreen.setAPriority(apriority);
    toScreen.setUsername(username);
    toScreen.setPassword(password);
    toScreen.setEmail(email);
    toScreen.setFullname(fullname);
    toScreen.setBedtime(bedtime);
    toScreen.setWaketime(waketime);
        
    toScreen.printAddAssign();
        
     }
}