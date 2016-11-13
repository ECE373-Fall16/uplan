import java.util.*;

public class Driver{
    public static void main(String[] args){
        Client r = new Client();
        //*
        r.createAccount("stevesprofile", "2", "3", "4",10);
        r.addAssignment("ass","classname", "dueDate", 20, 4325);
        r.addAssignment("secondAssign","classname", "dueDate", 20, 4325);
        r.addEvent("name","date",1240,140,"location");
        r.addEvent("second","date",1240,140,"location");
        r.addEvent("third","date",1240,140,"location");//*/
        r.deleteEvent("second");
        r.deleteAssignment("secondAssign");
        r.deleteAccount("stevesprofile");
    }

}