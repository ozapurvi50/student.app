package com.studentapp.assertionexample;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Created by Jay
 */
public class AssertionExampleDemo {

    //for asssertion we use body after then method it will give you body method
    static ValidatableResponse response; //because we are using then it is validatable

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
        //response.log().all(); to print all data
    }


    // 1) Verify that the products of limit is equal to 10
    @Test
    public void test001() {
        response.body("limit", equalTo(10)); //Test pass
        //     response.body("limit", equalTo(11)); //Test fail
    }

    // 2) Verify that the products of total is = 51957
    @Test
    public void test002() {
        //Homeowrk
        response.body("total", equalTo(51957)); //Test pass
    }

    // 3) Check the Name 'Duracell - AA Batteries (8-Pack)' is available in List of product's name
    @Test
    public void test003() {
        // check data in json.com $.data[*].name
        response.body("data.name",hasItem("Duracell - AA Batteries (8-Pack)"));

    }

    // 4) Check Multiple Names (Energizer - MAX Batteries AA (4-Pack), Duracell - 9V Batteries (2-Pack)) are available in list of product's name
    @Test
    public void test004() {
        //Homework  use hasitem s and second items with coma u can use as many as items

    }

    // 5) Verify the 'name' field inside first categories map for the first data (Checking Values inside Map using hasKey(entityType))
    @Test
    public void test005() {
        //make a path in json.com and get path
        response.body("data[0].categories[0]",hasKey("name")); //this is single key check


    }

    // 6) Check entry 'manufacturer = Energizer' is inside map of product name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test006() {

        response.body("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}", hasItem(hasEntry("manufacturer", "Energizer")));
       // response.body("data.findAll{it.price== '5.99'}"); you can validate anything use hasvalue..if multiple use hasitems

    }
    // 7) Checking multiple values in the same statement
    @Test
    public void test007() {
        response
                .body("limit", equalTo(10))
                .body("data.name", hasItem("Duracell - AA Batteries (8-Pack)"))
                .body("data[0].categories[0]", hasKey("name"));
    }
    // 8) Logical Assertions
    @Test
    public void test008() {

        response
                .body("limit", equalTo(10))  //or
                .body("limit", lessThan(11))   //or
                .body("limit", greaterThan(9))  //or
                .body("limit", greaterThanOrEqualTo(10)) //or
                .body("limit",lessThanOrEqualTo(11));
    }
}
