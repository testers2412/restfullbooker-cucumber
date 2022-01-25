package com.restfullbooker.testbase;

import com.restfullbooker.utils.PropertyReader;
import com.restfullbooker.utils.TestUtils;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase extends TestUtils {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader =propertyReader.getInstance();

        //   RestAssured.baseURI = "http://localhost";
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
       /* //RestAssured.port = 8080;
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        // RestAssured.basePath = "/student";
        RestAssured.basePath = Path.STUDENT;*/

}
}
