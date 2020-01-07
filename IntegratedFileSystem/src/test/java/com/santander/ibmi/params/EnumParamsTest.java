package com.santander.ibmi.params;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnumParamsTest {

    private EnumParams.Action enumAction;

    @Test
    public void cpyToIfsEquals() {
        enumAction = EnumParams.Action.get("cpyToIfs");
        assertEquals(EnumParams.Action.CPYTOIFS, enumAction);
    }

    @Test
    public void cpyFromIfsEquals() {
        enumAction = EnumParams.Action.get("cpyFromIfs");
        assertEquals(EnumParams.Action.CPYFROMFS, enumAction);
    }
}