public class TestAccount{   
public static void main(String[] args) {

		Client r = new Client();
	
        String username = r.username;	
        String password = r.password;
        String email = r.email;
        String fullname = r.fullname;
        String bedtime = r.bedtime;
        		
        Print toScreen = new Print();
        toScreen.setUsername(username);
        toScreen.setPassword(password);
        toScreen.setEmail(email);
        toScreen.setFullname(fullname);
        toScreen.setBedtime(bedtime);

        toScreen.printAccount();
        
     }
}