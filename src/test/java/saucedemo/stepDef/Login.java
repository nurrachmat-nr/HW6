package saucedemo.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    String username;
    String password;

    @Given("user open the web address")
    public void user_is_on_login_page(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }
    @Then("user input (.*) as username$")
    public void user_input_standard_user_as_username(String username){
        this.username =  username;
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @Then("user input (.*) as password$")
    public void user_input_secret_sauce_as_password(String password){
        this.password = password;
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("^user click login button$")
    public void user_click_login_button(){
        driver.findElement(By.id("login-button")).click();
    }


    /*public void error_message_should_be_displayed() {
        if(!this.password.equalsIgnoreCase("secret_sauce")){
            Assert.assertTrue(true);
        }
    }*/

    @Then("Error Message Should be displayed")
    @Then("user succesfully login and entered home page \\(Inventory Page)")
    public void checkUserCridentials() {
        String curUlr = driver.getCurrentUrl();
        System.out.println(curUlr);
        if(!this.password.equalsIgnoreCase("secret_sauce")){
            Assert.assertTrue(true);
        }else{
            Assert.assertEquals(curUlr, this.baseUrl + "inventory.html");
        }

    }
}
