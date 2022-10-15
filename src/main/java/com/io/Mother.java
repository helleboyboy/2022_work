package com.io;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-15:17:06
 * @Describe:
 */
public class Mother {
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
