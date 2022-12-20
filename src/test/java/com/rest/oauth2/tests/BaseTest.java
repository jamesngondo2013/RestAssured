package com.rest.oauth2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method){
        System.out.println("STARTING TEST: " + method.getName() + " ON THREAD : " + Thread.currentThread().getId());
        System.out.println("======================================================");
    }
}
