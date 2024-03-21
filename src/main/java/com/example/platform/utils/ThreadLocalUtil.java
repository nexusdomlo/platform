package com.example.platform.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal THREAD_LOCAL =new ThreadLocal();
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();//获取存的值
    }
    public static void set(Object value){THREAD_LOCAL.set(value);}//再ThreadLocal中存值

    public static void remove(){THREAD_LOCAL.remove();}//移除值

}
