package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Stickers extends TestBase {

  @Test
  public void testStickers() {
    driver.get(properties.getProperty("userUrl"));
    List<WebElement> images = driver.findElements(By.cssSelector(".image-wrapper"));
    for (WebElement image : images) {
      List <WebElement> stickers = image.findElements(By.cssSelector("div[class^=sticker]"));
      assertTrue(stickers.size() == 1);
    }
  }

}
