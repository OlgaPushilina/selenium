package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

  protected WebDriver driver;
  protected WebDriverWait wait;
  Properties properties = new Properties();

  @Before
  public void start() throws IOException {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    properties.load(new FileReader(new File("src/test/resources/local.properties")));
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}
