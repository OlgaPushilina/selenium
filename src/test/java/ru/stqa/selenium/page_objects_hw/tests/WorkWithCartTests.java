package ru.stqa.selenium.page_objects_hw.tests;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WorkWithCartTests extends TestBase {

  @Test
  public void testCart() {
    app.openMainPage();
    app.addAnyProductToCart(3);
    app.goToCart();
    app.removeAllProductsFromCart();
    assertTrue(app.emptyCartMessageIsPresent("There are no items in your cart."));
  }
}
