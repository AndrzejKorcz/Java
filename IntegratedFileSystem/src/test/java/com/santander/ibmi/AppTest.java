package com.santander.ibmi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithSuccess()
    {
        App.main(new String[] {"-a", "cpyTo", "-i", "local path", "-o", "ifs path"});
    }
    @Test
    public void shouldExitWithError()
    {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }

}
