import java.util.*;

public class Driver{
    public static void main(String[] args){
        Client r = new Client();

        r.deleteAccount("rhartnett1233");
        r.createAccount("rhartnett1233", "Richie Hartnett", "rhartnett@umass.edu", "UMASS","1000");

        int validateLogin = r.login("rhartnett1233", "UMASS");
        while(validateLogin == 0)
            r.login("rhartnett1233", "UMASS");

        r.addAssignment("Hw1","Circuits", "Sunday, December 4, 2016 11:00:00 PM EST", "2", "10");
        r.addAssignment("Hw2","Comp Sys", "Monday, December 5, 2016 07:00:00 PM EST", "1", "4");
        r.addAssignment("Hw3","Hardware", "Thursday, December 8, 2016 11:00:00 PM EST", "2", "15");

        r.addEvent("Electronics","MoWe","Monday, December 5, 2016 10:00:00 AM EST","Monday, December 5, 2016 11:00:00 AM EST","Elab 202");
        r.addEvent("Circuits","MoWeFrSa","Monday, December 5, 2016 12:00:00 PM EST","Monday, December 5, 2016 01:00:00 PM EST","LRC 301");
        r.addEvent("EnginWrit","TuTh","Tuesday, December 6, 2016 08:00:00 AM EST","Tuesday, December 6, 2016 10:00:00 AM EST","Marcus 110");
        r.addEvent("Seminar", "", "Thursday, December 8, 2016 02:00:00 PM EST", "Thursday, December 8, 2016 04:00:00 PM EST", "Marston 5");

        r.schedule();

        System.out.println("Account \"" + r.getUserName()+ "\" made and initiated");

    }

}