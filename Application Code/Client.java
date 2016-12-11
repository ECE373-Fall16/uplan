import java.util.*;
import java.text.*;
import org.apache.xmlrpc.*;

public class Client {
    
    private String username;
    private static String SERVER_ADDR = "http://104.154.192.22:8000/RPC2";

    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
    
    
    public Client(){
        ;
    }
    
    
    public Client(String user){
        username = user;
    }
    
    
    public String getUserName(){
        return username;
    }
    
    
    public boolean login(String user, String pass){
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            
            params.addElement(user);
            params.addElement(pass);
            
            Vector returnValue = (Vector)server.execute("sample.validateUser", params);
            
            if(Integer.parseInt(returnValue.get(0).toString()) == 1){
                username = user;
                System.out.println("You are logged in as: " + username);
                return true;
            }
            else{
                System.out.println("Invalid Username or Password");
                return false;
            }
        } catch (Exception e){
            System.err.println("ClientLogin: " + e);
        }
        return false;
    }
    

    public void logout(){
        username = null;
    }
    

    public int createAccount(String user, String name, String email, String password, String bedtime){

        bedtime = formatBedTime(bedtime);

        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();

            params.addElement(user);
            params.addElement(name);
            params.addElement(email);
            params.addElement(password);
            params.addElement(bedtime);
  
            Vector returnValue = (Vector)server.execute("sample.createAccount", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } 
        catch (Exception exception) {
            System.err.println("ClientCreateAccount " + exception);
        } 

        return 0;       
    }


    public Profile getAccountInfo(){
        Profile curUser = null;
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();

            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.getAccountInfo", params);

            String user = returnValue.get(0).toString();
            String name = returnValue.get(1).toString();
            String email = returnValue.get(2).toString();
            String bedtime = returnValue.get(3).toString();

            curUser = new Profile(user, name, email, bedtime);

        } catch (Exception e){
            System.err.println("ClientGetAccountInfo " + e);
        }

        return curUser;
    }
    
    
    public LinkedList<CalendarEvent> display(){
        LinkedList<CalendarEvent> calList = null;
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR);
            Vector params = new Vector();
            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.display", params);
            calList = vectorToCalList(returnValue);

            ListIterator iter = calList.listIterator();
            while(iter.hasNext()){
                System.out.println(calList.get(iter.nextIndex()).toString());
                iter.next();
            }
        } catch (Exception exception) {
            System.err.println("ClientDisplay " + exception);
        } 
        return calList;
    }
    
    
    public int addEvent(String name, String day, String startDay, String startHour, String endDay, String endHour, String location){
        try {

            String startTime = formatDate(startDay, startHour);
            String endTime = formatDate(endDay, endHour);

            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name); 
            params.addElement(username);
            params.addElement(day);         
            params.addElement(startTime); 
            params.addElement(endTime);
            params.addElement(location);         

            Vector returnValue = (Vector)server.execute("sample.addEvent", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;

        } catch (Exception exception) {
            System.err.println("ClientAddEvent: " + exception);
        } 

        return 0; 
    }
    
    
    public int addAssignment(String name, String classname, String dueDate, String dueHour, String hours, String priority, String appPriority){
        try {
            
            String finalDue = formatDate(dueDate, dueHour);

            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
            params.addElement(classname);
            params.addElement(finalDue);
            params.addElement(hours);
            params.addElement(priority);
            params.addElement(appPriority);
  
            Vector returnValue = (Vector)server.execute("sample.addAssignment", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;

        } catch (Exception exception) {
            System.err.println("ClientAddEvent: " + exception);
        }

        return 0;     
    }
    
    
    public int deleteEvent(String name){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
  
            Vector returnValue = (Vector)server.execute("sample.deleteEvent", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;

        } catch (Exception exception) {
            System.err.println("ClientDeleteEvent: " + exception);
        } 

        return 0;
    }
    
    
    public int deleteAssignment(String name){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(name);
            params.addElement(username);
  
            Vector returnValue = (Vector)server.execute("sample.deleteAssignment", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } catch (Exception exception) {
            System.err.println("ClientDeleteAssignment: " + exception);
        } 

        return 0;
    }
    
    
    public int deleteAccount(String user){
        int valid = 1;
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(user);
  
            Vector returnValue = (Vector)server.execute("sample.deleteAccount", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 0)
                valid = 0;
        } catch (Exception exception) {
            System.err.println("ClientDeleteAccount: " + exception);
        }
        if(user.equals(username)){
            username = null;
        }

        return valid;
    }
    
    
    public void updateAssignment(String assignmentName, String type, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(assignmentName);
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.updateAssignment", params);
        } catch (Exception exception) {
            System.err.println("ClientUpdateAssignment: " + exception);
        }
    }
    
    
    public int updateEvent(String eventName, String type, String newName){
        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(eventName);
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);
            
            Vector returnValue = (Vector)server.execute("sample.updateEvent", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } catch (Exception exception) {
            System.err.println("ClientUpdateEvent: " + exception);
        }

        return 0;
    }
    
    
    public int updateProfile(String type, String newName){
        
        if(type.equals("BEDTIME"))
            newName = formatBedTime(newName);

        try {
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(type);
            params.addElement(newName);
            params.addElement(username);
            
            Vector returnValue = (Vector)server.execute("sample.updateProfile", params);

            if(Integer.parseInt(returnValue.get(0).toString()) == 1)
                return 1;
        } catch (Exception exception) {
            System.err.println("ClientUpdateProfile: " + exception);
        }

        return 0;
    }


    public LinkedList<Assignment> getAssignmentList(){
        LinkedList<Assignment> assignList = new LinkedList<Assignment>();

        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(username);
            
            Vector returnValue = (Vector)server.execute("sample.getAssignmentList", params);

            assignList = vectorToAssignList(returnValue);

        } catch (Exception exception) {
            System.err.println("ClientGetAssignmentList: " + exception);
        }

        ListIterator iter = assignList.listIterator();
        while(iter.hasNext()){
            System.out.println(assignList.get(iter.nextIndex()).toString());
            iter.next();
        }
        System.out.println("");

        return assignList;
    }


    public LinkedList<Event> getEventList(){
        LinkedList<Event> eventList = new LinkedList<Event>();

        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.getEventList", params);

            eventList = vectorToEventList(returnValue);
        } catch (Exception exception) {
            System.err.println("ClientGetEventList: " + exception);
        }

        ListIterator iter = eventList.listIterator();
        while(iter.hasNext()){
            System.out.println(eventList.get(iter.nextIndex()).toString());
            iter.next();
        }
        System.out.println("");

        return eventList;
    }
    
    
    public LinkedList<CalendarEvent> schedule(){         //Creates schedule and returns list
        LinkedList<CalendarEvent> calList = null;
        try{
            XmlRpcClient server = new XmlRpcClient(SERVER_ADDR); 
            Vector params = new Vector();
            params.addElement(username);

            Vector returnValue = (Vector)server.execute("sample.scheduleAlgo", params);
            
            calList = vectorToCalList(returnValue);

            ListIterator calIter = calList.listIterator();
            while(calIter.hasNext()){
                System.out.println(calList.get(calIter.nextIndex()));
                calIter.next();
            }
            
        } catch (Exception e){
            System.err.println("ClientSchedule: " + e);
        }

        return calList;
    }
    
    
    private LinkedList<CalendarEvent> vectorToCalList(Vector vectorList) throws ParseException{       //going to be used to parse vector lists into Calendar object list or 2d array
        LinkedList<CalendarEvent> calList = new LinkedList<CalendarEvent>();
        Iterator iter = vectorList.iterator();
        int calCount = 1;       //number of calendar objects
        CalendarEvent temp;

        try{
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
            df.setTimeZone(TimeZone.getTimeZone("EST"));
            String name;
            Date start;
            Date end;
            String loc;
            boolean dis;
            int id;
            int counter = 0;
            while(iter.hasNext()){
                name = iter.next().toString();
                start = df.parse(iter.next().toString());
                end = df.parse(iter.next().toString());
                loc = iter.next().toString();
                dis = Boolean.parseBoolean(iter.next().toString());
                id = Integer.parseInt(iter.next().toString());
                temp = new CalendarEvent(name, start, end, loc, dis, id);
                calList.add(temp);
                calCount++;
            }

        } catch (Exception e){
            System.err.println("ClientVectorToCalList: " + e);
        }

        return calList;
    }


    private LinkedList<Assignment> vectorToAssignList(Vector vectorList) throws ParseException{
        LinkedList<Assignment> assignList = new LinkedList<Assignment>();
        Iterator iter = vectorList.iterator();

        try{
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
            df.setTimeZone(TimeZone.getTimeZone("EST"));

            String assignName;
            String className;
            Date dueDate;
            String hours;
            String pri;
            String appPri;
            int id;

            while(iter.hasNext()){
                assignName = iter.next().toString();
                className = iter.next().toString();
                dueDate = df.parse(iter.next().toString());
                hours = iter.next().toString();
                pri = iter.next().toString();
                appPri = iter.next().toString();
                id = Integer.parseInt(iter.next().toString());

                Assignment temp = new Assignment(assignName, className, dueDate, hours, pri, appPri, id);
                assignList.add(temp);
            }

        } catch (Exception e){
            System.err.println("ClientVectorToAssignList: " + e);
        }

        return assignList;
    }


    private LinkedList<Event> vectorToEventList(Vector vectorList) throws ParseException{
        LinkedList<Event> eventList = new LinkedList<Event>();
        Iterator iter = vectorList.iterator();

        try{
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
            df.setTimeZone(TimeZone.getTimeZone("EST"));

            String name;
            String reDays;
            Date start;
            Date end;
            String loc;

            while(iter.hasNext()){
                name = iter.next().toString();
                reDays = iter.next().toString();
                start = df.parse(iter.next().toString());
                end = df.parse(iter.next().toString());
                loc = iter.next().toString();

                Event temp = new Event(name, reDays, start, end, loc);
                eventList.add(temp);
            }
        } catch (Exception e){
            System.err.println("ClientVectorToEventList: " + e);

        }

        return eventList;
    }


    public String formatBedTime(String time){
        char[] bed = time.toCharArray();
        char[] temp = new char[2];

        int size = bed.length;
        int index = 0;

        String hour = "";
        String min = "";
        String offset = "";

        while(index < size){
            if(size == 7){
                if(index == 0){
                    temp[0] = bed[index++];
                    temp[1] = bed[index++];
                    hour = new String(temp);
                }
                else if(index == 2){
                    index++;
                    temp[0] = bed[index++];
                    temp[1] = bed[index++];
                    min = new String(temp);
                }
                else{
                    temp[0] = bed[index++];
                    temp[1] = bed[index++];
                    offset = new String(temp);
                }
            }
            else{
                if(index == 0){
                    temp[0] = 0;
                    temp[1] = bed[index++];
                    hour = new String(temp);
                }
                else if(index == 1){
                    index++;
                    temp[0] = bed[index++];
                    temp[1] = bed[index++];
                    min = new String(temp);
                }
                else{
                    temp[0] = bed[index++];
                    temp[1] = bed[index++];
                    offset = new String(temp);
                }
            }

        }

        int tempHour = Integer.parseInt(hour);

        if(offset.equals("pm"))
            tempHour = tempHour + 12;

        hour = Integer.toString(tempHour);

        String finalTime = hour + min;

        return finalTime;
    }


    public String formatDate(String dueDay, String dueHour){
        Calendar temp = Calendar.getInstance();
            char[] dueChar = dueDay.toCharArray();
            char[] hourChar = dueHour.toCharArray();
            char[] tempChar = new char[2];

            int counter = 0;
            int dueSize = dueChar.length;
            int hourSize = hourChar.length;

            String m = "";
            String d = "";
            String y = "";
            String h = "";
            String min = "";
            String offset = "";

            if(dueSize == 8){
                tempChar[0] = dueChar[0];
                tempChar[1] = dueChar[1];
                m = new String(tempChar);

                tempChar[0] = dueChar[3];
                tempChar[1] = dueChar[4];
                d = new String(tempChar);    

                tempChar[0] = dueChar[6];
                tempChar[1] = dueChar[7];
                y = new String(tempChar);
            }

            else if(dueSize == 7){
                tempChar[0] = dueChar[0];
                tempChar[1] = dueChar[1];
                m = new String(tempChar);

                tempChar[0] = 0;
                tempChar[1] = dueChar[3];
                d = new String(tempChar);    

                tempChar[0] = dueChar[5];
                tempChar[1] = dueChar[6];
                y = new String(tempChar);
            }

            if(hourSize == 7){
                tempChar[0] = hourChar[0];
                tempChar[1] = hourChar[1];
                h = new String(tempChar);

                tempChar[0] = hourChar[3];
                tempChar[1] = hourChar[4];
                min = new String(tempChar);

                tempChar[0] = hourChar[5];
                tempChar[1] = hourChar[6];
                offset = new String(tempChar);
            }

            else if(hourSize == 6){
                tempChar[0] = 0;
                tempChar[1] = hourChar[0];
                h = new String(tempChar);

                tempChar[0] = hourChar[2];
                tempChar[1] = hourChar[3];
                min = new String(tempChar);

                tempChar[0] = hourChar[4];
                tempChar[1] = hourChar[5];
                offset = new String(tempChar);
            }

            int month = Integer.parseInt(m) - 1;
            int day = Integer.parseInt(d);
            int year = Integer.parseInt(y) + 2000;
            int hour = hour = Integer.parseInt(h);
            

            if(offset.equals("pm") && hour != 12)
                hour = hour + 12;

            int minute = Integer.parseInt(min);

            temp.set(Calendar.MONTH, month);
            temp.set(Calendar.DAY_OF_MONTH, day);
            temp.set(Calendar.YEAR, year);
            temp.set(Calendar.HOUR_OF_DAY, hour);
            temp.set(Calendar.MINUTE, minute);


            Date tempDue = temp.getTime();

            String finalDue = df.format(tempDue);

            return finalDue;
    }
    
}