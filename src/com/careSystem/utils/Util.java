package com.careSystem.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by Enzo Cotter on 2021/7/8.
 */
public class Util {
    private static Properties prop = new Properties();

    static {
        //加载配置文件
        try {
            prop.load(Util.class.getClassLoader().getResourceAsStream("init.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //按key获得对象
    public static Object getObject(String key) {
        String value = prop.getProperty(key);
        try {
            Class clazz = Class.forName(value);
            Method m = clazz.getMethod("getInstance");
            Object result = m.invoke(null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
