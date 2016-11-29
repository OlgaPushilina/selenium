package ru.stqa.selenium.practice;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class Capabilities {

  public static void main(String [] args) {

    // start browser with capabilities

   /* DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
    caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
    WebDriver driver = new InternetExplorerDriver(caps);
    System.out.println(((HasCapabilities) driver).getCapabilities());
    driver.quit();*/

    // start browser with CLO (command line options)

    /*ChromeOptions options = new ChromeOptions();
    options.addArguments("start-fullscreen");
    WebDriver driver1 = new ChromeDriver(options);
    driver1.quit();*/

    // start browser with capabilities and CLO

    /*DesiredCapabilities caps1 = new DesiredCapabilities();
    caps1.setCapability(ChromeOptions.CAPABILITY, options);
    WebDriver driver2 = new ChromeDriver(caps1);*/

    // start Nightly firefox
    DesiredCapabilities caps2 = new DesiredCapabilities();
    WebDriver driver3 = new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Nightly\\firefox.exe")),
            new FirefoxProfile(), caps2);
    System.out.println(((HasCapabilities) driver3).getCapabilities());
    driver3.quit();

    // start firefox with Marionette (true, false)
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(FirefoxDriver.MARIONETTE, false);
    WebDriver driver4 = new FirefoxDriver(caps);
    System.out.println(((HasCapabilities) driver4).getCapabilities());
  }
}
