package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class ProductPage extends TestBase {

  @Test
  public void testProductPage() {
    WebElement product = findProduct();
    String productName = product.findElement(By.cssSelector("div.name")).getText();
    String regularPrice = findRegularPrice(product).getText();
    String campaignPrice = findCampaignPrice(product).getText();

    product.click();

    String innerProductName = driver.findElement(By.cssSelector("h1.title")).getText();
    WebElement innerRegularPrice = driver.findElement(By.cssSelector("s.regular-price"));
    WebElement innerCampaignPrice = driver.findElement(By.cssSelector("strong.campaign-price"));

    assertEquals(productName, innerProductName);
    assertEquals(regularPrice, innerRegularPrice.getText());
    assertEquals(campaignPrice, innerCampaignPrice.getText());
  }

  @Test
  public void testMainPageStyle() {
    WebElement product = findProduct();

    String regPriceColor = findRegularPrice(product).getCssValue("color");
    String regPriceTextDecor = findRegularPrice(product).getCssValue("text-decoration");
    String regPriceFont = findRegularPrice(product).getCssValue("font-size");

    assertEquals(regPriceColor, "rgba(119, 119, 119, 1)");
    assertEquals(regPriceTextDecor, "line-through");
    assertEquals(regPriceFont, "14.4px");

    String campPriceColor = findCampaignPrice(product).getCssValue("color");
    String campPriceFontWeight = findCampaignPrice(product).getCssValue("font-weight");
    String campPriceFont = findCampaignPrice(product).getCssValue("font-size");

    assertEquals(campPriceColor, "rgba(204, 0, 0, 1)");
    assertEquals(campPriceFontWeight, "bold");
    assertEquals(campPriceFont, "18px");
  }

  @Test
  public void testProductPageStyle() {
    findProduct().click();

    WebElement regularPrice = driver.findElement(By.cssSelector("s.regular-price"));
    String regPriceColor = regularPrice.getCssValue("color");
    String regPriceTextDecor = regularPrice.getCssValue("text-decoration");
    String regPriceFont = regularPrice.getCssValue("font-size");

    assertEquals(regPriceColor, "rgba(102, 102, 102, 1)");
    assertEquals(regPriceTextDecor, "line-through");
    assertEquals(regPriceFont, "16px");

    WebElement campaignPrice = driver.findElement(By.cssSelector("strong.campaign-price"));
    String campPriceColor = campaignPrice.getCssValue("color");
    String campPriceFontWeight = campaignPrice.getCssValue("font-weight");
    String campPriceFont = campaignPrice.getCssValue("font-size");

    assertEquals(campPriceColor, "rgba(204, 0, 0, 1)");
    assertEquals(campPriceFontWeight, "bold");
    assertEquals(campPriceFont, "22px");
  }

  private WebElement findProduct() {
    driver.get(properties.getProperty("userUrl"));
    return driver.findElement(By.cssSelector("#box-campaigns a[class=link]"));
  }

  private WebElement findRegularPrice(WebElement product) {
    return product.findElement(By.cssSelector("s.regular-price"));
  }

  private WebElement findCampaignPrice(WebElement product) {
    return product.findElement(By.cssSelector("strong.campaign-price"));
  }
}
