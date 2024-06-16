package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/fancode.feature",
        glue = "stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner {
}
