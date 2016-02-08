import java.io.IOException;
import java.net.MalformedURLException;

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {

		String login = "hseguest";
		String password = "hsepassword";
		
		Login.login(login, password);
		
	}
}
