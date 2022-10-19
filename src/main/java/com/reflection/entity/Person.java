package com.reflection.entity;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-19:22:06
 * @Describe:
 */
public class Person {
    public String name;
    private int age;

    public void show(){
        System.out.println("i am a show method !");
    }

    private boolean isBoy(boolean flag){
        System.out.println("我是男孩吗？ 请回答：" + flag);
        return flag;
    }

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
