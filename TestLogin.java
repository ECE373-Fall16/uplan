public class TestLogin{   
public static void main(String[] args) {

		Client r = new Client();
	
        String username = r.username;	
        String password = r.password;
        
        Print toScreen = new Print();
        toScreen.setUsername(username);
        toScreen.setPassword(password);
	
        toScreen.printLogin();
     }
}
