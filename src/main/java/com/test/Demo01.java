package com.test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo01 {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Demo01.class");
        logger.log(Level.INFO, "test ignore file");
        logger.log(Level.INFO, "test ignore file");
    }
}
