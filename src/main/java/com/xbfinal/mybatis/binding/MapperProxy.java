package com.xbfinal.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类
 * @version 1.0
 * @Author 笑霸final
 * @Date 2023/10/21 12:12
 * @注释 MapperProxy 负责实现 InvocationHandler 接口的 invoke 方法，最终所有的实际调用都会调用到这个方法包装的逻辑。
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {


    private static final long serialVersionUID = -6424540398559729838L;
    private Map<String, String> sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    /**
     * 通过实现 InvocationHandler#invoke 代理类接口，封装操作逻辑的方式，对外接口提供数据库操作对象。
     * @参数 代理对象（proxy）、被代理对象的方法（method）和方法的参数数组（args）
     * @return Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())) {
            //判断被代理对象的方法是否属于Object类，即是否是Java核心库中的通用方法，就应该不能被代理
            return method.invoke(this, args);
        } else {
            return "你的被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
