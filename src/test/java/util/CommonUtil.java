package util;

import io.restassured.path.json.JsonPath;

/**
class contains common utils together to support oop
 */
public class CommonUtil {

/**
* method will return the size of array inside the nested json
* @param response in String format
  @param jsonPath corresponding to which size needs to be find out
 @return size of jsonArray
 */
 public static int arraySizeInJson(String response, String jsonPath){
  String sizeElement = jsonPath+".size()";
  JsonPath jp = new JsonPath(response);
  return jp.getInt(sizeElement);
 }
}
