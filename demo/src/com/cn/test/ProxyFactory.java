package com.cn.test;

import com.sun.xml.internal.ws.api.server.WSEndpoint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Imassage{
    public void send(String obj);
}
class RealIMassage  implements  Imassage{
    @Override
    public void send(String obj) {
        System.out.println("发送成了啊"+obj);
    }
}
class IMssageProxy implements InvocationHandler
{
    private Object target;
   public Object bind(Object target)
   {
       this.target = target;
       return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
   }
   public boolean connect()
   {
       System.out.println("成功连接了");
       return true;
   }
   public void  close()
   {
       System.out.println("成功关闭了");
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
class PFactory
{
    private void PFactory(){};
    public static  <T>T getInstance(Class<T> classname)
    {
        try {
            return (T)new IMssageProxy().bind(classname.getDeclaredConstructor().newInstance()) ;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
class IMassageService
{
    private Imassage msg;
    public IMassageService()
    {
        msg = (Imassage)PFactory.getInstance(RealIMassage.class);
    }
    public void  send(String obj)
    {
        this.msg.send(obj);
    }
}
@Retention(RetentionPolicy.RUNTIME)
@interface UseIMassage{
}
public class ProxyFactory {
    public static void main(String[] args) {
        IMassageService msg = new IMassageService();
        msg.send("真的真的");

    }
}

