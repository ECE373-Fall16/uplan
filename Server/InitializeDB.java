import java.sql.*;

public class InitializeDB
{
  public static void main( String args[] )
  {
    //Statement stmt = null;
    Connection c = null;
    //String sql = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:inventory.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");

    
  }
}
