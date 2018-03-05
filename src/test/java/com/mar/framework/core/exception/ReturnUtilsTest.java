package com.mar.framework.core.exception;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ReturnUtilsTest {

    @Test
    public void testCreateException() {
        MarException e = ReturnUtils.createException(this, "message");
        Assertions.assertThat(e).isNotNull();
    }

    @Test
    public void testCreateExceptionFromThrowable() {
        MarException e = ReturnUtils.createException(this, new Throwable());
        Assertions.assertThat(e).isNotNull();
    }

}
