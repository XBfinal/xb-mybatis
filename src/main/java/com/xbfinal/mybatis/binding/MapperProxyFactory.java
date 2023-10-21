package com.xbfinal.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 映射器代理工厂
 * @version 1.0
 * @Author 笑霸final
 * @Date 2023/10/21 12:12
 * @注释 是对 MapperProxy 的包装，对外提供实例化对象的操作。
 * 当我们后面开始给每个操作数据库的接口映射器注册代理的时候，就需要使用到这个工厂类了。
 */
public class MapperProxyFactory<T> {

    //对接口的代理
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession){
        MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession,mapperInterface);
        /**
         * 是Java中的一个静态方法，用于创建一个代理对象。这个方法接收三个参数：
         * ClassLoader loader：指定加载代理类的类加载器。如果为null，则使用调用者的类加载器。
         * Class<?>[] interfaces：指定代理类需要实现的接口数组
         * InvocationHandler h：指定代理类的方法调用处理程序。会执行里面的invoke方法
         */
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                mapperProxy);
    }
}
