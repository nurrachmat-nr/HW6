package saucedemo.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import saucedemo.utils.ScenarioContext;

public class LoginStep {
    WebDriver driver;

    ScenarioContext context;

    public LoginStep(ScenarioContext context) {
        this.context = context;
        this.driver = this.context.getDriver();
    }

    @Given("user in login page")
    public void userInLoginPage() {
        this.context.userInLoginPage();
    }

    @Then("user submit username and password")
    public void userSubmitUsernameAndPassword(){
        this.context.loginStandard();
    }

    @Given("user was in home page")
    @Given("user was logged in successfully")
    public void userWasLoggedInSuccessfully() {
        this.context.userInHomePage();
    }


    @Given("user open the web address")
    public void user_is_on_login_page(){
        this.driver = this.context.getDriver();
    }
    @Then("user input (.*) as username$")
    public void user_input_standard_user_as_username(String username){
        this.context.username =  username;
        //this.driver.findElement(By.id("user-name")).sendKeys(username);
        this.context.getDriver().findElement(By.id("user-name")).sendKeys(username);
    }

    @Then("user input (.*) as password$")
    public void user_input_secret_sauce_as_password(String password){
        this.context.password = password;
        //this.driver.findElement(By.id("password")).sendKeys(password);
        this.driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("^user click login button$")
    public void user_click_login_button(){
        this.driver.findElement(By.id("login-button")).click();
    }


    @Then("error message should be displayed")
    public void checkErrorMessages() {
        this.context.checkErrorMessages();
    }
    @Then("user successfully login and entered home page \\(Inventory Page)")
    public void loginSuccess(){
        String curUlr = this.driver.getCurrentUrl();
        Assert.assertEquals(curUlr, this.context.baseUrl + "inventory.html");
    }

    @After
    public void tearDown() {
        this.context.tearDown();
    }
}
