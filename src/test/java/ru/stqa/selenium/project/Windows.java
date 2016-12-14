package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Windows extends TestBase {

  @Test
  public void testWindows() throws IOException {
    adminLogin();
    click(By.cssSelector("a[href$=countries]"));
    click(By.cssSelector("a[href$=edit_country]"));
    String mainWindow = driver.getWindowHandle();
    Set<String> oldWindows = driver.getWindowHandles();
    List<WebElement> externalLinks = driver.findElements(By.cssSelector("form[method=post] a[target=_blank]"));
    for (WebElement link : externalLinks ) {
      link.click();
      String newWindow = wait.until(WindowOtherThan(oldWindows));
      driver.switchTo().window(newWindow);
      driver.close();
      driver.switchTo().window(mainWindow);
    }
  }

  public ExpectedCondition<String> WindowOtherThan(Set<String> oldWindows) {
    return driver -> {
      Set<String> handles = driver.getWindowHandles();
      handles.removeAll(oldWindows);
      return handles.size() > 0 ? handles.iterator().next() : null;
    };
  }
}
