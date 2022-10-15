package com.io;

import java.io.Serializable;

/**
 * @Author: Java页大数据
 * @Date: 2022-10-15:17:33
 * @Describe:
 */
public class Son implements Serializable {
    private static final long serialVersionUID = -12995469092831L;
    private Boolean isBoy;

    @Override
    public String toString() {
        return "Son{" +
                "isBoy=" + isBoy +
                '}';
    }

    public Boolean getBoy() {
        return isBoy;
    }

    public void setBoy(Boolean boy) {
        isBoy = boy;
    }

    public Son() {
    }

    public Son(Boolean isBoy) {
        this.isBoy = isBoy;
    }
}