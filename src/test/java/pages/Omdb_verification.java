package pages;

import io.cucumber.datatable.DataTable;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

/**
 * Class is for validation of omdb api endpoint
 * it contains main methods of search by string
 *                             search by id
 *                             search by title
 */
public class Omdb_verification {

    Logger log = Logger.getLogger(Omdb_verification.class.getName());
    ConfigReader configReader = new ConfigReader();
    TestContext testContext = new TestContext();
    AttributeByPath attributeByPath = new AttributeByPath();

    /**
     * method is to search the api using the string as param
     * @param searchValue  is 's'
     * this will save the response in test context for future use
     * there is pagination which will runs from page number 1 till Movie Not Found is returned by api in response
     * param for pagination is page
     */
    public void search(String searchValue){
        log.info("In the search by string method");
        StringBuilder response = new StringBuilder();
        ArrayList<Response> responseList = new ArrayList<>();
        int pageNUmber =1;
        while (true) {
            Response resp =RequestBuilder.buildRequestSpecification("s", searchValue, "page", pageNUmber)
                    .when().get()
                    .then().log().all().extract().response();
            if(resp.asString().contains("Movie not found!"))
                break;
            else
                responseList.add(resp);
            pageNUmber++;
        }
        testContext.setResponseList(responseList);
    }

    /**
     to verify the status code of the response
     @param   statusCode is the expected Status Code
     @return  boolean
     */
    public boolean isStatusCodeMatched(int statusCode) {
        return testContext.getResponse().statusCode()==statusCode;
    }

    /**
     * to read a tag called totalResults
     * @return integer value of tag
     * @throws ParseException
     */
    public int numberOfItems() throws ParseException {
        String response = ((RestAssuredResponseImpl) testContext.getResponseList().get(0)).asString();
        return Integer.parseInt(attributeByPath.getStringByJsonPath(response,"totalResults"));

    }

    /**
     * this is to verify the list of titles of movies from the response body
     * @param dataTable of list of titles to be matched
     * @return boolean
     */
    public boolean isTitlesMatched(DataTable dataTable) {
        ArrayList responseList = testContext.getResponseList();
        List<List<Object>> titleList = new ArrayList<>();
        for (Object o : responseList) {
            String responseString = ((RestAssuredResponseImpl) o).asString();
            titleList.add(attributeByPath.getListByJsonPath(responseString, "Search.Title"));

        }
        List allTitles = titleList.stream().flatMap(list-> list.stream()).collect(Collectors.toList());
        return allTitles.containsAll(dataTable.asList());
    }

    /**
     to get the movie details by title of the movie
     * @param : title is the input parameter and return type is void
     */
    public void get_by_title(String title) {
        Response response =RequestBuilder.buildRequestSpecification("t", title)
                .when().get()
                .then().log().all().extract().response();
        testContext.setResponse(response);
    }

    /**
     to verify whether the plot contains the input string or not
     will return true in case plot contain input string
     else return false
     @param string to be matched against the Plot value
     */
    public boolean doesPlotContainsString(String str) {
        String plot= attributeByPath.getStringByJsonPath(testContext.getResponse().asString(), "Plot");
        return plot.toLowerCase().contains(str.toLowerCase());
    }

    /**
     * to get the parameter value of parameter passed as input
     * Example : param = runTime
     *           return = value of runTime
     */
    public String getParamValue(String param) {
        if(param.equalsIgnoreCase("release date"))
            param = "Released";
        return attributeByPath.getStringByJsonPath(testContext.getResponse().asString(), param);
    }

    /**
     this method is specific to saving a particular param, and it's value in testcontext
     value is saved in key value form in test context with
     @param  "paramToSave" as key, and it's corresponding value as value of Hashmap
     @param dataTable contains key_value pair of parameters to be matched from response
     this can further is made generic by taking "Search" as parameter
     */
    public void saveFromResponse(String paramToSave, DataTable dataTable) {
        String key = String.valueOf(dataTable.asMap(String.class,String.class).keySet().toArray()[0]);
        String value = String.valueOf(dataTable.asMap(String.class,String.class).values().toArray()[0]);
        HashMap param_paramValue = new HashMap();
        ArrayList responseList = testContext.getResponseList();
        //traversing each response List
        for (Object eachResponse : responseList) {
            boolean breakLoop =false;
            //getting size of each Response "Search" array
            int size = CommonUtil.arraySizeInJson(((RestAssuredResponseImpl) eachResponse).asString(),"Search");
            for(int i=0; i< size;i++){
                String jsonPath = "Search[" + i + "]." + key;
                String searchParamValue =attributeByPath.getStringByJsonPath(((RestAssuredResponseImpl) eachResponse).asString() ,jsonPath);
                if(searchParamValue.equalsIgnoreCase(value)){
                    param_paramValue.put(paramToSave,attributeByPath.getStringByJsonPath(((RestAssuredResponseImpl) eachResponse).asString()
                            ,"Search[" + i + "]." + paramToSave));
                    testContext.setParamValueMap(param_paramValue);
                    breakLoop=true;
                    break;
                }
            }
            if(breakLoop)
                break;
        }
    }

    /**
     creating a separate method for get details of movie by using id as queryParam
     @param id as query Param
     get_by_id and get_by_title can be combined to make generic method in case of query parameter
     */
    public void get_by_id(String id) {
        String idValue =testContext.getParamValueMap().get(id).toString();
        Response response =RequestBuilder.buildRequestSpecification("i", idValue)
                .when().get()
                .then().log().all().extract().response();
        testContext.setResponse(response);
    }
}
