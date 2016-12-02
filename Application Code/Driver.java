import java.util.*;

public class Driver{
    public static void main(String[] args){
        Client r = new Client();
        //r.login("stevesprofile");
        //*
        r.deleteAccount("rhartnett1233");
        r.createAccount("rhartnett1233", "Richie Hartnett", "rhartnett@umass.edu", "UMASS",10);
        r.login("rhartnett1233", "UMASS");
        r.addAssignment("Hw1","classname", "Sunday, December 4, 2016 11:59:00 PM EST", "2", "10");
        r.addAssignment("Hw1","classname", "Monday, December 5, 2016 07:59:00 PM EST", "1", "4");
        r.addAssignment("Hw1","classname", "Thursday, December 8, 2016 11:59:00 PM EST", "2", "15");
        /*r.addAssignment("Hw4","classname", "dueDate", 20, 4325);
        r.addAssignment("Hw5","classname", "dueDate", 20, 4325);
        r.addAssignment("Hw6","classname", "dueDate", 20, 4325);
        r.addAssignment("Hw7","classname", "dueDate", 20, 4325);*/
        r.addEvent("Electronics","MoWeFr","Monday, December 5, 2016 10:00:00 AM EST","Monday, December 5, 2016 11:00:00 AM EST","Elab 202");
        r.addEvent("Circuits","MoWeFr","Monday, December 5, 2016 12:00:00 AM EST","Monday, December 5, 2016 01:00:00 PM EST","LRC 301");
        r.addEvent("EnginWrit","TuTh","Tuesday, December 6, 2016 08:00:00 AM EST","Tuesday, December 6, 2016 10:00:00 AM EST","Marcus 110");
        r.addEvent("Seminar", "", "Thursday, December 8, 2016 02:00:00 PM EST", "Thursday, December 8, 2016 04:00:00 PM EST", "Marston 5");
        //r.addEvent("ECE3732","TuTh",930,1130,"location");
        r.schedule();
        /*r.updateEvent("Electronics", "START_TIME", "1000");
        r.updateAssignment("Hw1", "CLASSNAME", "Homework 1");
        r.schedule();*/
        System.out.println("Account \"" + r.getUserName()+ "\" made and initiated");
        //r.deleteEvent("second");
        //r.deleteAssignment("secondAssign");//*/
        //r.display();
        //r.deleteAccount("steve");
    }

}