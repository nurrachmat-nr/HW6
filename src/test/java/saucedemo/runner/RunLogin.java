package saucedemo.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/saucedemo/features",
        glue = "saucedemo.stepDef",
        plugin = {"html:target/HTML_report.html"}
)
public class RunLogin {
}
