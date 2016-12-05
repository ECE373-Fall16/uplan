import java.sql.*;
import java.util.*;
import java.text.*;

public class DataBase{
    
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;
    private Connection c = null;
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
    

    public DataBase(){
        
    }
    
    
    public int createUser(String username, String name, String email, String password, String bedTime) throws SQLException {
        int valid = 1;
        try {
            
            int a = createTable(username,"ASSIGNMENT");
            int b = createTable(username,"EVENT"); 
            int c = createTable(username,"SCHEDULE");

            if(a == 1 && b == 1 && c == 1)
                valid = 0;
   
        } catch (Exception e){
            System.err.println( "DatabaseCreateUser:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        try{
            c = connect();
            sql = "INSERT INTO PROFILE VALUES(?,?,?,?,?)";
            
            pstmt = c.prepareStatement(sql);
            
            pstmt.setString(1,username);
            pstmt.setString(2,name);
            pstmt.setString(3,email);
            pstmt.setString(4,password);
            pstmt.setString(5,bedTime);
            pstmt.executeUpdate();
            pstmt.close();

        }catch (Exception e){
            System.err.println( "DatabaseCreateProfile:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public int createTable(String username, String type) throws SQLException {
        int valid = 1;
        try{
            c = connect();
            stmt = c.createStatement();
            if(type.equals("EVENT")){
                sql = "CREATE TABLE " + username + "EVENT(" + 
                "EVENTNAME TEXT PRIMARY KEY NOT NULL," +
                "DAYS TEXT NOT NULL," +
                "START_TIME TEXT NOT NULL," + 
                "END_TIME TEXT NOT NULL," + 
                "LOCATION TEXT NOT NULL);";
            }
            if(type.equals("ASSIGNMENT")){
                sql = "CREATE TABLE " + username + "ASSIGNMENT(" +
                "ASSIGNMENTNAME TEXT PRIMARY KEY NOT NULL," +
                "CLASSNAME TEXT NOT NULL," + 
                "DUE TEXT NOT NULL," +
                "HOURSTOCOMPLETION TEXT NOT NULL," +
                "PRIORITY TEXT NOT NULL," +
                "APPPRIORITY TEXT NOT NULL);";
            }
            if(type.equals("SCHEDULE")){
                sql = "CREATE TABLE " + username + "SCHEDULE(" +
                "NAME TEXT PRIMARY KEY NOT NULL," +
                "DAY TEXT NOT NULL," + 
                "START_TIME TEXT NOT NULL," +
                "END_TIME TEXT NOT NULL," +
                "LOCATION TEXT NOT NULL);";
            }
            stmt.executeUpdate(sql);
            stmt.close();
            
        } catch ( Exception e ) {
            System.err.println( "DatabaseCreateTable:" + e.getClass().getName() + ": " + e.getMessage() );    
            valid = 0;
        }

        System.out.println(type + " Tables Created Successfully");
        c.close();
        return valid;
    }
    
    
    public int valUser(String username, String password) throws SQLException{
        int valid = 1;
        try{
            c = connect();
            stmt = c.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM PROFILE;" );
            while (rs.next()){
                String curUser = rs.getString("USERNAME");
                String curPass = rs.getString("PASSWORD");
                if(curUser.equals(username) && curPass.equals(password)){
                    rs.close();
                    stmt.close();
                    c.close();
                }
            } 
        } catch(Exception e){
            System.err.println( "DatabaseValidateUser:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }
        rs.close();
        stmt.close();
        c.close();
        return valid;
    }
    

    public int createAssignment(String name, String user, String className, String dueDate, String toCompletion, String priority, String appPriority) throws SQLException {
        int valid = 1;
        try{
            c = connect();
            sql = "INSERT INTO " + user + "ASSIGNMENT VALUES(?,?,?,?,?,?)";
            pstmt = c.prepareStatement(sql);
            
            pstmt.setString(1,name);
            pstmt.setString(2,className);
            pstmt.setString(3,dueDate);
            pstmt.setString(4,toCompletion);
            pstmt.setString(5,priority);
            pstmt.setString(6,appPriority);
            pstmt.executeUpdate();
            pstmt.close();
            
            System.out.println("Assignment created");

        } catch ( Exception e ) {
            System.err.println( "DatabaseCreateAssignment:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public int createEvent(String name, String username, String repeatDays, String startTime, String endTime, String loc) throws SQLException {
        int valid = 1;
        try{
            c = connect();

            //String start = Integer.toString(startTime);
            //String end = Integer.toString(endTime);
            sql = "INSERT INTO " + username + "EVENT VALUES(?,?,?,?,?)";
            pstmt = c.prepareStatement(sql);
            
            pstmt.setString(1,name);
            pstmt.setString(2,repeatDays);
            pstmt.setString(3,startTime);
            pstmt.setString(4,endTime);
            pstmt.setString(5,loc);
            pstmt.executeUpdate();
            pstmt.close();
            
            System.out.println("Event created");
        } catch ( Exception e ) {
            System.err.println( "DatabaseCreateEvent:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        c.close();
        return valid;

    }
    
    
    public int removeProfile(String username) throws SQLException{
        int valid = 1;
        System.out.println("Deleting " + username + "'s AssignmentTable...");
        sql = "DROP TABLE " + username + "ASSIGNMENT";
        
        try{
            c = connect();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            valid = 0;
        }
        
        System.out.println("Deleting " + username + "'s EventTable...");
        sql = "DROP TABLE " + username + "EVENT";
        
        try{
            if(c == null)
                c = connect();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
 
        } catch (SQLException e) {
            System.out.println("DatabaseRemoveProfile1: " + e.getMessage());
            valid = 0;
        }
        
        System.out.println("Deleting Profile " + username + "...");
        sql = "DELETE FROM PROFILE WHERE USERNAME = ?";
        
        try{
            if(c == null)
                c = connect();
            pstmt = c.prepareStatement(sql); 
            // set the corresponding param
            pstmt.setString(1, username);
            // execute the delete statement
            pstmt.executeUpdate();
            pstmt.close();
 
        } catch (SQLException e) {
            System.out.println("DatabaseRemoveProfile2: " + e.getMessage());
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public int removeEvent(String name1, String username) throws SQLException{
        int valid = 1;
        try{
            c = connect();
            sql = "DELETE FROM " + username + "EVENT WHERE EVENTNAME = ?";
            pstmt = c.prepareStatement(sql); 
            // set the corresponding param
            pstmt.setString(1, name1);
            // execute the delete statement
            pstmt.executeUpdate();
            pstmt.close();
 
        } catch (SQLException e) {
            System.out.println("DatabaseRemoveEvent: " + e.getMessage());
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public int removeAssignment(String name1, String username) throws SQLException{
        int valid = 1;
        try{
            c = connect();
            sql = "DELETE FROM " + username + "ASSIGNMENT WHERE ASSIGNMENTNAME = ?";
            pstmt = c.prepareStatement(sql); 
            // set the corresponding param
            pstmt.setString(1, name1);
            // execute the delete statement
            pstmt.executeUpdate();
            pstmt.close();
 
        } catch (SQLException e) {
            System.out.println("DatabaseRemoveAssignment: " + e.getMessage());
            valid = 0;;
        }

        c.close();
        return valid;
    }
    

    //updates
    public int updateAssignment(String assignmentName, String type, String newData, String user) throws SQLException{
        int valid = 1;
        try{
            System.out.println("Updating " + assignmentName + " assignment to " + newData + " for " + user);
            c = connect();
            sql = "UPDATE " + user + "ASSIGNMENT " + "SET " + type+ " = ? where ASSIGNMENTNAME=?;";
            pstmt = c.prepareStatement(sql); 
            pstmt.setString(1, newData);
            pstmt.setString(2, assignmentName);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println("DatabaseUpdateAssignment: " + e.getMessage());
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public int updateEvent(String eventName, String type, String newData, String user) throws SQLException{
        int valid = 1;
        try{
            System.out.println("Updating " + eventName + " event to " + newData + " for " + user);
            c = connect();
            sql = "UPDATE " + user + "EVENT " + "SET " + type + " = ? where EVENTNAME=?;";
            pstmt = c.prepareStatement(sql); 
            pstmt.setString(1, newData);
            pstmt.setString(2, eventName);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println("DatabaseUpdateEvent: " + e.getMessage());
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public int updateProfile(String type, String newData, String user) throws SQLException{
        int valid = 1;
        try{
            System.out.println("Updating " + user + " profile to " + newData + " for " + user);
            c = connect();
            sql = "UPDATE PROFILE " + "SET " + type + " = ? where USERNAME=?;";
            pstmt = c.prepareStatement(sql); 
            pstmt.setString(1, newData);
            pstmt.setString(2, user);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println("DatabaseUpdateProfile: " + e.getMessage());
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    //schedule section
    public int clearSchedule(String user) throws SQLException{
        int valid = 1;
        try{
            System.out.println("Clearing " + user + " schedule");
            c = connect();
            sql = "DELETE FROM " + user + "SCHEDULE;";
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException e) {
            System.out.println("DatabaseClearSchedule: " + e.getMessage());
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public int saveSchedule(String user, LinkedList<CalendarEvent> calList) {
        int valid = 1;
        try{
            System.out.println("Saving " + user + " schedule");
            clearSchedule(user);
            ListIterator calIter = calList.listIterator();
            while(calIter.hasNext()){
                CalendarEvent curCal = calList.get(calIter.nextIndex());
                valid = addCalEvent(user, curCal);
                calIter.next();
            }
        } catch (Exception e) {
            System.err.println( "DatabaseSaveSchedule:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }
        return valid;
    }


    public int addCalEvent(String username, CalendarEvent curCal) throws SQLException{
        int valid = 1;
        try{
            c = connect();

            sql = "INSERT INTO " + username + "SCHEDULE VALUES(?,?,?,?)";
            pstmt = c.prepareStatement(sql);
            
            pstmt.setString(1,curCal.getName());
            String startDate = df.format(curCal.getStartTime());
            pstmt.setString(2,startDate);
            String endDate = df.format(curCal.getEndTime());
            pstmt.setString(3,endDate);
            pstmt.setString(4,curCal.getLocation());
            pstmt.executeUpdate();
            pstmt.close();
            
            System.out.println("CalendarEvent created");

        } catch (Exception e){
            System.err.println( "DatabaseCreateCalenadarEvent:" + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        c.close();
        return valid;
    }


    public LinkedList<CalendarEvent> getSchedule(String username) throws SQLException{
        LinkedList<CalendarEvent> calList= new LinkedList<CalendarEvent>();
        try{
            c = connect();
            stmt = c.createStatement();
            
            rs = stmt.executeQuery( "SELECT * FROM " + username + "SCHEDULE;" );
            
            while(rs.next()){
                String name = rs.getString("NAME");
                String start = rs.getString("START_TIME");
                String end = rs.getString("END_TIME");
                String location = rs.getString("LOCATION");
                java.util.Date startTime = df.parse(start);
                java.util.Date endTime = df.parse(end);
                CalendarEvent curCal = new CalendarEvent(name, startTime, endTime, location);
                calList.add(curCal);
            }
            rs.close();
            stmt.close();

        } catch (Exception e){
            System.err.println( "DatabaseGetSchedule:" + e.getClass().getName() + ": " + e.getMessage() );
            calList = null;
        }
        
        c.close();
        return calList;
    }
    
    
    //displays
    public int display(String user) throws SQLException{
        int valid = 1;
        Connection c = null;
        try{
            c = connect();
            stmt = c.createStatement();
            
            rs = stmt.executeQuery( "SELECT * FROM PROFILE;" );
            
            System.out.println("\nPrinting DataBase...\n");
            System.out.println("Profile List:\n------------------------");
            while(rs.next()){
                String username = rs.getString("username");
                String name = rs.getString("profilename");
                String email = rs.getString("email");
                String pass = rs.getString("password");
                String bed = rs.getString("bedtime");
                System.out.println(username);
                System.out.println(name);
                System.out.println(email);
                System.out.println(pass);
                System.out.println(bed);
                System.out.println();
            }
            rs.close();
            
            System.out.println("Event List:\n------------------------");
            
            rs = stmt.executeQuery( "SELECT * FROM " + user + "EVENT;" );
            while(rs.next()){
                String name = rs.getString("EVENTNAME");
                String days = rs.getString("days");
                String start = rs.getString("start_time");
                String end = rs.getString("end_time");
                String loc = rs.getString("location");
                System.out.println(name);
                System.out.println(days);
                System.out.println(start);
                System.out.println(end);
                System.out.println(loc);
                System.out.println();
            }
            rs.close();
            
            System.out.println("Assignment List:\n------------------------");
            
            rs = stmt.executeQuery( "SELECT * FROM " + user + "ASSIGNMENT;" );
            while(rs.next()){
                String name = rs.getString("ASSIGNMENTNAME");
                String className = rs.getString("classname");
                String dueDate = rs.getString("due");
                String hours = rs.getString("hourstocompletion");
                String pri = rs.getString("priority");
                String appPri = rs.getString("apppriority");
                System.out.println(name);
                System.out.println(className);
                System.out.println(dueDate);
                System.out.println(hours);
                System.out.println(pri);
                System.out.println(appPri);
                System.out.println();
            }
            System.out.println("------------------------end");
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.err.println( "DatabaseDisplay: " + e.getClass().getName() + ": " + e.getMessage() );
            valid = 0;
        }

        c.close();
        return valid;
    }
    
    
    public LinkedList<Assignment> getAssignmentList(String user) throws SQLException{
        LinkedList<Assignment> assignList= new LinkedList<Assignment>();
        try{
            c = connect();
            stmt = c.createStatement();
            
            rs = stmt.executeQuery( "SELECT * FROM " + user + "ASSIGNMENT;" );
            
            while(rs.next()){
                String name = rs.getString("ASSIGNMENTNAME");
                String className = rs.getString("classname");
                String dueDate = rs.getString("due");
                String hours = rs.getString("hourstocompletion");
                String pri = rs.getString("priority");
                String appPri = rs.getString("apppriority");
                java.util.Date due = df.parse(dueDate);
                Assignment assign = new Assignment(name, className, due, hours, pri, appPri);
                assignList.add(assign);
            }
            rs.close();
            stmt.close();
        } catch (Exception e){
            System.err.println( "DatabaseGetAssignmentList:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        
        c.close();
        return assignList;
    }

    
    public LinkedList<Event> getEventList(String user) throws SQLException{
        LinkedList<Event> eventList= new LinkedList<Event>();
        df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        try{
            c = connect();
            stmt = c.createStatement();
            
            rs = stmt.executeQuery( "SELECT * FROM " + user + "EVENT;" );
            
            while(rs.next()){
                String eventName = rs.getString("EVENTNAME");
                String days = rs.getString("DAYS");
                String start = rs.getString("START_TIME");
                java.util.Date startTime = df.parse(start);
                String end = rs.getString("END_TIME");
                java.util.Date endTime = df.parse(end);
                String loc = rs.getString("LOCATION");
                Event eve = new Event(eventName, days, startTime, endTime, loc);
                eventList.addLast(eve);              
            }
            
            rs.close();
            stmt.close();
        } catch (Exception e){
            System.err.println( "DatabaseGetEventList:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        
        c.close();
        return eventList;
    }

    public int[] getBedTime(String user)throws SQLException{
        String bedTime = "";
        int[] times = new int[2];
        Boolean found = false;
        try{
            c = connect();
            stmt= c.createStatement();

            rs = stmt.executeQuery( "SELECT USERNAME,BEDTIME FROM PROFILE" );

            while(rs.next() && !found){
                if(rs.getString("USERNAME").equals(user)){
                    bedTime = rs.getString("BEDTIME");
                    found = true;
                }
            }
            System.out.println(bedTime);
            
            rs.close();
            stmt.close();

        } catch (Exception e){
            System.err.println( "DatabaseGetBedTime:" + e.getClass().getName() + ": " + e.getMessage() );
        }

        char[] bedToChar = bedTime.toCharArray();
        char[] temp = new char[2];
        int count = 0;
        int intArrayIndex = 0;

        while (count < 4){
            temp[0] = bedToChar[count++];
            temp[1] = bedToChar[count++];

            bedTime = new String(temp);
            int bed = Integer.parseInt(bedTime);

            times[intArrayIndex++] = bed;
        }
        
        c.close();
        return times;
    }
    
    
    private Connection connect() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
            return c;
        } catch (Exception e){
            System.err.println("DatabaseConnection:" + e.getClass() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }
}


