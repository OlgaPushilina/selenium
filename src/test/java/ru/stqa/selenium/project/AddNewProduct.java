package ru.stqa.selenium.project;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddNewProduct extends TestBase {

  @Test
  public void testAddNewProduct() throws IOException {
    adminLogin();
    click(By.cssSelector("a[href$=catalog]"));
    List<WebElement> before = driver.findElements(By.cssSelector(".row"));
    click(By.cssSelector("a[href$=edit_product]"));

    click(By.cssSelector("label input[value='1']"));
    String name = "Cat";
    sendKeys(By.name("name[en]"), name);
    sendKeys(By.name("code"), "111");
    check(By.cssSelector("input[name='categories[]'][value='0']"));
    check(By.cssSelector("input[name='product_groups[]'][value='1-3']"));
    clearAndSendKeys(By.name("quantity"), Keys.HOME + "10" );
    File file = new File("src/test/resources/Cat.jpg");
    sendKeys(By.name("new_images[]"), String.valueOf(file.getAbsolutePath()));
    sendKeys(By.name("date_valid_from"), Keys.HOME + "06-12-2016");
    sendKeys(By.name("date_valid_to"), Keys.HOME + "06-12-2017");
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("scroll(250, 0)");
    click(By.cssSelector(".index a[href='#tab-information']"));

    select(By.name("manufacturer_id"), "1");
    sendKeys(By.name("keywords"), "cat");
    sendKeys(By.name("short_description[en]"), "cat");
    sendKeys(By.className("trumbowyg-editor"), "A very nice cat!");
    sendKeys(By.name("head_title[en]"), "Cat");
    sendKeys(By.name("meta_description[en]"), "cat");
    js.executeScript("scroll(250, 0)");
    click(By.cssSelector("a[href='#tab-prices']"));

    clearAndSendKeys(By.name("purchase_price"), "50.00");
    select(By.name("purchase_price_currency_code"), "USD");
    clearAndSendKeys(By.name("prices[USD]"), "100.00");
    clearAndSendKeys(By.name("gross_prices[USD]"), "10.00");
    clearAndSendKeys(By.name("prices[EUR]"), "120.00");
    clearAndSendKeys(By.name("gross_prices[EUR]"), "12.00");
    click(By.name("save"));

    List<WebElement> after = driver.findElements(By.cssSelector(".row"));
    assertEquals(before.size(), after.size() - 1);
    List <String> names = new ArrayList<>();
    for (WebElement item : after) {
     names.add(item.findElement(By.cssSelector("td:nth-child(3) a")).getText());
    }
    Assert.assertTrue(names.contains(name));
  }
}
