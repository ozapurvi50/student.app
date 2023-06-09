package com.studentapp.jsonassert;

import com.studentapp.testbase.TestBase;
import io.restassured.RestAssured;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonAssertExample extends TestBase {


    @Test
    public void getStudents() throws IOException, JSONException { //using text from textfile
        String expectedValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") +
                File.separator + "file.txt")));
        String actualValue = RestAssured.given()
                .when()
                .get("/list")
                .asString();
        System.out.println(expectedValue);
        System.out.println(actualValue);
        JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.LENIENT); //no order required comparing two file
    }

    @Test
    public void getStudentsStrict() throws IOException, JSONException {

        String expectedValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") +
                File.separator + "difforder.txt")));
        String actualValue = RestAssured.given().when().get("/list").asString();
        System.out.println(expectedValue);
        System.out.println(actualValue);
        JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.STRICT); //Strict comparing line by line order hard assert
    }
}
