import java.util.*;
import java.text.*;

public class DateTest
{
    public static void main(String[] args) throws ParseException
    {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        Date current = new Date();
        
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("EST"));
        
        System.out.println(df.format(current));
        /*
        System.out.println(current.getYear());
        System.out.println(current.getDate());
        System.out.println(current.getDay());
        System.out.println(current.getHours());
        System.out.println(current.getMinutes());
        System.out.println(current.getSeconds());
        System.out.println(current.getTime());
        */
        //String local = current.toLocaleString();
        System.out.println(current);
        String str = df.format(current);
        System.out.println(str);
        
        current = df.parse(str);
        System.out.println(current);
    }
}