package com.mar.framework.core.testutils;

import com.mar.framework.core.settings.AbstractSettings;

public class TestSettings extends AbstractSettings {

    public static String TEST_PROP_STRING1 = "test.prop.string1";

    public static String TEST_PROP_STRING2 = "test.prop.string2";

    public static String TEST_PROP_INT1 = "test.prop.int1";

    public static String TEST_PROP_BOOL1 = "test.prop.bool1";

    public static String getPropBool1() {
        return getValue(TEST_PROP_BOOL1);
    }

    public static String getPropInt1() {
        return getValue(TEST_PROP_INT1);
    }

    public static String getPropString1() {
        return getValue(TEST_PROP_STRING1);
    }

    public static String getPropString2() {
        return getValue(TEST_PROP_STRING2);
    }

}
