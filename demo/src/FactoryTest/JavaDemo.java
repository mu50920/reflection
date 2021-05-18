package FactoryTest;


import java.lang.reflect.InvocationTargetException;

interface IMessage {
    public void send(String msg);

}
class News implements  IMessage {
    @Override
    public void send(String msg) {
        System.out.println("这是第一个消息欧赔 "+msg);
    }
}
class Second implements  IMessage{
    @Override
    public void send(String msg) {
        System.out.println("这是第二个"+msg);
    }
}
interface Service {
    public void print(String msg);
}
class FirstService implements Service
{
    @Override
    public void print(String msg) {
        System.out.println(msg+"这是Service");
    }
}

class Factory{
    @SuppressWarnings("unchecked")
    public  static <T>T getInstance (String className,Class<T> clazz){
        T instance = null;
        try {
            instance = (T)Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;

    }

}

public class JavaDemo {
    public static void main(String[] args) {

        IMessage message = Factory.getInstance("FactoryTest.News", News.class);
        message.send("某某新闻");
        FirstService firstService = Factory.getInstance("FactoryTest.FirstService", FirstService.class);
        firstService.print("某某新闻");
    }
}
