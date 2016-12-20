package ru.stqa.selenium.page_objects_hw.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.page_objects_hw.pages.CartPage;
import ru.stqa.selenium.page_objects_hw.pages.MainPage;
import ru.stqa.selenium.page_objects_hw.pages.ProductPage;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class Application {

  private WebDriverWait wait;
  private WebDriver driver;
  private MainPage mainPage;
  private ProductPage productPage;
  private CartPage cartPage;

  public Application() throws IOException {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().window().maximize();
    mainPage = new MainPage(driver);
    productPage = new ProductPage(driver);
    cartPage = new CartPage(driver);
  }

  public void quit() {
    driver.quit();
  }

  public void openMainPage() {mainPage.open();}

  public void addAnyProductToCart(int numberOfItems) {
    for (int i = 1; i <= numberOfItems; i++) {
      Set<WebElement> products = mainPage.products.stream().collect(toSet());
      WebElement product = products.iterator().next();
      WebElement productLink = mainPage.productLink(product);
      productLink.click();
      if (isProductHasSize()) {
        productPage.selectAnySize();
      }
      productPage.addToCartButton.click();
      wait.until(mainPage.numberOfItemsInCart(i));
      driver.navigate().back();
    }
  }

  public void goToCart() {
    mainPage.checkoutLink.click();
  }

  public void removeAllProductsFromCart() {
    List<WebElement> productsInCart = cartPage.productsInCart;
    for (int i = productsInCart.size(); i > 0; i--) {
      if (i > 1) {
        cartPage.productInCart.click();
      }
      cartPage.removeButton.click();
      wait.until(cartPage.orderSummaryLessThan(i));
    }
  }

  public boolean isProductHasSize() {
    return productPage.sizeSelectList.size() > 0;
  }

  public boolean emptyCartMessageIsPresent(String message) {
    return cartPage.emptyCartIndicator.getText().equals(message);
  }
}




