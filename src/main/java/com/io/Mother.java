package com.io;

import java.io.Serializable;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-15:17:06
 * @Describe:
 *  需要实现Java的序列化条件：
 *      1. 实现Serializable接口
 */
public class Mother implements Serializable {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Mother{" +
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
