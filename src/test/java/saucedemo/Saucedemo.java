package saucedemo;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Saucedemo {
    public WebDriver driver;
    public String baseUrl = "https://www.saucedemo.com/";

    public String username;
    public String password;

    public String errorUserPass = "Epic sadface: Username and password do not match any user in this service";
    public String lockedOutUser = "Epic sadface: Sorry, this user has been locked out.";

    public String navBackAfterLogin = "Epic sadface: You can only access '/inventory.html' when you are logged in.";

    public boolean fnIsEmpty, lnIsEmpty, zipIsEmpty;
    public boolean isAfterLogout = false;
    //private ScenarioContext context;
    public Saucedemo() {
        System.out.println("Constructor Saucedemo");

    }

    public void setup(){
        this.driver = new ChromeDriver();
        //this.driver.manage().window().maximize();
        this.driver.get(baseUrl);
    }

    /*@Before
    public void startChrome(){
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get(baseUrl);
    }*/
    //@After(value = "")
    public void tearDown() {
        System.out.println("Quit Chrome Driver");
        this.driver.quit();
    }

    public void loginStandard(){
        this.driver.findElement(By.id("user-name")).sendKeys("standard_user");
        this.driver.findElement(By.id("password")).sendKeys("secret_sauce");
        this.driver.findElement(By.id("login-button")).click();
        System.out.println("Login done");
    }


    public void checkErrorMessages() {
        String curUlr = this.driver.getCurrentUrl();
        //System.out.println(curUlr);
        //System.out.println(curUlr.equalsIgnoreCase(this.baseUrl + "checkout-step-one.html"));
        //
        if (curUlr.equalsIgnoreCase(this.baseUrl + "checkout-step-one.html")){
            String errorMsg = this.driver.findElement(By.xpath("/html//div[@id='checkout_info_container']//form//h3")).getText();
            System.out.println(errorMsg);
            Assert.assertEquals("Error: First Name is required", errorMsg);
        }else{
            String errorMsg = this.driver.findElement(By.xpath("//div[@id='login_button_container']//form//h3")).getText();
            if(isAfterLogout){
                Assert.assertEquals(this.navBackAfterLogin, errorMsg);
            }else{
                if(!this.password.equalsIgnoreCase("secret_sauce")) {
                    //wrong password
                    Assert.assertEquals(this.errorUserPass, errorMsg);
                }else if(!this.username.equalsIgnoreCase("standard_user")) {
                    //wrong username
                    if(this.username.equalsIgnoreCase("locked_out_user")){
                        Assert.assertEquals(this.lockedOutUser, errorMsg);
                    }else{
                        Assert.assertEquals(this.errorUserPass, errorMsg);
                    }
                }
            }


        }

    }


    public void userInLoginPage() {
        String curUlr = this.driver.getCurrentUrl();
        Assert.assertEquals(this.baseUrl, curUlr);
    }

    public void userInHomePage() {
        String curUlr = this.driver.getCurrentUrl();
        Assert.assertEquals(this.baseUrl + "inventory.html", curUlr);
    }
}
