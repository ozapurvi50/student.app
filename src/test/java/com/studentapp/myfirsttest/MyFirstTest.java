package com.studentapp.myfirsttest;

import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay Vaghani
 */
public class MyFirstTest extends TestBase {

    @Test
    public void getAllStudentInfo() {
        given()
                .when()
                .get("/list")
                //    .get("http://localhost:8090/student/list") when you extend you dont need to write this its all in basetest
                .then()
                .statusCode(200);

        Response response = given()
                .when()
                .get("/list");
        // .get("http://localhost:8090/student/list"); path is in base test
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
