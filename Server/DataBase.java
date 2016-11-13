import java.sql.*;

public class DataBase{
    
    private Statement stmt = null;
    private String sql = null;
    
    public DataBase(){
        //c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
        //connect();
        ;
    }
    
    public void createUser(String username) throws SQLException {
        try {
            Connection c = connect();
            stmt = c.createStatement();
            
            createTable(username,"ASSIGNMENT");
            createTable(username,"EVENT");
            createTable(username,"PROFILE");
            //System.out.println(username);
            stmt.close();
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        

    }
    
    public void createTable(String username, String type) throws SQLException {
        try{
            //c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
            Connection c = connect();
            stmt = c.createStatement();
            if(type.equals("EVENT")){
                sql = "CREATE TABLE " + username + type + "(" + 
                "CLASSNAME TEXT PRIMARY KEY NOT NULL," +
                "DAYS TEXT NOT NULL," +
                "START_TIME TEXT NOT NULL," + 
                "END_TIME TEXT NOT NULL," + 
                "LOCATION TEXT NOT NULL);";
            }
            if(type.equals("ASSIGNMENT")){
                sql = "CREATE TABLE " + username + type + "(" +
                "NAME TEXT PRIMARY KEY NOT NULL," +
                "CLASSNAME TEXT NOT NULL," + 
                "DUE TEXT NOT NULL," +
                "HOURSTOCOMPLETION TEXT NOT NULL," +
                "PRIORITY TEXT NOT NULL);";
            }
            if(type.equals("PROFILE")){
                sql = "CREATE TABLE " + username + type + "(" +
                "NAME TEXT PRIMARY KEY NOT NULL," +
                "USERNAME TEXT NOT NULL," + 
                "EMAIL TEXT NOT NULL," +
                "PASSWORD TEXT NOT NULL," +
                "BEDTIME TEXT NOT NULL);";
            }
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            
        }
        System.out.println(type + " Tables Created Successfully");
    }
    
    public void createAssignment(String name, String user, String className, String dueDate, int toCompletion, int priority) throws SQLException {
        try{
            Connection c = connect();
            stmt = c.createStatement();
            sql = "INSERT INTO DavidASSIGNMENT (NAME, CLASSNAME, DUE, HOURSTOCOMPLETION, PRIORITY)" + 
                "VALUES (" + name + ", " + className + ", " + dueDate + ", " + 
                Integer.toString(toCompletion) + ", " + Integer.toString(priority) + ");";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    
    private Connection connect(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
            return c;
        } catch (Exception e){
            System.err.println(e.getClass() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }
}


