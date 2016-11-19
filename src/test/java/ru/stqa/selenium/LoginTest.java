package ru.stqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends TestBase {

  @Test
  public void testAdminLogin() {
    driver.get(properties.getProperty("adminUrl"));
    driver.findElement(By.name("username")).sendKeys(properties.getProperty("adminUsername"));
    driver.findElement(By.name("password")).sendKeys(properties.getProperty("adminPassword"));
    driver.findElement(By.name("login")).click();
  }
}
