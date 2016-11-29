package ru.stqa.selenium.practice;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class Cookies {

  public static void main(String[] args) {

    // work with cookies
    WebDriver driver = new ChromeDriver();

    // only for Chrome you need to specify domain first
    //driver.get("http://www.google.com/");

    driver.manage().addCookie(new Cookie("test", "test"));
    Cookie testCookie = driver.manage().getCookieNamed("test");
    System.out.println(testCookie.toString());
    Set<Cookie> cookies = driver.manage().getCookies();
    for ( Cookie cookie : cookies) {
      System.out.println(cookie.toString());
    }
    driver.manage().deleteCookieNamed("test");
    Set<Cookie> cookiesAfter = driver.manage().getCookies();
    for ( Cookie cookie : cookiesAfter) {
      System.out.println(cookie.toString());
    }
   // driver.manage().deleteAllCookies();
    driver.quit();
  }
}
