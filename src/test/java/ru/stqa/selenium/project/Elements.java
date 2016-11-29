package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class Elements extends TestBase {

  @Test
  public void testElements() throws IOException {
    adminLogin();
    List<WebElement> menuItems = driver.findElements(By.id("app-"));
    for (int i = 1; i <= menuItems.size(); i++) {
      WebElement item = driver.findElement(By.cssSelector(String.format("#box-apps-menu>li:nth-child(%s)", i)));
      item.click();
      driver.findElement(By.cssSelector("#content h1"));
      List<WebElement> innerItems = driver.findElements(By.cssSelector(".docs>li"));
      if (innerItems.size() > 0) {
        for (int j = 1; j <= innerItems.size(); j++) {
          WebElement innerItem = driver.findElement(By.cssSelector(String.format(".docs>li:nth-child(%s)", j)));
          innerItem.click();
          driver.findElement(By.cssSelector("#content h1"));
        }
      }
    }
  }
}

