import java.util.*;

public class Driver{
    public static void main(String[] args){
        Client r = new Client();
        //r.login("stevesprofile");
        //*
        r.createAccount("steve", "2", "3", "4",10);
        r.login("steve");
        r.addAssignment("ass","classname", "dueDate", 20, 4325);
        r.addAssignment("secondAssign","classname", "dueDate", 20, 4325);
        r.addEvent("name","Fr",1520,1830,"location");
        r.addEvent("second","Mo",1250,1340,"location");
        r.addEvent("third","Mo",930,230,"location");///
        r.printLists();
        //r.deleteEvent("second");
        //r.deleteAssignment("secondAssign");//*/
        //r.display();
        //r.deleteAccount("steve");
    }

}