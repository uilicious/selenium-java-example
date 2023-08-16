package com.uilicious.demos;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class GithubTest {

	@Test
	public void invalidLogin() {

		try {

			System.out.println("Start of test");

			DesiredCapabilities capabilities = new DesiredCapabilities();

			Map<String, Object> config = new HashMap<>();
			config.put("width", "1280");
			config.put("height", "960");
			config.put("region", "");
			// config.put("name", ""); // Custom Name of the Webdriver Session, defaults to 'Webdriver Session'
			// config.put("userAgent", ""); // Optional user agent overwrite, chrome and firefox only

			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability("uilicious:config", config);

			WebDriver driver;

			// Use the following block if you want to run tests using the chrome browser on your local machine
			// ChromeDriverService service = new ChromeDriverService.Builder().build();
			// driver = new ChromeDriver(service);

			// Use the following block if you want to run tests using a remote webdriver grid
			URL url = new URL("https://project_id:access_key@webdriver.uilicious.com/wd/hub");
			System.out.println("Connecting to remote webdriver: " + url.toString());
			driver = new RemoteWebDriver(url, capabilities);
			System.out.println("Connection successful");

			// Login to github
			driver.get("https://github.com/login");
			System.out.println("Navigated to https://github.com/login");

			driver.findElement(By.id("login_field")).sendKeys("brucewayne");
			System.out.println("Filled login field");

			driver.findElement(By.id("password")).sendKeys("supersecretpassword");
			System.out.println("Filled password field");

			driver.findElement(By.cssSelector("input[type=submit]")).click();
			System.out.println("Clicked submit button");

			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement error = driver.findElement(By.cssSelector(".flash.flash-error"));
			try {
				wait.until(d -> error.getText().equalsIgnoreCase("Incorrect username or password."));
				System.out.println("Error message is displayed.");
			} catch(TimeoutException timeout) {
				throw new Exception("Expected error message 'Incorrect username or password' to be displayed, but was not seen.");
			}

			driver.quit();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("End of test");

	}

}
