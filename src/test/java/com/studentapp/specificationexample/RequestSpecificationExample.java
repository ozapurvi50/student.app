package com.studentapp.specificationexample;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay
 */
public class RequestSpecificationExample {

    private static RequestSpecBuilder builder;  //class
    private static RequestSpecification requestSpecification;   //Interface

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", "application/json"); //using same thing in all methods
        builder.addQueryParam("$limit", 2); //using same thing in all methods
        requestSpecification = builder.build(); //builder to build that specification
    }

    @Test
    public void test001() {
        given().log().all() //log for console printing to debug
               /* .header("Content-Type","application/json") //details from swagger document
                .queryParam("$limit", 2)*/  //used above builder
                .spec(requestSpecification)
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test
    public void test002() {
        given().log().all() //log for console printing to debug
         /* .header("Content-Type","application/json") //details from swagger document
                .queryParam("$limit", 2)*/  //used above builder
                .spec(requestSpecification)
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    }



