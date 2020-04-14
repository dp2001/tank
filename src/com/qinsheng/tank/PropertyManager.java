package com.qinsheng.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by qinsheng on 2020/4/14.
 */
public class PropertyManager {

    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if(properties == null) return null;
        return properties.get(key);
    }

    public static int getInt(String key) {
        if(properties == null) return 0;
        int value = Integer.parseInt(properties.get(key).toString());
        return value;
    }

}
