package com.reflection.classLoader;

import com.reflection.ReflectionTest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-20:0:59
 * @Describe:
 */
public class ClassLoaderTest {
    /**
     * 类加载器层级:递增（凡事先问父！！！）
     *  自定义类加载器 -->
     *      系统类加载器 System ClassLoader -->
     *              扩展类加载器 Extension ClassLoader -->
     *                      引导类加载器 Bootstrap ClassLoader
     */
    @Test
    public void testKindsClassLoader(){
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader exClassLoader = classLoader.getParent();
        System.out.println(exClassLoader);

//        无法得到 引导类加载器
        System.out.println(exClassLoader.getParent());

//        String类是由引导类加载器加载的
        System.out.println(String.class.getClassLoader());
    }


    /**
     * 区分 流和类加载器获取配置文件的不同点！！！
     * @throws IOException
     */
    @Test
    public void testLoadProperties() throws IOException {
        Properties properties = new Properties();
        /**
         * 方式一 ： 默认文件在模块路径下！！！
         */
//        FileInputStream fis = new FileInputStream("jdbcTemplate.properties");

        /**
         * 方式二 : 类加载器默认文件在module的src/main/resources(classpath) 路径下！
         */
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        InputStream fis = classLoader.getResourceAsStream("j.properties");

        properties.load(fis);
        System.out.println(properties.getProperty("user"));
        System.out.println(properties.getProperty("password"));
    }
}
