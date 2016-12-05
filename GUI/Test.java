
public class Test{
	public static void main(String[] args){
		LoginPage a = new LoginPage();
		HomePage s = new HomePage();
		AddAssignment b = new AddAssignment();
		String login = a.LoginUser();
		System.out.println(login);
		//s.DisplayCalendarEvents();
	}
}