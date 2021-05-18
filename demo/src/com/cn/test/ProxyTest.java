package com.cn.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

interface Message{
    public  void send();
}
class MessageReal implements  Message{
    @Override
    public void send() {
        System.out.println("成功发送了 ");
    }
}
class ProxyMessage implements InvocationHandler {


    private  Object target;
   public  Object bind (Object target)
    {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public  boolean connect()
    {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        HashSet<String>list1 = null;
        Hashtable<Integer,String>l = null;
        System.out.println("开始连接了");
        return true;
    }
    public void close()
    {
        System.out.println("关闭连接");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnData = null;
        if(this.connect())
        {
            returnData = method.invoke(this.target,args);
            this.close();
            return returnData;
        }
        return null;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        Message message = (Message)new ProxyMessage().bind(new MessageReal());
        message.send();

    }
}
