package ru.stqa.selenium.project;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

  protected WebDriver driver;
  protected WebDriverWait wait;
  protected Properties properties = new Properties();

  @Before
  public void start() throws IOException {
    properties.load(new FileReader(new File("src/test/resources/local.properties")));
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().window().maximize();
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  public void adminLogin() throws IOException {
    driver.get(properties.getProperty("adminUrl"));
    driver.findElement(By.name("username")).sendKeys(properties.getProperty("adminUsername"));
    driver.findElement(By.name("password")).sendKeys(properties.getProperty("adminPassword"));
    driver.findElement(By.name("login")).click();
  }

  public void sendKeys (By locator, String keys) {
    driver.findElement(locator).sendKeys(keys);
  }

  public void clearAndSendKeys (By locator, String keys) {
    WebElement element = driver.findElement(locator);
    element.clear();
    element.sendKeys(keys);
  }

  public void click (By locator) {
    driver.findElement(locator).click();
  }

  public void select(By locator, String value) {
    Select select = new Select(driver.findElement(locator));
    select.selectByValue(value);
  }

  public void check(By locator) {
    WebElement checkbox = driver.findElement(locator);
    if (!checkbox.isSelected()) {
      checkbox.click();
    }
  }



}
