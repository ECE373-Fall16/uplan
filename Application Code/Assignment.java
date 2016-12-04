import java.util.*;

public class Assignment{
    
    private String assignment;
    private String nameClass;
    private Date dueDate;
    private String completion;
    private String priority;
    

    public Assignment(String nameAssign, String nClass, Date days, String compTime, String pri){
        assignment = nameAssign;
        nameClass = nClass;
        dueDate = days;
        completion = compTime;
        priority = pri;
    }
    

    public String toString(){
        return assignment + " " + nameClass + " " + dueDate + " " + completion + " " + priority;
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
    

    public Date getDueDate(){
        return dueDate;
    }
    

    public void setDueDate(Date date){
        dueDate = date;
    }
    

    public String getCompletionTime(){
        return completion;
    }
    

    public void set(String time){
        completion = time;
    }
    

    public String getPriority(){
        return priority;
    }
    

    public void setPriority(String pri){
        priority = pri;
    }
    
}