package com.reflection.entity;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-24:21:57
 * @Describe:
 */
public class Animal<T> {
    public String name;
    private int age;

    public void introduce(String myName){
        System.out.println("myName is : " + myName);
    }

    private String doing(){
        return "sleeping";
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Animal() {
    }

    private Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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
}
