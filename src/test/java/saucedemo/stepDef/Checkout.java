package saucedemo.stepDef;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
public class Checkout{
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    public Checkout() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        this.login_standard_user();
    }

    public void login_standard_user(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Given("user is in Products page")
    public void userIsInProductsPage() {
        String curUlr = driver.getCurrentUrl();
        Assert.assertEquals(curUlr, this.baseUrl + "inventory.html");
    }


    @Given("user select the product want to buy.")
    public void userSelectTheProductWantToBuy() {
        driver.findElement(By.id("item_4_img_link")).click();
    }

    @Then("user click Add to cart button")
    public void userClickAddToCartButton() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

    }

    @Then("user click Shopping Cart icon on the top right of the page")
    public void userClickShoppingCartIconOnTheTopRightOfThePage() {
        driver.findElement(By.className("shopping_cart_link")).click();
        //driver.findElement(By.id("shopping_cart_link")).click();
    }

    @Then("click Checkout page")
    public void clickCheckoutPage() {
        driver.findElement(By.id("checkout")).click();
    }

    @Then("user input a valid first name")
    public void userInputAValidFirstName() {
        driver.findElement(By.id("first-name")).sendKeys("Nur");
    }

    @And("user input a valid last name")
    public void userInputAValidLastName() {
        driver.findElement(By.id("last-name")).sendKeys("Rachmat");
    }

    @And("user input a valid zip or postal code")
    public void userInputAValidZipPostalCode() {
        driver.findElement(By.id("postal-code")).sendKeys("30113");
    }

    @Then("user click Continue page")
    public void userClickContinuePage() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("user click Finish button")
    public void userClickFinishButton() {
        driver.findElement(By.id("finish")).click();
    }


    @Then("user receives confirmation messages")
    public void userReceivesConfirmationMessages() {
        String msg = driver.findElement(By.className("complete-header")).getText();
        System.out.println(msg);
        Assert.assertEquals("Thank you for your order!", msg);
    }


}
