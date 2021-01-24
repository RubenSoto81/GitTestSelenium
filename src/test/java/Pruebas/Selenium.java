package Pruebas;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class Selenium {
	
	WebDriver driver;
	 String baseURL;
	
	
  @Test
  public void f() {
	  baseURL = "https://www.economist.com/";
	    driver.get(baseURL);
	    String title = driver.getTitle();
	    System.out.println("El título es: " + title);
	    System.out.println("La longitud del título es: " + title.length());
	    String url = driver.getCurrentUrl();
	    driver.findElement(By.id("_evidon-banner-acceptbutton")).click();
	    Assert.assertEquals(url, baseURL, "la dirección no es igual, es: " + url + " y se esperaba: " + baseURL);
	    String pageSource = driver.getPageSource();
	    System.out.println(pageSource.length());
  }
  
  @Test
  public void comprobarBackYForwardNavegacion() {
    baseURL = "https://en.wikipedia.org/wiki/Main_Page";
    driver.get(baseURL);
    driver.findElement(By.linkText("Help")).click();
    driver.navigate().refresh();
    driver.navigate().to(baseURL);
    driver.navigate().back();
    driver.navigate().forward();
    String url = driver.getCurrentUrl();
    Assert.assertEquals(url, baseURL, "la dirección no es igual, es: " + url + " y se esperaba: " + baseURL);
  }
  
  @Test
  public void cambioDeVentanaConHandles() throws InterruptedException {
    baseURL = "https://omayo.blogspot.com/";
    driver.get(baseURL);
    String handle1 = driver.getWindowHandle();
    System.out.println(handle1);
    driver.findElement(By.linkText("SeleniumTutorial")).click();
    Set<String> handles = driver.getWindowHandles();
    System.out.println(handles);
    handles.remove(handle1);
    String handle2 = handles.iterator().next();
    System.out.println(handle2);
    Thread.sleep(3000);
    driver.switchTo().window(handle1);
     Thread.sleep(3000);
    driver.switchTo().window(handle2);
    Assert.assertEquals(handle2, driver.getWindowHandle(), "No coincide el handle1");
  }
  
  @Test
  public void pruebaEnPáginaTestAutomation() {
    baseURL = "https://www.testandquiz.com/selenium/testing.html";
    driver.get(baseURL);

    // Escribo nombre y apellidos
    driver.findElement(By.name("firstName")).sendKeys("Nombre Apellidos");

    // Click en botón y se confirma porque cambia el color a verde
    driver.findElement(By.id("idOfButton")).click();
    Assert.assertTrue(driver.findElement(By.cssSelector("button[style='background: green;']")).isDisplayed());

    // Selecciono hombre en radio button
    driver.findElement(By.cssSelector("input[value='male']")).click();

    // Activo los dos Checkbox
    driver.findElement(By.cssSelector("input.Automation")).click();
    driver.findElement(By.cssSelector("input.Performance")).click();

    // Uso el select y elijo "Manual Testing"
    Select dropDown = new Select(driver.findElement(By.id("testingDropdown")));
    dropDown.selectByVisibleText("Manual Testing");

    // Pulsar botón de alerta y aceptar
    driver.findElement(By.cssSelector("button[onclick=\"alert('hi, JavaTpoint Testing');\"]")).click();
    Assert.assertEquals(driver.switchTo().alert().getText(), "hi, JavaTpoint Testing");
    driver.switchTo().alert().accept();

    // Pulsar botón de confirmación y cancelar
    driver.findElement(By.cssSelector("button[onclick='generateConfirmBox()']")).click();
    driver.switchTo().alert().dismiss();
    Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "You pressed Cancel!");

    driver.findElement(By.cssSelector("button[onclick='generateConfirmBox()']")).click();
    driver.switchTo().alert().accept();
    Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "You pressed OK!");
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
  }

 

}
