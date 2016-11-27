import java.sql.*;

public class InitializeDB
{
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

    try {
     stmt = c.createStatement();
     sql = "CREATE TABLE INVENTORY(" +
         "ITEM_NUMBER INT PRIMARY KEY NOT NULL," +
         "QUANTITY INT NOT NULL," +
         "TITLE TEXT NOT NULL," +
         "TOPIC TEXT NOT NULL," +
         "COST DOUBLE NOT NULL);";
      stmt.executeUpdate(sql);
      stmt.close();
    } catch ( Exception e ) {
      //System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    System.out.println("Table created successfully");


    try {
    stmt = c.createStatement();
    sql = "INSERT INTO INVENTORY VALUES (53477, 1, 'Achieving Less Bugs in your Code', 'software systems', 19.99);";
    stmt.executeUpdate(sql);
    stmt.close();
    System.out.println("Records created successfully");
    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }

    try {
    stmt = c.createStatement();
    sql = "INSERT INTO INVENTORY VALUES (53573, 2, 'Software Design for Dummies', 'software systems', 29.99);";
    stmt.executeUpdate(sql);
    stmt.close();
    System.out.println("Records created successfully");
    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }

    try {
    stmt = c.createStatement();
    sql = "INSERT INTO INVENTORY VALUES (12365, 1, 'Surviving College', 'college life', 39.99);";
    stmt.executeUpdate(sql);
    stmt.close();
    System.out.println("Records created successfully");
    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }

    try {
    stmt = c.createStatement();
    sql = "INSERT INTO INVENTORY VALUES (12498, 3, 'Cooking for the Impatient Undergraduate', 'college life', 49.99);";
    stmt.executeUpdate(sql);
    stmt.close();
    System.out.println("Records created successfully\n");
    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }

    try {
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * from INVENTORY WHERE TOPIC='college life'");
      while ( rs.next() ) {
         int itemNumber = rs.getInt("ITEM_NUMBER");
         String title = rs.getString("TITLE");
         int quantity  = rs.getInt("QUANTITY");
         String topic = rs.getString("TOPIC");
         float cost = rs.getFloat("COST");
         System.out.println( "ITEM_NUMBER = " + itemNumber);
         System.out.println( "QUANTITY = " + quantity);
         System.out.println( "TITLE = " + title);
         System.out.println( "TOPIC = " + topic);
         System.out.println( "COST = " + cost);
	 System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
  }
}
