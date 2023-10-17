package saucedemo.utils;

import org.openqa.selenium.WebDriver;
import saucedemo.Saucedemo;

public class ScenarioContext extends Saucedemo {
    //private WebDriver driver;

    public ScenarioContext(){
        setup();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
