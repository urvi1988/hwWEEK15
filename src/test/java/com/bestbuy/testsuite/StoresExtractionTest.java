package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.plugin2.os.windows.Windows;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    // 1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("value of limit is:" + limit);
        Assert.assertEquals(10, limit);
        response.body("limit", equalTo(10));
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("value of total is:" + total);
        Assert.assertEquals(1561, total);
        response.body("total", equalTo(1561));
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String nameOfStore = response.extract().path("data[4].name");
        System.out.println(nameOfStore);
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> nameofallStores = response.extract().path("data.name");
        System.out.println("Name of all stores:" + nameofallStores);
        for (String s : nameofallStores)
            if (s.equals(1561))
                Assert.assertTrue(true);
    }

    //5. Extract the storeId of all the store.
    @Test
    public void test005() {
        List<Integer> idofallStores = response.extract().path("data.id");
        System.out.println("ID of all stores:" + idofallStores);
        for (Integer i : idofallStores)
            if (i.equals(1561))
                Assert.assertTrue(true);
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> dataSize = response.extract().path("data");
        int size = dataSize.size();
        System.out.println(size);
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println(values);

    }

    // 8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println(address);
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String,?>> services = response.extract().path("data[7].services");
        System.out.println(services);
    }
    //10. Get storeservices of the store where service name = Windows Store //x.data[0].services[5].name
    @Test
    public void test0010(){
        List<HashMap<?,?>>storeService=response.extract().path("data.findAll{it.storeServices='Windows Store'}.services");
        System.out.println(storeService);
    }
    //11. Get all the storeId of all the store
    @Test
    public void test0011(){
        List<Integer>storeIdofAllstore=response.extract().path("data.services.storeservices.storeId");
        System.out.println("StoreID of all Stores:"+storeIdofAllstore);
    }
    //12. Get id of all the store
    @Test
    public void test0012(){
        List<Integer>Idofallstore=response.extract().path("data.id");
        System.out.println("ID of all store:"+Idofallstore);
    }
    //13. Find the store names Where state = ND // x.data[7].state
    @Test
    public void test0013(){
        List<String>stateND=response.extract().path("data.findAll{it.state='ND'}.name");
        System.out.println("store names With State ND:" + stateND);
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test0014(){
        List<Integer>numofServices=response.extract().path("data.findAll{it.name='Rochester'}.services");
        System.out.println("Total number of services:"+numofServices);
    }
    //15. Find the createdAt for all services whose name = “Windows Store” // x.data[0].services[5].createdAt
    @Test
    public void test0015(){
        List<Integer>createdAt=response.extract().path("data.findAll{it.name='Windows Store'}.services");
        System.out.println(createdAt);
    }
    //16. Find the name of all services Where store name = “Fargo”  //x.data[7].name
    @Test
    public void test0016(){
        List<Integer>nameofallser=response.extract().path("data.findAll{it.name='Fargo'}.services");
        System.out.println(nameofallser);
    }
    //17. Find the zip of all the store
    @Test
    public void test0017(){
            List<Integer> listOfzip = response.extract().path("data.zip");
            System.out.println(listOfzip);
    }
    //18. Find the zip of store name = Roseville // x.data[2].zip
    @Test
    public void test0018(){
        List<Integer>zipofstoreName=response.extract().path("data.findAll{it.name='Roseville'}.zip");
        System.out.println(zipofstoreName);
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater // x.data[2].services[7].storeservices
    @Test
    public void test0019(){
        List<?>StoreServices=response.extract().path("data.findAll{it.name='Magnolia Home Theater'}.services");
        System.out.println(StoreServices);
    }
    //20. Find the lat of all the stores
    @Test
    public void test0020(){
        List<Integer>latofStore = response.extract().path("data.lat");
        System.out.println(latofStore);

    }

    }




