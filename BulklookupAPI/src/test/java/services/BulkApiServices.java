package services;

import Datamodel.BulkMgtModel;
import Utilities.ApiHelper;
import com.jayway.restassured.response.Response;

public class BulkApiServices extends ApiHelper {

    public static final String Bulk_Api_Url = "/postcodes";

    Response response;

    public Response createBulkRecord(BulkMgtModel bulkMgtModel){

        response = givenConfig().when().body(gson().toJson(bulkMgtModel))
                .post(Bulk_Api_Url);
        return response;
    }

}
