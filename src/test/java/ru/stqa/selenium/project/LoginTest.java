package ru.stqa.selenium.project;

import org.junit.Test;

import java.io.IOException;

public class LoginTest extends TestBase {

  @Test //@Ignore
  public void testAdminLogin() throws IOException {
    adminLogin();
  }

}
