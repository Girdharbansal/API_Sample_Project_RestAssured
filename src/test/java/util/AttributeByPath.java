package util;

import io.restassured.path.json.JsonPath;

import java.util.List;

/**
Class is to fetch the particular tag or attribute value from the response body
 */
public class AttributeByPath {

    /**
     * method is to get the value of corresponding jsonPath in response body
     * @param response in String
     * @param attributePath is the path of the tag need to be find in Json Object
     * @return String value of  the jsonPath provided
     */
    public String  getStringByJsonPath(String response, String attributePath){
        JsonPath jp = new JsonPath(response);
        return  jp.getString(attributePath);
    }

    /**
     * method is to get the value of corresponding jsonPath in response body
     * @param response in String
     * @param attributePath is the path of the tag need to be find in Json Object
     * @return Value of the jsonPath provided in case of value is in List format as in case of Nested Json
     */
    public List<Object> getListByJsonPath(String response, String attributePath){
        JsonPath jp = new JsonPath(response);
        return  jp.getList(attributePath);
    }
}
