import java.util.*;

public class Driver{
    public static void main(String[] args){
        Client r = new Client();

        r.deleteAccount("Testaccount");
        r.createAccount("Testaccount", "Full Name", "Test@umass.edu", "UMASS","10:00pm", "10:00am");

        r.login("Testaccount", "UMASS");
        
        //params: name, startdate, start hour, enddate, endhour, location, display
        r.addCalendarEvent("IM Football", "12/04/16", "06:00pm", "12/04/16", "08:00pm", "The Field", "true");
        r.addCalendarEvent("Computer Science", "12/05/16", "12:00pm", "12/05/16", "01:00pm", "Goessman", "true");
        r.addCalendarEvent("Hardware", "12/05/16", "02:00pm", "12/05/16", "03:00pm", "ELAB", "true");
        r.addCalendarEvent("Computer Architecture", "12/06/16", "10:00am", "12/06/16", "12:00pm", "Marston", "true");
        r.addCalendarEvent("Computer Science", "12/07/16", "12:00pm", "12/07/16", "01:00pm", "Goessman", "true");
        r.addCalendarEvent("Hardware", "12/07/16", "02:00pm", "12/07/16", "03:00pm", "ELAB", "true");
        r.addCalendarEvent("Computer Architecture", "12/08/16", "10:00am", "12/08/16", "12:00pm", "Marston", "true");
        r.addCalendarEvent("Computer Science", "12/09/16", "12:00pm", "12/09/16", "01:00pm", "Goessman", "true");
        r.addCalendarEvent("IM Football", "12/10/16", "06:00pm", "12/10/16", "08:00pm", "The Field", "true");

        System.out.println("Account \"" + r.getUserName()+ "\" made and initiated");

    }

}