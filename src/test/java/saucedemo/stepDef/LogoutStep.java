package saucedemo.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import saucedemo.utils.ScenarioContext;

public class LogoutStep {

    WebDriver driver;
    ScenarioContext context;

    public LogoutStep(ScenarioContext context) {
        this.context = context;
        this.driver = this.context.getDriver();
    }


    @Then("user click burger icon")
    public void userClickBurgerIcon() {
        this.driver.findElement(By.id("react-burger-menu-btn")).click();
        Assert.assertTrue("Sidebar Menu Shown", this.driver.findElement(By.className("bm-menu-wrap")).isDisplayed());
    }

    @Then("user click Logout menu")
    public void userClickLogoutMenu() {
        this.driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("user successfully logout and redirected to the login page")
    public void userSuccessfullyLogoutAndRedirectedToTheLoginPage() {
        String curUlr = this.driver.getCurrentUrl();
        Assert.assertEquals(this.context.baseUrl, curUlr);
    }





    @Given("user was logged out successfully")
    public void userWasLoggedOutSuccessfully() {
        this.userClickBurgerIcon();
        this.userClickLogoutMenu();
        this.context.userInLoginPage();
    }

    @Then("user navigate back")
    public void userNavigateBack() {
        this.driver.navigate().back();
        this.context.isAfterLogout = true;
    }


    /*@After("@LogoutPositive or @LogoutNegative")
    public void tearDown() {
        this.context.tearDown();
    }*/
}
