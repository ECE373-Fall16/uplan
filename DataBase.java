import java.sql.*;
import java.util.*;

public class DataBase{
    
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;
    private Connection c = null;
    
    public DataBase(){
        
    }
    
    
    public void createUser(String username, String name, String email, String password, int bedTime) throws SQLException {
        
        try {
            
            createTable(username,"ASSIGNMENT");
            createTable(username,"EVENT"); 
            
        } catch (Exception e){
            System.err.println( "CreateUser:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        try{
            c = connect();
            sql = "INSERT INTO PROFILE VALUES(?,?,?,?,?)";
            //stmt = c.createStatement();
            
            pstmt = c.prepareStatement(sql);
            
            String bed = Integer.toString(bedTime);
            pstmt.setString(1,username);
            pstmt.setString(2,name);
            pstmt.setString(3,email);
            pstmt.setString(4,password);
            pstmt.setString(5,bed);
            pstmt.executeUpdate();
            pstmt.close();
        }catch (Exception e){
            System.err.println( "CreateProfile:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        c.close();
    }
    
    
    public void createTable(String username, String type) throws SQLException {
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
                "PRIORITY TEXT NOT NULL);";
            }
            stmt.executeUpdate(sql);
            stmt.close();
            
        } catch ( Exception e ) {
            System.err.println( "CreateTable:" + e.getClass().getName() + ": " + e.getMessage() );
            
        }
        System.out.println(type + " Tables Created Successfully");
        c.close();
    }
    
    
    public void createAssignment(String name, String user, String className, String dueDate, String toCompletion, String priority) throws SQLException {
        try{
            c = connect();
            //stmt = c.createStatement();
            sql = "INSERT INTO " + user + "ASSIGNMENT VALUES(?,?,?,?,?)";
            pstmt = c.prepareStatement(sql);
            
            //String comp = Integer.toString(toCompletion);
            //String pri = Integer.toString(priority);
            pstmt.setString(1,name);
            pstmt.setString(2,className);
            pstmt.setString(3,dueDate);
            pstmt.setString(4,toCompletion);
            pstmt.setString(5,priority);
            pstmt.executeUpdate();
            pstmt.close();
            
            //stmt.executeUpdate(sql);
            //stmt.close();
            
            System.out.println("Assignment created");
        } catch ( Exception e ) {
            System.err.println( "CreateAssignment:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        c.close();
    }
    
    
    public void createEvent(String name, String username, String days, String startTime, String endTime, String loc) throws SQLException {
        try{
            c = connect();

            //String start = Integer.toString(startTime);
            //String end = Integer.toString(endTime);
            sql = "INSERT INTO " + username + "EVENT VALUES(?,?,?,?,?)";
            pstmt = c.prepareStatement(sql);
            
            pstmt.setString(1,name);
            pstmt.setString(2,days);
            pstmt.setString(3,startTime);
            pstmt.setString(4,endTime);
            pstmt.setString(5,loc);
            pstmt.executeUpdate();
            pstmt.close();
            
            System.out.println("Event created");
        } catch ( Exception e ) {
            System.err.println( "CreateEvent:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        c.close();
    }
    
    
    public void removeProfile(String username) throws SQLException{
        System.out.println("Deleting " + username + "'s AssignmentTable...");
        sql = "DROP TABLE " + username + "ASSIGNMENT";
        try{
            c = connect();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
        c.close();
    }
    
    
    public void removeEvent(String name1, String username) throws SQLException{
        try{
            c = connect();
            sql = "DELETE FROM " + username + "EVENT WHERE EVENTNAME = ?";
            pstmt = c.prepareStatement(sql); 
            // set the corresponding param
            pstmt.setString(1, name1);
            // execute the delete statement
            pstmt.executeUpdate();
//            System.out.println("hey");
            pstmt.close();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        c.close();
    }
    
    
    public void removeAssignment(String name1, String username) throws SQLException{
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
            System.out.println(e.getMessage());
        }
        c.close();
    }
    
    //updates
    public void updateAssignment(String assignmentName, String type, String newData, String user) throws SQLException{
        try{
            System.out.println("Updating " + assignmentName + " assignment to " + newData + " for " + user);
            c = connect();
            sql = "UPDATE " + user + "ASSIGNMENT " + "SET " + type+ " = ? where ASSIGNMENTNAME=?;";
            pstmt = c.prepareStatement(sql); 
            //pstmt.setString(1, type);
            pstmt.setString(1, newData);
            pstmt.setString(2, assignmentName);
            pstmt.executeUpdate();
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println("UpdateAssignment:" + e.getMessage());
        }
        c.close();
    }
    
    
    public void updateEvent(String eventName, String type, String newData, String user) throws SQLException{
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
            System.out.println("UpdateAssignment:" + e.getMessage());
        }
        c.close();
    }
    
    
    public void updateProfile(String type, String newData, String user) throws SQLException{
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
            System.out.println("UpdateAssignment:" + e.getMessage());
        }
        c.close();
    }
    
    
    //displays
    public void display(String user) throws SQLException{
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
                System.out.println(name);
                System.out.println(className);
                System.out.println(dueDate);
                System.out.println(hours);
                System.out.println(pri);
                System.out.println();
            }
            System.out.println("------------------------end");
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.err.println( "Display:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        c.close();
    }
    
    
    public LinkedList<Assignment> getAssignmentList(String user) throws SQLException{
        LinkedList<Assignment> assignList= new LinkedList<Assignment>();
        try{
            c = connect();
            stmt = c.createStatement();
            
            rs = stmt.executeQuery( "SELECT * FROM " + user + "ASSIGNMENT;" );
            
            while(rs.next()){
                String assignName = rs.getString("ASSIGNMENTNAME");
                String className = rs.getString("CLASSNAME");
                String dueDate = rs.getString("DUE");
                String hoursLeft = rs.getString("HOURSTOCOMPLETION");
                String pri = rs.getString("PRIORITY");
                Assignment assign = new Assignment(assignName, className, dueDate, hoursLeft, pri);
                assignList.addLast(assign);
            }
            rs.close();
            stmt.close();
        } catch (Exception e){
            System.err.println( "getAssignmentList:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        
        c.close();
        return assignList;
    }

    
    public LinkedList<Event> getEventList(String user) throws SQLException{
        LinkedList<Event> eventList= new LinkedList<Event>();
        try{
            c = connect();
            stmt = c.createStatement();
            
            rs = stmt.executeQuery( "SELECT * FROM " + user + "EVENT;" );
            
            while(rs.next()){
                String eventName = rs.getString("EVENTNAME");
                String days = rs.getString("DAYS");
                String start = rs.getString("START_TIME");
                String end = rs.getString("END_TIME");
                String loc = rs.getString("LOCATION");
                Event eve = new Event(eventName, days, start, end, loc);
                eventList.addLast(eve);              
            }
            
            rs.close();
            stmt.close();
        } catch (Exception e){
            System.err.println( "getAssignmentList:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        
        c.close();
        return eventList;
    }
    
    
    private Connection connect() throws SQLException{
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
            return c;
        } catch (Exception e){
            System.err.println("Connection:" + e.getClass() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }
}


