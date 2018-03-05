package com.mar.framework.core.settings;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

public class OrderedProperties extends Properties {

    private static final long serialVersionUID = 7756479082203050074L;

    @Override
    public synchronized Enumeration<Object> keys() {
        /* Return the keys in an ordered fashion. */
        return Collections.enumeration(new TreeSet<Object>(super.keySet()));
    }

}
