package saucedemo.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import saucedemo.utils.ScenarioContext;

public class AddtocartStep {

    WebDriver driver;
    ScenarioContext context;

    String productName;

    public AddtocartStep(ScenarioContext context) {
        this.context = context;
        this.driver = this.context.getDriver();
    }

    @Given("user is in Products page")
    public void userIsInProductsPage() {
        String curUlr = this.driver.getCurrentUrl();
        Assert.assertEquals(this.context.baseUrl + "inventory.html", curUlr);
    }
    @Then("{int} product added to cart")
    public void oneProductAddedToCart(Integer item) {
        WebElement badge = this.driver.findElement(By.className("shopping_cart_badge"));
        if (badge.isDisplayed()){
            System.out.println("Product : " + badge.getText());
            Assert.assertEquals(Integer.toString(item) , badge.getText());
        }
    }

    @Then("user click Continue Shopping button")
    public void userClickContinueShoppingButton() {
        this.driver.findElement(By.id("continue-shopping")).click();
    }

    @Then("user select product with id {int}")
    public void userSelectProductWithId(Integer id) {
        String product_id = "item_"+ id + "_img_link";
        this.driver.findElement(By.id(product_id)).click();
    }

    @Then("user click Add to cart button")
    public void userClickAddToCartButton() {
        String btn_id = this.productName.replace(" ", "-").toLowerCase();
        this.driver.findElement(By.id("add-to-cart-"+btn_id)).click();
    }

    @Then("user add (.*) to the cart by click Add to cart button$")
    public void userAddProduct_nameToTheCartByClickAddToCartButton(String name) {
        String btn_id = name.replace(" ", "-").toLowerCase();
        this.driver.findElement(By.id("add-to-cart-"+btn_id)).click();
    }

    @Then("user click Shopping Cart icon on the top right of the page")
    @And("user is in Your Cart Page")
    public void userClickShoppingCartIconOnTheTopRightOfThePage() {
        this.driver.findElement(By.className("shopping_cart_link")).click();
        String title =  this.driver.findElement(By.className("title")).getText();
        Assert.assertEquals("Your Cart", title);
        //this.driver.findElement(By.id("shopping_cart_link")).click();
    }

    @Given("user was added product to the cart")
    public void userWasAddedProductToTheCart() {
        this.userIsInProductsPage();
        this.userSelectProductWithId(1);
        this.userAddProduct_nameToTheCartByClickAddToCartButton("Sauce Labs Bolt T-Shirt");
        this.oneProductAddedToCart(1);
    }

    @Given("user was added one more product to the cart")
    public void userWasAddedOneMoreProductToTheCart() {
        this.driver.findElement(By.id("back-to-products")).click();

        this.userSelectProductWithId(2);
        this.userAddProduct_nameToTheCartByClickAddToCartButton("Sauce Labs Onesie");
        this.oneProductAddedToCart(2);
    }
}
