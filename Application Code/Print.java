public class Print { 
	String Username;
    String Password;
    String Email;
    String Fullname;
    String Bedtime;
    String Waketime;
    String Event;
    String Day;
    String startDay;
    String startHour;
    String endDay;
    String endHour;
    String Location;
    String Assignment;
    String classname;
    String dueDate;
    String dueTime;
    String Hours;
    String Priority;
    String aPriority;
    Client r = new Client();

       
        public void setUsername(String value) {
		Username = value;
	}

        public void setPassword(String value) {
		Password = value;
	}

        public void setEmail(String value) {
    	Email = value;
    }
        
        public void setFullname(String value) {
    	Fullname = value;
    }
        
        public void setBedtime(String value) {
    	Bedtime = value;
    }
        
        public void setWaketime(String value){
        Waketime = value;
    }
        
        public void setEvent(String value) {
        Event = value;
    }
        
        public void setDay(String value) {
        Day = value;
    }
        
        public void setstartDay(String value) {
        startDay = value;
    }
        
        public void setstartHour(String value) {
        startHour = value;
    }
        
        public void setendDay(String value) {
        endDay = value;
    }
    
        public void setendHour(String value) {
        endHour = value;
    }
        
        public void setLocation(String value) {
        Location = value;
    }
        
        public void setAssignment(String value) {
        Assignment = value;
    }
        
        public void setClassname(String value) {
        classname = value;
    }
        
        public void setDueDate(String value) {
        dueDate = value;
    }
        
        public void setDueTime(String value) {
        dueTime = value;
    }
        
        public void setHours(String value) {
        Hours = value;
    }
        
        public void setPriority(String value) {
        Priority = value;
    }
        
        public void setAPriority(String value) {
            aPriority = value;
    }
        
        public void printLogin() {
        	r.createAccount(Username,Fullname,Email,Password,Bedtime,Waketime);	
       	    System.out.print(r.login(Username,Password));
          
    }
        
        public void printAccount() {
        	 r.createAccount(Username,Fullname,Email,Password,Bedtime,Waketime);
        	 r.login(Username,Password);
        	 Profile p = new Profile(Username,Fullname,Email,Bedtime,Waketime);
        	 System.out.print(p.getuserName());
        	 System.out.print(p.getName());
        	 System.out.print(p.getEmail());
        	 System.out.print(p.getBedtime());
        	 System.out.print(p.getWaketime());
    }
        
       public void printAddEvent() {
    	   r.createAccount(Username,Fullname,Email,Password,Bedtime,Waketime);
      	   r.login(Username,Password);
      	   System.out.print(r.addEvent(Event,Day,startDay,startHour,endDay,endHour,Location));
    }
       
        public void printAddAssign() {
        	r.createAccount(Username,Fullname,Email,Password,Bedtime,Waketime);
       	    r.login(Username,Password);
       	    System.out.print(r.addAssignment(Assignment,classname,dueDate,dueTime,Hours,Priority,aPriority));
    }
          
}

