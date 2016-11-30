package ru.stqa.selenium.project;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static org.junit.Assert.assertEquals;

public class CountriesAndGeoZones extends TestBase {

  @Test
  public void testCountries() throws IOException {
    adminLogin();
    driver.findElement(By.cssSelector("a[href$=countries]")).click();
    List<WebElement> countries = driver.findElements(By.cssSelector(".dataTable td:nth-of-type(5) a"));
    List<String> countryNames = new ArrayList<>();
    countries.forEach((c) -> {countryNames.add(c.getText());});
    List<String> sortedCountryNames = countryNames.stream().sorted().collect(Collectors.toList());
    assertEquals(countryNames, sortedCountryNames);
  }


  @Test
  public void testInnerZones() throws IOException {
    adminLogin();
    driver.findElement(By.cssSelector("a[href$=countries]")).click();
    List<WebElement> zones = driver.findElements(By.cssSelector(".dataTable td:nth-child(5)"));
    for (int i = 2; i <= zones.size() + 1; i++) {
      WebElement zone = driver.findElement(By.cssSelector(String.format(".dataTable tr:nth-child(%s) td:nth-child(6)", i)));
      if (Integer.parseInt(zone.getText()) > 0) {
        driver.findElement(By.cssSelector(String.format(".dataTable tr:nth-child(%s) td:nth-of-type(5) a", i))).click();
        List<WebElement> innerZones = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
        List<String> zoneNames = new ArrayList<>();
        innerZones.forEach((z) -> {zoneNames.add(z.getText());});
        zoneNames.remove(innerZones.size() - 1);
        List<String> sortedZoneNames = zoneNames.stream().sorted().collect(Collectors.toList());
        assertEquals(zoneNames, sortedZoneNames);
        driver.navigate().back();
      }
    }
  }

  @Test
  public void testGeoZones() throws IOException {
    adminLogin();
    driver.findElement(By.cssSelector("a[href$=geo_zones]")).click();
    List <WebElement> geoZones = driver.findElements(By.cssSelector(".dataTable tr"));
    for (int i = 2; i <= geoZones.size() - 1; i++) {
      driver.findElement(By.cssSelector(String.format(".dataTable tr:nth-child(%s) td:nth-of-type(3) a", i))).click();
      List <WebElement> selectedZones = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) select"));
      List <String> zones = new ArrayList<>();
      for (WebElement selZone : selectedZones) {
        Select select = new Select(selZone);
        zones.add(select.getFirstSelectedOption().getText());
      }
      List <String> sortedZones = zones.stream().sorted().collect(Collectors.toList());
      assertEquals(zones, sortedZones);
      driver.navigate().back();
    }
  }
}





