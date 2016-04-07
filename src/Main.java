import java.io.IOException;
import java.net.MalformedURLException;

class Main {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {

		String login = "hseguest";
		String password = "hsepassword";
		String campus_location = "https://nnov-wlc-03.hse.ru/login.html";

		Login.connect(login, password, campus_location);

	}
}
