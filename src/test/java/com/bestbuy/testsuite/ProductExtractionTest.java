package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("value of limit is:" + limit);
        Assert.assertEquals(10, limit);
        response.body("limit", equalTo(10));
    }

    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("value of total is:" + total);
        Assert.assertEquals(51957, total);
        response.body("total", equalTo(51957));
    }

    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String nameOfproduct = response.extract().path("data[4].name");
        System.out.println(nameOfproduct);
    }

    //24. Extract the names of all the products
    @Test
    public void test004() {
        List<String> nameofallproducts = response.extract().path("data.name");
        System.out.println("Name of all products:" + nameofallproducts);
        for (String s : nameofallproducts)
            if (s.equals(51957))
                Assert.assertTrue(true);
    }

    //25. Extract the productId of all the products
    @Test
    public void test005() {
        List<Integer> idforallProducts = response.extract().path("data.id");
        System.out.println("ID of all products:" + idforallProducts);
        for (Integer i : idforallProducts)
            if (i.equals(51957))
                Assert.assertTrue(true);
    }

    //26. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> dataSize = response.extract().path("data");
        int size = dataSize.size();
        System.out.println(size);
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println(values);
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<String> model = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}");
        System.out.println(model);
    }

    //29. Get all the categories of 8th products
    @Test
    public void test009() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println(categories);
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<?, ?>> categories = response.extract().path("data.findAll{it.id ==150115}.categories");
        System.out.println(categories);
    }

    //31. Get all the descriptions of all the products.//x.data[0].description
    @Test
    public void test011() {
        List<HashMap<?, ?>> descriptions = response.extract().path("data.description");
        System.out.println(descriptions);
    }

    //32. Get id of all the all categories of all the products // x.data[0].categories[0].id
    @Test
    public void test012() {
        List<Integer> id = response.extract().path("data.categories.id");
        System.out.println(id);
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String> product = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println(product);
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        List<Integer> totalcategories = response.extract().path("data.findAll{it.name=' Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println(totalcategories);
    }

    //35. Find the createdAt for all products whose price < 5.49 //x.data[0].createdAt
    @Test
    public void test015() {
        List<Integer> ProductcreatedAt = response.extract().path("data.findAll{it.price<5.49}.createdAt");
        System.out.println(ProductcreatedAt);
    }

    //36.Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() { // not working
        List<String> nameofcategories = response.extract().path("data.findAll{it.name ='Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println(nameofcategories);
    }
    //37. Find the manufacturer of all the products
    @Test
    public void test017(){
        List<String>manufacturer=response.extract().path("data.manufacturer");
        System.out.println(manufacturer);
    }
    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018(){
        List<String>image=response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println(image);
    }
    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019(){
        List<Integer>createdAt=response.extract().path("data.findAll{it.price>5.99}.createdAt");
        System.out.println(createdAt);

    }
   // 40. Find the uri of all the products
    @Test
    public void test20(){
        List<String>url=response.extract().path("data.url");
        System.out.println(url);

    }
}