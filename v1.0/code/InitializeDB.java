import java.sql.*;

public class InitializeDB{
  
  public static void main( String args[] ){

    Statement stmt = null;
    Connection c = null;
    String sql = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
      stmt = c.createStatement();
      
      sql = "CREATE TABLE PROFILE(" +
                "USERNAME TEXT PRIMARY KEY NOT NULL," +
                "PROFILENAME TEXT NOT NULL," +
                "EMAIL TEXT NOT NULL," +
                "PASSWORD TEXT NOT NULL," +
                "BEDTIME TEXT NOT NULL," +
                "WAKETIME TEXT NOT NULL);";
      
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    
    }
    System.out.println("Opened database successfully");
    
  }

}
