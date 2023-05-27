package com.studentapp.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Jay Vaghani
 */
public class TestBase {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8090;
        RestAssured.basePath = "/student";
    }
}
