package com.generic.entry;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-09:16:53
 * @Describe: 子类需要指定父类的泛型参数，否则会进行泛型擦除！
 *              如果没有引入<Interge>泛型，即会进行泛型擦除，即Dog实例的eInfo类型为Object类型！</>
 *              当然，也可以不限定为Intege类型！详情看Cat类
 */
public class Dog extends Animal<Integer>{
//public class Dog<T> extends Animal<T>{
    private String host;

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", eInfo=" + eInfo +
                ", host='" + host + '\'' +
                '}';
    }
}
