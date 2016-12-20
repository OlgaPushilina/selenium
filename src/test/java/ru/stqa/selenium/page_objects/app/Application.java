package ru.stqa.selenium.page_objects.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.page_objects.model.Customer;
import ru.stqa.selenium.page_objects.pages.CustomerListPage;
import ru.stqa.selenium.page_objects.pages.RegistrationPage;
import ru.stqa.selenium.page_objects.pages.AdminPanelLoginPage;

import java.util.Set;

public class Application {

  private WebDriver driver;
  private RegistrationPage registrationPage;
  private AdminPanelLoginPage adminPanelLoginPage;
  private CustomerListPage customerListPage;

  public Application() {
    driver = new ChromeDriver();
    registrationPage = new RegistrationPage(driver);
    adminPanelLoginPage = new AdminPanelLoginPage(driver);
    customerListPage = new CustomerListPage(driver);
  }

  public void quit() {
    driver.quit();
  }

  public void registerNewCustomer(Customer customer) {
    registrationPage.open();
    registrationPage.firstnameInput.sendKeys(customer.getFirstname());
    registrationPage.lastnameInput.sendKeys(customer.getLastname());
    registrationPage.address1Input.sendKeys(customer.getAddress());
    registrationPage.postcodeInput.sendKeys(customer.getPostcode());
    registrationPage.cityInput.sendKeys(customer.getCity());
    registrationPage.selectCountry(customer.getCountry());
    registrationPage.selectZone(customer.getZone());
    registrationPage.emailInput.sendKeys(customer.getEmail());
    registrationPage.phoneInput.sendKeys(customer.getPhone());
    registrationPage.passwordInput.sendKeys(customer.getPassword());
    registrationPage.confirmedPasswordInput.sendKeys(customer.getPassword());
    registrationPage.createAccountButton.click();
  }

  public Set<String> getCustomerIds() {
    if (adminPanelLoginPage.open().isOnThisPage()) {
      adminPanelLoginPage.enterUsername("admin").enterPassword("admin").submitLogin();
    }
   return customerListPage.open().getCustomerIds();
  }
}
