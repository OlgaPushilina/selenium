package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.io.IOException;
import java.util.List;

public class BrowserLog extends TestBase {

  @Test
  public void testBrowserLog() throws IOException {
    adminLogin();
    click(By.cssSelector("a[href$=catalog]"));
    List<WebElement> rows = driver.findElements(By.cssSelector(".dataTable tr"));
    for (int i = 1; i < rows.size(); i++ ) {
      WebElement row = driver.findElement(By.cssSelector(String.format(".dataTable tr:nth-child(%s)", i)));
      if (row.getAttribute("class").equals("row")) {
        WebElement productLink = row.findElement(By.cssSelector("td:nth-child(3) a"));
          if (productLink.getAttribute("href").contains("doc=catalog")) {
            productLink.click();
            List<WebElement> newRows = driver.findElements(By.cssSelector(".dataTable tr"));
            rows = newRows;
          } else {
            productLink.click();
            List<LogEntry> browserLogs = driver.manage().logs().get("browser").getAll();
            for (LogEntry log : browserLogs) {
              System.out.println(row.getText() + ": " + log);
            }
            click(By.name("cancel"));
          }
      }
    }
  }
}
