package ru.stqa.selenium.page_objects_hw.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class CartPage extends Page {

  public CartPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(className = "shortcut")
  public List<WebElement> productsInCart;

  @FindBy(className = "shortcut")
  public WebElement productInCart;

  @FindBy(name = "remove_cart_item")
  public WebElement removeButton;

  @FindBy(css = "#checkout-cart-wrapper em")
  public WebElement emptyCartIndicator;

  @FindBy(css = "#order_confirmation-wrapper td.item")
  public List<WebElement> orderSummaryRows;


  public ExpectedCondition orderSummaryLessThan(int number) {
    return numberOfElementsToBeLessThan(orderSummaryRows, number);
  }

  private ExpectedCondition<List<WebElement>> numberOfElementsToBeLessThan(List<WebElement> list, int number) {
    return new ExpectedCondition<List<WebElement>>() {
      int currentNumber = 0;

      public List<WebElement> apply(WebDriver webDriver) {
        List<WebElement> elements = list;
        currentNumber = elements.size();
        return currentNumber < number ? elements : null;
      }
    };
  }
}
