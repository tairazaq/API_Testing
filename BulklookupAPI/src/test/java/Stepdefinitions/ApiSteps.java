package Stepdefinitions;
import Datamodel.BulkMgtModel;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
//import cucumber.api.junit.Cucumber;
import cucumber.api.java.en.When;
import org.junit.Assert;
import services.BulkApiServices;

import java.util.List;
import java.util.Map;


public class ApiSteps {

     private BulkMgtModel bulkMgtModel;
     private Response response;
     private BulkApiServices bulkApiServices;



     BulkMgtModel.BulkMgtModelBuilder bulkMgtData = BulkMgtModel.builder();

     public ApiSteps(BulkApiServices bulkApiServices){

         this.bulkApiServices = bulkApiServices;

     }

        @Given("^that the api is available and running  with Post Resource$")
        public void that_the_api_is_available_and_running_with_post_resource() throws Throwable {

         String[] bulkpostcode = new String[] {"OX49 5NU", "M32 0JG", "NE30 1DP"};

         bulkMgtModel = bulkMgtData
                .postcodes(bulkpostcode).build();


     }

    @When("^i make a post request to the bulk lookup api post request")
    public void iMakeAPostRequestToTheBulkLookupApiItShouldGiveStatusCode() throws Throwable {

        response = bulkApiServices.createBulkRecord(bulkMgtModel);

    }

    @Then("^the bulk postcodes should be retrieved successfully with status code (.*)")
    public void theBulkPostcodesShouldBeRetrievedSuccessfully(int statusCode) {

        Assert.assertEquals(statusCode, response.statusCode());
       String actualQuery = response.jsonPath().getList("result.query").get(0).toString();
        Assert.assertEquals("OX49 5NU", actualQuery);

    }


    @Given("^that the api is available and running  with invalid postcodes$")
    public void thatTheApiIsAvailableAndRunningWithInvalidPostcodes() {

        //incorrect postcodes from task 1.3
        String[] bulkpostcode = new String[] {"AB8 1RA" , "DL8 1RA", "EX2M 2PF"};
        bulkMgtModel = bulkMgtData.postcodes(bulkpostcode).build();
    }

    @Then("^the bulk lookup postcodes should not be retrieved with status code (.*)")
    public void theBulkLookupPostcodesShouldNotBeRetrievedWithStatusCode(int statusCode) {

        Assert.assertEquals(statusCode, response.statusCode());

        Object actualQuery = response.jsonPath().getList("result.result").get(0);
        Assert.assertEquals(null, actualQuery);

    }
}

