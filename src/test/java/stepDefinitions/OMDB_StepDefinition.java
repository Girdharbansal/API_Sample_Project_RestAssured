package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import pages.Omdb_verification;

/**
 * Step Definition to define the technical implementation of feature which is business end
 */
public class OMDB_StepDefinition {
    static Logger log = Logger.getLogger(OMDB_StepDefinition.class.getName());
    Omdb_verification omdb = new Omdb_verification();

    @Given("The API is generated successfully")
    public void apiKeyIsGenerated(){
        log.info("API key has been generated manually");
    }

    @When("User call the GET ombi api using search {string} and using pagination")
    public void userCallTheOmbiApiUsingSearchAndUsingPagination(String searchValue) {
        omdb.search(searchValue);
    }

    @Then("Status code of is {int}")
    public void statusCodeOfIs(int statusCode) {
        Assert.assertTrue(omdb.isStatusCodeMatched(statusCode));
    }

    @And("Number of items {string} {int} in response")
    public void numberOfItemsInResponse(String operator, int number) throws ParseException {
        Assert.assertTrue(omdb.numberOfItems() > number);
    }

    @And("Response contains below titles")
    public void responseContainsBelowTitles(DataTable dataTable) {
        Assert.assertTrue(omdb.isTitlesMatched(dataTable));

    }

    @When("User call the GET ombi api using title {string}")
    public void userCallTheOmbiApiUsingTitle( String title) {
        omdb.get_by_title(title);
    }

    @And("Plot of movie contains {string}")
    public void plotOfMovieContains(String str) {
        Assert.assertTrue(omdb.doesPlotContainsString(str));
    }

    @And("Movie {string} is {string}")
    public void movieParamVerify(String param, String paramValue) {
        Assert.assertEquals(paramValue,omdb.getParamValue(param));
    }

    @And("Save the {string} from response for")
    public void saveTheFromResponseFor(String paramToSave, DataTable dataTable) {
        omdb.saveFromResponse(paramToSave,dataTable);
    }

    @When("User call the GET ombi api using saved {string}")
    public void userCallTheGETOmbiApiUsingSaved(String id) {
        omdb.get_by_id(id);
    }
}
