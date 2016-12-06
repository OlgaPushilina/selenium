package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class userSignUp extends TestBase {

  @Test
  public void testSignUp() {
    driver.get(properties.getProperty("userUrl"));
    click(By.cssSelector(".content a[href$=create_account]"));
    sendKeys(By.name("firstname"),"Olga");
    sendKeys(By.name("lastname"),"Test");
    sendKeys(By.name("address1"),"111 Main st.");
    sendKeys(By.name("postcode"),"11111");
    sendKeys(By.name("city"),"Green City");
    WebElement postcode= driver.findElement(By.name("postcode"));
    postcode.clear();
    postcode.sendKeys(Keys.HOME + "11111-1111");
    click(By.cssSelector(".select2-selection__arrow"));
    sendKeys(By.cssSelector(".select2-search__field"),"United States" + Keys.ENTER);
    Select select = new Select(driver.findElement(By.cssSelector("select[name=zone_code")));
    wait.until(presenceOfElementLocated(By.cssSelector("select[name=zone_code] option[value=CA]")));
    select.selectByValue("CA");
    String email = randomString(10) + "@localhost";
    sendKeys(By.name("email"),email);
    sendKeys(By.name("phone"),"+11111111");
    click(By.name("newsletter"));
    String password = "111111";
    sendKeys(By.name("password"),password);
    sendKeys(By.name("confirmed_password"),password);
    click(By.name("create_account"));
    logout();
    login(email, password);
    logout();
  }

  public void login(String email, String password) {
    sendKeys(By.name("email"),email);
    sendKeys(By.name("password"),password);
    click(By.name("login"));
  }

  public void sendKeys (By locator, String keys) {
    driver.findElement(locator).sendKeys(keys);
  }

  public void click (By locator) {
    driver.findElement(locator).click();
  }

  public void logout() {
    click(By.cssSelector(".content a[href$=logout]"));
  }

  String randomString(int len){
    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Random rnd = new Random();
    StringBuilder sb = new StringBuilder(len);
    for(int i = 0; i < len; i++)
      sb.append( chars.charAt(rnd.nextInt(chars.length())));
    return sb.toString();
  }
}
