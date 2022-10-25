package com.reflection;

import com.reflection.entity.Dog;
import com.reflection.entity.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Random;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-19:22:10
 * @Describe:
 */
public class ReflectionTest {
    /**
     * 正常方式创建对象，且操作的权限
     * 无法操作私有结构：如私有属性，私有方法和私有构造器
     */
    @Test
    public void testNormalNewInstance(){
//        只能调用公有构造器
        Person javaAndBigdata = new Person("javaAndBigdata", 3);
        System.out.println(javaAndBigdata.toString());
//        可以直接操作非私有属性，但是不能直接操作私有属性，通过getter和setter方法可以操作
        javaAndBigdata.name = "java页";
        System.out.println(javaAndBigdata.toString());
        javaAndBigdata.setAge(4);
        System.out.println(javaAndBigdata.toString());
//        可以直接操作非私有的方法，不能操作私有方法
         javaAndBigdata.show();
    }

    /**
     * 反射方法创建、操作对象及其属性方法
     * 反射的初识
     *      constructor.newInstance
     *      publicField.set
     *      show.invoke
     *  刚接触，不懂如何操作私有结构
     */
    @Test
    public void testReflectionNewInstance() throws Exception {
//        反射获取构造器来实例化对象
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        Person javaAndBigdata = constructor.newInstance("javaAndBigdata", 3);
        System.out.println(javaAndBigdata.toString());

//        java.lang.NoSuchFieldException: age  getField方法获取的是共有属性，无法获取私有属性
//        Field privateField = personClass.getField("age");
        Field privateField = personClass.getDeclaredField("age");
//        但是无法操作私有属性
//        Object o = privateField.get(javaAndBigdata);
//        int age = privateField.getInt(javaAndBigdata);
//        System.out.println(age);
        Field publicField = personClass.getField("name");
        publicField.set(javaAndBigdata, "java页");
        System.out.println(javaAndBigdata.toString());

        Method show = personClass.getMethod("show");
        show.invoke(javaAndBigdata);
    }

    /**
     * 利用反射来操作类实例的私有结构
     *      前提：所调用的私有结构对象均需要设置访问权限（即调用 setAccessible(true) ）才能够操作，否则会报错
     * @throws  Exception
     */
    @Test
    public void testPrivateStructure() throws Exception {
        //        反射获取构造器来实例化对象
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person javaAndBigdata = constructor.newInstance("javaAndBigdata");
        System.out.println(javaAndBigdata.toString());

        Field privateField = personClass.getDeclaredField("age");
        privateField.setAccessible(true);
        int age = privateField.getInt(javaAndBigdata);
        System.out.println(age);
        privateField.set(javaAndBigdata, 3);
        System.out.println(javaAndBigdata.toString());

        Method privateMethod = personClass.getDeclaredMethod("isBoy", boolean.class);
        privateMethod.setAccessible(true);
        Object privateMethodReturn = privateMethod.invoke(javaAndBigdata, true);
        Boolean isBoy = (Boolean) privateMethodReturn;
        System.out.println(isBoy);
    }


    /**
     * 创建运行时类对象的四种方式
     *      1. Person.class # 动态性不够好
     *      2. personInstance.getClass(); # 前提都已经有对应的实例产生了！！！
     *      3. Class.forName(absoluteClassPath) # 更加常用！更能展示动态性！
     *      4. classLoader.loadClass(absoluteClassPath)  # 情况少用
     * @throws ClassNotFoundException
     */
    @Test
    public void testNewInstanceWays() throws ClassNotFoundException {
//        way1
        Class<Person> person1 = Person.class;

//        way2
        Person person = new Person();
        Class<? extends Person> person2 = person.getClass();

//        way3
        String absoluteClassPath = "com.reflection.entity.Person";
        Class<?> person3 = Class.forName(absoluteClassPath);

//        way4
        // 先获取需要运行的类！！！才能得到类加载器！
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> person4 = classLoader.loadClass(absoluteClassPath);

        System.out.println(person1 == person2);
        System.out.println(person1 == person3);
        System.out.println(person1 == person4);
    }


    /**
     * 通过运行时类对象调用newInstance方法来构建对应的实例
     *      1. 对应的类需要有 空参 构造器
     *      2. 空参构造器权限得够，常设置为 public
     *    java bean对象常常需要设置 空参构造器，避免出现问题。
     *      1. 含参数的构造器的参数不定导致无法统一
     *      2. 避免子类加载时调用加载父类的实例！
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void testClazzNewInstance() throws Exception {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();
        System.out.println(person);
    }

    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance();
    }

    /**
     * 举个例子来认识 反射的动态性！！！
     * @throws Exception
     */
    @Test
    public void testReflection() throws Exception {
        String classPath = "";
        for (int i = 0; i < 100; i++) {
            int j = new Random().nextInt(3);
            switch (j) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.reflection.entity.Person";
                    break;
                default:
            }
            Object o = getInstance(classPath);
            System.out.println(o);
        }
    }


    /**
     * 通过反射获取运行时类的更多信息！！！
     */
    @Test
    public void testGetMoreStructureByReflection(){
        Class<Dog> dogClass = Dog.class;
        Field[] publicFields = dogClass.getFields();
        System.out.println("===");
        // getFields 获取 "当前类及其父类" public权限的属性
        for (Field publicField : publicFields) {
            System.out.println(publicField);
        }
        System.out.println("===");
        // 获取 "当前类" 的所有属性,不包含父类;
        Field[] declaredFields = dogClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.print(declaredField + "\t");
            // 获取属性的访问权限，参数类型，方法名
//            获取属性的访问权限
            System.out.print(Modifier.toString(declaredField.getModifiers()) + "\t");
//            获取属性的形参
            System.out.print(declaredField.getType() + "\t");
//            获取属性名
            System.out.print(declaredField.getName());
            System.out.println();
        }
        System.out.println("===");
        Method[] methods = dogClass.getMethods();
        for (Method method : methods) {
            System.out.print(Modifier.toString(method.getModifiers()) + "\t");
            System.out.print(method.getReturnType() + "\t");
            System.out.print(method.getName());
            System.out.print("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length > 0){
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (i != parameterTypes.length - 1){
                        System.out.print(parameterTypes[i] + " arg_" + i + ",");
                    }else {
                        System.out.print(parameterTypes[i] + " arg_" + i);
                    }
                }
            }
            System.out.print(")");
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length > 0){
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i != exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName() + " , ");
                    }else {
                        System.out.print(exceptionTypes[i].getName());
                    }
                }
            }
            System.out.println();
        }
        System.out.println("===");
        Constructor<?>[] constructors = dogClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.toString());
        }
        System.out.println("===");
        Constructor<?>[] declaredConstructors = dogClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.toString());
        }
        System.out.println("===");
        Class<? super Dog> superclass = dogClass.getSuperclass();
        System.out.println(superclass);
        System.out.println("===");
//        带泛型的父类
        Type genericSuperclass = dogClass.getGenericSuperclass();
        System.out.println(genericSuperclass.getTypeName());
        System.out.println("===");
//        获取带泛型的父类的泛型,例子获取第一个泛型
        ParameterizedType genericSuperclass1 = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = genericSuperclass1.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println("===");
        Annotation[] annotations = dogClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        System.out.println("===");
        Class<?>[] interfaces = dogClass.getInterfaces();
        for (Class<?> inter : interfaces) {
            System.out.println(inter);
        }
        System.out.println("===");
        Class<?>[] patherInters = superclass.getInterfaces();
        for (Class<?> patherInter : patherInters) {
            System.out.println(patherInter);
        }
        System.out.println("===");
        Package pack = dogClass.getPackage();
        System.out.println(pack.getName());
        System.out.println("===");

    }

}
