import java.sql.*;
//import java.util.Vector;
//import java.util.Iterator;
import java.util.*;
import java.io.*;

public class DataBase {
    private Connection c;
    private Statement stmt;
    private String sql;

    public DataBase(){
        ;
    }
    /*
      public static void main( String args[] )
      {
        Statement stmt = null;
        Connection c = null;
        String sql = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
        */
       public void connect(){
           c = null;
           try {
               Class.forName("org.sqlite.JDBC");
               c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
           } catch (Exception e){
               System.err.println( e.getClass().getName() + ": " + e.getMessage() );
               System.exit(0);
           }
           System.out.println("Opened database successfully");
           //c.close();
       }
       /*
        public void connect(){
        Statement stmt = null;
            Connection c = null;
            String sql = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
            } catch ( Exception e ) {
            //System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            }
            System.out.println("Opened database successfully");
    }
    
        
    /*/
    public void createUser(String username){
        try {
            //createTable(username,"ASSIGNMENT");
            //createTable(username,"EVENT");
            System.out.println(username);
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

    }
/*
    public void createTable(String username, String type){
        try{
            stmt = c.createStatement();
            if(type.equals("ASSIGNMENT")){
                sql = "CREATE TABLE " + username + " " + type + "(NAME STRING PRIMARY KEY NOT NULL" + "CLASSNAME STRING NOT NULL," + "START_TIME STRING NOT NULL," + "END_TIME STRING NOT NULL," + "LOCATION STRING NOT NULL);";
            }
            if(type.equals("EVENT")){
                sql = "CREATE TABLE " + username + " " + type + "(NAME STRING PRIMARY KEY NOT NULL" + "DAY STRING NOT NULL," + "DAYS_LEFT STRING NOT NULL," + "PRIORITY STRING NOT NULL," + "HOURS STRING NOT NULL);";
            }
            stmt.excecuteUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        //System.out.println("User Tables Created Successfully");
    }

            
    public void addAssignment(Vector assignment){
        Vector assignmentinfo = (Vector)server.execute("//servername.function", params);
        String username = (String)returnValue.get(0)
        String name = (String)returnValue.get(1);
        String classname = (String)returnValue.get(2);
        String days = (String)returnValue.get(3);
        String duration = (String)returnValue.get(4);
        String priority = (String)returnValue.get(5);   
        try{
            stmt = c.createStatement();
            sql = "INSERT INTO " + username + " " + "ASSIGNMENT VALUES (" + name + ", " + classname + ", " + days + ", " + duration + ", " + priority + ");";
            stmt.excecuteUpdate(sql);
            stmt.close();
            //System.out.println("Added Assignment Successfully");
        }
        catch ( Exception e ) {
        //      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public void addEvent(Vector event){
        Vector eventinfo = (Vector)server.execute("//servername.function", params);
        String username = (String)returnValue.get(0)
        String name = (String)returnValue.get(1);
        String day = (String)returnValue.get(2);
        String start = (String)returnValue.get(3);
        String end = (String)returnValue.get(4);
        String location = (String)returnValue.get(5);   
        try{
            stmt = c.createStatement();
            sql = "INSERT INTO " + username + " " + "EVENT VALUES (" + name + ", " + day + ", " + start + ", " + end + ", " + location + ");";
            stmt.excecuteUpdate(sql);
            stmt.close();
            //System.out.println("Added Assignment Successfully");
        }
        catch ( Exception e ) {
        //      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }  

    public void Edit(String username, String type, String name){
        try {
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * from " + username + " " + type + " WHERE TOPIC= " + name);
             if(type.equals("ASSIGNMENT")){
                 while ( rs.next() ) {
                     String name = rs.getString(name);
                     String classname = rs.getString(classname);
                     String days  = rs.getString(days);
                     String priority = rs.getString(priority
                     String duration = rs.getString(duration);
                     Vector params = new Vector();
                    params.addElement(name);          
                    params.addElement(classname);         
                    params.addElement(days);            
                    params.addElement(priority);            
                    params.addElement(duration);         
                    params.addElement(new Character('~'));
                  }
            }
             if(type.equals("EVENT")){
                 while ( rs.next() ) {
                     String name = rs.getString(name);
                     String day = rs.getString(day);
                     String start  = rs.getString(start);
                     String end = rs.getString(end);
                     String location = rs.getString(location);
                     Vector params = new Vector();
                    params.addElement(name);          
                    params.addElement(day);         
                    params.addElement(start);            
                    params.addElement(end);            
                    params.addElement(location);         
                    params.addElement(new Character('~'));
                  }
            }

          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
      }*/
}
