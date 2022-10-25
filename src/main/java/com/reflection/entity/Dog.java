package com.reflection.entity;

import java.sql.SQLException;
import java.util.Comparator;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-24:21:57
 * @Describe:
 */
@MyAnnotation(value = "java页")
public class Dog extends Animal<String> implements Comparator<String> , MyInterface{
    public String address;
    private boolean isBoy;

    @MyAnnotation(value = "java页-play")
    public void play(String time, boolean isReal) throws NullPointerException, SQLException {
        System.out.println("dog is playing !!!");
    }

    @MyAnnotation(value = "java页-go")
    private void go(){
        System.out.println("dog is lazy ! it doesn't want to go !!!");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "address='" + address + '\'' +
                ", isBoy=" + isBoy +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBoy() {
        return isBoy;
    }

    public void setBoy(boolean boy) {
        isBoy = boy;
    }

    public Dog(String address, boolean isBoy) {
        this.address = address;
        this.isBoy = isBoy;
    }

    public Dog(){

    }
    private Dog(String address){
        this.address = address;
    }

    @Override
    public int compare(String o1, String o2) {
        return 0;
    }

    @Override
    public void eat() {
        System.out.println("dog is eating !!!");
    }
}
