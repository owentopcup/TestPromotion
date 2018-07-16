package com.example.app.controller;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isIn;

public class ApiTest {
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/api";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;


    }

    @Test
    public void findServiceChargeAll() {

        get("/findServiceChargeAll").then().assertThat().statusCode(200);

    }

    @Test
    public void importCustomerTelUsageFile() {
        File file=new File("E:\\work\\Test\\promotion1.log");
        System.out.println("isExists:"+file.exists());
        given().multiPart("file","sw").formParam("file",file).when().post("importCustomerTelUsageFile").then().assertThat().statusCode(200);

    }
    @Test
    public void calculateServiceCharge() {

        get("/calculateServiceCharge").then().assertThat().statusCode(200);

    }





}
