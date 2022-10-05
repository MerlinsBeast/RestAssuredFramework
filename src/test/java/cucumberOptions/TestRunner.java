package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    features="src/test/java/featurefiles",
        plugin="json:target/jsonReports/cucumber-reports.json",
        glue={"stepDefinations"},
        tags= ""
            )
public class TestRunner {

}
