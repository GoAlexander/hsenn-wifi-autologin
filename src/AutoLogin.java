
import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AutoLogin {

	static boolean DEBUG = false;

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {

		String login = "hseguest";
		String password = "hsepassword";

		// Create a new instance of the html unit driver
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true); // enable javascript

		// And now use this to hse page
		driver.get("https://nnov-wlc-03.hse.ru/login.html");
		// for logout: https://1.1.1.1/logout.html
		if (DEBUG) {
			System.out.println(driver.getCurrentUrl());
			System.out.println("Page title is: " + driver.getTitle());
		}

		// Find the text input element by its name
		WebElement element_login = driver.findElement(By.name("username"));
		// Enter something
		element_login.sendKeys(login);

		WebElement element_password = driver.findElement(By.name("password"));
		element_password.sendKeys(password);

		WebElement element_button = driver.findElement(By.name("Submit"));

		try {
			// Now click the button.
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("submitAction();", element_button);

			System.out.println("Connection with HSE Wi-fi successful!");
			if (DEBUG) {
				System.out.println(driver.getCurrentUrl());
				System.out.println("Page title is: " + driver.getTitle());
			}
		} catch (UnhandledAlertException f) {
			System.out.println("(You already logged in.)");
			// exception because of driver.getTitle()
		}

		driver.quit();
	}
}
