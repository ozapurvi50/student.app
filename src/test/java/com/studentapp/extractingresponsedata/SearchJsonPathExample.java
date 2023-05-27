package com.studentapp.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class SearchJsonPathExample {

    static ValidatableResponse response;

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


    // 1) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit"); //getting integer value from limit path and store into variable limit

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract the list of IDs of all products
    @Test
    public void test002() {
        List<Integer> listOfIDs = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIDs);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract first product name from data by providing list index value
    @Test
    public void test003() {
        //String productName = response.extract().path("data[0].name");
        List<String> productName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Get the categories list of the first data
    @Test
    public void test004() {
        //reponse returing string value as list
        List<String> categories = response.extract().path("data.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Categories list are : " + categories);
        System.out.println("------------------End of Test---------------------------");

    }

    // 5)Print the size of data
    @Test
    public void test005() {
//Homework
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Get All the products Name from data
    @Test
    public void test006() {

        //Homework
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are : ");
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get all the values for Name == Duracell - AA Batteries (8-Pack) //from json all detials of particular product
    @Test
    public void test007() {
        //we are taking hashmap , because response is returning map
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Duracell - AA Batteries (8-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Duracell - AA Batteries (8-Pack)' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the price for product Name == Duracell - D Batteries (4-Pack)
    @Test
    public void test008() {
        List<Double> price = response.extract().path("data.findAll{it.name == 'Duracell - D Batteries (4-Pack)'}.price");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The price of product name 'Duracell - D Batteries (4-Pack)' is : " + price);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get the Names of products which have price less than 16.99
    @Test
    public void test009() {
        List<String> price1 = response.extract().path("data.findAll{it.price < 9.99}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of products that price is less than 9.99 are: " + price1);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get the manufacturer of products whose name Start = Ene
    @Test
    public void test010() {
//if you don't know return data type use ? in angle bracket
        List<?> nameWithENE = response.extract().path("data.findAll{it.name.startsWith('Ene')}.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of products whose name Start = Ene are: " + nameWithENE);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get the price of products whose name end with = Black
    @Test
    public void test011() {
        List<?> price = response.extract().path("data.findAll{it.name.endsWith('Black')}.price");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The prices of products whose name end with = Vehicles are: " + price);
        System.out.println("------------------End of Test---------------------------");
    }

    // 12) Get the id of product whose name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test012() {
        List<HashMap<String, ?>> productListMap = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}");
        HashMap<String,?> productMap = productListMap.get(0);
        int id = (Integer) productMap.get("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of product whose name 'Energizer - N Cell E90 Batteries (2-Pack)' is : "+ id);
        System.out.println("------------------End of Test---------------------------");
    }

}
