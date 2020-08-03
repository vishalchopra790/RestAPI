package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\java\\features",
                   glue = {"stepDefinations"}
        //tags = "@DeletePlace"
                   ,dryRun = false,
                    monochrome =true,
        plugin = {"html:target/default-cucumber-reports",
                "json:target/jsonReports/cucumber.json",
                "rerun:target/rerun.txt"})
public class TestRunner {
}
