package com.standalone.ibmi.params;

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
        assertEquals(EnumParams.Action.CPYFROMIFS, enumAction);
    }

    @Test
    public void cpyTxtFromIfsEquals() {
        enumAction = EnumParams.Action.get("cpyTxtFromIfs");
        assertEquals(EnumParams.Action.CPYTXTFROMIFS, enumAction);
    }

    @Test
    public void cpyByteFromIfsEquals() {
        enumAction = EnumParams.Action.get("cpyByteFromIfs");
        assertEquals(EnumParams.Action.CPYBYTEFROMIFS, enumAction);
    }
}