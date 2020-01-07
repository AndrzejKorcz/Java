package com.santander.ibmi;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.IOException;

public class AppTest 
{
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWithSuccess() throws IOException {
        exit.expectSystemExitWithStatus(0);
        App.main(new String[] {"-c", "RUCRTTST TSTPGM(KORCZA03/RST000000U) SRCFILE(KORCZA03/RSTSORC)"});
    }

    @Test
    public void shouldExitWithError() throws IOException {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }
}
