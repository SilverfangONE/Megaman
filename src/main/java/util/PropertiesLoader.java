package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private static final Properties props = getProperties();

    private static Properties getProperties()  {
        Properties p = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("application.properties");
        try {
            p.load(is);
        } catch (IOException ioException) {
            logger.warn(ioException.getMessage());
        }
        return p;
    }

    public static String getStr ( String key) {
        return props.getProperty(key);
    }

    public static int getInt ( String key) {
        return Integer.parseInt(props.getProperty(key));
    }
}
