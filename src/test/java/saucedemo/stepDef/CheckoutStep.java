package saucedemo.stepDef;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import saucedemo.Saucedemo;
import saucedemo.utils.ScenarioContext;

public class CheckoutStep {
    //WebDriver this.driver;
    //String baseUrl = "https://www.saucedemo.com/";
    boolean fnIsEmpty, lnIsEmpty, zipIsEmpty;
    WebDriver driver;
    ScenarioContext context;
    public CheckoutStep(ScenarioContext context) {
        this.context = context;
        this.driver = this.context.getDriver();
    }

    /*@Before("@Checkout1 or @Checkout2")
    public void loginStandard(){
        this.context.loginStandard();
    }*/




    @Given("user select the product want to buy.")
    public void userSelectTheProductWantToBuy() {
        this.driver.findElement(By.id("item_4_img_link")).click();
    }

    /*@Then("user click Add to cart button")
    public void userClickAddToCartButton() {
        this.driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

    }*/



    @Then("user click Checkout page")
    public void clickCheckoutPage() {
        this.driver.findElement(By.id("checkout")).click();

        String title =  this.driver.findElement(By.className("title")).getText();
        Assert.assertEquals("Checkout: Your Information", title);
    }

    @Then("user input a valid first name")
    public void userInputAValidFirstName() {
        this.driver.findElement(By.id("first-name")).sendKeys("Nur");
    }

    @And("user input a valid last name")
    public void userInputAValidLastName() {
        this.driver.findElement(By.id("last-name")).sendKeys("Rachmat");
    }

    @And("user input a valid zip or postal code")
    public void userInputAValidZipPostalCode() {
        this.driver.findElement(By.id("postal-code")).sendKeys("30113");
    }

    @Then("user click Continue page")
    public void userClickContinuePage() {

        if(!this.driver.findElement(By.id("first-name")).getText().isEmpty()
                && !this.driver.findElement(By.id("last-name")).getText().isEmpty()
                && !this.driver.findElement(By.id("postal-code")).getText().isEmpty()
        ){
            this.driver.findElement(By.id("continue")).click();
            String title =  this.driver.findElement(By.className("title")).getText();
            Assert.assertEquals("Checkout: Overview", title);
        }else{
            System.out.println("From : " + this.driver.getCurrentUrl());
            this.driver.findElement(By.id("continue")).click();
            System.out.println("To : " + this.driver.getCurrentUrl());
        }

    }

    @Then("user click Finish button")
    public void userClickFinishButton() {
        this.driver.findElement(By.id("finish")).click();
        String title =  this.driver.findElement(By.className("title")).getText();
        Assert.assertEquals("Checkout: Complete!", title);
    }


    @Then("user receives confirmation messages")
    public void userReceivesConfirmationMessages() {
        String msg = this.driver.findElement(By.className("complete-header")).getText();
        System.out.println(msg);
        Assert.assertEquals("Thank you for your order!", msg);
    }

    @Then("user forbidden to checkout without product")
    public void userForbiddenToCheckoutWithoutProduct() {
        String title =  this.driver.findElement(By.className("title")).getText();
        //user harus tetap berada di halaman Your Cart
        Assert.assertEquals("Your Cart", title);
    }
}
