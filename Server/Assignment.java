import java.util.*;

public class Assignment{
    
    String assignment;
    String nameClass;
    int dueDate;
    int completion;
    int priority;
    
    public Assignment(String nameAssign, String nClass, int days, int compTime, int pri){
        assignment = nameAssign;
        nameClass = nClass;
        dueDate = days;
        completion = compTime;
        priority = pri;
    }
    
    public String getAssignName(){
        return assignment;
    }
    
    public void setAssignName(String name){
        assignment = name;
    }
    
    public String getClassName(){
        return nameClass;
    }
    
    public void setClassName(String name){
        nameClass = name;
    }
    
    public int getDueDate(){
        return dueDate;
    }
    
    public void setDueDate(int date){
        dueDate = date;
    }
    
    public int getCompletionTime(){
        return completion;
    }
    
    public void set(int time){
        completion = time;
    }
    
    public int getPriority(){
        return priority;
    }
    
    public void setPriority(int pri){
        priority = pri;
    }
}