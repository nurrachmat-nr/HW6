package saucedemo.stepDef;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import saucedemo.utils.ScenarioContext;

public class RemovefromcartStep {

    WebDriver driver;
    ScenarioContext context;

    String productName;

    public RemovefromcartStep(ScenarioContext context) {
        this.context = context;
        this.driver = this.context.getDriver();
    }
    @Then("user has (.*) in the cart$")
    public void userHasProduct_nameInTheCart(String productName) {
        this.productName = productName;
        Assert.assertEquals(productName, this.driver.findElement(By.className("inventory_item_name")).getText());
    }

    @Then("user click Remove button")
    public void userClickRemoveButton() {
        WebElement badge = this.driver.findElement(By.className("shopping_cart_badge"));
        System.out.println("Current Cart : " + badge.getText());
        String btn_id = this.productName.replace(" ", "-").toLowerCase();
        this.driver.findElement(By.id("remove-"+btn_id)).click();
    }

    @Then("{int} product left in the cart")
    public void item_leftProductLeftInTheCart(Integer item_left) {
        System.out.println("Item Left : " + item_left);
        if(item_left > 0){
            WebElement badge = this.driver.findElement(By.className("shopping_cart_badge"));
            System.out.println("Cart Left : " + badge.getText());
            if (badge.isDisplayed()){
                Assert.assertEquals("Cart left", (int) item_left, Integer.parseInt(badge.getText()));
            }else{
                Assert.fail();
            }

        }else{
            Assert.assertTrue("Cart is empty",true);
        }

    }
}
