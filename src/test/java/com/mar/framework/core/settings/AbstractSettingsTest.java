package com.mar.framework.core.settings;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mar.framework.core.testutils.TestSettings;

public class AbstractSettingsTest {

    @Test
    public void testPropertiesAreLoaded() {
        String propString1 = TestSettings.getPropString1();
        Assertions.assertThat(propString1).isEqualTo("this is a property");
    }

    @Test
    public void testUpdateProperties() {
        String propString1 = TestSettings.getPropString1();
        String newValue = "new property";

        TestSettings.setValue(TestSettings.TEST_PROP_STRING1, newValue);
        TestSettings.saveProperties();

        TestSettings.reload();
        String newPropString1 = TestSettings.getPropString1();

        Assertions.assertThat(newPropString1).isEqualTo(newValue);

        TestSettings.setValue(TestSettings.TEST_PROP_STRING1, propString1);
        TestSettings.saveProperties();
    }

}
