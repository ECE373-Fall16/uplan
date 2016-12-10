public class TestAddEvent{   
public static void main(String[] args) {

		Client r = new Client();
	
        String name = r.name;
        String classname = r.classname;
        String day = r.duedate;
        String due = r.duetime;
        String hours = r.hoursToCompletion;
        String priority = r.priority;
        		
        Print toScreen = new Print();
        toScreen.setName(name);
        toScreen.setClassname(classname);
        toScreen.setDay(Day);
        toScreen.setDueHour(due);
        toScreen.setHours(hours);
        toScreen.setPriority(priority);
        
        toScreen.printAddAssign();
        
     }
}