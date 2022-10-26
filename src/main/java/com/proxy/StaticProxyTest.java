package com.proxy;

import com.sun.prism.ResourceFactory;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-26:22:41
 * @Describe:
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Proxy proxy = new Proxy(customer);
        proxy.purchase();
        System.out.println("==========");
        One one = new One();
        Two two = new Two(one);
        two.all();
    }
}
interface Resource{
    void purchase();
}
class Proxy implements Resource {
    // 需要帮别人买东西吧，还不为中介代理实例化一个需要服务的客人？ 代理就是为了顾客而生的！！！
    private Resource customer;
    public Proxy(Resource resource){
        this.customer = resource;
    }

    @Override
    public void purchase() {
        System.out.println("我是代理类，专门为顾客而生！");
        System.out.println("现在开始为顾客服务！");
        customer.purchase();
        System.out.println("顾客要为我付尾款了！");
    }
}
class Customer implements Resource{

    @Override
    public void purchase() {
        System.out.println("我需要买东西！我才是真正的顾客");
    }
}
class One{
    public void oneMethod(){
        System.out.println("this is One's oneMethod");
    }
}
class Two{
    private One one;
    public Two(One one){
        this.one = one;
    }
    public void all(){
        System.out.println("all method start");
        one.oneMethod();
        System.out.println("all method end");
    }
}