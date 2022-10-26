package com.proxy;

import com.reflection.entity.Animal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-26:22:37
 * @Describe:
 */
public class DynamicProxyTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> loadClass = StaticProxyTest.class.getClassLoader().loadClass("com.reflection.entity.Animal");
        Animal animal = (Animal) loadClass.newInstance();
        System.out.println(animal.toString());
        System.out.println("上面是测试可以通过加载器来获得对应的运行时类对象");

        GuangDong guangDong = new GuangDong();
        Chinese o = (Chinese) ProxyEveryThing.newDiffProxyInstance(guangDong);
        o.province();
        String food = o.eat("化州糖水");
        System.out.println("食物是:" + food);

        System.out.println("===");

        Customer customer = new Customer();
        Resource o1 = (Resource) ProxyEveryThing.newDiffProxyInstance(customer);
        o1.purchase();
    }
}
interface Chinese{
    void province();
    String eat(String food);
}
class GuangDong implements Chinese{

    @Override
    public void province() {
        System.out.println("我是广东人！");
    }

    @Override
    public String eat(String food) {
        return food;
    }
}
class ProxyEveryThing{
    public static Object newDiffProxyInstance(Object beiProxyObj){
//        借助Reflict包下的Proxy类； 获取被代理类的加载器以及被代理类所实现的接口； 第三个参数是需要 被代理类实现去 调用代理类的真正方法！
        return Proxy.newProxyInstance(beiProxyObj.getClass().getClassLoader(), beiProxyObj.getClass().getInterfaces(), new MyInvocationHandler(beiProxyObj));
    }
}
class MyInvocationHandler implements InvocationHandler {
//    引入被代理类变量
    public Object beiProxyObject;
//    通过构造器引入被代理类对象实例，绑定被代理类变量！
    public MyInvocationHandler(Object beiProxyInstance){
        this.beiProxyObject = beiProxyInstance;
    }

    /**
     * 被执行的方法 主角是被代理类
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        getBeiProxyObject(beiProxyObject);
        return method.invoke(beiProxyObject, args);
    }
}
