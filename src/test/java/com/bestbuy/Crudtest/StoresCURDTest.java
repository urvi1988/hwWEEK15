package com.bestbuy.Crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {

    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }

    @Test // get method to get all list
    public void test001() {
        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test // post method to post new and retrive id
    public void test002() {

        StorePojo pojo = new StorePojo();
        pojo.setName("Monkey");
        pojo.setType("Retail");
        pojo.setAddress("101 London");
        pojo.setAddress2("England");
        pojo.setCity("London");
        pojo.setState("Bucks");
        pojo.setZip("LA101");
        pojo.setLat(12345);
        pojo.setLng(1001);
        pojo.setHours("Mon To Sun");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
        int idNumber = response.then().extract().path("id");
        System.out.println(idNumber);
    }

    @Test // patch method to update
    public void test003() {
        StorePojo pojo = new StorePojo();

        pojo.setCity("Paris");
        pojo.setState("France");
        pojo.setZip("LA201");
        pojo.setHours("Mon To Sat");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "8923")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);
    }
    @Test // delete method
    public void test004(){
        Response response=given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","8923")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    @Test // retrive id and validate
    public void test005(){
        Response response=given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","8923")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }

}