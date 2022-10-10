package com.generic;

import com.generic.entry.Animal;
import com.generic.entry.Cat;
import com.generic.entry.Dog;


import java.util.*;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-09:15:29
 * @Describe:
 */
public class UpdateGeneric {
    /**
     * jdk 1.5之前的list可以添加任何的类型的元素，只要是object类及子类都可以！
     */
    public static void beforeJdk5(){
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("hello, world");
        objects.add(1);
        objects.add('c');
        objects.forEach(System.out::println);
    }

    /**
     * jdk1.5 引入泛型
     */
    public static void afterJdk5(){
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        integerArrayList.add(2);
        integerArrayList.add(3);
//        integerArrayList.add("4"); // 会抱类型错误
        integerArrayList.forEach(System.out::println);
        System.out.println("===");
        for (Integer integer : integerArrayList) {
            System.out.println(integer);
        }
    }

    /**
     *  hashmap类型的entry
     */
    public static void afterJdk5HashMap(){
        HashMap<Integer, String> hashMap = new HashMap<>(42);
        hashMap.put(1, "a");
        hashMap.put(2, "b");
        hashMap.put(3, "c");
        Set<Map.Entry<Integer, String>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            String res = String.format("key = %d, value = %s", key, value);
            System.out.println(res);
        }
    }

    /**
     * 子类继承 带有泛型的父类；
     *  Dog为指定父类的泛型类型为Integer类型
     *  Cat为泛型类！不限定为Integer类型
     */
    public static void genericClass(){
        Animal<String> stringAnimal = new Animal<>();
        stringAnimal.seteInfo("hello, lion !!!");
        String s = stringAnimal.geteInfo();
        stringAnimal.setAge(11);
        stringAnimal.setName("狮子王");
        System.out.println(stringAnimal.toString());
        Dog dog = new Dog();
//        dog.seteInfo(1);
        Integer eInfo = dog.geteInfo();
//        需要注意的是，eInfo可能为null 值！
        if (eInfo != null){
            System.out.println(eInfo.intValue());
        }else {
            String out = "eInfo为null ！！！";
            System.out.println(out);
        }
        // 测试保持泛型的cat,可以带不同类型！
        Cat<Integer> integerCat = new Cat<>();
        Cat<String> stringCat = new Cat<>();
        Integer intInfo = integerCat.geteInfo();
        integerCat.seteInfo(111);
        String strInfo = stringCat.geteInfo();
        stringCat.seteInfo("111 str");
        System.out.println("===");
        System.out.println("===");
        System.out.println("int类型的Animal ： " + integerCat.geteInfo());
        System.out.println("string类型的Animal ： " + stringCat.geteInfo());
    }

    /**
     * 泛型类型不同的类型无法相互引用！
     */
    public static void diffTypeGenericTran(){
        ArrayList<Integer> integerArrayList = null;
        ArrayList<String> stringArrayList = new ArrayList<>();
//                不同类型的泛型的引用无法相互引用
//        integerArrayList =stringArrayList;
    }

    /**
     *  描述：
     *      泛型方法，如果在泛型类中，常会用E,T两个区分不同的类型参数。
     *      方法无法自动辨认出类型E，会默认为一个 E类，所以需要在方法返回值之前加个 <E>标志 来。
     * @param arr
     * @param <E>
     * @return
     */
    public static <E> List<E> genericMethod(E[] arr){
        return new ArrayList<>(Arrays.asList(arr));
    }

    /**
     * 泛型在继承上的应用
     * 通配符的应用：共同父类！！！
     */
    public static void testExtends(){
        /**
         * 可以将子类的类型变量赋值给父类
         */
        Object obj = null;
        String str = null;
        obj = str;

        /**
         * 可以将子类的类型变量赋值给父类
         */
        Animal<Integer> animal = null;
        Dog dog = null;
        animal = dog;

        /**
         * 泛型互为子父类的List变量，无法由子类的List变量赋值给父类的List变量。！！！
         *  报错信息为：
         Required type:   ArrayList<Object>
         Provided:        ArrayList<String>
         两者为并列关系。
         可以引入 通配符 ? 来作为两者的共同父类 来处理！
         */

        ArrayList<Object> objs = null;
        ArrayList<String> strs = null;
//        报错，类型不一致，下面引入通配符来处理！！！
//        objs = strs;

//        引入 通配符 ? 来作为两者的共同父类 来处理！
        ArrayList<?> parentList = null;
        parentList = objs;
        parentList = strs;
    }

    /**
     * List集合引入了通配符的功能改变！
     */
    public static void testWildCard(){
        ArrayList<String> strList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();
        strList.add("1");
        strList.add("2");
        strList.add("3");
        intList.add(4);
        intList.add(5);
        intList.add(6);
        ArrayList<?> allList = null;
        allList = strList;
//        引入通配符会丧失增加功能
//        allList.add("hello");
//        引入通配符获取功能不失效，但是返回类型进行了泛型擦除，转换为了Object类
        Object o = allList.get(0);
        System.out.println(o);
        allList = intList;
//        引入通配符获取功能不失效，但是返回类型进行了泛型擦除，转换为了Object类
        Object o1 = allList.get(0);
        System.out.println(o1);
    }

    /**
     * 有限制的通配符！！！
     *  ？ extends A
     *  ？ super A
     *   改变： 获取的返回值及 可能性插入
     */
    public static void testWildCard2(){
        ArrayList<? extends Animal<Integer>> littleList= null;
        ArrayList<? super Animal<Integer>> bigList = null;

        ArrayList<Object> objList = null;
        ArrayList<Animal<Integer>> intAnimalList = null;
        ArrayList<Dog> dogList = null;

//        <? extends Animal<Integer>> 表示 ？为继承或者等于Animal类的实例才能赋值！
//        littleList = objList;
        littleList = intAnimalList;
        littleList = dogList;

        bigList = objList;
        bigList = intAnimalList;
//        <? super Animal<Integer>> 表示 ？ 为 Animal类或者其父类！！
//        bigList = dogList;

//        返回值类型为 最小的最大类型
        Animal<Integer> animal = littleList.get(0);
        Object object = bigList.get(0);

//        添加功能:  如果有  ’确定’  的最小类型参数，则可以添加，否则只能添加 null；
//        littleList.add(new Animal<Integer>());
        boolean isAdd = bigList.add(new Dog());
    }


    public static void main(String[] args) {
        beforeJdk5();
        System.out.println("===");
        afterJdk5();
        System.out.println("===");
        afterJdk5HashMap();
        System.out.println("===");
        genericClass();
        System.out.println("===");
//        不可以使用基本类型，如int[]会报错。需要用封装类！！！！
        Integer[] intArr = new Integer[]{1, 2, 3, 4};
        String[] strArr = new String[]{"a", "b"};
        genericMethod(intArr).forEach(System.out::println);
        genericMethod(strArr).forEach(System.out::println);
        System.out.println("===");
        testWildCard();
        System.out.println("===");
    }
}
