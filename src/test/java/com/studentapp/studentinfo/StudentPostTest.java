package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class StudentPostTest extends TestBase {

    @Test
    public void createStudent() {
//        String payload = "{\n" +
//                "    \"firstName\": \"Purvi\",\n" +
//                "    \"lastName\": \"Patel\",\n" +
//                "    \"email\": \"prime123@gmail.com\",\n" +
//                "    \"programme\": \"QA Engineer\",\n" +
//                "    \"courses\": [\n" +
//                "        \"Java\",\n" +
//                "        \"Python\"\n" +
//                "    ]\n" +
//                "}";
        //instead of writing this we wrote methods in studentPOJO class.
        String email = TestUtils.getRandomValue()+"Prime@gmail.com";
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Prime");
        studentPojo.setLastName("Test");
        studentPojo.setEmail(email); //random email gerantion object created above
        studentPojo.setProgramme("Automation Testing");
        studentPojo.setCourses(courseList);
        Response response = given()
                .header("Content-Type", "application/json") //taken from post request in post man from header
                .when()
                .body(studentPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

    }
}
