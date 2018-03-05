package com.mar.framework.core.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ObjectUtilsTest {

    @Test
    public void testObjectEmptyBoolean() {
        Boolean b1 = null;
        Boolean b2 = Boolean.TRUE;
        Boolean b3 = Boolean.FALSE;

        Assertions.assertThat(ObjectUtils.isObjectEmpty(b1)).isEqualTo(true);
        Assertions.assertThat(ObjectUtils.isObjectEmpty(b2)).isEqualTo(false);
        Assertions.assertThat(ObjectUtils.isObjectEmpty(b3)).isEqualTo(false);
    }

    @Test
    public void testObjectEmptyCollection() {
        Collection<String> c1 = null;
        Collection<String> c2 = new ArrayList<String>();
        Collection<String> c3 = new ArrayList<String>();
        c3.add("value");

        Assertions.assertThat(ObjectUtils.isObjectEmpty(c1)).isEqualTo(true);
        Assertions.assertThat(ObjectUtils.isObjectEmpty(c2)).isEqualTo(true);
        Assertions.assertThat(ObjectUtils.isObjectEmpty(c3)).isEqualTo(false);
    }

}
