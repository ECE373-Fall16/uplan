import java.util.*;

public class Profile{

	
	private String username;
	private String name;
	private String email;
	private String bedtime;
	private String waketime;
	

	public Profile(String user, String n, String em, String bed, String wake){
		username = user;
		name = n;
		email = em;
		bedtime = bed;
	}


	public String toString(){
		return "Profile: Username: " + username + " Name: " + name + " Email: " + email + " Bedtime: " + bedtime + " Waketime: " + waketime;
	}


	public String getUsername(){
		return username;
	}


	public void setUserName(String user){
		username = user;
	}


	public String getName(){
		return name;
	}


	public void setName(String n){
		name = n;
	}


	public String getEmail(){
		return email;
	}


	public void setEmail(String em){
		email = em;
	}


	public String getBedtime(){
		return bedtime;
	}


	public void setBedtime(String bed){
		bedtime = bed;
	}


	public String getWaketime(){
		return waketime;
	}


	public void setWaketime(String wake){
		waketime = wake;
	}
}