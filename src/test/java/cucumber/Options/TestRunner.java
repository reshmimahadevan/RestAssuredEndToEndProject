package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",plugin="json:target/jsonReports/cucumber-reports.json", glue = { "stepDefinition" }) //,tags= "@DeletePlace")
//mvn test -Dcucumber.filter.tags="@DeletePlace" -- If you run from maven
//mvn test verify -To run cucumber reporting -- plugin is used to create folder
public class TestRunner {

}
