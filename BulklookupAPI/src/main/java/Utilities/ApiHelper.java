package Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;

public class ApiHelper {


        private static Gson gson;


        static {

            RestAssured.baseURI = "https://api.postcodes.io";
        }

        protected static RequestSpecification givenConfig() {

            RestAssured.useRelaxedHTTPSValidation();

            return given().header("Accept-Language", "en")
                    .header("Content-Type", "application/json");

        }

        public static Gson gson(GsonBuilder gsonBuilder) {
            gson = gsonBuilder.create();
            return gson;
        }

        public static Gson gson() {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gson(gsonBuilder);
            return gson;
        }


}
