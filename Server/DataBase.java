import java.sql.*;

public class DataBase{
    
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;
    
    public DataBase(){
        //c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
        //connect();
        ;
    }
    
    
    public void createUser(String username, String name, String email, String password, int bedTime) throws SQLException {
        Connection c = null;
        
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
        Connection c = null;
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
    
    public void createAssignment(String name, String user, String className, String dueDate, int toCompletion, int priority) throws SQLException {
        Connection c = null;
        try{
            c = connect();
            //stmt = c.createStatement();
            sql = "INSERT INTO " + user + "ASSIGNMENT VALUES(?,?,?,?,?)";
            pstmt = c.prepareStatement(sql);
            
            String comp = Integer.toString(toCompletion);
            String pri = Integer.toString(priority);
            pstmt.setString(1,name);
            pstmt.setString(2,className);
            pstmt.setString(3,dueDate);
            pstmt.setString(4,comp);
            pstmt.setString(5,pri);
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
    
    public void createEvent(String name, String username, String days, int startTime, int endTime, String loc) throws SQLException {
        Connection c = null;
        try{
            c = connect();

            String start = Integer.toString(startTime);
            String end = Integer.toString(endTime);
            sql = "INSERT INTO " + username + "EVENT VALUES(?,?,?,?,?)";
            pstmt = c.prepareStatement(sql);
            
            pstmt.setString(1,name);
            pstmt.setString(2,days);
            pstmt.setString(3,start);
            pstmt.setString(4,end);
            pstmt.setString(5,loc);
            pstmt.executeUpdate();
            pstmt.close();
            
            System.out.println("Event created");
        } catch ( Exception e ) {
            System.err.println( "CreateEvent:" + e.getClass().getName() + ": " + e.getMessage() );
        }
        c.close();
    }
    
   
    
    private Connection connect(){
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
    
     public void removeProfile(String username) throws SQLException{
        Connection c = null;
        System.out.println("Deleting AssignmentTable...");
        sql = "DROP TABLE " + username + "ASSIGNMENT";
        try{
            c = connect();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Deleting EventTable...");
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
        display(username);
        sql = "DELETE FROM " + username + "EVENT WHERE EVENTNAME = ?";
        Connection c = null;
        try{
            c = connect();
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
        sql = "DELETE FROM " + username + "ASSIGNMENT WHERE ASSIGNMENTNAME = ?";
        Connection c = null;
        try{
            c = connect();
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
        display(username);
    }
    
    public void display(String user) throws SQLException{
        Connection c = null;
        try{
            c = connect();
            stmt = c.createStatement();
            
            rs = stmt.executeQuery( "SELECT * FROM PROFILE;" );
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
            
            System.out.println("------------------------");
            
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
            
            System.out.println("------------------------");
            
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
}


