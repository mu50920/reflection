package com.cn.test;

import java.util.concurrent.*;

interface IMessage{
    public void send(String msg);
}

class NewsPaper implements IMessage{
    @Override
    public void send(String msg) {
        System.out.println("【新闻发布："+msg+"】");
    }
}

class NetMessage implements IMessage{
    @Override
    public void send(String msg) {
        System.out.println("【消息发布："+msg+"】");
    }
}

class Factory{   //反射实现工厂类
    private Factory(){}
    public static <T>T  getInstance(String className){
        T instance = null;
        try {
            instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();

        } catch (Exception e) { }
        return instance;
    }

}
public class TestDemo {
    public static void main(String[] args) {
        IMessage message =(IMessage) Factory.getInstance("com.cn.test.NewsPaper");
        message.send("某某新闻");
       ExecutorService service = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());


    }

}

