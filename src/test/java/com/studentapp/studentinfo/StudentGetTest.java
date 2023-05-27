package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentsInfo() {
        Response response = given()
                .when()
                .get("/list");
        response.then().statusCode(200); //test for validation only
        response.prettyPrint();
    }

    @Test
    public void getSingleStudentInfo() {
        Response response = given()
                .pathParam("id", 8)
                .when()
                .get("/{id}"); //storing as variable and get result...
        response.prettyPrint();
    }

    @Test
    public void searchStudentWithParameter() {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("programme", "Disaster Management");
        qParams.put("limit", 2);

        Response response = given()
        //.queryParam("programme", "Disaster Management") //key and value
        //.queryParam("limit", 2) //to check 2 data
        //As above is key and value pair you can use map above response
                .queryParams(qParams)
                .when()
                .get("/list");
        response.prettyPrint();
    }

}
