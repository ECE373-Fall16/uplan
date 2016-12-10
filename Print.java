public class Print { 
	String Username;
    String Password;
    String Email;
    String Fullname;
    String Bedtime;
    String name;
    String Day;
    String startDay;
    String startHour;
    String endDay;
    String endHour;
    String location;
    String classname;
    String due;
    String hours;
    String priority;
       
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
        
        public void setName(String value) {
        	name = value;
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
        
        public void setlocation(String value) {
        	location = value;
    }
        
        public void setClassname(String value) {
        	classname = value;
    }
        
        public void setDueHour(String value) {
        	due = value;
    }
        
        public void setHours(String value) {
        	hours = value;
    }
        
        public void setPriority(String value) {
        	priority = value;
    }
        
        public void printLogin() {
          System.out.print(Username);
          System.out.print(Password);
    }
        
        public void printAccount() {
            System.out.print(Username);
            System.out.print(Password);
            System.out.print(Email);
            System.out.print(Fullname);
            System.out.print(Bedtime);
    }
        
        public void printAddEvent() {
            System.out.print(name);
            System.out.print(Day);
            System.out.print(startDay);
            System.out.print(startHour);
            System.out.print(endDay);
            System.out.print(endHour);
            System.out.print(location);
    }
        
        public void printAddAssign() {
            System.out.print(name);
            System.out.print(classname);
            System.out.print(Day);
            System.out.print(due);
            System.out.print(hours);
            System.out.print(priority);
    }
        
        
        
        
    
}
