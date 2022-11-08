package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import util.ConfigReader;

import static io.restassured.RestAssured.given;

/**
 generic class to create common body for the request chaining using request specification
 */
public class RequestBuilder {
    static ConfigReader configReader = new ConfigReader();

    /**
     * @param param is the queryParam
     * @param paramValue is the Value of QueryParam
     * @return RequestSpecification
     */
    public static RequestSpecification buildRequestSpecification(String param, String paramValue){
        RequestSpecification resSpec = new RequestSpecBuilder().setBaseUri(configReader.properties.getProperty("baseURL"))
                .addQueryParam("apikey","61894ab9")
                .build();
        return given().spec(resSpec).queryParam(param,paramValue);
    }

    /**
     * overiding of above parameter in case of pagination
     * @param param first query parameter
     * @param paramValue value of first query parameter
     * @param page second query parameter
     * @return RequestSpecification
     */
    public static RequestSpecification buildRequestSpecification(String param, String paramValue, String page, int pageNumber){
        RequestSpecification resSpec = new RequestSpecBuilder().setBaseUri(configReader.properties.getProperty("baseURL"))
                .addQueryParam("apikey","61894ab9")
                .build();
        return given().spec(resSpec).queryParam(param,paramValue).queryParam(page,pageNumber);
    }
}