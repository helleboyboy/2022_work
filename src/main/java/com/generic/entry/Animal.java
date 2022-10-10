package com.generic.entry;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-09:16:43
 * @Describe:
 */
public class Animal<E> {

    public int age;
    public String name;
    public E eInfo;

    /**
     * 静态方法无法使用类的泛型。
     *  cause：
     *          类的泛型再new 实例化对象时才能确定；
     *           而静态方法是先于实例化对象进行的；所以其泛型无法使用！
     */
//    public static void notStaticMethod(E eInfo){
//        System.out.println(eInfo);
//    }

    public Animal() {
    }

    public Animal(int age, String name, E eInfo) {
        this.age = age;
        this.name = name;
        this.eInfo = eInfo;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", eInfo=" + eInfo +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public E geteInfo() {
        return eInfo;
    }

    public void seteInfo(E eInfo) {
        this.eInfo = eInfo;
    }
}