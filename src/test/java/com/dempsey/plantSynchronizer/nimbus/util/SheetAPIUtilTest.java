package com.dempsey.plantSynchronizer.nimbus.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SheetAPIUtilTest {
    @Test
    public void convertRange() throws Exception {
    }

    @Test
    public void getHeaders() throws Exception {
    }

    @Test
    public void getRangeString() throws Exception {
        assertEquals("A1", SheetAPIUtil.getRangeString(1,1));
        assertEquals("B2", SheetAPIUtil.getRangeString(2,2));
        assertEquals("Z2", SheetAPIUtil.getRangeString(26,2));
        assertEquals("AA1", SheetAPIUtil.getRangeString(27,1));
        assertEquals("AB1", SheetAPIUtil.getRangeString(28,1));
        assertEquals("AZ1", SheetAPIUtil.getRangeString(52,1));
        assertEquals("BZ1", SheetAPIUtil.getRangeString(26 * 3,1));
        assertEquals("ZZ1", SheetAPIUtil.getRangeString(26 * 27,1));

    }

}