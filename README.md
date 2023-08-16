# Example Selenium Java Project

This project shows a minimal Java project setup for Selenium tests. 

This includes a sample test "GithubTest.java" which goes to the Github login page, fills in the email and password, submits the form, and checks the error message.

## Pre-requisites

You need to have Java SDK 8 and Maven installed to run this project.

## Running tests

To run the sample test, run `mvn test` at the project root.

## Configuration

Note that the sample test is configured to run the test on chrome using a Remote Selenium Grid.

If you want to run tests remotely on a cloud webdriver grid provider such as [UI-licious](https://uilicious.com), use the following code to initialise the WebDriver object:
```java
// Replace the URL with the URL to your webdriver grid given by your service provider
URL url = new URL("https://project_id:access_key@webdriver.uilicious.com/wd/hub");
WebDriver driver = new RemoteWebDriver(url, capabilities);
```

If you want to run a test locally on chrome, use the following code instead initialise the WebDriver object:
```java
ChromeDriverService service = new ChromeDriverService.Builder().build();
WebDriver driver = new ChromeDriver(service);
```




