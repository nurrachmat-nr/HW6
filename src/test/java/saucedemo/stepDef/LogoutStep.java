package saucedemo.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import saucedemo.utils.ScenarioContext;

import java.time.Duration;

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
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        try{
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
            logoutBtn.click();
            Assert.assertTrue("Logout menu clicked", true);
        }catch (ElementNotInteractableException e) {
            System.out.println("Element not interactable: " + e.getMessage());
            Assert.fail();
        }
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

}
