package util;

import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class use is to store the values from the preceding api for the proceeding request usage
 * response as Response Class object
 * responseString as string value of response
 * responseList if the response is multiple as in case of pagination
 * paramValueMap to store any key value pair
 */
public class TestContext {

    private Response response;
    private String responseString;
    private ArrayList responseList;
    private HashMap paramValueMap;


    public ArrayList getResponseList() {
        return responseList;
    }

    public void setResponseList(ArrayList responseList) {
        this.responseList = responseList;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public HashMap getParamValueMap() {
        return paramValueMap;
    }

    public void setParamValueMap(HashMap paramValueMap) {
        this.paramValueMap = paramValueMap;
    }

}
