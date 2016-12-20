package ru.stqa.selenium.page_objects_hw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends Page {

  public MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    driver.get("http://localhost/litecart");
  }

  @FindBy(css = "li[class='product column shadow hover-light']")
  public List<WebElement> products;

  @FindBy(css = "#cart a.link")
  public WebElement checkoutLink;

  public WebElement productLink(WebElement product) {
    return product.findElement(By.tagName("a"));
  }

  @FindBy(css = "#cart span.quantity")
  private WebElement cartItemsQuantity;

  public ExpectedCondition numberOfItemsInCart(int number) {
    return ExpectedConditions.textToBePresentInElement(cartItemsQuantity, String.valueOf(number));
  }
}