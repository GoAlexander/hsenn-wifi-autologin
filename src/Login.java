
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Login {

	private final static boolean DEBUG = false;

	public static void disconnect() throws MalformedURLException, IOException, InterruptedException, NoSuchElementException {
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS); //TODO CHECK IT!
		driver.get("http://1.1.1.1/logout.html");
		
		
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;  
			executor.executeAsyncScript("document.forms[0].submit();"); //TODO CHECK IT!
			//executor.executeScript("document.forms[0].submit();"); // old version
		} catch (UnhandledAlertException f) {
			System.out.println("(Something wrong.)");
			// exception because of driver.getTitle()
		}

		driver.quit();
	}

	public static void connect(String login, String password, String campus_location)
			throws MalformedURLException, IOException, InterruptedException {

		// Create a new instance of the html unit driver
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true); // enable javascript only for
											// htmlunit!!!

		// And now use this to hse page
		driver.get(campus_location);
		if (DEBUG) {
			System.out.println(driver.getCurrentUrl());
			System.out.println("Page title is: " + driver.getTitle());
		}

		// Find the text input element by its name
		WebElement element_login = driver.findElement(By.name("username"));
		element_login.sendKeys(login); // Enter something

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
