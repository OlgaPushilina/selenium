package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class workWithCart extends TestBase {

  @Test
  public void testCart() {
    driver.get(properties.getProperty("userUrl"));

    for (int i = 1; i < 4; i++) {
      List<WebElement> items = driver.findElements(By.cssSelector("li[class='product column shadow hover-light']"));
      Set<WebElement> itemsAsSet = items.stream().collect(Collectors.toSet());
      WebElement item = itemsAsSet.iterator().next().findElement(By.tagName("a"));
      item.click();
      if ((driver.findElements(By.name("options[Size]")).size() > 0)) {
        Select select = new Select(driver.findElement(By.name("options[Size]")));
        List<WebElement> options = select.getOptions();
        Set<WebElement> optionsToSet = options.stream()
                .filter(o -> !o.getAttribute("value").equals(""))
                .collect(Collectors.toSet());
        select.selectByVisibleText(optionsToSet.iterator().next().getText());
      }
      click(By.name("add_cart_product"));
      wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#cart span.quantity"), String.valueOf(i)));
      driver.navigate().back();
    }

    click(By.cssSelector("#cart a.link"));
    List<WebElement> checkoutItems = driver.findElements(By.className("shortcut"));
    for (int i = checkoutItems.size(); i > 0; i--) {
      if (i > 1) {
        click(By.className("shortcut"));
      }
      click(By.name("remove_cart_item"));
      wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("td.item"), i));
    }
  }
}
