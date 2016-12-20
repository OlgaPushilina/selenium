package ru.stqa.selenium.page_objects_hw.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.selenium.page_objects_hw.pages.Page;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ProductPage extends Page {
  public ProductPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(name = "options[Size]")
  public List<WebElement> sizeSelectList;

  @FindBy(name = "options[Size]")
  WebElement sizeSelect;

  public void selectAnySize() {
    Select select = new Select(sizeSelect);
    List<WebElement> options = select.getOptions();
    Set<WebElement> optionsToSet = options.stream()
            .filter(o -> !o.getAttribute("value").equals(""))
            .collect(toSet());
    select.selectByVisibleText(optionsToSet.iterator().next().getText());
  }

  @FindBy(name = "add_cart_product")
  public WebElement addToCartButton;

}
