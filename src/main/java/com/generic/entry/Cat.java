package com.generic.entry;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-09:17:06
 * @Describe:
 *              子类伴随父类的泛型类型 ，可以参考Collection的 ；
 *              public class ArrayList<E> extends AbstractList<E>
 *                  implements List<E>, RandomAccess, Cloneable, java.io.Serializable
 */
public class Cat<T> extends Animal<T> {
    private String catInfo;

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", eInfo=" + eInfo +
                ", catInfo='" + catInfo + '\'' +
                '}';
    }

    public String getCatInfo() {
        return catInfo;
    }

    public void setCatInfo(String catInfo) {
        this.catInfo = catInfo;
    }
}
