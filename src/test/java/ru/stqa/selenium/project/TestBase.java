package ru.stqa.selenium.project;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

  protected WebDriver driver;
  protected WebDriverWait wait;

  @Before
  public void start() throws IOException {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  public void adminLogin() throws IOException {
    Properties properties = new Properties();
    properties.load(new FileReader(new File("src/test/resources/local.properties")));
    driver.get(properties.getProperty("adminUrl"));
    driver.findElement(By.name("username")).sendKeys(properties.getProperty("adminUsername"));
    driver.findElement(By.name("password")).sendKeys(properties.getProperty("adminPassword"));
    driver.findElement(By.name("login")).click();
  }

  public void pathToDriver() {
    // first way - set System property
    System.setProperty("webdriver.chrome.driver", "C:/Tools/chromedriver.exe");
    // second way - DriverService
    driver = new ChromeDriver(
    new ChromeDriverService.Builder().
    usingDriverExecutable(new File("C:/Tools/chromedriver.exe")).build());
  }

}
