package com.standalone.ibmi.scrambler;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScramblerTest {

    private static Scrambler scrambler;

    @BeforeClass
    public static void setUp() {
        scrambler = new Scrambler();
    }

    @AfterClass
    public static void cleanUp() {
        scrambler = null;
    }

    @Test
    public void equalEncodedText() {
        String expected = "a3b1c1d2y4r2t2p1k1m4";
        assertEquals(expected, scrambler.encode("aaabcddyyyyrrttpkmmmm"));
    }

    @Test
    public void equalDecodedText() {
        String expected = "aaabcddyyyyrrttpkmmmm";
        assertEquals(expected, scrambler.decode("a3b1c1d2y4r2t2p1k1m4"));
    }

}