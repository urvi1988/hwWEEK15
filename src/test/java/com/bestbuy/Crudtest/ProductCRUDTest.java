package com.bestbuy.Crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest extends TestBase {
    int idNumber;
    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI= "http://localhost";
        RestAssured.port=3030;
        RestAssured.basePath="/products";
    }
@Test // get method to get all list
    public  void test001(){
     given()
            .when()
             .log().all()
            .get()
    .then().log().all().statusCode(200);
}
@Test // post method to post new and retrive id
    public void test002() {

    ProductPojo pojo = new ProductPojo();
    pojo.setName("amazon batteries");
    pojo.setManufacturer("Amazon basics");
    pojo.setModel("MY5967WV");
    pojo.setType("basics");
    pojo.setUPC("00124456");
    pojo.setImage("img/hijk");
    pojo.setPrice(8.99);
    pojo.setShipping(0);
    pojo.setDescription("stronger to use");
    pojo.setURL("http//www.amazon.com");

    Response response = given()
            .log().all()
                    .header("Content-Type","application/json")
                    .when()
                    .body(pojo)
                    .post();
    response.then().statusCode(201);
    int idNumber=response.then().extract().path("id");
    System.out.println(idNumber);

}
@Test // patch method to update
    public void test003() {
    ProductPojo pojo = new ProductPojo();
    pojo.setManufacturer("Amazon basics");
    pojo.setModel("MY5967WV");
    pojo.setPrice(10.50);
    pojo.setShipping(0);

    Response response = given()
            .log().all()
            .header("Content-Type", "application/json")
            .pathParam("id", "9999686")
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
                .pathParam("id","9999686")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    @Test // retrive id and validate
    public void test005(){
        Response response=given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999686")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }
}


