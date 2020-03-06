package com.standalone.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class AppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithError() throws Exception {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }

    @Test
    public void shouldExitWithSuccessForSndJsonReport() throws Exception {
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-j", "{\"name\":\"test\", \"salary\":\"123\"}", "-u", "http://dummy.restapiexample.com/api/v1/create"});
    }

}