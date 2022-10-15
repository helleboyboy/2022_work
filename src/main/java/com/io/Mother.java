package com.io;


import java.io.Serializable;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-15:17:06
 * @Describe:
 *  需要实现Java的序列化条件：
 *      1. 实现Serializable接口
 *      2. 提供一个全局变量serialVersionUID，确保类信息前后一致！
 *      3. 类中的所有属性都需要实现序列化！！
 */
public class Mother implements Serializable {
    private static final long serialVersionUID = -1299546909289720531L;
    private String name;
    private Integer age;
    private Boolean isTeacher;
    private Son son;

    @Override
    public String toString() {
        return "Mother{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isTeacher=" + isTeacher +
                ", son=" + son +
                '}';
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public Mother(String name, Integer age, Boolean isTeacher, Son son) {
        this.name = name;
        this.age = age;
        this.isTeacher = isTeacher;
        this.son = son;
    }

    public Boolean getTeacher() {
        return isTeacher;
    }

    public void setTeacher(Boolean teacher) {
        isTeacher = teacher;
    }

    public Mother(String name, Integer age, Boolean isTeacher) {
        this.name = name;
        this.age = age;
        this.isTeacher = isTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Mother() {
    }

    public Mother(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
