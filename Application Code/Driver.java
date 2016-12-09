import java.util.*;

public class Driver{
    public static void main(String[] args){
        Client r = new Client();

        r.deleteAccount("rhartnett1233");
        r.createAccount("rhartnett1233", "Richie Hartnett", "rhartnett@umass.edu", "UMASS","10:00pm");

        boolean validateLogin = r.login("rhartnett1233", "UMASS");
        if(validateLogin == false)
            r.login("rhartnett1233", "UMASS");

        //r.getAccountInfo();

        r.addAssignment("Hw1","Circuits", "12/04/16", "09:00pm", "10", "2", "");
        r.addAssignment("Hw2","Comp Sys", "12/05/16", "07:00pm", "4", "3", "");
        r.addAssignment("Hw3","Hardware", "12/08/16", "11:00pm", "1", "1", "");
        r.addAssignment("Hw4","Har", "12/10/16", "06:00am", "3", "3", "");

        r.addAssignment("Hw5","math", "12/09/16", "06:00am", "5", "1", "");
        r.addAssignment("Hw6","english", "12/10/16", "10:00pm", "10", "1", "");
        r.addAssignment("Hw7","dsds", "12/11/16", "12:00pm", "4", "3", "");

        //r.getAssignmentList();
        /*
        r.addEvent("Electronics","MoWe","Monday, December 5, 2016 10:00:00 AM EST","Monday, December 5, 2016 11:00:00 AM EST","Elab 202");
        r.addEvent("Circuits","MoWeFrSa","Monday, December 5, 2016 12:00:00 PM EST","Monday, December 5, 2016 01:00:00 PM EST","LRC 301");
        r.addEvent("EnginWrit","TuTh","Tuesday, December 6, 2016 10:00:00 AM EST","Tuesday, December 6, 2016 11:00:00 AM EST","Marcus 110");
        r.addEvent("Seminar", "", "Thursday, December 8, 2016 02:00:00 PM EST", "Thursday, December 8, 2016 04:00:00 PM EST", "Marston 5");

        r.getEventList();*/
        r.schedule();

        System.out.println("Account \"" + r.getUserName()+ "\" made and initiated");

    }

}