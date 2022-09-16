package com.datastruction;

import java.util.Scanner;

/**
 * @Author: Java页大数据
 * @Date: 2022-09-17:0:32
 * @Describe:
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = null;
        boolean flag = true;
        System.out.print("请输入你的选择:");
        while (flag){
            System.out.println();
            scanner = new Scanner(System.in);
            String inputWord = scanner.next();
            switch (inputWord){
                case "e":
                    System.out.printf("队列是否为空: %b", arrayQueue.isEmpty());
                    break;
                case "f":
                    System.out.printf("队列是否已满: %b", arrayQueue.isFull());
                    break;
                case "a":
                    int element = scanner.nextInt();
                    arrayQueue.addElement(element);
                    System.out.printf("队列添加了元素: %d", element);
                    break;
                case "g":
                    System.out.printf("取出的元素为: %d", arrayQueue.getElement());
                    break;
                case "s":
                    arrayQueue.showElement();
                    break;
                case "h":
                    System.out.printf("队列的首元素为: %d", arrayQueue.headElement());
                    break;
                case "q":
                    flag = false;
                    System.out.println("推出程序");
                    break;
                default:
                    break;
            }
        }
    }
}
class ArrayQueue{
    public int maxSize;
    public int front;
    public int rear;
    public int[] queue;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull(){
        return rear == maxSize;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void addElement(int elementValue){
        if (isFull()){
            throw new RuntimeException("队列已满！无法再添加元素！");
        }else {
            queue[rear] = elementValue;
            rear ++;
        }
    }

    public int getElement(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有元素可以被取出！");
        }else {
            return queue[front++];
        }
    }

    public void showElement(){
        for (int i : queue) {
            System.out.printf("%d\t", i);
        }
    }

    /**
     * 查看队列首元素
     * @return 队列首元素
     */
    public int headElement(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据！");
            throw new RuntimeException("队列为空，无法拿出数据");
        }else {
            return queue[front];
        }
    }
}
