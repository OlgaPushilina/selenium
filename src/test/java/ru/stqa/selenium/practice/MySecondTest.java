package ru.stqa.selenium.practice;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MySecondTest extends TestBaseParallel {

  @Test
  public void myFirstTest() {
    driver.get("http://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    WebElement btnG = driver.findElement(By.name("btnG"));
    btnG.click();
    wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void mySecondTest() {
    driver.get("http://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void myThirdTest() {
    driver.get("http://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void myForthTest() {
    driver.get("http://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void myFifthTest() {
    driver.get("http://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }


}
