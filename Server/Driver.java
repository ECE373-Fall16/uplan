import java.util.*;

public class Driver{
    public static void main(String[] args){
        Client r = new Client();
        //r.login("stevesprofile");
        //*
        r.deleteAccount("steve");
        r.createAccount("steve", "2", "3", "4",10);
        r.login("steve");
        r.addAssignment("Hw1","classname", "dueDate", 2, 4325);
        r.addAssignment("Hw2","classname", "dueDate", 1, 4325);
        r.addAssignment("Hw3","classname", "dueDate", 20, 4325);
        r.addAssignment("Hw4","classname", "dueDate", 20, 4325);
        r.addAssignment("Hw5","classname", "dueDate", 20, 4325);
        r.addAssignment("Hw6","classname", "dueDate", 20, 4325);
        r.addAssignment("Hw7","classname", "dueDate", 20, 4325);
        r.addEvent("Electronics3","Fr",1030,1230,"location");
        r.addEvent("Circuits","Mo",1330,1430,"location");
        r.addEvent("EnginWrit2","Th",1330,1430,"location");
        r.addEvent("ECE3732","Th",930,1130,"location");
        r.addEvent("Electronics","Mo",1030,1230,"location");
        r.addEvent("Circuits3","Fr",1330,1430,"location");
        r.addEvent("ECE373","Tu",930,1130,"location");
        r.addEvent("Electronics2","We",1030,1230,"location");
        r.addEvent("Circuits2","We",1330,1430,"location");
        r.addEvent("EnginWrit","Tu",1330,1430,"location");
        r.printLists();
        System.out.println("Account \"steve\" made and initiated");
        //r.deleteEvent("second");
        //r.deleteAssignment("secondAssign");//*/
        //r.display();
        //r.deleteAccount("steve");
    }

}