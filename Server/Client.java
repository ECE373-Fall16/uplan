import java.util.*;
import org.apache.xmlrpc.*;

public class Client {
   public static void main (String [] args) {
   
      try {
         XmlRpcClient server = new XmlRpcClient("http://localhost:8080/RPC2"); 
         Vector params = new Vector();
         
         params.addElement(new String("ae"));
         params.addElement(new String("HW1"));
         params.addElement(new String("373"));
         params.addElement(new Integer(0101));
         params.addElement(new String("here"));

         Vector returnValue = (Vector)server.execute("sample.add", params);

	 int size = ((Vector)returnValue).size();
	 //Integer intValue = (Integer)returnValue.get(0); 
	 //Double  doubleValue = (Double)returnValue.get(1);
	 //String  stringValue = (String)returnValue.get(2);
	 //System.out.println("The first array value is " + intValue);

      } catch (Exception exception) {
         System.err.println("Client: " + exception);
      }
   }
}