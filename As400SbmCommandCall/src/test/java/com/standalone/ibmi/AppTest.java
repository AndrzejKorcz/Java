package com.standalone.ibmi;

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
        App.main(new String[] {"-c", "dsplibl"});
    }

    @Test
    public void shouldExitWithError() throws IOException {
        exit.expectSystemExitWithStatus(1);
        App.main(new String[] {});
    }

    @Test
    public void shouldExitWithAcmsError() throws IOException {
        exit.expectSystemExitWithStatus(1);
        String cmd = "ACMSPROMOT OBJ(*PROJECT) PROJECT(J127404) ENV(DVP KORCZA03) FAILURE(*CONT) LISTING(*YES) CPYFFMTOPT(*NOCHK) JOBD(ACMSSECMRP ACMSCTL) OUTQ(*USRPRF) REL(ICBSV710/ICBSV710/AIBINTMRO2) ";
        App.main(new String[] {"-c", cmd});
    }
}