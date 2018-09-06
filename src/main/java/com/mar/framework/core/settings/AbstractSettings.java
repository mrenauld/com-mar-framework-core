package com.mar.framework.core.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import com.mar.framework.core.logging.LogUtils;
import com.mar.framework.core.utils.ObjectUtils;

public abstract class AbstractSettings {

    public static final String SEPARATOR = ",";

    protected static boolean propInitialized = false;

    protected static Properties prop = new Properties();

    protected static URL propertyFileUrl;

    protected static String filename = "application.properties";

    protected static String filenameForJar = "resources/application.properties";

    public static void reload() {
        init();
    }

    public static void saveProperties() {
        if (propertyFileUrl == null) {
            LogUtils.logError(AbstractSettings.class,
                    "Impossible to save properties because the " + "property file path cannot be determined");
            return;
        }

        OutputStream output = null;

        try {
            File propFile = new File(propertyFileUrl.toURI());
            output = new FileOutputStream(propFile);

            prop.store(output, null);

        } catch (IOException e) {
            LogUtils.logError(AbstractSettings.class, "IOException while saving properties", e);
        } catch (URISyntaxException e) {
            LogUtils.logError(AbstractSettings.class, "URISyntaxException while saving properties", e);
        } catch (IllegalArgumentException e) {
            LogUtils.logError(AbstractSettings.class, "IllegalArgumentException while saving properties", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    LogUtils.logError(AbstractSettings.class, "Impossible to close stream", ex);
                }
            }
        }
    }

    public static void setValue(String pKey, boolean pValue) {
        prop.put(pKey, Boolean.toString(pValue));
    }

    public static void setValue(String pKey, double pValue) {
        prop.put(pKey, Double.toString(pValue));
    }

    public static void setValue(String pKey, int pValue) {
        prop.put(pKey, Integer.toString(pValue));
    }

    public static void setValue(String pKey, String pValue) {
        prop.put(pKey, pValue);
    }

    /**
     * Returns the property value for the specified key.
     *
     * @param pKey
     * @return
     */
    protected static String getValue(String pKey) {
        if (!propInitialized) {
            init();
        }

        String value = null;
        if (prop != null) {
            value = prop.getProperty(pKey);
        }

        return value;
    }

    /**
     * Returns the property value for the specified key as a boolean.
     *
     * @param pKey
     * @return
     */
    protected static boolean getValueBoolean(String pKey) {
        String value = getValue(pKey);
        if (!ObjectUtils.isObjectEmpty(value) && "true".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the property value for the specified key as a double.
     *
     * @param pKey
     * @return
     */
    protected static double getValueDouble(String pKey) {
        String value = getValue(pKey);
        double n = 0;
        try {
            n = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            LogUtils.logError(AbstractSettings.class,
                    "Impossible to parse [" + value + "] as double. Using default value [" + n + "]");
        }
        return n;
    }

    /**
     * Returns the property value for the specified key as an integer.
     *
     * @param pKey
     * @return
     */
    protected static int getValueInt(String pKey) {
        String value = getValue(pKey);
        int n = 0;
        try {
            n = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LogUtils.logInfo(AbstractSettings.class,
                    "Impossible to parse [" + value + "] as integer. Using default value [" + n + "]");
        }
        return n;
    }

    /**
     * Returns the property value for the specified key as a list of strings.
     * The strings are separated by {@link AbstractSettings#SEPARATOR} and empty
     * strings are skipped.
     *
     * @param pKey
     * @return
     */
    protected static ArrayList<String> getValueList(String pKey) {
        String value = getValue(pKey);
        String[] split = value.split(SEPARATOR);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < split.length; ++i) {
            if (!ObjectUtils.isObjectEmpty(split[i])) {
                list.add(split[i]);
            }
        }
        return list;
    }

    /**
     * Initializes the properties.
     */
    protected static void init() {
        InputStream input = null;
        URL url = null;

        try {
            String filenameProp = System.getProperty("tmlproperty");
            if (!ObjectUtils.isObjectEmpty(filenameProp)) {
                filename = filenameProp;
                File propFile = new File(filenameProp);
                url = propFile.toURI().toURL();
                input = new FileInputStream(propFile);
            } else {
                input = AbstractSettings.class.getClassLoader().getResourceAsStream(filename);

                if (input != null) {
                    url = AbstractSettings.class.getClassLoader().getResource(filename);
                    if (!isRunningFromJar()) {
                        LogUtils.logInfo(AbstractSettings.class,
                                "Property file [" + filename + "] found at URL [" + url.toString() + "]");
                    } else {
                        LogUtils.logInfo(AbstractSettings.class,
                                "Externalized property file [" + filename + "] found at URL [" + url.toString() + "]");
                    }
                } else {
                    if (!isRunningFromJar()) {
                        LogUtils.logError(AbstractSettings.class, "Property file [" + filename + "] not found");
                        return;
                    } else {
                        input = AbstractSettings.class.getClassLoader().getResourceAsStream(filenameForJar);

                        if (input != null) {
                            url = AbstractSettings.class.getClassLoader().getResource(filenameForJar);
                            LogUtils.logInfo(AbstractSettings.class,
                                    "Property file [" + filenameForJar + "] found at URL [" + url.toString() + "]");
                        } else {
                            LogUtils.logError(AbstractSettings.class,
                                    "Property file [" + filenameForJar + "] not found");
                            return;
                        }
                    }
                }
            }

            propertyFileUrl = url;

            // load a properties file from class path, inside static method
            prop.load(input);

            // prop.list(System.out);
        } catch (IOException ex) {
            LogUtils.logError(AbstractSettings.class, "Impossible to load property file [" + filename + "]", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    LogUtils.logError(AbstractSettings.class, "Impossible to close stream", ex);
                }
            }
        }

        propInitialized = true;
    }

    private static boolean isRunningFromJar() {
        URL resource = AbstractSettings.class.getResource("AbstractSettings.class");
        return resource.toString().startsWith("jar:");
    }
}
