import java.util.*;
import java.text.*;

public class DateTest
{
    public static void main(String[] args){
        Date current = new Date();
        System.out.println(current);
        System.out.println(current.getYear());
        System.out.println(current.getDate());
        System.out.println(current.getDay());
        System.out.println(current.getHours());
        System.out.println(current.getMinutes());
        System.out.println(current.getSeconds());
        System.out.println(current.getTime());
        String local = current.toLocaleString();
        System.out.println(local);
        current = DateFormat.parse(local);
        System.out.println(current);
    }
}